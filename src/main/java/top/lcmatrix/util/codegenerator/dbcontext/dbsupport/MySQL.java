package top.lcmatrix.util.codegenerator.dbcontext.dbsupport;

public class MySQL implements IDbSupport{

	@Override
	public Class<?> loadDriver() throws ClassNotFoundException {
		try {
			return Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			return Class.forName("com.mysql.jdbc.Driver");
		}
	}
}
