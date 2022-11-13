package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

public class Reservation {
	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) {
		if (!checkOut.after(checkIn)) {
			throw new DomainException("Check-out date must be after check-in date");
		}		
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

	public long duration() {
		long diff = checkOut.getTime() - checkIn.getTime();
		// esse codigo acima pega a diferença das datas em milisegundos
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		// converte o valor em milisegundos para DIAS
	}

	public void updateDates(Date checkIn, Date checkOut) {	
		Date now = new Date();
		// cria uma variavel com a data de AGORA
		if (checkIn.before(now) || checkOut.before(now)) {
			// SE O CHECKIN FOR ANTES DE AGORA OU O CHECKOUT FOR ANTES DE AGORA VAI DAR ERRO
			throw new DomainException("Reservation dates for update must be future dates");
		} 
		if (!checkOut.after(checkIn)) {
			throw new DomainException ("Check-out date must be after check-in date");
		} 		
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	@Override
	public String toString() {
		return "Room " + roomNumber + ", check-in: " + sdf.format(checkIn) + ", check-out: " + sdf.format(checkOut)
				+ ", " + duration() + " nights";
	}

}