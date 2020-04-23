package com.thinkgem.jeesite.modules.lwmatch.web;

import java.util.ArrayList;
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
import com.thinkgem.jeesite.common.paper.PaperUtils;
import com.thinkgem.jeesite.common.paper.WordUtils;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.lwmatch.entity.LwMatch;
import com.thinkgem.jeesite.modules.lwmatch.entity.LwMatchResult;
import com.thinkgem.jeesite.modules.lwmatch.service.LwMatchResultService;
import com.thinkgem.jeesite.modules.lwmatch.service.LwMatchService;
import com.thinkgem.jeesite.modules.model.entity.LwModel;
import com.thinkgem.jeesite.modules.model.service.LwModelService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.threshold.entity.LwThreshold;
import com.thinkgem.jeesite.modules.threshold.service.LwThresholdService;

/**
 * 论文匹配Controller
 * @author admin
 * @version 2019-11-11
 */
@Controller
@RequestMapping(value = "${adminPath}/lwmatch/lwMatch")
public class LwMatchController extends BaseController {

	@Autowired
	private LwMatchService lwMatchService;
	@Autowired
	private LwModelService lwModelService;
	@Autowired
	private LwThresholdService lwThresholdService;
	@Autowired
	private LwMatchResultService lwMatchResultService;
	
	@ModelAttribute
	public LwMatch get(@RequestParam(required=false) String id) {
		LwMatch entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = lwMatchService.get(id);
		}
		if (entity == null){
			entity = new LwMatch();
		}
		return entity;
	}
	
	@RequiresPermissions("lwmatch:lwMatch:view")
	@RequestMapping(value = {"list", ""})
	public String list(LwMatch lwMatch, HttpServletRequest request, HttpServletResponse response, Model model) {
		User u= UserUtils.getUser();
		lwMatch.setCreateBy(u);
		Page<LwMatch> page = lwMatchService.findPage(new Page<LwMatch>(request, response), lwMatch); 
		model.addAttribute("page", page);
		
		
		return "modules/lwmatch/lwMatchList";
	}

	@RequiresPermissions("lwmatch:lwMatch:view")
	@RequestMapping(value = "form")
	public String form(LwMatch lwMatch, Model model) {
		User u= UserUtils.getUser();
		LwModel where=new LwModel();
		where.setCreateBy(u);
		List<LwModel> lmlist=lwModelService.findList(where);
		model.addAttribute("lmlist", lmlist);
		model.addAttribute("lwMatch", lwMatch);
		return "modules/lwmatch/lwMatchForm";
	}

	@RequiresPermissions("lwmatch:lwMatch:edit")
	@RequestMapping(value = "save")
	public String save(LwMatch lwMatch, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, lwMatch)){
			return form(lwMatch, model);
		}
		String[] ids= lwMatch.getModelid().split(",");
		String names="";
		for(int i=0;i<ids.length;i++) {
			LwModel lm= lwModelService.get(ids[i]);
			if(!names.equals("")) {
				names+=",";
			}
			names+=lm.getModelname();
		}
		lwMatch.setModelname(names);
		lwMatchService.save(lwMatch);
		addMessage(redirectAttributes, "保存论文匹配成功");
		return "redirect:"+Global.getAdminPath()+"/lwmatch/lwMatch/?repage";
	}
	
	@RequiresPermissions("lwmatch:lwMatch:edit")
	@RequestMapping(value = "delete")
	public String delete(LwMatch lwMatch, RedirectAttributes redirectAttributes) {
		lwMatchService.delete(lwMatch);
		addMessage(redirectAttributes, "删除论文匹配成功");
		return "redirect:"+Global.getAdminPath()+"/lwmatch/lwMatch/?repage";
	}
	
	@RequestMapping(value = "lwmatch")
	public String lwmatch(LwMatch lwMatch, RedirectAttributes redirectAttributes) {
		//取出阀值，用于结果比较
		User u= UserUtils.getUser();
		LwThreshold where=new LwThreshold();
		where.setCreateBy(u);
		List<LwThreshold> list= lwThresholdService.findList(where);
		Double value=0D;
		if(list!=null&&!list.isEmpty()) {
			value=Double.parseDouble(list.get(0).getTsvalue());
		}else {
			addMessage(redirectAttributes, "请先设置阀值");
			return "redirect:"+Global.getAdminPath()+"/lwmatch/lwMatch/?repage";
		}
		//1.取数据模型文档数据
		LwMatch obj= lwMatchService.get(lwMatch.getId());
		//2.取出待查重文档数据
		String file1= WordUtils.readWord(obj.getFilepath());
		String[] ids= obj.getModelid().split(",");
		for(int i=0;i<ids.length;i++) {
			LwModel lm= lwModelService.get(ids[i]);
			String mcontent= WordUtils.readWord(lm.getFilepath());
			//3.开始查重
			double score=PaperUtils.paperMatch(file1, mcontent);
			//4.将查重结果保存到数据库
			LwMatchResult mr=new LwMatchResult();
			mr.setModelname(lm.getFilename());
			mr.setMatchname(obj.getFilename());
			mr.setMatchvalue(score+"");
			mr.setTsvalue(value+"");
			if(score<value) {
				mr.setResult("不相似");
			}else {
				mr.setResult("相似");
			}
			lwMatchResultService.save(mr);
		}
		addMessage(redirectAttributes, "查重成功，请前往查询结果进行查询");
		return "redirect:"+Global.getAdminPath()+"/lwmatch/lwMatchResult/?repage";
	}

}