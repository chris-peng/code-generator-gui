package ${globalsettings.packageName}.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ${globalsettings.packageName}.Constants;
import ${globalsettings.packageName}.common.BaseController;
import ${globalsettings.packageName}.entity.${entity.className};
import ${globalsettings.packageName}.entity.vo.Q${entity.className};
import ${globalsettings.packageName}.entity.vo.PageParams;
import ${globalsettings.packageName}.service.${entity.className}Service;
import ${globalsettings.packageName}.util.ExcelUtil;
import ${globalsettings.packageName}.webutil.response.ResponseException;

<#assign entityComplex = entity.entityName + "s">
<#if entity.entityName?ends_with("a") ||
	entity.entityName?ends_with("e") ||
	entity.entityName?ends_with("i") ||
	entity.entityName?ends_with("o") ||
	entity.entityName?ends_with("u") ||
	entity.entityName?ends_with("s")>
	<#assign entityComplex = entity.entityName + "es">
</#if>

@RequestMapping("${entityComplex}")
@Controller
public class DataAController extends BaseController{
	
	@Autowired
	private DataAService dataAService;
	
	@GetMapping("/")
	@ResponseBody
	public Iterable<DataA> list(DataAQueryParams queryParams, PageParams pageParams){
		return dataAService.findAll(queryParams, pageParams, resolveSortParam());
	}
	
	@PostMapping("/")
	@ResponseBody
	public DataA save(@RequestBody DataA data){
		return dataAService.save(data);
	}
	
	@DeleteMapping("/")
	@ResponseBody
	public void delete(@RequestBody List<Long> ids){
		dataAService.delete(ids);
		responseNoContent();
	}
	
	@PutMapping("/{id}")
	@ResponseBody
	public DataA update(@PathVariable long id, @RequestBody DataA data){
		data.setId(id);
		return dataAService.save(data);
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public DataA findById(@PathVariable long id){
		DataA data = dataAService.findById(id);
		if(data == null){
			throw new ResponseException.NotFound();
		}
		return data;
	}
	
	@DeleteMapping("/{id}")
	@ResponseBody
	public void delete(@PathVariable long id){
		dataAService.delete(id);
		responseNoContent();
	}
	
	@GetMapping("/oper/export")
	@ResponseBody
	public void export(DataAQueryParams queryParams){
		List<String> titles = new ArrayList<>();
		titles.add("ID");
		titles.add("field1");
		titles.add("field2");
		titles.add("创建时间");

		List<List<Object>> datas = new ArrayList<>();
		Iterable<DataA> allDatas = dataAService.findAll(queryParams, null, null);
		if (allDatas != null) {
			Iterator<DataA> iterator = allDatas.iterator();
			while (iterator.hasNext()) {
				DataA data = iterator.next();
				List<Object> row = new ArrayList<>();
				row.add(data.getId());
				row.add(data.getField1() == null ? "" : data.getField1());
				row.add(StringUtils.isEmpty(data.getField2()) ? "" : data.getField2());
				if (data.getCreateTime() != null) {
					row.add(Constants.COMMON_DATETIME_FORMATTER.format(data.getCreateTime()));
				} else {
					row.add("");
				}
				datas.add(row);
			}

			// 导出
			response().setContentType("application/vnd.ms-excel");
			response().setHeader("Content-Disposition",
					"attachment;filename=dataA-datas(" + Constants.COMMON_DATE_FORMATTER.format(new Date()) + ").xls");
			try {
				OutputStream outputStream = new BufferedOutputStream(response().getOutputStream());
				ExcelUtil.exportExcel(outputStream, "sheet", titles, datas);
			} catch (IOException e) {
				throw new RuntimeException("导出excel失败");
			}

		}
	}
}
