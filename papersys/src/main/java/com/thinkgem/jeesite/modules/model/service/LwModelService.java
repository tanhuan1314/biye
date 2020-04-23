package com.thinkgem.jeesite.modules.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.model.entity.LwModel;
import com.thinkgem.jeesite.modules.model.dao.LwModelDao;

/**
 * 模型管理Service
 * @author admin
 * @version 2019-11-11
 */
@Service
@Transactional(readOnly = true)
public class LwModelService extends CrudService<LwModelDao, LwModel> {

	public LwModel get(String id) {
		return super.get(id);
	}
	
	public List<LwModel> findList(LwModel lwModel) {
		return super.findList(lwModel);
	}
	
	public Page<LwModel> findPage(Page<LwModel> page, LwModel lwModel) {
		return super.findPage(page, lwModel);
	}
	
	@Transactional(readOnly = false)
	public void save(LwModel lwModel) {
		super.save(lwModel);
	}
	
	@Transactional(readOnly = false)
	public void delete(LwModel lwModel) {
		super.delete(lwModel);
	}
	
}