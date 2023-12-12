package busmanagementsystem;
import java.sql.*;
public class User extends ConnectJDBC {

	public void startUserTasks() {
		setconnection(); 
		userMenu();
	}

	public void userMenu() {
		int cuschoice;
		do {
			System.out.println("----------USER OPTIONS------------");
			System.out.println("|    |1| ADD CUSTOMER          ");
			System.out.println("|    |2| DELETE CUSTOMER       ");
			System.out.println("|    |3| DISPLAY CUSTOMER      ");
			System.out.println("|    |4| EXIT                  ");
			System.out.println("----------------------------------");
			System.out.println("Enter your choice");
			cuschoice = sc.nextInt();

			if (cuschoice == 1)
				booknewticket();
			else if (cuschoice == 2)
				deletecustomer();
			else if (cuschoice == 3)
				displaycustomer();
			else
				break;

		} while (cuschoice != 4);
	}

	public void booknewticket() {
		System.out.println("-----NEW TICKET-----");
		System.out.print("Enter the CUSTOMER NAME        :");
		String cusname = sc.next();
		System.out.print("Enter your phone_number        :");
		String phone = sc.next();
		System.out.print("Enter your Email_Address       :");
		String mail = sc.next();
		System.out.print("Enter your source point        :");
		String cus_source = sc.next();
		System.out.print("Enter your destination point   :");
		String cus_destination = sc.next();
		try {
			int count = 0, price = 0;
			ResultSet sr = stmt.executeQuery("select BUS_NAME from bus_details where BUS_SOURCE='" + cus_source
					+ "' AND BUS_DESTINATION='" + cus_destination + "'");
			String buschoosen = "";
			int noofseat = 0;
			int perTicketPrice = 0;

			while (sr.next()) {
				count++;
			}
			if (count != 0) {
				try {
					ResultSet rs = stmt.executeQuery("select * from bus_details where BUS_SOURCE='" + cus_source
							+ "' AND BUS_DESTINATION='" + cus_destination + "'");
					while (rs.next()) {
						String s1 = rs.getString("BUS_NAME");
						String s2 = rs.getString("BUS_SOURCE");
						String s3 = rs.getString("BUS_DESTINATION");
						String s4 = rs.getString("ARRIVE_TIME");
						String s5 = rs.getString("TRAVEL_DURATION");
						int s6 = rs.getInt("SEAT_AVAILABLE");
						int s7 = rs.getInt("PER_TICKET_PRICE");
						perTicketPrice = s7;
						System.out.println("BUS_NAME:" + s1 + "---BUS_SOURCE:" + s2 + "---BUS_DESTINATION:" + s3
								+ "---ARRIVE_TIME:" + s4 + "---TRAVEL_DUR:" + s5 + "---SEAT AVAILABLE:" + s6
								+ "---PER_TICKET_PRICE:" + s7);
						sc.nextLine();
						System.out.println("Enter your choosed bus name  :");
						buschoosen = sc.nextLine();
						System.out.println("Enter no of seats you required :");
						noofseat = sc.nextInt();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("BUS NOT AVAILABLE NOW!!!");
			}
			try {
				String sql = "UPDATE bus_details " +
						"SET SEAT_AVAILABLE = SEAT_AVAILABLE - '" + noofseat + "' "
						+ "WHERE BUS_NAME = '" + buschoosen + "'";
				stmt.executeUpdate(sql);
				 price = noofseat * perTicketPrice;
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("*****Payment Page*****");
			System.out.print("Enter your CARD_NO                   :");
			String cardno = sc.next();
			System.out.print("Enter your CARD_CCV                  :");
			String cardccv = sc.next();
			System.out.print("Enter your CARD_EXP date (DD-MM-YYYY):");
			String carddate = sc.next();
			try {
				String sql = "INSERT INTO user_details VALUES('" + cusname + "','" + phone + "','" + mail + "','"
						+ cus_source + "','" + cus_destination + "','" + noofseat + "','" + price + "','" + buschoosen
						+ "','" + cardno + "','" + cardccv + "','" + carddate + "')";
				stmt.executeUpdate(sql);
				System.out.println("Customer records inserted into the table...");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deletecustomer() {
		System.out.println("-----------------Customer Delete Page-----------------");
		sc.nextLine();
		System.out.print("Enter the customer name that you need to delete  :");
		String cus_name = sc.nextLine().trim();
		try {
			stmt.executeUpdate("delete from user_details where CUSTOMER_NAME = '" + cus_name + "'");
			System.out.println("CUSTOMER Deleted from the table...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void displaycustomer() {
		System.out.println("-----------------Display Page-----------------");
		sc.nextLine(); 
		try {
			System.out.println("Enter the Customer name: ");
			String customername = sc.nextLine().trim();
			int count = 0;
			ResultSet r = stmt
					.executeQuery("select CUSTOMER_NAME from user_details where CUSTOMER_NAME ='" + customername + "'");
			while (r.next()) {
				count++;
			}
			if (count != 0) {
				ResultSet rs = stmt
						.executeQuery("select * from user_details where CUSTOMER_NAME ='" + customername + "'");
				while (rs.next()) {
					String s1 = rs.getString("CUSTOMER_NAME");
					String s2 = rs.getString("PHONE_NUMBER");
					String s3 = rs.getString("EMAIL_ID");
					String s4 = rs.getString("CUS_SOURCE");
					String s5 = rs.getString("CUS_DESTINATION");
					int s6 = rs.getInt("SEAT_REQUIRED");
					int s7 = rs.getInt("PRICE");
					String s8 = rs.getString("BUS_NAME");
					String s9 = rs.getString("CARD_NO");
					String s10 = rs.getString("CARD_CCV");
					String s11 = rs.getString("CARD_EXP_DATE");
					System.out.println("-----CUSTOMER DETAILS-----");
					System.out.println("|CUSTOMER NAME        : " + s1);
					System.out.println("|PHONE NUMBER         : " + s2);
					System.out.println("|EMAIL ADDRESS        : " + s3);
					System.out.println("|CUSTOMER SOURCE      : " + s4);
					System.out.println("|CUSTOMER DESTINATION : " + s5);
					System.out.println("|NO OF SEAT BOOKED    : " + s6);
					System.out.println("|TOTAL PRICE          : " + s7);
					System.out.println("|BUS NAME             : " + s8);
					System.out.println("|CARD NO              : " + s9);
					System.out.println("|CARD CVV             : " + s10);
					System.out.println("|CARD DATE            : " + s11);
				}
			} else {
				System.out.println("CUSTOMER NAME IS INVALID!!!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
