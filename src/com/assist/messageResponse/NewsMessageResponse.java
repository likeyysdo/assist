package com.assist.messageResponse;

import java.util.List;

import com.assist.model.Article;

public class NewsMessageResponse {
	private String ToUserName;
	private String FromUserName;
	private int CreateTime;
	private String MsgType;
	private int ArticleCount;
	private List<Article> addlist;
	
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
	public int getArticleCount() {
		return ArticleCount;
	}
	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}
	public List<Article> getAddlist() {
		return addlist;
	}
	public void setAddlist(List<Article> addlist) {
		this.addlist = addlist;
	}
}
