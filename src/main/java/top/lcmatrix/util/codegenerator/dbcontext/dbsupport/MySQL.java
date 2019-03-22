package top.lcmatrix.util.codegenerator.dbcontext.dbsupport;

public class MySQL implements IDbSupport{

	@Override
	public Class<?> loadDriver() throws ClassNotFoundException {
		return Class.forName("com.mysql.cj.jdbc.Driver");
	}
}
