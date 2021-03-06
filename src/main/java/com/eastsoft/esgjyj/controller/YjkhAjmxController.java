package com.eastsoft.esgjyj.controller;

 import java.util.List;
 import java.util.Map;

 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.*;

import com.eastsoft.esgjyj.domain.Ajmx;
import com.eastsoft.esgjyj.service.YjkhAjmxService;
import com.eastsoft.esgjyj.util.R;

/**
 * ${comments}
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date November 29, 2017 6:22:58 PM CST
 */
 
@Controller
@RequestMapping("/esgjyj/yjkhAjmx")
public class YjkhAjmxController {
	@Autowired
	private YjkhAjmxService yjkhAjmxService;
	
	@GetMapping("/get/{id}")
	Ajmx get(@PathVariable("id") String id){
	    return   yjkhAjmxService.get(id);
	}
	
	@ResponseBody
	@GetMapping("/list")
	public List<Map<String, Object>> list(@RequestParam Map<String, Object> params){
		//查询列表数据
		List<Map<String, Object>>  yjkhAjmxDOS= yjkhAjmxService.list(params);

		return yjkhAjmxDOS;
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save( Ajmx yjkhAjmx){
		if(yjkhAjmxService.save(yjkhAjmx)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update( Ajmx yjkhAjmx){
		yjkhAjmxService.update(yjkhAjmx);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	public R remove( String id){
		if(yjkhAjmxService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		yjkhAjmxService.batchRemove(ids);
		return R.ok();
	}
	
}
