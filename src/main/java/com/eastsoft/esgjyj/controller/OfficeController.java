package com.eastsoft.esgjyj.controller;

import java.util.List;

import com.eastsoft.esgjyj.util.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eastsoft.esgjyj.context.ScopeUtil;
import com.eastsoft.esgjyj.domain.User;
import com.eastsoft.esgjyj.form.TreeNode;
import com.eastsoft.esgjyj.service.impl.OfficeServiceImpl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 部门控制器。
 * @author Ben
 * @since 1.0.0
 * @version 1.0.0
 */
@Controller
public class OfficeController {
	@Autowired
	private OfficeServiceImpl officeService;
	
	/**
	 * 获取部门人员树。
	 * @return 部门人员树结点列表。
	 */
	@GetMapping("/office/officeUserTree")
	@ResponseBody
	public JSONArray main() {
		User sessionUser = ShiroUtils.getUser();
		List<TreeNode> list = this.officeService.getOfficeUserTreeNodes("0F");
		
		JSONArray jsonArray = JSONArray.fromObject(list);
		JSONArray children;
		JSONObject json, state;
		for (int i = 0; i < jsonArray.size(); i++) {
			json = jsonArray.getJSONObject(i);
			state = new JSONObject();
			//state.put("opened", i == 0);
			state.put("disabled", false);
			state.put("selected", false);
			json.put("state", state);
			
			children = (JSONArray) json.get("children");
			for (int j = 0; j < children.size(); j++) {
				state = new JSONObject();
				state.put("opened", false);
				state.put("disabled", false);
				state.put("selected", false);
				children.getJSONObject(j).put("state", state);
			}
		}
		return jsonArray;
	}
	@GetMapping("/office/officeTree")
	@ResponseBody
	public JSONArray office() {
		User sessionUser = (User) ScopeUtil.getRequest().getSession().getAttribute("user");
		List<TreeNode> list = this.officeService.getOfficeTreeNodes(sessionUser.getCourtNo());

		JSONArray jsonArray = JSONArray.fromObject(list);
		JSONArray children;
		JSONObject json, state;
		for (int i = 0; i < jsonArray.size(); i++) {
			json = jsonArray.getJSONObject(i);
			state = new JSONObject();
			state.put("opened", i == 0);
			state.put("disabled", false);
			state.put("selected", false);
			json.put("state", state);

			children = (JSONArray) json.get("children");
			for (int j = 0; j < children.size(); j++) {
				state = new JSONObject();
				state.put("opened", false);
				state.put("disabled", false);
				state.put("selected", false);
				children.getJSONObject(j).put("state", state);
			}
		}
		return jsonArray;
	}
}
