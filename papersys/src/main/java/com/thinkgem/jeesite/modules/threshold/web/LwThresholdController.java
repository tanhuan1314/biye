package com.thinkgem.jeesite.modules.threshold.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.threshold.entity.LwThreshold;
import com.thinkgem.jeesite.modules.threshold.service.LwThresholdService;

/**
 * 阀值Controller
 * @author admin
 * @version 2019-11-11
 */
@Controller
@RequestMapping(value = "${adminPath}/threshold/lwThreshold")
public class LwThresholdController extends BaseController {

	@Autowired
	private LwThresholdService lwThresholdService;
	
	@ModelAttribute
	public LwThreshold get(@RequestParam(required=false) String id) {
		LwThreshold entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = lwThresholdService.get(id);
		}
		if (entity == null){
			entity = new LwThreshold();
		}
		return entity;
	}
	
	@RequiresPermissions("threshold:lwThreshold:view")
	@RequestMapping(value = {"list", ""})
	public String list(LwThreshold lwThreshold, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<LwThreshold> page = lwThresholdService.findPage(new Page<LwThreshold>(request, response), lwThreshold); 
		model.addAttribute("page", page);
		return "modules/threshold/lwThresholdList";
	}

	@RequiresPermissions("threshold:lwThreshold:view")
	@RequestMapping(value = "form")
	public String form(LwThreshold lwThreshold, Model model) {
		User u= UserUtils.getUser();
		//一个用户只能设置一个阀值，这里需要先判断是否存在，如果存在则直接取出
		LwThreshold where=new LwThreshold();
		where.setCreateBy(u);
		List<LwThreshold> list= lwThresholdService.findList(where);
		if(list!=null&&!list.isEmpty()) {
			lwThreshold=list.get(0);
		}
		model.addAttribute("lwThreshold", lwThreshold);
		return "modules/threshold/lwThresholdForm";
	}

	@RequiresPermissions("threshold:lwThreshold:edit")
	@RequestMapping(value = "save")
	public String save(LwThreshold lwThreshold, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, lwThreshold)){
			return form(lwThreshold, model);
		}
		lwThresholdService.save(lwThreshold);
		addMessage(redirectAttributes, "保存阀值成功");
		return "redirect:"+Global.getAdminPath()+"/threshold/lwThreshold/form?repage";
	}
	
	@RequiresPermissions("threshold:lwThreshold:edit")
	@RequestMapping(value = "delete")
	public String delete(LwThreshold lwThreshold, RedirectAttributes redirectAttributes) {
		lwThresholdService.delete(lwThreshold);
		addMessage(redirectAttributes, "删除阀值成功");
		return "redirect:"+Global.getAdminPath()+"/threshold/lwThreshold/?repage";
	}

}