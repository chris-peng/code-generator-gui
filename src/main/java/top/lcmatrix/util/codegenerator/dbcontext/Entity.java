package top.lcmatrix.util.codegenerator.dbcontext;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.text.WordUtils;
import org.jumpmind.db.model.Column;
import org.jumpmind.db.model.Table;

import top.lcmatrix.util.codegenerator.util.JdbcUtils;

public class Entity extends Table{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Entity(Table table){
		super();
		this.setCatalog(table.getCatalog());
		this.setDescription(table.getDescription());
		this.setName(table.getName());
		this.setSchema(table.getSchema());
		this.setType(table.getType());
		
		//处理其他字段
		this.setEntityName(WordUtils.uncapitalize(JdbcUtils.convertUnderscoreNameToPropertyName(table.getName())));
		this.setClassName(WordUtils.capitalize(getEntityName()));
		for(Column c : table.getColumns()){
			Field f = new Field(c);
			fields.add(f);
			if(f.isPrimaryKey()){
				primaryFields.add(f);
			}
		}
	}
	
	private String entityName;
	private String className;
	private List<Field> fields = new ArrayList<Field>();
	private List<Field> primaryFields = new ArrayList<Field>();
	
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public List<Field> getFields() {
		return fields;
	}
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
	public List<Field> getPrimaryFields() {
		return primaryFields;
	}
	public void setPrimaryFields(List<Field> primaryFields) {
		this.primaryFields = primaryFields;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
}
