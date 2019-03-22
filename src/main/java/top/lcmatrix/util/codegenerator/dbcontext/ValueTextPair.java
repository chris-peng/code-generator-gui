package top.lcmatrix.util.codegenerator.dbcontext;

import java.io.Serializable;

public class ValueTextPair implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String text;
	private Object value;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "{value:" + value + ", text:" + text + "}";
	}
}
