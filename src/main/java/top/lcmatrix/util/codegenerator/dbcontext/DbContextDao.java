package top.lcmatrix.util.codegenerator.dbcontext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ddlutils.Platform;
import org.apache.ddlutils.PlatformFactory;
import org.apache.ddlutils.PlatformUtils;
import org.apache.ddlutils.model.Database;
import org.apache.ddlutils.model.Table;

import top.lcmatrix.util.codegenerator.base.GenerateException;
import top.lcmatrix.util.codegenerator.dbcontext.dbsupport.IDbSupport;
import top.lcmatrix.util.codegenerator.gui.InputBean;
import top.lcmatrix.util.codegenerator.util.AsteriskExp;

public class DbContextDao{

	private IDbSupport dbSupport;
	private Database database;
	private Table[] allTables;
	
	public DbContextDao(InputBean inputBean) {
		String dbName = new PlatformUtils().determineDatabaseType(null, inputBean.getJdbcUrl());
		if(StringUtils.isBlank(dbName)) {
			throw new GenerateException("Incorrect or unsupported jdbc url!");
		}
		dbSupport = getDbSupport(dbName);
		try {
			dbSupport.loadDriver();
		} catch (ClassNotFoundException e1) {
			throw new GenerateException("No driver found for this database!");
		}
		Platform platform = PlatformFactory.createNewPlatformInstance(dbName);
		Connection connection = null;
		try {
			String password = inputBean.getPassword();
			if("".equals(password)) {
				password = null;
			}
			connection = DriverManager.getConnection(inputBean.getJdbcUrl(),
					inputBean.getUserName(), password);
			database = platform.readModelFromDatabase(connection, null);
			allTables = database.getTables();
		} catch (SQLException e) {
			throw new GenerateException(e);
		} finally{
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public List<Table> getTableModels(String tableNameExp){
		List<Table> tables = new ArrayList<>();
		AsteriskExp asteriskExp = new AsteriskExp(tableNameExp, true);
		for(Table t : allTables) {
			if(asteriskExp.match(t.getName())) {
				tables.add(t);
			}
		}
		return tables;
	}
	
	private IDbSupport getDbSupport(String dbName) {
		try {
			Class<?> dbSupportClass = Class.forName("top.lcmatrix.util.codegenerator.dbcontext.dbsupport." + dbName);
			return (IDbSupport) dbSupportClass.newInstance();
		} catch (ClassNotFoundException e) {
			throw new GenerateException("This database is not supported for the time being!");
		} catch (InstantiationException | IllegalAccessException e) {
			throw new GenerateException("Incorrect Implementation of db support class!");
		}
	}
}
