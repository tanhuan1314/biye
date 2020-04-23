package com.thinkgem.jeesite.modules.lwmatch.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.lwmatch.entity.LwMatchResult;
import com.thinkgem.jeesite.modules.lwmatch.dao.LwMatchResultDao;

/**
 * 匹配结果Service
 * @author admin
 * @version 2019-11-11
 */
@Service
@Transactional(readOnly = true)
public class LwMatchResultService extends CrudService<LwMatchResultDao, LwMatchResult> {

	public LwMatchResult get(String id) {
		return super.get(id);
	}
	
	public List<LwMatchResult> findList(LwMatchResult lwMatchResult) {
		return super.findList(lwMatchResult);
	}
	
	public Page<LwMatchResult> findPage(Page<LwMatchResult> page, LwMatchResult lwMatchResult) {
		return super.findPage(page, lwMatchResult);
	}
	
	@Transactional(readOnly = false)
	public void save(LwMatchResult lwMatchResult) {
		super.save(lwMatchResult);
	}
	
	@Transactional(readOnly = false)
	public void delete(LwMatchResult lwMatchResult) {
		super.delete(lwMatchResult);
	}
	
}