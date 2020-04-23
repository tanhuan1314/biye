/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.dao.CsOrderSerialRuleDao;
import com.thinkgem.jeesite.modules.sys.entity.CsOrderSerialRule;

/**
 * 订单编规则Service
 * @author gaozy
 * @version 2016-10-26
 */
@Service
@Transactional(readOnly = true)
public class CsOrderSerialRuleService extends CrudService<CsOrderSerialRuleDao, CsOrderSerialRule> {

	@Autowired
	private CsOrderSerialRuleDao csOrderSerialRuleDao;
	
	public CsOrderSerialRule get(String id) {
		return super.get(id);
	}
	
	public List<CsOrderSerialRule> findList(CsOrderSerialRule csOrderSerialRule) {
		return super.findList(csOrderSerialRule);
	}
	
	public Page<CsOrderSerialRule> findPage(Page<CsOrderSerialRule> page, CsOrderSerialRule csOrderSerialRule) {
		return super.findPage(page, csOrderSerialRule);
	}
	
	@Transactional(readOnly = false)
	public void save(CsOrderSerialRule csOrderSerialRule) {
		super.save(csOrderSerialRule);
	}
	
	@Transactional(readOnly = false)
	public void delete(CsOrderSerialRule csOrderSerialRule) {
		super.delete(csOrderSerialRule);
	}
	public CsOrderSerialRule findByUser(String userId) {
		return csOrderSerialRuleDao.findByUser(userId);
	}
}