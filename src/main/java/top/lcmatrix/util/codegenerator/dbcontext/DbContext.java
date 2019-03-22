package top.lcmatrix.util.codegenerator.dbcontext;

import top.lcmatrix.util.codegenerator.base.IContext;
import top.lcmatrix.util.codegenerator.setting.Globalsettings;

public class DbContext implements IContext{

	private Globalsettings globalsettings;
	
	private String name;
	
	private Entity entity;
	
	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	@Override
	public Globalsettings getGlobalsettings() {
		return globalsettings;
	}

	public void setGlobalsettings(Globalsettings globalsettings) {
		this.globalsettings = globalsettings;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
