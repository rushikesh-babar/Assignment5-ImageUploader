package com.example.imageUploader.model;

import java.sql.Timestamp;

public class Image {
	
	private int id;
	private String fileName;
	private Timestamp uploadTime;
	
    public Image() {}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Timestamp getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Timestamp uploadTime) {
		this.uploadTime = uploadTime;
	}

	@Override
	public String toString() {
		return "Image [id=" + id + ", fileName=" + fileName + ", uploadTime=" + uploadTime + "]";
	}
	
	

}
