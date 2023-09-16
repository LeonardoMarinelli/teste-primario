package com.springboot;

import com.springboot.Controllers.Generic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class MenuController {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(MenuController.class, args);
		Thread.sleep(3000);
		init();
	}

	private static void init() throws InterruptedException {
		String[] options = {"Pesquisar drink por nome", "Pesquisar drink pela primeira letra",
				"Pesquisar ingrediente por nome", "Drink aleatório", "Sair do programa"};

		System.out.println("========================");
		System.out.println("JOE'S BAR");

		Scanner scanner = new Scanner(System.in);

		while (true) {
			int choice = 0;

			for (int i = 0; i < options.length; i++) {
				System.out.println((i + 1) + " - " + options[i]);
			}

			System.out.println("------------------------");
			System.out.print("Escolha uma opção (1-5): ");

			if (scanner.hasNextInt()) {
				choice = scanner.nextInt();
				if (choice >= 1 && choice <= 5) {
					switch (choice) {
						case 1:
							Generic.getGenericForDrinks(String.valueOf(choice));
							break;
						case 2:
							System.out.println("Opção em construção.");
							break;
						case 3:
							Generic.getIngredient();
							break;
						case 4:
							Generic.getIngredient();
							break;
						case 5:
							System.out.println("Obrigado por utilizar nosso sistema!\nSaindo do programa...");
							break;
					}
					if (choice == 5) {
						break;
					}
				} else {
					System.out.println("Opção inválida. Por favor, escolha uma opção de 1 a 5.");
				}
			} else {
				System.out.println("Entrada inválida. Por favor, insira um número de 1 a 5.");
			}
		}

		scanner.close();
	}
}