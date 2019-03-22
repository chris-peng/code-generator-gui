package top.lcmatrix.util.codegenerator.gui;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

import top.lcmatrix.util.codegenerator.gui.base.FormItemPanel;

public class DbOptionPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField cJdbcUrl = new JTextField();
	private JTextField cUserName = new JTextField();
	private JTextField cPassword = new JTextField();
	private JTextField cTableName = new JTextField();

	public DbOptionPanel() {
		super();
		this.setLayout(new GridLayout(0, 1));
		this.add(new FormItemPanel("jdbc url ( Currently only support Mysql ) *:", cJdbcUrl));
		this.add(new FormItemPanel("db user name *:", cUserName));
		this.add(new FormItemPanel("db password:", cPassword));
		this.add(new FormItemPanel("table name ( * denotes any character ) *:", cTableName));
	}

	public String getcJdbcUrl() {
		return cJdbcUrl.getText();
	}

	public void setJdbcUrl(String jdbcUrl) {
		this.cJdbcUrl.setText(jdbcUrl);
	}

	public String getUserName() {
		return cUserName.getText();
	}

	public void setUserName(String userName) {
		this.cUserName.setText(userName);
	}

	public String getPassword() {
		return cPassword.getText();
	}

	public void setPassword(String password) {
		this.cPassword.setText(password);
	}
	
	public String getTableName() {
		return cTableName.getText();
	}

	public void setTableName(String tableName) {
		this.cTableName.setText(tableName);
	}
}
