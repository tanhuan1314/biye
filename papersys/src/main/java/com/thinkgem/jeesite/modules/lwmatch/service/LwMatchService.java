package com.thinkgem.jeesite.modules.lwmatch.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.lwmatch.entity.LwMatch;
import com.thinkgem.jeesite.modules.lwmatch.dao.LwMatchDao;

/**
 * 论文匹配Service
 * @author admin
 * @version 2019-11-11
 */
@Service
@Transactional(readOnly = true)
public class LwMatchService extends CrudService<LwMatchDao, LwMatch> {

	public LwMatch get(String id) {
		return super.get(id);
	}
	
	public List<LwMatch> findList(LwMatch lwMatch) {
		return super.findList(lwMatch);
	}
	
	public Page<LwMatch> findPage(Page<LwMatch> page, LwMatch lwMatch) {
		return super.findPage(page, lwMatch);
	}
	
	@Transactional(readOnly = false)
	public void save(LwMatch lwMatch) {
		super.save(lwMatch);
	}
	
	@Transactional(readOnly = false)
	public void delete(LwMatch lwMatch) {
		super.delete(lwMatch);
	}
	
}