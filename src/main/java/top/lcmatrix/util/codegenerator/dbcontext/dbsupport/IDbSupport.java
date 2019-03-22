package top.lcmatrix.util.codegenerator.dbcontext.dbsupport;

public interface IDbSupport {

	public Class<?> loadDriver() throws ClassNotFoundException;
}
