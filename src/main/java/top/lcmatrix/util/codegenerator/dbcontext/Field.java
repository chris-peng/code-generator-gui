package top.lcmatrix.util.codegenerator.dbcontext;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ddlutils.model.Column;
import org.apache.ddlutils.model.TypeMap;

import top.lcmatrix.util.codegenerator.util.JdbcUtils;
import top.lcmatrix.util.codegenerator.util.SqlJavaTypeConvertor;

public class Field extends Column{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Field(Column column){
		super();
		this.setAutoIncrement(column.isAutoIncrement());
		this.setDefaultValue(column.getDefaultValue());
		this.setDescription(column.getDescription());
		this.setJavaName(column.getJavaName());
		this.setName(column.getName());
		this.setPrecisionRadix(column.getPrecisionRadix());
		this.setPrimaryKey(column.isPrimaryKey());
		this.setRequired(column.isRequired());
		this.setScale(column.getScale());
		this.setSize(column.getSize());
		this.setLength(column.getSizeAsInt());
		this.setType(column.getType());
		this.setTypeCode(column.getTypeCode());
		this.setOfBinaryType(column.isOfBinaryType());
		this.setOfNumericType(column.isOfNumericType());
		this.setOfTextType(column.isOfTextType());
		this.setOfDateTimeType(TypeMap.isDateTimeType(column.getTypeCode()));
		
		//处理其他字段
		this.setFieldName(WordUtils.uncapitalize(JdbcUtils.convertUnderscoreNameToPropertyName(column.getName())));
		this.setFieldType(FieldType.fromClass(SqlJavaTypeConvertor.toJavaType(column.getTypeCode())));
		//可选值
		parseComment2Enums();
	}

	private String fieldName;
	private FieldType fieldType;
	/**
	 * 可选值
	 */
	private List<ValueTextPair> enums;
	private boolean ofBinaryType;
	private boolean ofNumericType;
	private boolean ofTextType;
	private boolean ofDateTimeType;
	private int length;
	
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public List<ValueTextPair> getEnums() {
		return enums;
	}

	public void setEnums(List<ValueTextPair> enums) {
		this.enums = enums;
	}
	public FieldType getFieldType() {
		return fieldType;
	}
	public void setFieldType(FieldType fieldType) {
		this.fieldType = fieldType;
	}
	
	private void parseComment2Enums(){
		String comment = getDescription();
		if(StringUtils.isBlank(comment)){
			return;
		}
		Pattern commentPattern = Pattern.compile("(^.*?[,，\\.。\\s]*).*?[:：]");
		Matcher matcher = commentPattern.matcher(comment);
		if(matcher.find()){
			String enumStr = comment.substring(matcher.group(1).length());
			
			String[] enumExps = enumStr.split("[,，;；]");
			List<ValueTextPair> enums = new ArrayList<ValueTextPair>();
			for(String enumExp : enumExps){
				String[] valueText = enumExp.split("[:：]");
				if(valueText.length >= 2){
					ValueTextPair vtp = new ValueTextPair();
					vtp.setValue(valueText[0]);
					vtp.setText(valueText[1]);
					enums.add(vtp);
				}
			}
			setEnums(enums);
		}
	}
	public boolean isOfBinaryType() {
		return ofBinaryType;
	}
	public void setOfBinaryType(boolean ofBinaryType) {
		this.ofBinaryType = ofBinaryType;
	}
	public boolean isOfNumericType() {
		return ofNumericType;
	}
	public void setOfNumericType(boolean ofNumericType) {
		this.ofNumericType = ofNumericType;
	}
	public boolean isOfTextType() {
		return ofTextType;
	}
	public void setOfTextType(boolean ofTextType) {
		this.ofTextType = ofTextType;
	}
	public boolean isOfDateTimeType() {
		return ofDateTimeType;
	}
	public void setOfDateTimeType(boolean ofDateTimeType) {
		this.ofDateTimeType = ofDateTimeType;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
}
