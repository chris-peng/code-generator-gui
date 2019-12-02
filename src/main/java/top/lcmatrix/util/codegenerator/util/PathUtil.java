package top.lcmatrix.util.codegenerator.util;

import java.io.File;
import java.io.UnsupportedEncodingException;

import top.lcmatrix.util.codegenerator.template.TemplateLoader;

public class PathUtil {

	public static File getJarDir(){
		String jarFilePath = TemplateLoader.class.getProtectionDomain().getCodeSource().getLocation().getFile();
		try {
			jarFilePath = java.net.URLDecoder.decode(jarFilePath, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		jarFilePath = jarFilePath.replace("file:", "");
		jarFilePath = jarFilePath.replaceAll("\\!/.*\\!/", "");
		System.out.println("jarFilePath:" + jarFilePath);
		return new File(jarFilePath).getParentFile();
	}

	public static String createNoRepeatPath(String baseDir, String subPath){
		String mdir = baseDir + File.separator + subPath;
		String dir = mdir + "";
		int i = 1;
		while(new File(dir).exists()){
			dir = mdir + "_" + i;
			i++;
		}
		return dir;
	}
}
