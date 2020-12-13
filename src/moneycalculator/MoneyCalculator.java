package moneycalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class MoneyCalculator {
    double amount;
    double exchangerate;
    
    public static void main(String[] args) throws IOException {
        MoneyCalculator moneycalculator = new MoneyCalculator();
        moneycalculator.control();
    }

    private static double getExchangeRate(String from, String to) throws IOException {
        URL url = 
            new URL("http://free.currencyconverterapi.com/api/v5/convert?q=" +
                    from + "_" + to + "&compact=y&apiKey=f0fe48bb7e7faed3bae5");
        URLConnection connection = url.openConnection();
        try (BufferedReader reader = 
                new BufferedReader(
                        new InputStreamReader(connection.getInputStream()))) {
            String line = reader.readLine();
            String line1 = line.substring(line.indexOf(to)+12, line.indexOf("}"));
            return Double.parseDouble(line1);
        }
    }

    private void control() throws IOException {
        input();
        process();
        output();
    }

    private void input() {
        System.out.println("Introduce una cantidad de dólares: ");
        Scanner scanner = new Scanner(System.in);
        amount = scanner.nextDouble();
    }

    private void process() throws IOException {
        exchangerate = getExchangeRate("USD","EUR");
    }

    private void output() {
        System.out.println(amount + " $ = " + amount*exchangerate + " €");
    }
}