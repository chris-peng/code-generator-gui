package top.lcmatrix.util.codegenerator.util;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import top.lcmatrix.util.codegenerator.base.GenerateException;

public class JarLoader {

	public static void loadLocalJar(String jarFilePath) {
		File jarFile = new File(jarFilePath);
	    // 从URLClassLoader类中获取类所在文件夹的方法，jar也可以认为是一个文件夹
	    Method method = null;
	    try {
	        method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
	    } catch (NoSuchMethodException | SecurityException e1) {
	        throw new GenerateException("the java runtime version is too low.Java 8+ is required.");
	    }
	    // 获取方法的访问权限以便写回
	    boolean accessible = method.isAccessible();
	    try {
	        method.setAccessible(true);
	        // 获取系统类加载器
	        URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
	        URL url = jarFile.toURI().toURL();
	        method.invoke(classLoader, url);
	    } catch (SecurityException e) {
	        e.printStackTrace();
	    } catch (MalformedURLException e) {
	    	throw new GenerateException("Incorrect jar file.");
		} catch (IllegalAccessException e) {
			throw new GenerateException("the java runtime version is too low.Java 8+ is required.");
		} catch (IllegalArgumentException e) {
			throw new GenerateException("Load jar file error.");
		} catch (InvocationTargetException e) {
			throw new GenerateException("Load jar file error.");
		} finally {
	        method.setAccessible(accessible);
	    }
	}
}
