package com.thinkgem.jeesite.modules.threshold.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.threshold.entity.LwThreshold;
import com.thinkgem.jeesite.modules.threshold.dao.LwThresholdDao;

/**
 * 阀值Service
 * @author admin
 * @version 2019-11-11
 */
@Service
@Transactional(readOnly = true)
public class LwThresholdService extends CrudService<LwThresholdDao, LwThreshold> {

	public LwThreshold get(String id) {
		return super.get(id);
	}
	
	public List<LwThreshold> findList(LwThreshold lwThreshold) {
		return super.findList(lwThreshold);
	}
	
	public Page<LwThreshold> findPage(Page<LwThreshold> page, LwThreshold lwThreshold) {
		return super.findPage(page, lwThreshold);
	}
	
	@Transactional(readOnly = false)
	public void save(LwThreshold lwThreshold) {
		super.save(lwThreshold);
	}
	
	@Transactional(readOnly = false)
	public void delete(LwThreshold lwThreshold) {
		super.delete(lwThreshold);
	}
	
}