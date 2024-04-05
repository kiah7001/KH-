
package product;

public class ProductVo {
	private String no;
	private String drinkName;
	private String price;
	private String productStock;
	private String regisDate;
	private String delYn;
	
	
	public ProductVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ProductVo(String no, String drinkName, String price, String productStock, String regisDate, String delYn) {
		super();
		this.no = no;
		this.drinkName = drinkName;
		this.price = price;
		this.productStock = productStock;
		this.regisDate = regisDate;
		this.delYn = delYn;
	}

	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getDrinkName() {
		return drinkName;
	}
	public void setDrinkName(String drinkName) {
		this.drinkName = drinkName;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getProductStock() {
		return productStock;
	}
	public void setProductStock(String productStock) {
		this.productStock = productStock;
	}
	public String getRegisDate() {
		return regisDate;
	}
	public void setRegisDate(String regisDate) {
		this.regisDate = regisDate;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

	@Override
	public String toString() {
		return "ProductVo [no=" + no + ", drinkName=" + drinkName + ", price=" + price + ", productStock="
				+ productStock + ", regisDate=" + regisDate + ", delYn=" + delYn + "]";
	}
	
	
}


