package maven_demo.dto2;



public class Gadgets {
	private int sno;
	private String name;
	private String companyName;
	private int modelNumber;
	private double MRP;
	private double discount;
	private double salePrice;
	
	//Constructor 
	public Gadgets(int sno,String name, String companyName, int modelNumber, double mRP, double discount, double salePrice) {
		super();
		this.sno=sno;
		this.name = name;
		this.companyName = companyName;
		this.modelNumber = modelNumber;
		MRP = mRP;
		this.discount = discount;
		this.salePrice = salePrice;
	}
//updated constructor	
	public Gadgets(double discount, int sno, String name) {
		this.discount=discount;
		this.sno=sno;
		this.name=name;
	}
	//delete constructor
	public Gadgets(int sno, String name) {
		this.sno=sno;
		this.name=name;

	}
	//get and set methods
	
	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public int getModelNumber() {
		return modelNumber;
	}
	public void setModelNumber(int modelNumber) {
		this.modelNumber = modelNumber;
	}
	public double getMRP() {
		return MRP;
	}
	public void setMRP(double mRP) {
		MRP = mRP;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}

	@Override
	public String toString() {
		return "[\nsno=" + sno + ", name=" + name + ", companyName=" + companyName + ", modelNumber="
				+ modelNumber + ", MRP=" + MRP + ", discount=" + discount + ", salePrice=" + salePrice + "]";
	}
	

}
