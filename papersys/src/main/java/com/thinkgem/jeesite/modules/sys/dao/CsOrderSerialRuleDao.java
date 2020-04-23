/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.CsOrderSerialRule;

/**
 * 订单编规则DAO接口
 * @author gaozy
 * @version 2016-10-26
 */
@MyBatisDao
public interface CsOrderSerialRuleDao extends CrudDao<CsOrderSerialRule> {
	public CsOrderSerialRule findByUser(String userId);
}