package com.kh.app.member.vo;

public class MemberVo {

	private String no;
	private String vip_no;
	private String mcoin;
	private String pay_total;
	private String id;
	private String pwd;
	private String pwd2;
	private String nick;
	private String address;
	private String interested_yn;
	private String enroll_date;
	private String modify_date;
	private String del_yn;
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getVip_no() {
		return vip_no;
	}
	public void setVip_no(String vip_no) {
		this.vip_no = vip_no;
	}
	public String getMcoin() {
		return mcoin;
	}
	public void setMcoin(String mcoin) {
		this.mcoin = mcoin;
	}
	public String getPay_total() {
		return pay_total;
	}
	public void setPay_total(String pay_total) {
		this.pay_total = pay_total;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getPwd2() {
		return pwd2;
	}
	public void setPwd2(String pwd2) {
		this.pwd2 = pwd2;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getInterested_yn() {
		return interested_yn;
	}
	public void setInterested_yn(String interested_yn) {
		this.interested_yn = interested_yn;
	}
	public String getEnroll_date() {
		return enroll_date;
	}
	public void setEnroll_date(String enroll_date) {
		this.enroll_date = enroll_date;
	}
	public String getModify_date() {
		return modify_date;
	}
	public void setModify_date(String modify_date) {
		this.modify_date = modify_date;
	}
	public String getDel_yn() {
		return del_yn;
	}
	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}
	public MemberVo(String no, String vip_no, String mcoin, String pay_total, String id, String pwd, String pwd2,
			String nick, String address, String interested_yn, String enroll_date, String modify_date, String del_yn) {
		super();
		this.no = no;
		this.vip_no = vip_no;
		this.mcoin = mcoin;
		this.pay_total = pay_total;
		this.id = id;
		this.pwd = pwd;
		this.pwd2 = pwd2;
		this.nick = nick;
		this.address = address;
		this.interested_yn = interested_yn;
		this.enroll_date = enroll_date;
		this.modify_date = modify_date;
		this.del_yn = del_yn;
	}
	public MemberVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "MemberVo [no=" + no + ", vip_no=" + vip_no + ", mcoin=" + mcoin + ", pay_total=" + pay_total + ", id="
				+ id + ", pwd=" + pwd + ", pwd2=" + pwd2 + ", nick=" + nick + ", address=" + address
				+ ", interested_yn=" + interested_yn + ", enroll_date=" + enroll_date + ", modify_date=" + modify_date
				+ ", del_yn=" + del_yn + "]";
	}

	
}
