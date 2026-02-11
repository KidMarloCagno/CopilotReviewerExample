import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reservation {
    private int reservationId;
    private Room room;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String guestName;
    private double totalPrice;

    public Reservation(int reservationId, Room room, LocalDate checkInDate, LocalDate checkOutDate, String guestName) {
        this.reservationId = reservationId;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.guestName = guestName;
        this.totalPrice = calculatePrice();
    }

    public double calculatePrice() {
        long nights = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
        if (nights < 0) nights = 0;
        return nights * room.getPricePerNight();
    }

    public int getReservationId() { return reservationId; }
    public Room getRoom() { return room; }
    public LocalDate getCheckInDate() { return checkInDate; }
    public LocalDate getCheckOutDate() { return checkOutDate; }
    public String getGuestName() { return guestName; }
    public double getTotalPrice() { return totalPrice; }

    @Override
    public String toString() {
        return "Reservación #" + reservationId + 
               " | Huésped: " + guestName + 
               " | Habitación: " + room.getRoomNumber() + 
               " | Check-in: " + checkInDate + 
               " | Check-out: " + checkOutDate + 
               " | Total: $" + String.format("%.2f", totalPrice);
    }
}
