package com.thinkgem.jeesite.modules.threshold.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 阀值Entity
 * @author admin
 * @version 2019-11-11
 */
public class LwThreshold extends DataEntity<LwThreshold> {
	
	private static final long serialVersionUID = 1L;
	private String tsvalue;		// 阀值
	
	public LwThreshold() {
		super();
	}

	public LwThreshold(String id){
		super(id);
	}

	@Length(min=1, max=20, message="阀值长度必须介于 1 和 20 之间")
	public String getTsvalue() {
		return tsvalue;
	}

	public void setTsvalue(String tsvalue) {
		this.tsvalue = tsvalue;
	}
	
}