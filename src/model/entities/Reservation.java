package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {
	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;
	private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) {
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}
	
	public Integer getRoomNumber() {
		return roomNumber;
	}
	
	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}
	
	public Date getCheckIn() {
		return checkIn;
	}
	
	public Date getCheckOut() {
		return checkOut;
	}
	
	private long duration() {
		long diff = checkOut.getTime()-checkIn.getTime();
		return TimeUnit.DAYS.convert(diff,TimeUnit.MILLISECONDS);
	}
	
	//Retorna o erro de lógica do sistema
	//Se retornar não retornar null quer dizer que houve um erro
	public void updateDates(Date checkIn,Date checkOut) {
		
		Date now = new Date();
		if(checkIn.before(now) || checkOut.before(now)) {
			throw new IllegalArgumentException("Error in reservation: dates for update must be future dates.");
		}
		if(!checkOut.after(checkIn)){
			throw new IllegalArgumentException("Error in reservation: Check-out date must be after check-in date.");
		}
		
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Room "+roomNumber);
		sb.append(", check-in: "+ sdf.format(checkIn));
		sb.append("check-out: "+sdf.format(checkOut));
		sb.append(", "+duration()+" nights");
		return sb.toString();
	}
}
