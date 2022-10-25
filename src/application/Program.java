package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Reservation;

public class Program {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.print("Room number: ");
		int number = sc.nextInt();
		sc.nextLine();
		System.out.print("Check-in date (dd/MM/yyyy): ");
		Date checkIn = sdf.parse(sc.next());
		//vou colocar a data como um texto e o sdf vai converter em data
		System.out.print("Check-out date (dd/MM/yyyy): ");
		Date checkOut = sdf.parse(sc.next());
		
		if (!checkOut.after(checkIn)) {
		//NÃO se a saida for depois da entrada(o ! inverte o resultado por isso vira NÃO)
			System.out.println("Error in reservation: Check-out date must be after check-in date");			
		}
		else {
			Reservation reservation = new Reservation(number, checkIn, checkOut);
			System.out.println("Reservation: " +reservation);
			
			System.out.println();
			System.out.println("Enter data to update the reservation: ");
			
			System.out.print("Check-in date (dd/MM/yyyy): ");
			checkIn = sdf.parse(sc.next());

			System.out.print("Check-out date (dd/MM/yyyy): ");
			checkOut = sdf.parse(sc.next());
			
			Date now = new Date();
			//cria uma variavel com a data de AGORA 
			if(checkIn.before(now) || checkOut.before(now)) {
		//SE O CHECKIN FOR ANTES DE AGORA OU O CHECKOUT FOR ANTES DE AGORA VAI DAR ERRO		
				System.out.println("Error in reservation: Reservation dates for update must be future dates");
			}
			else if (! checkOut.after(checkIn)) {
				System.out.println("Error in reservation: Check-out date must be after check-in date");			
			}			
			else {
			reservation.updateDates(checkIn, checkOut);			
			System.out.println("Reservation: " +reservation);
		}
	}
		sc.close();
	}
}
