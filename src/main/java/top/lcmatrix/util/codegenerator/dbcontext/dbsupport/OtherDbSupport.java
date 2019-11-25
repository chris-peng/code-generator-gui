package top.lcmatrix.util.codegenerator.dbcontext.dbsupport;

import java.util.HashMap;
import java.util.Map;

import top.lcmatrix.util.codegenerator.util.JarLoader;

public class OtherDbSupport implements IDbSupport{
	
	private static final Map<String, String> DB_DRIVERS = new HashMap<String, String>();
	static {
		//registerDbDrivers();
	}
	
	private String driverJar;
	private String dbName;
	
	public OtherDbSupport(String dbName, String driverJar) {
		this.dbName = dbName;
		this.driverJar = driverJar;
	}

	@Override
	public Class<?> loadDriver() throws ClassNotFoundException {
		JarLoader.loadLocalJar(driverJar);
		return Class.forName(DB_DRIVERS.get(dbName));
	}
	
	/*private static void registerDbDrivers()
    {
        DB_DRIVERS.put(AxionPlatform.DATABASENAME,       AxionPlatform.JDBC_DRIVER);
        DB_DRIVERS.put(CloudscapePlatform.DATABASENAME,  "COM.cloudscape.core.JDBCDriver");
        DB_DRIVERS.put(Db2Platform.DATABASENAME,         Db2Platform.JDBC_DRIVER);
        DB_DRIVERS.put(Db2v8Platform.DATABASENAME,       Db2v8Platform.JDBC_DRIVER);
        DB_DRIVERS.put(DerbyPlatform.DATABASENAME,       DerbyPlatform.JDBC_DRIVER);
        DB_DRIVERS.put(FirebirdPlatform.DATABASENAME,    FirebirdPlatform.JDBC_DRIVER);
        DB_DRIVERS.put(HsqlDbPlatform.DATABASENAME,      HsqlDbPlatform.JDBC_DRIVER);
        DB_DRIVERS.put(InterbasePlatform.DATABASENAME,   InterbasePlatform.JDBC_DRIVER);
        DB_DRIVERS.put(MaxDbPlatform.DATABASENAME,       MaxDbPlatform.JDBC_DRIVER);
        DB_DRIVERS.put(MckoiPlatform.DATABASENAME,       MckoiPlatform.JDBC_DRIVER);
        DB_DRIVERS.put(MSSqlPlatform.DATABASENAME,       "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        DB_DRIVERS.put(MySqlPlatform.DATABASENAME,       MySqlPlatform.JDBC_DRIVER);
        DB_DRIVERS.put(MySql50Platform.DATABASENAME,     MySql50Platform.JDBC_DRIVER);
        DB_DRIVERS.put(Oracle8Platform.DATABASENAME,     Oracle8Platform.JDBC_DRIVER);
        DB_DRIVERS.put(Oracle9Platform.DATABASENAME,     Oracle9Platform.JDBC_DRIVER);
        DB_DRIVERS.put(Oracle10Platform.DATABASENAME,    Oracle10Platform.JDBC_DRIVER);
        DB_DRIVERS.put(PostgreSqlPlatform.DATABASENAME,  PostgreSqlPlatform.JDBC_DRIVER);
        DB_DRIVERS.put(SapDbPlatform.DATABASENAME,       SapDbPlatform.JDBC_DRIVER);
        DB_DRIVERS.put(SybasePlatform.DATABASENAME,      SybasePlatform.JDBC_DRIVER);
        DB_DRIVERS.put(SybaseASE15Platform.DATABASENAME, SybaseASE15Platform.JDBC_DRIVER);
    }*/
}
