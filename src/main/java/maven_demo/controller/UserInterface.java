package maven_demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import maven_demo.dao.Operations;
import maven_demo.dto1.User;
import maven_demo.dto2.Gadgets;

public class UserInterface {
	public static int size;
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws Exception {
		HashMap<Gadgets, Integer> orders=new HashMap<Gadgets,Integer>();
		List<Gadgets> cart = new ArrayList<Gadgets>();
		Operations o = new Operations();
		o.createUserTable();
		o.createGadgetsTable();
		System.out.println("********Welcome********");
		boolean e = true;
		do {
			System.out.println("Enter your Option \n 1.User \n 2.Gadgets \n 3.Exit");
			int choice = sc.nextInt();
			switch (choice) {
//--------------------------------------------------------------------------------------------------
//User
			case 1: {
				boolean e1 = true;
				do {
					System.out.println(" 1.Register \n 2.Login \n 3.Back \n Enter any one Option");
					int choice2 = sc.nextInt();
					switch (choice2) {
					// *******************************************************************************
					// Register
					case 1: {
						System.out.println("ID nuber:");
						int id = sc.nextInt();
						System.out.println("First Name :");
						String firstName = sc.next();
						System.out.println("Last Name :");
						String lastName = sc.next();
						System.out.println("Gender :");
						String gender = sc.next();
						System.out.println("Age :");
						int age = sc.nextInt();
						System.out.println("*EMail :");
						String eMail = sc.next();
						System.out.println("Phone Number :");
						Long phoneNumber = sc.nextLong();
						System.out.println("Password :");
						String password = sc.next();
						System.out.println("Add Money :");
						double wallet = sc.nextDouble();
						System.out.println("*Delivery Address");
						String Address = sc.nextLine();
						// sc.nextLine();
						User user = new User(id, firstName, lastName, gender, age, eMail, phoneNumber, password, wallet,
								Address);
						o.register(user);
						System.out.println("Registration Successful");
					}
					// ***************************************************************************
					// Login
					case 2: {
						
						boolean f1 = true;
						do {
							// login start
							System.out.println("*******Loging*******");
							System.out.println("eMail :");
							String eMail = sc.next();
							System.out.println("Password :");
							String password = sc.next();
							// check
							if (o.login(eMail, password)) {// successfully
								System.out.println("Login Successfully");
								boolean f2 = true;
								do {
									System.out.println("********Available Gadgets********");
									o.print();
									System.out.println(" 1.BUY \n 2.View Bag \n 3.Your Orders \n 4.LogOut");
									int c = sc.nextInt();
									double salePrice = 0.0;
									
									switch (c) {
									// BUY
									case 1: {
										double s = 0;
										boolean f3 = true;
										do {
											System.out.println("********Available Gadgets********");
											o.print();
											System.out.println(" Add To Cart by serial number   \n 0.To Exit:");
											int product_Sno = sc.nextInt();
											if (product_Sno == 0) {
												f3 = false;
												break;
											}
											cart.add(o.cart(product_Sno));
											size = cart.size();
											System.out.println("Bag("+(cart.size())+")"+cart);
											System.out.println("_______________________________________________________________________________");

											double total = 0.0;
											for (Gadgets g : cart) {
												salePrice = g.getSalePrice();
												total =total+salePrice;
											}
											
											System.out.println("Total Amount :"+total);
											if (salePrice == 0.0) {
												System.out.println("Product not avoilable");
												f2 = false;
											}
											System.out.println(
													" 1.Add More \n 2.proceed to Payment \n 3.clear All \n 4.Back");
											int l = sc.nextInt();
											//add more
											if (l == 1) {
												continue;
											}
											//proceed to pay
											else if (l == 2) {
												double wallet = o.wallet(eMail, password);
												System.out.println(" Available Balance =" + wallet);
												if (wallet < total) {
													System.out.println(" insuffcient balance \n 1.Add Money(" + -(total - wallet)+")\n 2.Back");
													int n = sc.nextInt();
													if (n == 1) {
														System.out.println("Enter Money");
														double add = sc.nextDouble();
														o.walletUpdate(wallet+add, eMail, password);
														System.out.println(" Available Balance =" + o.wallet(eMail, password));
														wallet=o.wallet(eMail, password);
													} else {
														f3 = false;
													}
												}
												System.out.println("Conform your address :");
												String Address1 = o.Address(eMail, password);
												System.out.println(Address1);
												System.out.println(" 1.Add Address \n 2.Proceed to Pay");
												int a = sc.nextInt();
												switch (a) {
												case 1:{
													System.out.println("New Address =");
													String Address = sc.next();
													o.AddressUpdate(Address, eMail, password);
												}
												case 2: {
													double newwallet = wallet - total;
													o.walletUpdate(newwallet, eMail, password);
													System.out.println("Payment Successfully Completed");
													System.out.println("Your Order has been placed Successfully");
													for(int i=0,j=1;i<cart.size();i++) {
														Gadgets g=cart.get(i);
														if(orders.containsKey(g)) {
															orders.replace(g, j++);
														}
														else {
															orders.put(cart.get(i),1);
														}
													}
													System.out.println("Orders :"+orders);
													System.out.println();
													cart.clear();
													f3=false;
													
												}break;
												default:{ 
													System.out.println("Enter the Right option");
													f3 = false;
												}
												
											}
											}
											//clear all
											else if(l==3) {
												cart.clear();
												salePrice = 0.0;
												total = 0.0;
												f3=false;	
											}
											//logout
											else if(l==4) {
												f3=false;
											break;
											}
											else {
												f2=false;
												System.out.println("Enter the Right Option");
											}

										}

										while (f3);
										{

										}
									}
										break;
									// view bag
									case 2: {
										System.out.println(cart);
										System.out.println(" 1.Remove \n 2.Proceed to Pay \n 3.Back");
										int r=sc.nextInt();
										if(r==1) {
											System.out.println("Enter Deleted Product index value :");
											int sno=sc.nextInt();
											cart.remove(sno);
										}
										else if(r==2){
											double wallet = o.wallet(eMail, password);
											double total = 0.0;
											for (Gadgets g : cart) {
												salePrice = g.getSalePrice();
												total =total+salePrice;
											}
											double newwallet = wallet - total;
											o.walletUpdate(newwallet, eMail, password);
											System.out.println("Payment Successfully Completed");
											System.out.println("Your Order has been placed Successfully");
											for(int i=0,j=1;i<cart.size();i++) {
												Gadgets g=cart.get(i);
												if(orders.containsKey(g)) {
													orders.replace(g, j++);
												}
												else {
													orders.put(cart.get(i),1);
												}
											}
											System.out.println("Orders :"+orders);
											System.out.println();
											cart.clear();
											continue;
										}
										else {
											
											System.out.println("Enter the Right Option");
											continue;
										}

									}
										break;
									// your orders
									case 3: {
										System.out.println(orders);
										System.out.println("1.Back");
										int a=sc.nextInt();
										if(a==1) {
											continue;
										}
										

									}
										break;
									// Logout
									case 4: {
										f2 = false;

									}break;
									}
								} while (f2);
								{

								}

							} else {
								System.out.println("Check Email and Password (or) Register now");
								f1 = false;
							}
						} while (f1);
						{

						}
					}
						break;
					// *****************************************************************************
					// Back
					case 3: {
						e1 = false;
					}
						break;
					}
				} while (e1);
				{

				}
				System.out.println("-:Thank you Visit Again:-");

			}
				break;
//**************************************************************************************************
//---------------------------------------------------------------------------------------------------
//Gadgets 
			case 2: {
				boolean e2 = true;
				do {
					System.out.println(" 1.Save \n 2.Update \n 3.Delete \n 4.PrintAll \n 5.back \n Enter your option");
					int choice2 = sc.nextInt();

					switch (choice2) {
					// ####################################################################################
					// Save
					case 1: {
						List<Gadgets> gadgets = new ArrayList<Gadgets>();
						boolean loop = true;
						do {
							System.out.println("SNo :");
							int sno = sc.nextInt();
							System.out.println("Name :");
							String name = sc.next();
							System.out.println("Company Name :");
							String companyName = sc.next();
							System.out.println("Model Number :");
							int modelNumber = sc.nextInt();
							System.out.println("MRP price :");
							double MRP = sc.nextDouble();
							System.out.println("Discount :");
							double discount = sc.nextDouble();
							double salePrice = MRP - discount;
							Gadgets g = new Gadgets(sno, name, companyName, modelNumber, MRP, discount, salePrice);
							gadgets.add(g);
							System.out.println(" 1.Add More Gadgets  \n 2.Uplod ");
							int n = sc.nextInt();
							if (n == 1) {
								continue;
							} else {
								loop = false;
							}
						} while (loop);
						{
							System.out.println("Data saved");
						}
						o.save(gadgets);
					}
						break;
					// #######################################################################################
					// Update
					case 2: {
						List<Gadgets> update = new ArrayList<Gadgets>();
						boolean loop = true;
						do {
							System.out.println("Enter new Discount :");
							double discount = sc.nextDouble();
							System.out.println("Enter Gadgent s.number");
							int sno = sc.nextInt();
							System.out.println("Enter Gadgent Name");
							String name = sc.next();
							Gadgets g = new Gadgets(discount, sno, name);
							update.add(g);
							System.out.println(" 1.Update more Gadgets  \n 2.Uplod ");
							int n = sc.nextInt();
							if (n == 1) {
								continue;
							} else {
								loop = false;
							}
							o.update(update);
						} while (loop);
						{
							System.out.println("your data updated");
						}
					}
						break;
					// ############################################################################
					// Delete
					case 3: {
						List<Gadgets> delete = new ArrayList<Gadgets>();
						boolean loop = true;
						do {
							System.out.println("Enter Gadgent sno :");
							int sno = sc.nextInt();
							System.out.println("Enter Gadgent Name");
							String name = sc.next();

							Gadgets g = new Gadgets(sno, name);
							delete.add(g);
							System.out.println(" 1.Add more Gadgets to Delete  \n 2.Delete ");
							int n = sc.nextInt();
							if (n == 1) {
								continue;
							} else {
								loop = false;
							}
							o.delete(delete);
						} while (loop);
						{
							System.out.println("data Deleted");

						}
					}
						break;
					// #################################################################################
					// PrintAll
					case 4: {
						o.print();
						System.out.println("data printed");
					}
						break;
					// ############################################################################
					// back
					case 5: {
						e2 = false;
					}
						break;
					}
				} while (e2);
				{

				}

			}
				break;
//##############################################################################################			
//--------------------------------------------------------------------------------------------			
//Exit
			case 3: {
				e = false;
			}
				break;
			}
//End------------------------------------------------------------------------------------------		
		} while (e);
		{
			System.out.println("-:Thank you Visit Again:-");
		}

	}

}
