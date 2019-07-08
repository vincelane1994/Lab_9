import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Lab9Method {
	static Scanner scnr = new Scanner(System.in);

	public static void main(String[] args) {
//		System.out.printf("%-15s  %-1s $ %-1d", "cow", "moon", 2);
		ArrayList<String> orderedItems = new ArrayList<>();
		ArrayList<Double> orderedPrices = new ArrayList<>();
		ArrayList<Integer> quantity = new ArrayList<>();

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
		for (Map.Entry<String, Double> my : menuItems.entrySet()) {
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
				int itemChoice = scnr.nextInt();
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
					System.out.println(
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
						scnr.nextLine();
						break;
					} catch (NumberFormatException ex) {
						System.out.println("Please enter a valid ammount");
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
		} while (doAgain(scnr, "Would you like to add another item to your cart (Y/N)?"));

		double subtotal = subtotal(orderedPrices, quantity);
		double tax = (subtotal * 0.07);
		double total = (subtotal + tax);
		System.out.println("Subtotal: " + defaultFormat.format(subtotal));
		System.out.println("Tax: " + defaultFormat.format(tax));
		System.out.println("Total: " + defaultFormat.format(total));

		System.out.println("Most expensive item: " + mostExpensive(orderedItems, orderedPrices, quantity));
		System.out.println("Least expensive item: " + leastExpensive(orderedItems, orderedPrices, quantity));
		System.out.println(
				"The average price per item: " + (defaultFormat.format(avgCost(orderedPrices, quantity, subtotal))));

	}

	public static boolean doAgain(Scanner scnr, String prompt) {
		boolean isValid = false;
		boolean decision = false;
		do {
			System.out.println(prompt);
			String userInput = scnr.nextLine();
			if (userInput.matches("[yYnN][eEoO]{0,1}[sS]{0,1}")) {
				isValid = true;
				if (userInput.equalsIgnoreCase("y") || userInput.equalsIgnoreCase("yes")) {
					decision = true;
					isValid = true;
				} else if (userInput.equalsIgnoreCase("n") || userInput.equalsIgnoreCase("no")) {
					decision = false;
					isValid = true;
				}
			} else {
				isValid = false;
				System.out.println("\"" + userInput + "\""
						+ " is not a valid option. You can type \"Y\" or \"Yes\" to continue or  \"N\" and \"No\" to exit");
			}
			// System.out.println(isValid);
		} while (!isValid);

		return decision;

	}

	public static boolean isYORN(Scanner scnr, String userInput) {
		boolean yorn = false;
		boolean isValid = false;
		do {
			if (userInput.equalsIgnoreCase("y") || userInput.equalsIgnoreCase("yes")) {
				yorn = true;
				isValid = true;
			} else if (userInput.equalsIgnoreCase("n") || userInput.equalsIgnoreCase("no")) {
				yorn = false;
				isValid = true;
			}

		} while (!isValid);
		return yorn;
	}

	public static double subtotal(ArrayList<Double> orderedPrices, ArrayList<Integer> quantity) {
		double sum = 0;
		for (int i = 0; i < orderedPrices.size(); i++) {
			sum = sum + (orderedPrices.get(i) * quantity.get(i));
		}
		return sum;

	}

	public static String mostExpensive(ArrayList<String> orderedItems, ArrayList<Double> orderedPrices,
			ArrayList<Integer> amount) {
		String most = null;
		double highest = 0;
		for (int i = 0; i < orderedPrices.size(); i++) {
			if ((orderedPrices.get(i) / amount.get(i)) > highest) {
				highest = (orderedPrices.get(i) / amount.get(i));
				most = orderedItems.get(i);
			}
		}
		return most;
	}

	public static String leastExpensive(ArrayList<String> orderedItems, ArrayList<Double> orderedPrices,
			ArrayList<Integer> amount) {
		String least = null;
		double lowest = 99999999999999.99;
		for (int i = 0; i < orderedPrices.size(); i++) {
			if ((orderedPrices.get(i) / amount.get(i)) < lowest) {
				lowest = (orderedPrices.get(i) / amount.get(i));
				least = orderedItems.get(i);
			}
		}
		return least;
	}

	public static double avgCost(ArrayList<Double> orderedPrices, ArrayList<Integer> quantity, double subtotal) {
		double avg = 0.0;
		int sumAmount = 0;

		for (int i = 0; i < quantity.size(); i++) {
			sumAmount = sumAmount + quantity.get(i);
		}
		avg = (subtotal / sumAmount);
		return avg;

	}
}
