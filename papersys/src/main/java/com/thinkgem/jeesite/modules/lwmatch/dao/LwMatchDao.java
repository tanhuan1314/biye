package com.thinkgem.jeesite.modules.lwmatch.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.lwmatch.entity.LwMatch;

/**
 * 论文匹配DAO接口
 * @author admin
 * @version 2019-11-11
 */
@MyBatisDao
public interface LwMatchDao extends CrudDao<LwMatch> {
	
}