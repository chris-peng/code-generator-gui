package top.lcmatrix.util.codegenerator.template;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import top.lcmatrix.util.codegenerator.Constants;
import top.lcmatrix.util.codegenerator.base.GenerateException;
import top.lcmatrix.util.codegenerator.base.IContext;
import top.lcmatrix.util.codegenerator.util.PathUtil;

public class TemplateProcessor {

	public static void process(TemplateStruct ts, IContext context, String storeDir) {
		Template contentTemplate;
		Writer out = null;
		try {
			contentTemplate = TemplateLoader.getTemplate(ts.getContentTemplate());
			Template fileNameTemplate = TemplateLoader.getTemplate(ts.getFileNameTemplate());
			StringWriter stringWriter = new StringWriter();
			fileNameTemplate.process(context, stringWriter);
			String fileName = stringWriter.toString();
			String filePath = PathUtil.createNoRepeatPath(storeDir, fileName);
			FileOutputStream fos = new FileOutputStream(filePath);
			out = new BufferedWriter(new OutputStreamWriter(fos, Constants.DEFAULT_CHARSET), 10240);
			contentTemplate.process(context, out);
		} catch (IOException | TemplateException e) {
			throw new GenerateException(e);
		} finally {
			if(out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
