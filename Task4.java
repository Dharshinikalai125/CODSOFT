import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;

public class Task4 {

    // Method to get exchange rate from API
    public static double getExchangeRate(String base, String target) throws Exception {
        String apiUrl = "https://api.exchangerate.host/latest?base=" + base + "&symbols=" + target;
        URL url = new URL(apiUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        int status = con.getResponseCode();
        if (status != 200) {
            throw new RuntimeException("Failed to fetch data. HTTP response code: " + status);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        JSONObject json = new JSONObject(response.toString());
        return json.getJSONObject("rates").getDouble(target);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Currency Converter ===");

        System.out.print("Enter base currency (e.g., USD): ");
        String base = scanner.nextLine().toUpperCase();

        System.out.print("Enter target currency (e.g., INR): ");
        String target = scanner.nextLine().toUpperCase();

        System.out.print("Enter amount in " + base + ": ");
        double amount = scanner.nextDouble();

        try {
            double rate = getExchangeRate(base, target);
            double result = amount * rate;

            System.out.printf("Exchange Rate: 1 %s = %.4f %s%n", base, rate, target);
            System.out.printf("Converted Amount: %.2f %s%n", result, target);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }
}

/*OUTPUT:

=== Currency Converter ===
Enter base currency (e.g., USD): USD
Enter target currency (e.g., INR): INR
Enter amount in USD: 100
Exchange Rate: 1 USD = 83.2400 INR
Converted Amount: 8324.00 INR */
