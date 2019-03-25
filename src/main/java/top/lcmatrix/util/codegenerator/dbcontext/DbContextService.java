package top.lcmatrix.util.codegenerator.dbcontext;

import java.util.ArrayList;
import java.util.List;

import org.apache.ddlutils.model.Table;

import top.lcmatrix.util.codegenerator.base.AbsContextService;
import top.lcmatrix.util.codegenerator.base.GenerateException;
import top.lcmatrix.util.codegenerator.base.IContext;
import top.lcmatrix.util.codegenerator.gui.InputBean;
import top.lcmatrix.util.codegenerator.setting.Globalsettings;

public class DbContextService extends AbsContextService{

	@Override
	public List<IContext> getContexts(InputBean inputBean) {
		Globalsettings globalsettings = Globalsettings.fromSettingsExp(inputBean.getGlobalSettings());
		
		DbContextDao tableMetaDataDao = new DbContextDao(inputBean);
		List<Table> tableModels = tableMetaDataDao.getTableModels(inputBean.getTableName());
		if(tableModels.isEmpty()) {
			throw new GenerateException("Not found any table to process!");
		}
		
		List<IContext> contexts = new ArrayList<IContext>();
		for(Table table : tableModels) {
			contexts.add(tableModel2Context(table, inputBean, globalsettings));
		}
		return contexts;
	}
	
	private IContext tableModel2Context(Table table, InputBean inputBean, Globalsettings globalsettings) {
		DbContext dbContext = new DbContext();
		dbContext.setGlobalsettings(globalsettings);
		dbContext.setName(table.getName());
		dbContext.setEntity(new Entity(table));
		return dbContext;
	}
}
