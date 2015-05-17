package com.assist.messageResponse;

import java.util.ArrayList;
import java.util.List;

import com.assist.model.MyImage;

public class ImageMessageResponse {
	private String ToUserName;
	private String FromUserName;
	private int CreateTime;
	private String MsgType;
	private MyImage Image;
	
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public int getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(int createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public MyImage getImage() {
		return Image;
	}
	public void setImage(MyImage image) {
		Image = image;
	}
}
