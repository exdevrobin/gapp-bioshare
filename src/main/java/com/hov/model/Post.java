package com.hov.model;

public class Post 
{
	private int postid;
	private String userid;
	private String title;
	private String content;
	private String hashtag;
	
	public int getPostid() 
	{
		return postid;
	}
	public void setPostid(int postid) 
	{
		this.postid = postid;
	}
	public String getUserid() 
	{
		return userid;
	}
	public void setUserid(String userid) 
	{
		this.userid = userid;
	}
	public String getTitle() 
	{
		return title;
	}
	public void setTitle(String title) 
	{
		this.title = title;
	}
	public String getContent() 
	{
		return content;
	}
	public void setContent(String content) 
	{
		this.content = content;
	}
	public String getHashtag() 
	{
		return hashtag;
	}
	public void setHashtag(String hashtag) 
	{
		this.hashtag = hashtag;
	}
}
