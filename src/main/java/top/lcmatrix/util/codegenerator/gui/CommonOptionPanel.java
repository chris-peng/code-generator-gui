package top.lcmatrix.util.codegenerator.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import top.lcmatrix.util.codegenerator.gui.base.FileInput;
import top.lcmatrix.util.codegenerator.gui.base.FormItemPanel;
import top.lcmatrix.util.codegenerator.gui.base.LinkLabel;

public class CommonOptionPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private FileInput cTemplateDir = new FileInput();
	private FileInput cOutputDir = new FileInput();

	public CommonOptionPanel() {
		super();
		this.setLayout(new GridLayout(0, 1));
		cTemplateDir.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		JPanel templatePanel = new JPanel();
		LayoutManager templatePanelLayout = new GridLayout(0, 1);
		templatePanel.setLayout(templatePanelLayout);
		JPanel templateLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 5));
		templateLabelPanel.add(new JLabel("template dir (Freemarker File with .ftl suffix supported."));
		LinkLabel variableLinkLabel = new LinkLabel(" View template variables supported) *:", "https://chris-peng.github.io/code-generator-gui/dbContext-data-model.txt");
		variableLinkLabel.setForeground(Color.red);
		templateLabelPanel.add(variableLinkLabel);
		templatePanel.add(templateLabelPanel);
		templatePanel.add(cTemplateDir);
		
		this.add(templatePanel);
		cOutputDir.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		this.add(new FormItemPanel("output dir *:", cOutputDir));
	}
	
	public String getTemplateDir() {
		return cTemplateDir.getValue();
	}

	public void setTemplateDir(String templateDir) {
		cTemplateDir.setValue(templateDir);
	}

	public String getOutputDir() {
		return cOutputDir.getValue();
	}

	public void setOutputDir(String outputDir) {
		cOutputDir.setValue(outputDir);
	}
}
