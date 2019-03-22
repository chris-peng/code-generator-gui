package top.lcmatrix.util.codegenerator.base;

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import top.lcmatrix.util.codegenerator.gui.InputBean;
import top.lcmatrix.util.codegenerator.template.TemplateLoader;
import top.lcmatrix.util.codegenerator.template.TemplateProcessor;
import top.lcmatrix.util.codegenerator.template.TemplateStruct;

public abstract class AbsContextService {
	
	public abstract List<IContext> getContexts(InputBean inputBean);
	
	public void generate(InputBean inputBean) {
		List<TemplateStruct> templates = TemplateLoader.loadTemplates(inputBean.getTemplateDir());
		List<IContext> contexts = getContexts(inputBean);
		for(IContext context : contexts) {
			String storeDir = getOutputDir(inputBean.getOutputDir(), context.getName(), inputBean.getPackageName());
			File fStoreDir = new File(storeDir);
			fStoreDir.mkdirs();
			for(TemplateStruct ts : templates){
				TemplateProcessor.process(ts, context, storeDir);
			}
		}
	}
	
	private String getOutputDir(String baseDir, String contextName, String packageName){
		if(StringUtils.isBlank(packageName)){
			packageName = "generated_code";
		}
		String mdir = baseDir + File.separator + packageName + "_" + contextName;
		String dir = mdir + "";
		int i = 1;
		while(new File(dir).exists()){
			dir = mdir + "_" + i;
			i++;
		}
		return dir;
	}
}
