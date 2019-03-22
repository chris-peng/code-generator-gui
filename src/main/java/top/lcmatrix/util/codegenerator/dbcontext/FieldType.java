package top.lcmatrix.util.codegenerator.dbcontext;

import java.util.HashMap;
import java.util.Map;

public class FieldType {
	
	private static final Map<Class<?>, String> BASE_NAME_MAP = new HashMap<>();
	static {
		BASE_NAME_MAP.put(Byte.class, "byte");
		BASE_NAME_MAP.put(Byte[].class, "byte[]");
		BASE_NAME_MAP.put(Short.class, "short");
		BASE_NAME_MAP.put(Short[].class, "short[]");
		BASE_NAME_MAP.put(Integer.class, "int");
		BASE_NAME_MAP.put(Integer[].class, "int[]");
		BASE_NAME_MAP.put(Long.class, "long");
		BASE_NAME_MAP.put(Long[].class, "long[]");
		BASE_NAME_MAP.put(Float.class, "float");
		BASE_NAME_MAP.put(Float[].class, "float[]");
		BASE_NAME_MAP.put(Double.class, "double");
		BASE_NAME_MAP.put(Double[].class, "double[]");
		BASE_NAME_MAP.put(Boolean.class, "boolean");
		BASE_NAME_MAP.put(Boolean[].class, "boolean[]");
		BASE_NAME_MAP.put(Character.class, "char");
		BASE_NAME_MAP.put(Character[].class, "char[]");
	}
	
	public static FieldType fromClass(Class<?> clazz) {
		FieldType ft = new FieldType();
		ft.setCanonicalName(clazz.getCanonicalName());
		ft.setSimpleName(clazz.getSimpleName());
		String baseName = BASE_NAME_MAP.get(clazz);
		if(baseName == null) {
			baseName = clazz.getSimpleName();
		}
		ft.setBaseName(baseName);
		return ft;
	}
	
	/**
	 * 类型全名，如java.lang.String,java.lang.String[]等
	 */
	private String canonicalName;
	/**
	 * 类型简名，如String,String[]等
	 */
	private String simpleName;
	/**
	 * 类型基本名，若非java基本类型，则与simpleName相同，如int,long,String等
	 */
	private String baseName;
	
	public String getCanonicalName() {
		return canonicalName;
	}
	public void setCanonicalName(String canonicalName) {
		this.canonicalName = canonicalName;
	}
	public String getSimpleName() {
		return simpleName;
	}
	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}
	public String getBaseName() {
		return baseName;
	}
	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}
}
