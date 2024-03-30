package notion;

public class NotionVo {

	private String no;
	private String title;
	private String content;
	private String enroll_date;
	private String writer_no;
	public NotionVo(String no, String title, String content, String enroll_date, String writer_no) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.enroll_date = enroll_date;
		this.writer_no = writer_no;
	}
	public NotionVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getEnroll_date() {
		return enroll_date;
	}
	public void setEnroll_date(String enroll_date) {
		this.enroll_date = enroll_date;
	}
	public String getWriter_no() {
		return writer_no;
	}
	public void setWriter_no(String writer_no) {
		this.writer_no = writer_no;
	}
	@Override
	public String toString() {
		return "NotionVo [no=" + no + ", title=" + title + ", content=" + content + ", enroll_date=" + enroll_date
				+ ", writer_no=" + writer_no + "]";
	}
	
	
}
