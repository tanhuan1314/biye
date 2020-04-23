package com.thinkgem.jeesite.modules.threshold.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.threshold.entity.LwThreshold;

/**
 * 阀值DAO接口
 * @author admin
 * @version 2019-11-11
 */
@MyBatisDao
public interface LwThresholdDao extends CrudDao<LwThreshold> {
	
}