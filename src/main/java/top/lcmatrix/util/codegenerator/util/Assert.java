package top.lcmatrix.util.codegenerator.util;

public class Assert {
	public static void assertTrue(String msg, boolean condition) throws AssertFailException {
		if(!condition) {
			throw new AssertFailException(msg);
		}
	}
	
	public static class AssertFailException extends Exception {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public AssertFailException() {
			super();
			// TODO Auto-generated constructor stub
		}

		public AssertFailException(String message, Throwable cause, boolean enableSuppression,
				boolean writableStackTrace) {
			super(message, cause, enableSuppression, writableStackTrace);
			// TODO Auto-generated constructor stub
		}

		public AssertFailException(String message, Throwable cause) {
			super(message, cause);
			// TODO Auto-generated constructor stub
		}

		public AssertFailException(String message) {
			super(message);
			// TODO Auto-generated constructor stub
		}

		public AssertFailException(Throwable cause) {
			super(cause);
			// TODO Auto-generated constructor stub
		}
		
	}
}
