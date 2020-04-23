package com.thinkgem.jeesite.modules.lwmatch.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 论文匹配Entity
 * @author admin
 * @version 2019-11-11
 */
public class LwMatch extends DataEntity<LwMatch> {
	
	private static final long serialVersionUID = 1L;
	private String modelid;		// modelid
	private String modelname;		// 模型名称
	private String filename;		// 文档名称
	private String filepath;		// 文档路径
	private String tsvalue;		// 相似度
	private String author;//作者
	private String result;//匹配结果
	
	public LwMatch() {
		super();
	}

	public LwMatch(String id){
		super(id);
	}

	@Length(min=1, max=100, message="modelid长度必须介于 1 和 100 之间")
	public String getModelid() {
		return modelid;
	}

	public void setModelid(String modelid) {
		this.modelid = modelid;
	}
	
	@Length(min=1, max=500, message="模型名称长度必须介于 1 和 255 之间")
	public String getModelname() {
		return modelname;
	}

	public void setModelname(String modelname) {
		this.modelname = modelname;
	}
	
	@Length(min=1, max=255, message="文档名称长度必须介于 1 和 255 之间")
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	@Length(min=0, max=500, message="文档路径长度必须介于 0 和 500 之间")
	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
	@Length(min=1, max=20, message="相似度长度必须介于 1 和 20 之间")
	public String getTsvalue() {
		return tsvalue;
	}

	public void setTsvalue(String tsvalue) {
		this.tsvalue = tsvalue;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
}