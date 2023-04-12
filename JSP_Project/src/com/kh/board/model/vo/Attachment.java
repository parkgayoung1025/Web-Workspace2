package com.kh.board.model.vo;

import java.sql.Date;

public class Attachment {
	
	private int fileNo;				//	FILE_NO
	private int refBno;				//	REF_BNO 	-> BOARD 테이블의 PK값 저장
	private String orignName;		//	ORIGIN_NAME -> 업로드당시 원본 이름
	private String changeName;		//	CHANGE_NAME -> 동일한 파일명은 존재할수 없기때문에 동일명 바꿔줄 이름
	private String filePath;		//	FILE_PATH
	private Date uploadDate;		//	UPLOAD_DATE
	private	int fileLevel;			//	FILE_LEVEL	-> 이미지 형태의 데이터를 여러개 올릴경우 각각의 이미지파일에 번호를 붙혀 기능을 부여
	private	String status;			//	STATUS

	public Attachment() {
		super();
	}

	public Attachment(int fileNo, int refBno, String orignName, String changeName, String filePath, Date uploadDate,
			int fileLevel, String status) {
		super();
		this.fileNo = fileNo;
		this.refBno = refBno;
		this.orignName = orignName;
		this.changeName = changeName;
		this.filePath = filePath;
		this.uploadDate = uploadDate;
		this.fileLevel = fileLevel;
		this.status = status;
	}

	public int getFileNo() {
		return fileNo;
	}

	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}

	public int getRefBno() {
		return refBno;
	}

	public void setRefBno(int refBno) {
		this.refBno = refBno;
	}

	public String getOrignName() {
		return orignName;
	}

	public void setOrignName(String orignName) {
		this.orignName = orignName;
	}

	public String getChangeName() {
		return changeName;
	}

	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public int getFileLevel() {
		return fileLevel;
	}

	public void setFileLevel(int fileLevel) {
		this.fileLevel = fileLevel;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Attachment [fileNo=" + fileNo + ", refBno=" + refBno + ", orignName=" + orignName + ", changeName="
				+ changeName + ", filePath=" + filePath + ", uploadDate=" + uploadDate + ", fileLevel=" + fileLevel
				+ ", status=" + status + "]";
	}

}
