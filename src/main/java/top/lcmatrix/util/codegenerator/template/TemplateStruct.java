package top.lcmatrix.util.codegenerator.template;

public class TemplateStruct {

	private String contentTemplate;
	private String fileNameTemplate;
	private String relativeDirPath;
	
	public String getContentTemplate() {
		return contentTemplate;
	}
	public void setContentTemplate(String contentTemplate) {
		this.contentTemplate = contentTemplate;
	}
	public String getFileNameTemplate() {
		return fileNameTemplate;
	}
	public void setFileNameTemplate(String fileNameTemplate) {
		this.fileNameTemplate = fileNameTemplate;
	}

	public String getRelativeDirPath() {
		return relativeDirPath;
	}

	public void setRelativeDirPath(String relativeDirPath) {
		this.relativeDirPath = relativeDirPath;
	}
}
