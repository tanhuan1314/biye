package com.thinkgem.jeesite.modules.lwmatch.web;

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
import com.thinkgem.jeesite.modules.lwmatch.entity.LwMatchResult;
import com.thinkgem.jeesite.modules.lwmatch.service.LwMatchResultService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 匹配结果Controller
 * @author admin
 * @version 2019-11-11
 */
@Controller
@RequestMapping(value = "${adminPath}/lwmatch/lwMatchResult")
public class LwMatchResultController extends BaseController {

	@Autowired
	private LwMatchResultService lwMatchResultService;
	
	@ModelAttribute
	public LwMatchResult get(@RequestParam(required=false) String id) {
		LwMatchResult entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = lwMatchResultService.get(id);
		}
		if (entity == null){
			entity = new LwMatchResult();
		}
		return entity;
	}
	
	@RequiresPermissions("lwmatch:lwMatchResult:view")
	@RequestMapping(value = {"list", ""})
	public String list(LwMatchResult lwMatchResult, HttpServletRequest request, HttpServletResponse response, Model model) {
		User u= UserUtils.getUser();
		lwMatchResult.setCreateBy(u);
		Page<LwMatchResult> page = lwMatchResultService.findPage(new Page<LwMatchResult>(request, response), lwMatchResult); 
		model.addAttribute("page", page);
		return "modules/lwmatch/lwMatchResultList";
	}

	@RequiresPermissions("lwmatch:lwMatchResult:view")
	@RequestMapping(value = "form")
	public String form(LwMatchResult lwMatchResult, Model model) {
		model.addAttribute("lwMatchResult", lwMatchResult);
		return "modules/lwmatch/lwMatchResultForm";
	}

	@RequiresPermissions("lwmatch:lwMatchResult:edit")
	@RequestMapping(value = "save")
	public String save(LwMatchResult lwMatchResult, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, lwMatchResult)){
			return form(lwMatchResult, model);
		}
		lwMatchResultService.save(lwMatchResult);
		addMessage(redirectAttributes, "保存匹配结果成功");
		return "redirect:"+Global.getAdminPath()+"/lwmatch/lwMatchResult/?repage";
	}
	
	@RequiresPermissions("lwmatch:lwMatchResult:edit")
	@RequestMapping(value = "delete")
	public String delete(LwMatchResult lwMatchResult, RedirectAttributes redirectAttributes) {
		lwMatchResultService.delete(lwMatchResult);
		addMessage(redirectAttributes, "删除匹配结果成功");
		return "redirect:"+Global.getAdminPath()+"/lwmatch/lwMatchResult/?repage";
	}

}