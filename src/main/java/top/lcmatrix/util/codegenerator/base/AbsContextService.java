package top.lcmatrix.util.codegenerator.base;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import top.lcmatrix.util.codegenerator.gui.InputBean;
import top.lcmatrix.util.codegenerator.template.TemplateLoader;
import top.lcmatrix.util.codegenerator.template.TemplateProcessor;
import top.lcmatrix.util.codegenerator.template.TemplateStruct;
import top.lcmatrix.util.codegenerator.util.PathUtil;

public abstract class AbsContextService {
	
	public abstract List<IContext> getContexts(InputBean inputBean);
	
	public void generate(InputBean inputBean) {
		List<TemplateStruct> templates = TemplateLoader.loadTemplates(inputBean.getTemplateDir());
		List<IContext> contexts = getContexts(inputBean);
		String baseStoreDir = inputBean.getOutputDir();
		new File(baseStoreDir).mkdirs();
		for(IContext context : contexts) {
			String tableNameDir = null;
			if(inputBean.isOutputToTableNameDir()){
				String fileStoreDir = PathUtil.createNoRepeatPath(inputBean.getOutputDir(), context.getName());
				new File(fileStoreDir).mkdirs();
				tableNameDir = FilenameUtils.getBaseName(fileStoreDir);
			}
			for(TemplateStruct ts : templates){
				String fileStoreDir = baseStoreDir + (tableNameDir != null ? (File.separator + tableNameDir) : "") + File.separator + ts.getRelativeDirPath();
				new File(fileStoreDir).mkdirs();
				TemplateProcessor.process(ts, context, fileStoreDir);
			}
		}
	}
}
