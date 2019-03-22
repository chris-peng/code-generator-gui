package top.lcmatrix.util.codegenerator.gui;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.apache.commons.io.FileUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

import top.lcmatrix.util.codegenerator.Constants;
import top.lcmatrix.util.codegenerator.base.ContextServiceFactory;
import top.lcmatrix.util.codegenerator.base.GenerateException;
import top.lcmatrix.util.codegenerator.util.Assert.AssertFailException;
import top.lcmatrix.util.codegenerator.util.PathUtil;

public class MainWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static MainWindow mainWindow;
	
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private JPanel contentPanel;
	private CommonOptionPanel commonOptionPanel = new CommonOptionPanel();
	private DbOptionPanel dbOptionPanel = new DbOptionPanel();
	private ExtraOptionPanel extraOptionPanel = new ExtraOptionPanel();
	private JButton generateButton = new JButton("GENERATE");
	private Color gBtnNormalBgColor = generateButton.getBackground();
	private String gBtnNormalText = generateButton.getText();
	
	public static MainWindow getInstance() {
		if(mainWindow == null) {
			mainWindow = new MainWindow();
		}
		return mainWindow;
	}
	
	private MainWindow(){
		super();
		initMyself();
		initComponents();
	}
	
	private void initMyself() {
		this.setTitle("LcMatrix code generator");
//		this.setVisible(true);
		this.setSize((int)(screenSize.width * 0.5), (int)(screenSize.height * 0.6));
		this.setLocation((int)(screenSize.width * 0.25), (int)(screenSize.height * 0.15));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setLayout(new FlowLayout());
		contentPanel = new JPanel();
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setPreferredSize(new Dimension((int)(getWidth() * 0.8), (int)(getHeight() * 1.5)));
		JScrollPane jScrollPane = new JScrollPane(contentPanel);
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane.setPreferredSize(new Dimension((int)(getWidth() - 20), (int)(getHeight() * 0.85)));
		this.getContentPane().add(jScrollPane);
	}
	
	private void initComponents() {
		initCommonOptionPanel();
		initDbOptionPanel();
		initExtraOptionPanel();
		addOtherComponent();
		initGenerateButton();
		readDefaultInputBean();
	}

	private void initCommonOptionPanel() {
		commonOptionPanel.setPreferredSize(new Dimension((int)(getWidth() * 0.9), (int)(getHeight() * 0.3)));
		contentPanel.add(commonOptionPanel);
	}

	private void initDbOptionPanel() {
		dbOptionPanel.setPreferredSize(new Dimension((int)(getWidth() * 0.9), (int)(getHeight() * 0.4)));
		contentPanel.add(dbOptionPanel);
	}

	private void initExtraOptionPanel() {
		extraOptionPanel.setPreferredSize(new Dimension((int)(getWidth() * 0.9), (int)(getHeight() * 0.1)));
		contentPanel.add(extraOptionPanel);
	}
	
	private volatile boolean generating = false;
	private void initGenerateButton() {
		generateButton.setPreferredSize(new Dimension((int)(getWidth() * 0.5), (int)(getHeight() * 0.05)));
		this.getContentPane().add(generateButton);
		generateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(generating) {
					return;
				}
				InputBean inputBean = assembleInputBean();
				try {
					inputBean.validate();
				} catch (AssertFailException e1) {
					JOptionPane.showMessageDialog(MainWindow.this, e1.getMessage());
					return;
				}
				generating = true;
				changeGBtnStatus();
				
				saveInputBean(inputBean);
				
				doGenerateInBackground(inputBean);
			}

		});
	}
	
	private void doGenerateInBackground(InputBean inputBean) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					ContextServiceFactory.create(ContextServiceFactory.CONTEXT_TYPE_DB).generate(inputBean);
					int toOpen = JOptionPane.showConfirmDialog(MainWindow.this, "Generate finish!\nOpen the output dir now?", "", JOptionPane.YES_NO_OPTION);
					if(toOpen == JOptionPane.YES_OPTION) {
						try {
							Desktop.getDesktop().open(new File(inputBean.getOutputDir()));
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				} catch (GenerateException e1) {
					JOptionPane.showMessageDialog(MainWindow.this, e1.getMessage());
				}
				generating = false;
				changeGBtnStatus();
			}
		}).start();
	}
	
	private void changeGBtnStatus() {
		if(generating) {
			generateButton.setText("Generating...");
			generateButton.setBackground(Color.cyan);
		}else {
			generateButton.setText(gBtnNormalText);
			generateButton.setBackground(gBtnNormalBgColor);
		}
	}

	private InputBean assembleInputBean() {
		InputBean inputBean = new InputBean();
		inputBean.setPackageName(commonOptionPanel.getPackageName());
		inputBean.setTemplateDir(commonOptionPanel.getTemplateDir());
		inputBean.setOutputDir(commonOptionPanel.getOutputDir());
		inputBean.setJdbcUrl(dbOptionPanel.getcJdbcUrl());
		inputBean.setUserName(dbOptionPanel.getUserName());
		inputBean.setPassword(dbOptionPanel.getPassword());
		inputBean.setTableName(dbOptionPanel.getTableName());
		inputBean.setGlobalSettings(extraOptionPanel.getGlobalSettings());
		return inputBean;
	}
	
	private void saveInputBean(InputBean inputBean) {
		String ibJson = JSON.toJSONString(inputBean);
		File jarDir = PathUtil.getJarDir();
		File defaultJson = new File(jarDir.getAbsoluteFile() + File.separator + "default.json");
		try {
			FileUtils.write(defaultJson, ibJson, Constants.DEFAULT_CHARSET, false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void readDefaultInputBean() {
		File jarDir = PathUtil.getJarDir();
		try {
			String ibJson = FileUtils.readFileToString(new File(jarDir.getAbsoluteFile() + File.separator + "default.json"), Constants.DEFAULT_CHARSET);
			InputBean inputBean = JSON.parseObject(ibJson, InputBean.class);
			commonOptionPanel.setPackageName(inputBean.getPackageName());
			commonOptionPanel.setTemplateDir(inputBean.getTemplateDir());
			commonOptionPanel.setOutputDir(inputBean.getOutputDir());
			dbOptionPanel.setJdbcUrl(inputBean.getJdbcUrl());
			dbOptionPanel.setUserName(inputBean.getUserName());
			dbOptionPanel.setPassword(inputBean.getPassword());
			dbOptionPanel.setTableName(inputBean.getTableName());
			extraOptionPanel.setGlobalSettings(inputBean.getGlobalSettings());
		} catch (IOException | JSONException e) {
			e.printStackTrace();
		}
		
	}
	
	private void addOtherComponent() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 20));
		panel.setPreferredSize(new Dimension((int)(getWidth() * 0.9), (int)(getHeight() * 0.2)));
		JButton githubBtn = new JButton("Github");
		githubBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().browse(new URL("https://github.com/chris-peng/code-generator-gui").toURI());
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		});
		panel.add(githubBtn);
		contentPanel.add(panel);
	}
}
