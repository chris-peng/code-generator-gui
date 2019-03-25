package top.lcmatrix.util.codegenerator.setting;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;

import top.lcmatrix.util.codegenerator.base.GenerateException;

public class Globalsettings extends HashMap<String, Object>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String EXP_SPLIT_TOKEN = ";";
	public static final String EXP_ASSIGN_TOKEN = "=";

	public static Globalsettings fromSettingsExp(String exp) {
		Globalsettings g = new Globalsettings();
		if(StringUtils.isNotBlank(exp)) {
			String[] assignExps = exp.split(EXP_SPLIT_TOKEN);
			for(String assignExp : assignExps) {
				try {
					String[] nameValue = assignExp.split(EXP_ASSIGN_TOKEN);
					g.put(nameValue[0], nameValue[1]);
				} catch (ArrayIndexOutOfBoundsException e) {
					throw new GenerateException("Incorrect global settings!");
				}
			}
		}
		return g;
	}
}
