import java.util.*;

public class Program {
	
	private static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		int id,option = 6;
		do {
			printOptions();
			option = sc.nextInt();
			
			switch(option) {
				case 1:
					ArrayList<Product> temp = ProductDAO.readAll();
					for(Product i: temp) {
						System.out.print(i);
					}
					System.out.println("-----------------");
					break;
				case 2:
					System.out.print("Enter product id: ");
					id = sc.nextInt();
					ArrayList<Product> temp1 = ProductDAO.findProduct(id);
					for(Product i: temp1) {
						System.out.print(i);
					}
					System.out.println("-----------------");
					break;
				case 3:
					ProductDAO.addProduct();
					System.out.println("-----------------");
					break;
				case 4:
					System.out.print("Enter product id: ");
					id = sc.nextInt();
					ProductDAO.updateProduct(id);
					System.out.println("-----------------");
					break;
				case 5:
					System.out.print("Enter product id: ");
					id = sc.nextInt();
					ProductDAO.deleteProduct(id);
					System.out.println("-----------------");
					break;
				case 6:
					System.out.println("Goodbye!!!");
					break;
			}
		} while(option != 6);

	}
	
	private static void printOptions() {
		System.out.println("1. Read all product");
		System.out.println("2. Read detail of a product by id");
		System.out.println("3. Add a new product");
		System.out.println("4. Update a product");
		System.out.println("5. Delete a product by id");
		System.out.println("6. Exit\n");
		System.out.println("Your choice: ");
	}
	
}
