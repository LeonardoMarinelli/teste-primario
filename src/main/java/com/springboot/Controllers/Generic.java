package com.springboot.Controllers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.springboot.Entities.Drink;
import com.springboot.Utilities.JsonParser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

@Component
public class Generic {
    private static String apiUrl = "https://www.thecocktaildb.com/api/json/v1/1/";
    public static void getIngredient() {
        RestTemplate restTemplate = new RestTemplate();

        String newUrl = apiUrl + "list.php?i=list";
        ResponseEntity<String> response = restTemplate.getForEntity(newUrl, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();
            JsonArray parsed = JsonParser.Conversor(responseBody);

            List<String> genericList = new ArrayList<>();

            for (int i = 0; i < parsed.size(); i++) {
                JsonObject drinkObject = parsed.get(i).getAsJsonObject();
                String generic = drinkObject.get("strIngredient1").getAsString();
                genericList.add((i+1) + " - " + generic);
            }

            System.out.println("These are the ingredients available:");
            for (String s : genericList) {
                System.out.println(s);
            }

        } else {
            System.err.println("Offline API, please try again later.");
        }
    }

    public static void getGenericForDrinks(String parameter) throws InterruptedException {
        RestTemplate restTemplate = new RestTemplate();
        List<Drink> drinks = new ArrayList<>();


        String newUrl = apiUrl + "search.php?" + "s=";

        Scanner scanner = new Scanner(System.in);
        String choice = "";
        System.out.print("Insert the drink: ");
        choice = scanner.nextLine();

        newUrl = newUrl + choice;

        ResponseEntity<String> response = restTemplate.getForEntity(newUrl, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();
            JsonArray parsed = new JsonArray();

            try {
                parsed = JsonParser.Conversor(responseBody);
            } catch (Exception e) {
                System.err.println("Drink not found, please check the input and try again.");
                Thread.sleep(1000);
            }

            for (int i = 0; i < parsed.size(); i++) {
                List<String> ingredients = new ArrayList<>();
                JsonObject drinkObject = parsed.get(i).getAsJsonObject();

                Drink drink = new Drink();
                drink.setDrinkName(drinkObject.get("strDrink").getAsString());
                if (Objects.equals(drinkObject.get("strAlcoholic").getAsString(), "Alcoholic")) {
                    drink.setAlcoholic("Yes");
                } else {
                    drink.setAlcoholic("No");
                }

                drink.setGlass(drinkObject.get("strGlass").getAsString());
                drink.setInstructions(drinkObject.get("strInstructions").getAsString());
                drink.setLinkToDrinkIcon(drinkObject.get("strDrinkThumb").getAsString());
                for(int k = 1; k <= 15; k++) {
                    JsonElement ingredientElement = drinkObject.get("strIngredient" + k);
                    if (!ingredientElement.isJsonNull()) {
                        ingredients.add(drinkObject.get("strIngredient" + k).getAsString());
                    } else break;
                }
                drink.setIngredients(ingredients.toArray(new String[0]));

                drinks.add(drink);
            }

            while (true) {
                System.out.println("We found " + drinks.size() + " drinks with this name: ");
                int l = 1;
                for (Drink d : drinks) {
                    System.out.println(l + " - " + d.getDrinkName());
                    l++;
                }

                System.out.println("Select the drink (1 to " + drinks.size() + "): ");
                choice = scanner.nextLine();

                if (choice.length() > 1) {
                    System.out.print("Invalid input. Please, insert just one character. ");
                    scanner.next();
                } else if (Integer.parseInt(choice) <= 0 || Integer.parseInt(choice) > drinks.size()) {
                    System.out.print("Invalid input. Please, insert a number that is in the list. ");
                    scanner.next();
                } else break;
            }

            System.out.println(drinks.get(Integer.parseInt(choice) - 1).toString());

            System.out.println("Press ENTER to go back to the menu.");
            choice = scanner.nextLine();

        } else {
            System.err.println("Offline API, please try again later.");
        }
        scanner.close();
    }
}
