package com.au.entity;

import java.util.Date;

public class Record {
	private int type;
	//买入
	private String id;
	private double gram;
	private double price;
	private String time;
	private String selltime;
	private double amount=0;
	private double profit=0;
	private double sellgram=0;
	private int sellall=0;
	//卖出
	private String buyid;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getGram() {
		return gram;
	}
	public void setGram(double gram) {
		this.gram = gram;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getProfit() {
		return profit;
	}
	public void setProfit(double profit) {
		this.profit = profit;
	}
	public double getSellgram() {
		return sellgram;
	}
	public void setSellgram(double sellgram) {
		this.sellgram = sellgram;
	}
	public int getSellall() {
		return sellall;
	}
	public void setSellall(int sellall) {
		this.sellall = sellall;
	}
	public String getBuyid() {
		return buyid;
	}
	public void setBuyid(String buyid) {
		this.buyid = buyid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Record [type=" + type + ", id=" + id + ", gram=" + gram + ", price=" + price + ", time=" + time
				+ ", selltime=" + selltime + ", amount=" + amount + ", profit=" + profit + ", sellgram=" + sellgram
				+ ", sellall=" + sellall + ", buyid=" + buyid + "]";
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSelltime() {
		return selltime;
	}
	public void setSelltime(String selltime) {
		this.selltime = selltime;
	}


}
