package top.lcmatrix.util.codegenerator.template;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;

import freemarker.template.Template;
import top.lcmatrix.util.codegenerator.Constants;
import top.lcmatrix.util.codegenerator.base.IContext;

public class TemplateProcessor {

	public static void process(TemplateStruct ts, IContext context, String storeDir) {
		try {
			Template contentTemplate = TemplateLoader.getTemplate(ts.getContentTemplate());
			Template fileNameTemplate = TemplateLoader.getTemplate(ts.getFileNameTemplate());
			StringWriter stringWriter = new StringWriter();
			fileNameTemplate.process(context, stringWriter);
			String fileName = stringWriter.toString();
			FileOutputStream fos = new FileOutputStream(storeDir + File.separator + fileName);
			Writer out = new BufferedWriter(new OutputStreamWriter(fos, Constants.DEFAULT_CHARSET), 10240);
			contentTemplate.process(context, out);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
