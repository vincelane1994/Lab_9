import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Lab9 {
	static Scanner scnr = new Scanner(System.in);

	public static void main(String[] args) {
//		System.out.printf("%-15s  %-1s $ %-1d", "cow", "moon", 2);
		ArrayList<String> orderedItems = new ArrayList<>();
		ArrayList<Double> orderedPrices = new ArrayList<>();
		ArrayList<Integer> quantity = new ArrayList<>();
		int itemChoice = 0;

		Map<String, Double> menuItems = new HashMap<>();
		menuItems.put("Apple", 0.99);
		menuItems.put("Banana", 0.59);
		menuItems.put("Cauliflower", 1.59);
		menuItems.put("Elderberry", 1.79);
		menuItems.put("Dragonfruit", 2.19);
		menuItems.put("Figs", 2.09);
		menuItems.put("Grapefruit", 1.99);
		menuItems.put("Honeydew", 3.49);

		NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
		System.out.println("Welcome to Grand Circus's Market!");
		System.out.printf("%-16s %-1s\n", "Item", "Prices");
		System.out.println("=======================");
		int i = 0;
		for (Map.Entry<String, Double> my : menuItems.entrySet()) {// Prints out menu.
			System.out.printf("%-2s %-15s  %-1s %-1s\n", (i + 1) + ".", my.getKey(), "$", my.getValue());
			i++;
		}
		do {
			String selection = "";
			int amount = 0;
			double price = 0.00;
			boolean isValid;
			do {
				System.out.println("");
				System.out.print("Enter the nubmer of the item you would like to add to your cart (1-8): ");
				try { 
					itemChoice = scnr.nextInt();
				} catch (InputMismatchException | NumberFormatException ex) {
					System.out.println("That item does not exist. Please try again.");
					isValid = false;
					scnr.nextLine();
					continue;
				}
				if (itemChoice == 1) {
					selection = "Apple";
				} else if (itemChoice == 2) {
					selection = "Dragonfruit";
				} else if (itemChoice == 3) {
					selection = "Cauliflower";
				} else if (itemChoice == 4) {
					selection = "Grapefruit";
				} else if (itemChoice == 5) {
					selection = "Elderberry";
				} else if (itemChoice == 6) {
					selection = "Figs";
				} else if (itemChoice == 7) {
					selection = "Honeydew";
				} else if (itemChoice == 8) {
					selection = "Banana";
				} else {
					System.out.println(// Prints out if the user types in something that doesn't exist.
							"That item does not exist. Please type in the number next to the item you wish to purchase.");
					isValid = false;
					continue;
				}

				scnr.nextLine();

				if (menuItems.get(selection) != null) {
					isValid = true;
				} else {
					System.out.println("That item does not exist. Please try again.");
					isValid = false;
					continue;
				}

				do {
					System.out.println("");
					System.out.print("Amount of " + selection + "(s) to add to your cart?: ");
					try {
						amount = scnr.nextInt();
						if (amount < 0) {
							isValid = false;
							continue;
						}
						scnr.nextLine();
						break;
					} catch (NumberFormatException | InputMismatchException ex) {
						System.out.println("Please enter a valid ammount");
						scnr.nextLine();
						isValid = false;
						continue;
					}

				} while (!isValid);
			} while (!isValid);
			System.out.println("");
			System.out.println(
					"Adding " + amount + " " + selection + "(s) " + defaultFormat.format(menuItems.get(selection))
							+ " each, for " + defaultFormat.format((menuItems.get(selection) * amount)));
			System.out.println("");
			price = (menuItems.get(selection));
			orderedItems.add(selection);
			orderedPrices.add(menuItems.get(selection));
			quantity.add(amount);
			System.out.println("Cart so far:");
			for (i = 0; i < orderedItems.size(); i++) {
				System.out.printf("%-16s %-10s\n", orderedItems.get(i) + "(" + quantity.get(i) + ")",
						(defaultFormat.format(orderedPrices.get(i) * quantity.get(i))));
			}
			System.out.println("");
		} while (Lab9Method.doAgain(scnr, "Would you like to add another item to your cart (Y/N)?"));

		double subtotal = Lab9Method.subtotal(orderedPrices, quantity);
		double tax = (subtotal * 0.07);
		double total = (subtotal + tax);
		System.out.println("Subtotal: " + defaultFormat.format(subtotal));
		System.out.println("Tax: " + defaultFormat.format(tax));
		System.out.println("Total: " + defaultFormat.format(total));

		System.out.println("Most expensive item: " + Lab9Method.mostExpensive(orderedItems, orderedPrices, quantity));
		System.out.println("Least expensive item: " + Lab9Method.leastExpensive(orderedItems, orderedPrices, quantity));
		System.out.println("The average price per item: "
				+ (defaultFormat.format(Lab9Method.avgCost(orderedPrices, quantity, subtotal))));

	}
}
