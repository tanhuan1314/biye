package com.thinkgem.jeesite.modules.model.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.model.entity.LwModel;

/**
 * 模型管理DAO接口
 * @author admin
 * @version 2019-11-11
 */
@MyBatisDao
public interface LwModelDao extends CrudDao<LwModel> {
	
}