package maven_demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.mysql.cj.jdbc.Driver;

import maven_demo.dto1.User;
import maven_demo.dto2.Gadgets;



public class Operations {
	//User
    //Create user database 
	public void createUserTable() throws Exception {
//firstName lastName Gender age eMail phneNumber password wallet
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db4?createDatabaseIfNotExist=True","root","root");
		Statement s= con.createStatement();
		s.execute("create table If Not Exists UserData(id int,firstName varchar(45),lastName varchar(45),Gender varchar(45),age int,eMail varchar(45) PRIMARY KEY,phneNumber bigint,password varchar(45) PRIMARY KEY,wallet double)");
		s.close();
		con.close();
	}
	//Register
	public void register(User user) throws Exception {
		DriverManager.registerDriver(new Driver());
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db4","root", "root");
		PreparedStatement s=con.prepareStatement("insert into UserData values(?,?,?,?,?,?,?,?,?,?)");
		s.setInt(1,user.getId());
		s.setString(2,user.getFirstName());
		s.setString(3,user.getLastName());
		s.setString(4, user.getgender());
		s.setInt(5, user.getAge());
		s.setString(6, user.geteMail());
		s.setLong(7, user.getPhoneNumber());
		s.setString(8, user.getPassword());
		s.setDouble(9, user.getWallet());
		s.setString(10, user.getAddress());
		s.execute();
		s.close();
		con.close();
		
		
		
	}
	//Login
	public boolean login(String eMail,String password) throws Exception {
		DriverManager.registerDriver(new Driver());
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db4","root", "root");
		PreparedStatement s=con.prepareStatement("select eMail,password from UserData where eMail=? and password=?");
		s.setString(1, eMail);
		s.setString(2, password);
		ResultSet res=s.executeQuery();
		while(res.next()) {
		String	email=res.getString("eMail");
		String	pwd=res.getString("password");
	    if(email.equals(eMail) && pwd.equals(pwd)){
		s.close();
		con.close();
		return true;
	    }
	    }
		s.close();
		con.close();
		return false;	
		
	}
//Gadgets
	//Create Gadgets table
	public void createGadgetsTable() throws Exception {
		//name,companyName modelNumber,MRP,discount,salePrice
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db4?createDatabaseIfNotExist=True","root","root");
		Statement s= con.createStatement();
		s.execute("create table If Not Exists GadgetsData(sno int PRIMARY KEY,name varchar(45),companyName varchar(45),modelNumber int,MRP double,discount double,salePrice double)");
		s.close();
		con.close();
	}
	//Save
	public void save(List<Gadgets> gadgets) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db4", "root", "root");
		PreparedStatement s=con.prepareStatement("insert into gadgetsdata values(?,?,?,?,?,?,?)");
		for(Gadgets g:gadgets) {
			s.setInt(1, g.getSno());
			s.setString(2, g.getName());
			s.setString(3, g.getCompanyName());
			s.setInt(4, g.getModelNumber());
			s.setDouble(5, g.getMRP());
			s.setDouble(6, g.getDiscount());
			s.setDouble(7, g.getSalePrice());
			s.addBatch();
		}
		s.executeBatch();
		s.close();
		con.close();
	}
	//Update
	public void update(List<Gadgets> update) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db4", "root", "root");
		PreparedStatement ps=con.prepareStatement("update gadgetsdata set discount=? where sno=? and name=?");
		for(Gadgets g:update) {
		ps.setDouble(1,g.getDiscount());
		ps.setInt(2, g.getSno());
		ps.setString(3, g.getName());
		ps.addBatch();
		}
		ps.executeBatch();
		ps.close();
		con.close();
		
		
	}
	//Delete
	public void delete(List<Gadgets> delete) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db4", "root", "root");
		PreparedStatement ps=con.prepareStatement("delete from gadgetsdata where sno=? and name=?");
		for(Gadgets gadgents:delete) {
		ps.setInt(1, gadgents.getSno());
		ps.setString(2,gadgents.getName());
		ps.addBatch();
		}
		ps.executeBatch();
		ps.close();
		con.close();

		
	}
	//Print
	public void print() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db4", "root", "root");
		PreparedStatement ps=con.prepareStatement("select * from gadgetsdata");
		ResultSet res=ps.executeQuery();
		while(res.next()) {
		System.out.println("sno :"+res.getInt("sno")+" name :"+res.getString("name")+" CompanyName :"
		+res.getString("companyName")+" ModelName"+res.getInt("modelNumber")
		+" MRP"+res.getDouble("MRP")+" Discount"+res.getDouble("discount")+" salePrice :"
		+res.getDouble("salePrice"));
		}
		ps.close();
		con.close();

		
	}
//-------------------------------------------------------------------------------------------------------------	
	//Cart
	public Gadgets cart(int sno) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db4", "root", "root");
		PreparedStatement ps=con.prepareStatement("select * from gadgetsdata where sno=?");
		ps.setInt(1, sno);
		ResultSet res=ps.executeQuery();
		while(res.next()) {
			Gadgets g = new Gadgets(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4), res.getDouble(5), res.getDouble(6), res.getDouble(7));
		ps.close();
		ps.close();
			return g;
		}
		ps.close();
		con.close();
		return null;
		
	}
	//wallet
	public double wallet(String eMail,String password) throws Exception {
		DriverManager.registerDriver(new Driver());
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db4","root", "root");
		PreparedStatement s=con.prepareStatement("select wallet from UserData where eMail=? and password=?");
		s.setString(1, eMail);
		s.setString(2, password);
		ResultSet res=s.executeQuery();
		while(res.next()) {
		double wallet=res.getDouble("wallet");
		if(wallet>0 || wallet<0
				) {
		return wallet;
		}
		}
		s.close();
		con.close();
		return 0.0;
			
	}
	//wallet
	public void walletUpdate(Double wallet,String eMail,String password) throws Exception {
		DriverManager.registerDriver(new Driver());
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db4","root", "root");
		PreparedStatement s=con.prepareStatement("update UserData set wallet=? where eMail=? and password=?");
		s.setDouble(1, wallet);
		s.setString(2, eMail);
		s.setString(3, password);
		s.execute();
		s.close();
		con.close();
	}
	public String Address(String eMail,String password) throws Exception {
		DriverManager.registerDriver(new Driver());
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db4","root", "root");
		PreparedStatement s=con.prepareStatement("select Address from UserData where eMail=? and password=?");
		s.setString(1, eMail);
		s.setString(2, password);
		ResultSet res=s.executeQuery();
		while(res.next()) {
		String Address=res.getString("Address");
		return Address;
		}
		s.close();
		con.close();
		return null;	
	}
	public void AddressUpdate(String Address,String eMail,String password) throws Exception {
		DriverManager.registerDriver(new Driver());
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db4","root", "root");
		PreparedStatement s=con.prepareStatement("update UserData set Address=? where eMail=? and password=?");
		s.setString(1, Address);
		s.setString(2, eMail);
		s.setString(3, password);
		s.execute();
		s.close();
		con.close();
	}

}
