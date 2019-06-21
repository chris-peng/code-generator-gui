package top.lcmatrix.util.codegenerator.gui;

import java.awt.GridLayout;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import top.lcmatrix.util.codegenerator.gui.base.FileInput;
import top.lcmatrix.util.codegenerator.gui.base.FormItemPanel;

public class DbOptionPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private FileInput cJdbcDriverJar = new FileInput();
	private JTextField cJdbcUrl = new JTextField();
	private JTextField cUserName = new JTextField();
	private JTextField cPassword = new JTextField();
	private JTextField cTableName = new JTextField();

	public DbOptionPanel() {
		super();
		this.setLayout(new GridLayout(0, 1));
		cJdbcDriverJar.setPrompt("MySql 8");
		cJdbcDriverJar.setFileFilter(new FileFilter() {
			
			@Override
			public String getDescription() {
				return "jar file filter";
			}
			
			@Override
			public boolean accept(File f) {
				return (f.isFile() && f.getName().endsWith(".jar")) || f.isDirectory();
			}
		});
		this.add(new FormItemPanel("jdbc driver jar ( Mysql is built-in supported,just leave it empty when using Mysql):", cJdbcDriverJar));
		this.add(new FormItemPanel("jdbc url *:", cJdbcUrl));
		this.add(new FormItemPanel("db user name *:", cUserName));
		this.add(new FormItemPanel("db password:", cPassword));
		this.add(new FormItemPanel("table name ( * denotes any character ) *:", cTableName));
	}
	
	public String getJdbcDriverJar() {
		return cJdbcDriverJar.getValue();
	}

	public void setJdbcDriverJar(String jdbcDriverJar) {
		this.cJdbcDriverJar.setValue(jdbcDriverJar);
	}

	public String getJdbcUrl() {
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
