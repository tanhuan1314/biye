package com.thinkgem.jeesite.modules.lwmatch.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 匹配结果Entity
 * @author admin
 * @version 2019-11-11
 */
public class LwMatchResult extends DataEntity<LwMatchResult> {
	
	private static final long serialVersionUID = 1L;
	private String matchname;		// 匹配文件
	private String modelname;		// 模型文件
	private String matchvalue;		// 相似度
	private String result;		// 匹配结果
	private String tsvalue;//
	
	public LwMatchResult() {
		super();
	}

	public LwMatchResult(String id){
		super(id);
	}

	@Length(min=0, max=255, message="匹配文件长度必须介于 0 和 255 之间")
	public String getMatchname() {
		return matchname;
	}

	public void setMatchname(String matchname) {
		this.matchname = matchname;
	}
	
	@Length(min=0, max=255, message="模型文件长度必须介于 0 和 255 之间")
	public String getModelname() {
		return modelname;
	}

	public void setModelname(String modelname) {
		this.modelname = modelname;
	}
	
	@Length(min=0, max=255, message="相似度长度必须介于 0 和 255 之间")
	public String getMatchvalue() {
		return matchvalue;
	}

	public void setMatchvalue(String matchvalue) {
		this.matchvalue = matchvalue;
	}
	
	@Length(min=0, max=255, message="匹配结果长度必须介于 0 和 255 之间")
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getTsvalue() {
		return tsvalue;
	}

	public void setTsvalue(String tsvalue) {
		this.tsvalue = tsvalue;
	}
	
}