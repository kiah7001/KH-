package product_order;

public class ProductOrderVo {
	private String no;
	private String mem_no;
	private String product_no;
	private String status_no;
	private String product_cnt;
	private String address;
	private String cancel_yn;
	private String price;
	private String estimate_time;
	public ProductOrderVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductOrderVo(String no, String mem_no, String product_no, String status_no, String product_cnt,
			String address, String cancel_yn, String price, String estimate_time) {
		super();
		this.no = no;
		this.mem_no = mem_no;
		this.product_no = product_no;
		this.status_no = status_no;
		this.product_cnt = product_cnt;
		this.address = address;
		this.cancel_yn = cancel_yn;
		this.price = price;
		this.estimate_time = estimate_time;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public String getProduct_no() {
		return product_no;
	}
	public void setProduct_no(String product_no) {
		this.product_no = product_no;
	}
	public String getStatus_no() {
		return status_no;
	}
	public void setStatus_no(String status_no) {
		this.status_no = status_no;
	}
	public String getProduct_cnt() {
		return product_cnt;
	}
	public void setProduct_cnt(String product_cnt) {
		this.product_cnt = product_cnt;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCancel_yn() {
		return cancel_yn;
	}
	public void setCancel_yn(String cancel_yn) {
		this.cancel_yn = cancel_yn;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getEstimate_time() {
		return estimate_time;
	}
	public void setEstimate_time(String estimate_time) {
		this.estimate_time = estimate_time;
	}
	@Override
	public String toString() {
		return "ProductOrderVo [no=" + no + ", mem_no=" + mem_no + ", product_no=" + product_no + ", status_no="
				+ status_no + ", product_cnt=" + product_cnt + ", address=" + address + ", cancel_yn=" + cancel_yn
				+ ", price=" + price + ", estimate_time=" + estimate_time + "]";
	}
	
	
	
	
	
}