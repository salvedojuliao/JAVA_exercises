package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import entities.Contract;
import entities.Installment;
import services.ContractService;
import services.PaypalService;


public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
	
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		System.out.println("Entre com os dados do contrato: ");
		System.out.print("Número: ");
		int number = sc.nextInt();
		System.out.print("Data (dd/MM/yyyy): ");
		LocalDate date = LocalDate.parse(sc.next(), fmt);
		System.out.print("Valor do contrato: ");
		double totalValue = sc.nextDouble();
		
		Contract contract = new Contract(number, date, totalValue);
		
		System.out.print("Entre com o número de parcelas: ");
		int installmentsAmount = sc.nextInt();
		
		System.out.println("Parcelas:");
		
		ContractService contractService = new ContractService(new PaypalService());
		
		contractService.processContract(contract, installmentsAmount);
		
		for (Installment installment : contract.getInstallments()) {
			System.out.println(installment);
		}
		
		sc.close();

	}

}
