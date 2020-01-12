package top.lcmatrix.util.codegenerator.util;

import org.jumpmind.db.model.ColumnTypes;

import java.sql.Types;

public class SqlJavaTypeConvertor {

	/**
	 * Translates a data type from an integer (java.sql.Types value) to a string
	 * that represents the corresponding class.
	 * 
	 * REFER:
	 * https://www.cis.upenn.edu/~bcpierce/courses/629/jdkdocs/guide/jdbc/getstart/mapping.doc.html
	 * 
	 * @param type
	 *            The java.sql.Types value to convert to its corresponding
	 *            class.
	 * @return The class that corresponds to the given java.sql.Types value, or
	 *         Object.class if the type has no known mapping.
	 */
	public static Class<?> toJavaType(int type) {
		Class<?> result = Object.class;

		switch (type) {
		case Types.CHAR:
		case Types.VARCHAR:
		case Types.LONGVARCHAR:
		case ColumnTypes.MSSQL_NTEXT:
			result = String.class;
			break;

		case Types.NUMERIC:
		case Types.DECIMAL:
			result = java.math.BigDecimal.class;
			break;

		case Types.BIT:
			result = Boolean.class;
			break;

		case Types.TINYINT:
			result = Byte.class;
			break;

		case Types.SMALLINT:
			result = Short.class;
			break;

		case Types.INTEGER:
			result = Integer.class;
			break;

		case Types.BIGINT:
			result = Long.class;
			break;

		case Types.REAL:
		case Types.FLOAT:
			result = Float.class;
			break;

		case Types.DOUBLE:
			result = Double.class;
			break;

		case Types.BINARY:
		case Types.VARBINARY:
		case Types.LONGVARBINARY:
			result = Byte[].class;
			break;

		case Types.DATE:
		case Types.TIMESTAMP:
			result = java.util.Date.class;
			break;

		case ColumnTypes.ORACLE_TIMESTAMPTZ:
		case ColumnTypes.ORACLE_TIMESTAMPLTZ:
		case ColumnTypes.MSSQL_DATETIMEOFFSET:
			result = java.sql.Timestamp.class;
			break;

		case Types.TIME:
			result = java.sql.Time.class;
			break;
		}

		return result;
	}
}
