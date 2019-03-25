package top.lcmatrix.util.codegenerator.gui;

import org.apache.commons.lang3.StringUtils;

import top.lcmatrix.util.codegenerator.util.Assert;
import top.lcmatrix.util.codegenerator.util.Assert.AssertFailException;

public class InputBean {

	private String templateDir;
	private String outputDir;
	private String jdbcDriverJar;
	private String jdbcUrl;
	private String userName;
	private String password;
	private String tableName;
	private String globalSettings;
	
	public void validate() throws AssertFailException {
		Assert.assertTrue("template dir can not be blank", StringUtils.isNotBlank(templateDir));
		Assert.assertTrue("output dir can not be blank", StringUtils.isNotBlank(outputDir));
		Assert.assertTrue("jdbc url can not be blank", StringUtils.isNotBlank(jdbcUrl));
		Assert.assertTrue("user name can not be blank", StringUtils.isNotBlank(userName));
		Assert.assertTrue("table name can not be blank", StringUtils.isNotBlank(tableName));
	}
	
	public String getTemplateDir() {
		return templateDir;
	}
	public void setTemplateDir(String templateDir) {
		this.templateDir = templateDir;
	}
	public String getOutputDir() {
		return outputDir;
	}
	public void setOutputDir(String outputDir) {
		this.outputDir = outputDir;
	}
	public String getJdbcUrl() {
		return jdbcUrl;
	}
	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getGlobalSettings() {
		return globalSettings;
	}
	public void setGlobalSettings(String globalSettings) {
		this.globalSettings = globalSettings;
	}

	public String getJdbcDriverJar() {
		return jdbcDriverJar;
	}

	public void setJdbcDriverJar(String jdbcDriverJar) {
		this.jdbcDriverJar = jdbcDriverJar;
	}
	
}
