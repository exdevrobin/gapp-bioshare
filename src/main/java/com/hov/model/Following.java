package com.hov.model;

public class Following 
{
	private int connid;
	private int userid;
	private int followingid;
	
	public int getConnid() 
	{
		return connid;
	}
	public void setConnid(int connid) 
	{
		this.connid = connid;
	}
	public int getUserid() 
	{
		return userid;
	}
	public void setUserid(int userid) 
	{
		this.userid = userid;
	}
	public int getFollowingid() {
		return followingid;
	}
	public void setFollowingid(int followingid) {
		this.followingid = followingid;
	}
}