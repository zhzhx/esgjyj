package com.eastsoft.esgjyj.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.eastsoft.esgjyj.context.ScopeUtil;
import com.eastsoft.esgjyj.domain.User;
import com.eastsoft.esgjyj.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.eastsoft.esgjyj.domain.YjkhDO;
import com.eastsoft.esgjyj.service.YjkhService;
import com.eastsoft.esgjyj.util.Query;
import com.eastsoft.esgjyj.util.R;

/**
 * ${comments}
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date November 13, 2017 8:56:59 AM CST
 */

@RestController
@RequestMapping("/esgjyj/yjkh")
public class YjkhController {
	@Autowired
	private YjkhService yjkhService;

	@GetMapping("/get/{id}")
	YjkhDO get(@PathVariable("id") String id){
		return  yjkhService.get(id);
	}

	@GetMapping("/list")
	public List<YjkhDO>  list(@RequestParam Map<String, Object> params){
		//查询列表数据
	//	Query query = new Query(params);
		List<YjkhDO> yjkhList = yjkhService.list(params);
//		int total = yjkhService.count(query);
//		PageUtils pageUtils = new PageUtils(yjkhList, total);
		return yjkhList;
	}

	/**
	 * 保存
	 */
	@PostMapping("/save")
	public R save( YjkhDO yjkh){
		yjkh.setActive("1");
		yjkh.setId(UUID.randomUUID().toString().replace("-",""));
		yjkh.setCourtNo(ScopeUtil.getSessionUser(User.class).getCourtNo());
		if(yjkhService.save(yjkh)>0){
			return R.ok();
		}
		return R.ok();
	}
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update( YjkhDO yjkh){
		yjkhService.update(yjkh);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	public R remove( String id){
		if(yjkhService.remove(id)>0){
			return R.ok();
		}
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		yjkhService.batchRemove(ids);
		return R.ok();
	}

	@GetMapping("/init")
	public R init(){
		yjkhService.init();
		return R.ok();
	}

}
