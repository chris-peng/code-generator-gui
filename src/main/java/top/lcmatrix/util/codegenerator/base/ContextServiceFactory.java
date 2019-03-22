package top.lcmatrix.util.codegenerator.base;

import top.lcmatrix.util.codegenerator.dbcontext.DbContextService;

public class ContextServiceFactory {

	public static final String CONTEXT_TYPE_DB = "DbContext";
	
	public static AbsContextService create(String contextType) {
		switch (contextType) {
		case CONTEXT_TYPE_DB:
			return new DbContextService();

		default:
			return null;
		}
	}
}
