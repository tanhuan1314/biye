package com.thinkgem.jeesite.modules.model.entity;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 模型管理Entity
 * @author admin
 * @version 2019-11-11
 */
public class LwModel extends DataEntity<LwModel> {
	
	private static final long serialVersionUID = 1L;
	private String modelname;		// 模型名称
	private String filename;		// 文件名称
	private String filepath;		// 文件路径
	
	
	private MultipartFile file;
	
	public LwModel() {
		super();
	}

	public LwModel(String id){
		super(id);
	}

	@Length(min=1, max=255, message="模型名称长度必须介于 1 和 255 之间")
	public String getModelname() {
		return modelname;
	}

	public void setModelname(String modelname) {
		this.modelname = modelname;
	}
	
	@Length(min=1, max=255, message="文件名称长度必须介于 1 和 255 之间")
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	@Length(min=1, max=500, message="文件路径长度必须介于 1 和 500 之间")
	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
}