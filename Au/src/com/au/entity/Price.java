package com.au.entity;

import java.util.Date;

public class Price {
	private String id;
	private String ename;
	private String cname;
	private double zxj;
	private double kpj;
	private double zgj;
	private double zdj;
	private double zdf;
	private double zsj;
	private Date gxsj;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public double getZxj() {
		return zxj;
	}
	public void setZxj(double zxj) {
		this.zxj = zxj;
	}
	public double getKpj() {
		return kpj;
	}
	public void setKpj(double kpj) {
		this.kpj = kpj;
	}
	public double getZgj() {
		return zgj;
	}
	public void setZgj(double zgj) {
		this.zgj = zgj;
	}
	public double getZdj() {
		return zdj;
	}
	public void setZdj(double zdj) {
		this.zdj = zdj;
	}
	public double getZdf() {
		return zdf;
	}
	public void setZdf(double zdf) {
		this.zdf = zdf;
	}
	public double getZsj() {
		return zsj;
	}
	public void setZsj(double zsj) {
		this.zsj = zsj;
	}
	@Override
	public String toString() {
		return "price [id=" + id + ", ename=" + ename + ", cname=" + cname + ", zxj=" + zxj + ", kpj=" + kpj + ", zgj="
				+ zgj + ", zdj=" + zdj + ", zdf=" + zdf + ", zsj=" + zsj + ", gxsj=" + gxsj + "]";
	}
	public Date getGxsj() {
		return gxsj;
	}
	public void setGxsj(Date gxsj) {
		this.gxsj = gxsj;
	}
}
