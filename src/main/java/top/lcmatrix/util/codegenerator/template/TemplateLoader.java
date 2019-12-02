package top.lcmatrix.util.codegenerator.template;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import freemarker.cache.StringTemplateLoader;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateNotFoundException;
import top.lcmatrix.util.codegenerator.Constants;
import top.lcmatrix.util.codegenerator.base.GenerateException;

public class TemplateLoader {
	
	private static Configuration configuration;
	
	public static List<TemplateStruct> loadTemplates(String templateDir){
		List<TemplateStruct> templates = new ArrayList<TemplateStruct>();
		configuration = new Configuration(Configuration.VERSION_2_3_28);
		StringTemplateLoader stl = new StringTemplateLoader();
		configuration.setTemplateLoader(stl);
		Path templateDirPath = new File(templateDir).toPath();
		try {
			for(File templateFile : getTemplateFiles(templateDir)){
				TemplateStruct ts = new TemplateStruct();
				String contentTemplateId = UUID.randomUUID().toString();
				stl.putTemplate(contentTemplateId, FileUtils.readFileToString(templateFile, Constants.DEFAULT_CHARSET));
				ts.setContentTemplate(contentTemplateId);
				
				String fileNameTemplateId = UUID.randomUUID().toString();
				stl.putTemplate(fileNameTemplateId, FilenameUtils.getBaseName(templateFile.getAbsolutePath()));
				ts.setFileNameTemplate(fileNameTemplateId);

				String relativeFilePath = templateDirPath.relativize(templateFile.toPath()).toString();
				String relativeDirPath = FilenameUtils.getPath(relativeFilePath);
				ts.setRelativeDirPath(relativeDirPath);
				
				templates.add(ts);
			}
		}catch (IOException e) {
			throw new GenerateException("Can not read template file!");
		}
		return templates;
	}
	
	public static Template getTemplate(String name) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException{
		return configuration.getTemplate(name);
	}

	private static Collection<File> getTemplateFiles(String sTemplateDir){
		File templateDir = new File(sTemplateDir);
		if(!templateDir.exists() || !templateDir.isDirectory()) {
			throw new GenerateException("Incorrect template dir");
		}
		return FileUtils.listFiles(templateDir, new String[]{Constants.TPL_FILE_SUFFIX}, true);
	}
}
