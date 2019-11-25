package top.lcmatrix.util.codegenerator.dbcontext;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jumpmind.db.model.Table;
import org.jumpmind.db.platform.IDatabasePlatform;
import org.jumpmind.db.platform.JdbcDatabasePlatformFactory;

import com.zaxxer.hikari.HikariDataSource;

import top.lcmatrix.util.codegenerator.base.GenerateException;
import top.lcmatrix.util.codegenerator.gui.InputBean;
import top.lcmatrix.util.codegenerator.util.AsteriskExp;
import top.lcmatrix.util.codegenerator.util.JarLoader;

public class DbContextDao{

	private IDatabasePlatform platform;
	private List<String> allTableNames;
	private HikariDataSource ds;
	
	public DbContextDao(InputBean inputBean) {
		if(StringUtils.isNotBlank(inputBean.getJdbcDriverJar())) {
			JarLoader.loadLocalJar(inputBean.getJdbcDriverJar());
		}
		ds = new HikariDataSource();
		ds.setJdbcUrl(inputBean.getJdbcUrl());
		ds.setUsername(inputBean.getUserName());
		ds.setPassword(inputBean.getPassword());
		ds.setConnectionTimeout(5000);
		ds.setMaximumPoolSize(5);
		ds.setIdleTimeout(30000);
		platform = JdbcDatabasePlatformFactory.createNewPlatformInstance(ds, null, false, true);
		if(platform == null) {
			throw new GenerateException("Unsuportted database!");
		}
		allTableNames = platform.readTableNamesFromDatabase(null, null, null);
	}
	
	public List<Table> getTableModels(String tableNameExp){
		List<Table> tables = new ArrayList<>();
		AsteriskExp asteriskExp = new AsteriskExp(tableNameExp, true);
		for(String tName : allTableNames) {
			if(asteriskExp.match(tName)) {
				Table t = platform.getDdlReader().readTable(null, null, tName);
				if(t != null) {
					tables.add(t);
				}
			}
		}
		ds.close();
		return tables;
	}
	
	/*
	 * private IDbSupport getDbSupport(String dbName, String driverJar) { try {
	 * Class<?> dbSupportClass =
	 * Class.forName("top.lcmatrix.util.codegenerator.dbcontext.dbsupport." +
	 * dbName); return (IDbSupport) dbSupportClass.newInstance(); } catch
	 * (ClassNotFoundException e) { if(StringUtils.isBlank(driverJar)) { throw new
	 * GenerateException("jdbc driver jar must not be empty."); } return new
	 * OtherDbSupport(dbName, driverJar); } catch (InstantiationException |
	 * IllegalAccessException e) { throw new
	 * GenerateException("Incorrect Implementation of db support class!"); } }
	 */
}
