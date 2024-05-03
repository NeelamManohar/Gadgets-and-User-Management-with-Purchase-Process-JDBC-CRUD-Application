package maven_demo.dto1;

public class User {
	private int id;
	private String firstName;
	private String lastName;
	private String gender;
	private int age;
	private String eMail;
	private long phoneNumber;
	private String password;
	private double wallet;
	private String Address;
	//Constructor 
	public User(int id,String firstName, String lastName, String gender, int age, String eMail, long phoneNumber,
			String password, double wallet,String Address) {
		super();
		this.id=id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.age = age;
		this.eMail = eMail;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.wallet = wallet;
		this.Address=Address;
	}
	//get and set methods
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getgender() {
		return gender;
	}
	public void setgender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getWallet() {
		return wallet;
	}
	public void setWallet(double wallet) {
		this.wallet = wallet;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}


	@Override
	public String toString() {
		return "User [\nid=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
				+ ", age=" + age + ", eMail=" + eMail + ", phoneNumber=" + phoneNumber + ", password=" + password
				+ ", wallet=" + wallet +"Address="+Address+"]";
	}
	
	

}
