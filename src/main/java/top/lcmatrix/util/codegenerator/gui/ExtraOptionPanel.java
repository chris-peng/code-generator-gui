package top.lcmatrix.util.codegenerator.gui;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

import top.lcmatrix.util.codegenerator.gui.base.FormItemPanel;

public class ExtraOptionPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField cGlobalSettings = new JTextField();

	public ExtraOptionPanel() {
		super();
		this.setLayout(new GridLayout(0, 1));
		this.add(new FormItemPanel("global settings ( eg: setting1=value1;setting2=value2 ):", cGlobalSettings));
	}

	public String getGlobalSettings() {
		return cGlobalSettings.getText();
	}

	public void setGlobalSettings(String globalSettings) {
		this.cGlobalSettings.setText(globalSettings);
	}
}
