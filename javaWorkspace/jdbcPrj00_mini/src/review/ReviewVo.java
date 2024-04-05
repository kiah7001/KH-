package review;

public class ReviewVo {

	private String no;
	private String productNo;
	private String writerNo;
	private String title;
	private String content;
	private String enrollDate;
	private String delYn;
	
	public ReviewVo() {
		super();
	}

	public ReviewVo(String no, String productNo, String writerNo, String title, String content, String enrollDate,
			String delYn) {
		super();
		this.no = no;
		this.productNo = productNo;
		this.writerNo = writerNo;
		this.title = title;
		this.content = content;
		this.enrollDate = enrollDate;
		this.delYn = delYn;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getWriterNo() {
		return writerNo;
	}

	public void setWriterNo(String writerNo) {
		this.writerNo = writerNo;
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

	public String getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}

	public String getDelYn() {
		return delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

	@Override
	public String toString() {
		return "ReviewVo [no=" + no + ", productNo=" + productNo + ", writerNo=" + writerNo + ", title=" + title
				+ ", content=" + content + ", enrollDate=" + enrollDate + ", delYn=" + delYn + "]";
	}
	
	
	
}
