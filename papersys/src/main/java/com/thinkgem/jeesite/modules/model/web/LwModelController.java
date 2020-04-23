package com.thinkgem.jeesite.modules.model.web;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.model.entity.LwModel;
import com.thinkgem.jeesite.modules.model.service.LwModelService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.threshold.entity.LwThreshold;

/**
 * 模型管理Controller
 * @author admin
 * @version 2019-11-11
 */
@Controller
@RequestMapping(value = "${adminPath}/model/lwModel")
public class LwModelController extends BaseController {

	@Autowired
	private LwModelService lwModelService;
	
	@ModelAttribute
	public LwModel get(@RequestParam(required=false) String id) {
		LwModel entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = lwModelService.get(id);
		}
		if (entity == null){
			entity = new LwModel();
		}
		return entity;
	}
	
	@RequiresPermissions("model:lwModel:view")
	@RequestMapping(value = {"list", ""})
	public String list(LwModel lwModel, HttpServletRequest request, HttpServletResponse response, Model model) {
		User u= UserUtils.getUser();
		lwModel.setCreateBy(u);
		Page<LwModel> page = lwModelService.findPage(new Page<LwModel>(request, response), lwModel); 
		model.addAttribute("page", page);
		return "modules/model/lwModelList";
	}

	@RequiresPermissions("model:lwModel:view")
	@RequestMapping(value = "form")
	public String form(LwModel lwModel, Model model) {
		model.addAttribute("lwModel", lwModel);
		return "modules/model/lwModelForm";
	}

	@RequiresPermissions("model:lwModel:edit")
	@RequestMapping(value = "save")
	public String save(LwModel lwModel, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, lwModel)){
			return form(lwModel, model);
		}
		lwModelService.save(lwModel);
		addMessage(redirectAttributes, "保存论文模型成功");
		return "redirect:"+Global.getAdminPath()+"/model/lwModel/?repage";
	}
	
	@RequiresPermissions("model:lwModel:edit")
	@RequestMapping(value = "delete")
	public String delete(LwModel lwModel, RedirectAttributes redirectAttributes) {
		lwModelService.delete(lwModel);
		addMessage(redirectAttributes, "删除论文模型成功");
		return "redirect:"+Global.getAdminPath()+"/model/lwModel/?repage";
	}
	
	/**
	 * 导入模型数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value = "upload", method=RequestMethod.POST)
    public Map<String,Object> upload(@RequestParam("apkFile") MultipartFile file, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		Map<String,Object> result=new HashMap<String,Object>();
		try {
			 String fileName = file.getOriginalFilename();
			 SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
			 String fn=sdf.format(new Date());
			 String realpath=request.getSession().getServletContext().getRealPath("/")+"static/upload";
			 File targetFile = new File(realpath+"/"+fn+ fileName);
			 file.transferTo(targetFile);
				result.put("flag", true);
				result.put("data",targetFile);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入用户失败！失败信息："+e.getMessage());
		}
		return result;
    }
	
	/**
	 * 导入模型数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("model:lwModel:edit")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			 String fileName = file.getOriginalFilename();
			 SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
			 String fn=sdf.format(new Date());
			 String realpath=request.getSession().getServletContext().getRealPath("/");
			 File targetFile = new File(realpath+"/"+fn+"/"+ fileName);
			 file.transferTo(targetFile);
			addMessage(redirectAttributes, "上传成功");
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入用户失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/sys/user/list?repage";
    }

}