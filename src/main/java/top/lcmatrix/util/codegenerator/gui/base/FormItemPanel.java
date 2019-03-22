package top.lcmatrix.util.codegenerator.gui.base;

import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FormItemPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel label;
	private JComponent inputComponent;	

	public FormItemPanel(String labelText, JComponent inputComponent) {
		super();
		this.setLayout(new GridLayout(0, 1));
		this.setLabel(labelText);
		this.setInputComponent(inputComponent);
	}
	
	public void setLabel(String labelText) {
		if(label == null) {
			label = new JLabel(labelText);
			this.add(label);
		}
		label.setText(labelText);
	}
	
	public void setInputComponent(JComponent inputComponent) {
		if(this.inputComponent != null) {
			this.remove(this.inputComponent);
		}
		this.add(inputComponent);
		this.inputComponent = inputComponent;
	}

}
