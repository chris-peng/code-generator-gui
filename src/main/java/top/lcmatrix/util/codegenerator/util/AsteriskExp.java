package top.lcmatrix.util.codegenerator.util;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * 星号表达式，星号指代任意字符
 * @author pengh
 *
 */
public class AsteriskExp {
	
	private String asteriskExp;
	private boolean ignoreCase;
	private Pattern eqPattern;
	private boolean hasAsterisk = true;
	
	public AsteriskExp(String asteriskExp, boolean ignoreCase) {
		this.asteriskExp = asteriskExp;
		this.ignoreCase = ignoreCase;
		if(asteriskExp.contains("*")) {
			String unescapedStr = asteriskExp.replace("\\\\", Character.MIN_VALUE + Character.MIN_VALUE + "")
				.replace("\\*", "" + Character.MIN_VALUE + Character.MAX_VALUE)
				.replace(Character.MIN_VALUE + Character.MIN_VALUE + "", "\\\\");
			String eqPatternStr = escapeRegExExceptAsterisk(unescapedStr).replace("*", ".*")
				.replace("" + Character.MIN_VALUE + Character.MAX_VALUE, "\\*");
			if(ignoreCase) {
				eqPattern = Pattern.compile("^" + eqPatternStr + "$", Pattern.CASE_INSENSITIVE);
			}else {
				eqPattern = Pattern.compile("^" + eqPatternStr + "$");
			}
		}else {
			hasAsterisk = false;
		}
	}

	public boolean match(String text) {
		if(text == null) {
			return false;
		}
		if(hasAsterisk) {
			return eqPattern.matcher(text).matches();
		}
		if(ignoreCase) {
			return asteriskExp.equalsIgnoreCase(text);
		}
		return asteriskExp.equals(text);
	}
	
	private static String escapeRegExExceptAsterisk(String text) {
	    if (StringUtils.isNotBlank(text)) {
	        String[] fbsArr = { "$", "(", ")", "+", ".", "[", "]", "?", "^", "{", "}", "|" };
	        for (String key : fbsArr) {
	            if (text.contains(key)) {
	            	text = text.replace(key, "\\" + key);
	            }
	        }
	    }
	    return text;
	}

}
