package faq;

public class FaqBoardVo {

	private String no;
	private String title;
	private String content;
	private String category;
	private String hit;
	private String enrollDate;
	private String modifyDate;
	private String delYn;
	
	// constructor w/o fields
	public FaqBoardVo() {
		
	}

	// constructor w/ fields
	public FaqBoardVo(String no, String title, String content, String category, String hit, String enrollDate,
			String modifyDate, String delYn) {
		this.no = no;
		this.title = title;
		this.content = content;
		this.category = category;
		this.hit = hit;
		this.enrollDate = enrollDate;
		this.modifyDate = modifyDate;
		this.delYn = delYn;
	}

	// getters and setters
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getHit() {
		return hit;
	}

	public void setHit(String hit) {
		this.hit = hit;
	}

	public String getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getDelYn() {
		return delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

	// toString()
	@Override
	public String toString() {
		return "FaqBoardVo [no=" + no + ", title=" + title + ", content=" + content + ", category=" + category
				+ ", hit=" + hit + ", enrollDate=" + enrollDate + ", modifyDate=" + modifyDate + ", delYn=" + delYn
				+ "]";
	}
	
	
}
