import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductDAO {
	
	private static Scanner sc = new Scanner(System.in);
	
	private final static String userName = "root";
	private final static String password = "";
	
	private final static String url = "jdbc:mysql://localhost:3306/productmanagement";
	public static Connection connect() {
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url,userName,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	public static ArrayList<Product> readAll() {
		ArrayList<Product> list = new ArrayList<Product>();
		String sql = "SELECT * FROM Product";
		try {
			Connection conn = connect();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				list.add(new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price")));
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return list;
	}
	
	public static ArrayList<Product> findProduct(int id) {
		ArrayList<Product> list = new ArrayList<Product>();
		String sql = "SELECT * FROM Product WHERE Product.id ="+ id;
		try {
			Connection conn = connect();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				list.add(new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price")));
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return list;
	}
	
	public static void addProduct() {
		
		System.out.print("Enter ID: ");
		int id = sc.nextInt();
		sc.nextLine();
		System.out.print("Enter name: ");
		String name = sc.nextLine();
		System.out.print("Enter price: ");
		double price = sc.nextDouble();
		
		String sql = "INSERT INTO product(id,name,price) VALUES("+id+",'"+name+"',"+price+")";
		try {
			Connection conn = connect();
			Statement st = conn.createStatement();

			st.execute(sql);
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public static void updateProduct(int id) {
		String sql = "UPDATE Product SET name = ?, price = ? WHERE Product.id ="+ id;
		try {
			Connection conn = connect();
			
			PreparedStatement st = conn.prepareStatement(sql);
			
			System.out.print("Enter name: ");
			String name = sc.nextLine();
			System.out.print("Enter price: ");
			double price = sc.nextDouble();
			
			st.setString(1,name);
			st.setDouble(2, price);
			
			st.execute(sql);
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public static void deleteProduct(int id) {
		String sql = "DELETE FROM Product WHERE Product.id ="+ id;
		try {
			Connection conn = connect();
			if(findProduct(id) != null) {
				Statement st = conn.createStatement();
				st.execute(sql);
			} else System.out.println("Product not found!");
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
}
