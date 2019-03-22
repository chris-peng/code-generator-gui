package ${globalsettings.packageName}.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
<#if entity.primaryFields?size gt 1 >
import javax.persistence.IdClass;
</#if>
<#assign fieldTypeForImport={}>
<#list entity.fields as field>
	<#if !field.fieldType.canonicalName?starts_with("java.lang.")>
		<#assign fieldTypeForImport = fieldTypeForImport + {field.fieldType.canonicalName : 1}>
	</#if>
	<#if !field.autoIncrement>
		<#assign fieldTypeForImport = fieldTypeForImport + {"javax.persistence.GeneratedValue" : 1}>
		<#assign fieldTypeForImport = fieldTypeForImport + {"javax.persistence.GenerationType" : 1}>
	</#if>
</#list>
<#list fieldTypeForImport?keys as fieldType>
import ${fieldType};
</#list>

/**
 * 
 * ${entity.description!}
 *
 */
@Entity
@Table(name = "${entity.name}")
<#if entity.primaryFields?size gt 1 >
@IdClass(${entity.className}.ID.class)
</#if>
public class ${entity.className} implements Serializable {
	private static final long serialVersionUID = 1L;
	
	<#list entity.fields as field>
		<#if field.primaryKey>
	@Id
		</#if>
		<#if field.autoIncrement>
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		</#if>
	@Column(name = "${field.name}")
	private ${field.fieldType.simpleName} ${field.fieldName};
	</#list>
	
	<#list entity.fields as field>
	public ${field.fieldType.simpleName} get${field.fieldName?cap_first}(){
		return ${field.fieldName};
	}
	public void set${field.fieldName?cap_first}(${field.fieldType.simpleName} ${field.fieldName}){
		this.${field.fieldName} = ${field.fieldName};
	}
	</#list>
	
	<#if entity.primaryFields?size gt 1 >
	public static class ID implements Serializable{
		private static final long serialVersionUID = 1L;
		
		public ID(){
			super();
		}
		
		public ID(<#list entity.primaryFields as primaryField>${primaryField.fieldType.simpleName} ${primaryField.fieldName}<#sep>, </#list>){
			super();
			<#list entity.primaryFields as primaryField>
			this.${primaryField.fieldName} = ${primaryField.fieldName};
			</#list>
		}
		
		<#list entity.primaryFields as primaryField>
		private ${primaryField.fieldType.simpleName} ${primaryField.fieldName};
		</#list>
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			<#list entity.primaryFields as primaryField>
			result = prime * result + ((${primaryField.fieldName} == null) ? 0 : ${primaryField.fieldName}.hashCode());
			</#list>
			return result;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ID other = (ID) obj;
			<#list entity.primaryFields as primaryField>
			if (${primaryField.fieldName} == null) {
				if (other.${primaryField.fieldName} != null)
					return false;
			} else if (!${primaryField.fieldName}.equals(other.${primaryField.fieldName}))
				return false;
			</#list>
			return true;
		}
		
		<#list entity.primaryFields as primaryField>
		public ${primaryField.fieldType.simpleName} get${primaryField.fieldName?cap_first}(){
			return ${primaryField.fieldName};
		}
		public void set${primaryField.fieldName?cap_first}(${primaryField.fieldType.simpleName} ${primaryField.fieldName}){
			this.${primaryField.fieldName} = ${primaryField.fieldName};
		}
		</#list>
	}
	</#if>
}