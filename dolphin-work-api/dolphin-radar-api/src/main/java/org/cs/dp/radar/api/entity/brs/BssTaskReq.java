package org.cs.dp.radar.api.entity.brs;

public class BssTaskReq {

	private String type;
	private String subtype;
	private String task_id;
	private String username;
	private String passwd;
	private String file_name;
	private String thumbnail_name;
	private int time;

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getThumbnail_name() {
		return thumbnail_name;
	}

	public void setThumbnail_name(String thumbnail_name) {
		this.thumbnail_name = thumbnail_name;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSubtype() {
		return subtype;
	}
	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}
	public String getTask_id() {
		return task_id;
	}
	public void setTask_id(String task_id) {
		this.task_id = task_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	
	
}
