package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.entities.Reservation;

public class Program {

	public static void main(String[] args) throws ParseException{
		//Metódo muito ruim
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.print("Room number: ");
		int number = sc.nextInt();
		System.out.print("Checck-in date (dd/MM/yyyy): ");
		Date checkIn = sdf.parse(sc.next());
		System.out.print("Checck-out date (dd/MM/yyyy): ");
		Date checkOut = sdf.parse(sc.next());
		
		if(!checkOut.after(checkIn)) {
			System.out.println("Error in reservation: Check-out date must be after check-in date.");
		}else {
			Reservation reservation = new Reservation(number, checkIn, checkOut);
			System.out.println("Reservation: "+reservation);
		
			System.out.println();
			System.out.println("Enter data to update the reservation: ");
			System.out.print("Checck-in date (dd/MM/yyyy): ");
			checkIn = sdf.parse(sc.next());
			System.out.print("Checck-out date (dd/MM/yyyy): ");
			checkOut = sdf.parse(sc.next());
			
			String erro = reservation.updateDates(checkIn, checkOut);
			if(erro==null) {
				System.out.println("Reservation: "+reservation);
			}else {
				System.out.println("Reservation: "+erro);
			}
			
			System.out.println("Fim do programa!");
			
		}

		sc.close();
	}

}
