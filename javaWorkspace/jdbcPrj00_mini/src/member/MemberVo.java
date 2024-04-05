package member;

public class MemberVo {
	
	public MemberVo() {
		super();
	}
	
	public MemberVo(String no, String id, String pwd, String nick, String address, String quitYn, String enrollDate, String adminYn) {
		super();
		this.no = no;
		this.id = id;
		this.pwd = pwd;
		this.nick = nick;
		this.address = address;
		this.quitYn = quitYn;
		this.enrollDate = enrollDate;
		this.adminYn = adminYn;
	}

	private String no;
	private String id;
	private String pwd;
	private String nick;
	private String address;
	private String quitYn;
	private String enrollDate;
	private String adminYn;
	
	
	public MemberVo(String adminYn) {
		super();
		this.adminYn = adminYn;
	}

	public String getadminYn() {
		return adminYn;
	}

	public void setadminYn(String adminYn) {
		this.adminYn = adminYn;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
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

	public String getQuitYn() {
		return quitYn;
	}

	public void setQuitYn(String quitYn) {
		this.quitYn = quitYn;
	}

	public String getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}

	@Override
	public String toString() {
		return "MemberVo [no=" + no + ", id=" + id + ", pwd=" + pwd + ", nick=" + nick + ", address=" + address
				+ ", quitYn=" + quitYn + ", enrollDate=" + enrollDate + ", adminYn=" + adminYn + "]";
	}

	
	
}
