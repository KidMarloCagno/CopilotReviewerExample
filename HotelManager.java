import java.util.ArrayList;
import java.util.List;

public class HotelManager {
    private List<Room> rooms;
    private List<Reservation> reservations;
    private int nextReservationId;

    public HotelManager() {
        this.rooms = new ArrayList<>();
        this.reservations = new ArrayList<>();
        this.nextReservationId = 1;
        initializeRooms();
    }

    private void initializeRooms() {
        rooms.add(new Room(101, "Single", 1, 50.0));
        rooms.add(new Room(102, "Double", 2, 80.0));
        rooms.add(new Room(103, "Double", 2, 80.0));
        rooms.add(new Room(201, "Suite", 4, 150.0));
        rooms.add(new Room(202, "Single", 1, 50.0));
    }

    public void listAvailableRooms() {
        System.out.println("\n--- HABITACIONES DISPONIBLES ---");
        List<Room> available = getAvailableRooms();
        if (available.isEmpty()) {
            System.out.println("No hay habitaciones disponibles en este momento.");
        } else {
            available.forEach(System.out::println);
        }
    }

    private List<Room> getAvailableRooms() {
        List<Room> available = new ArrayList<>();
        for (Room room : rooms) if (room.isAvailable()) available.add(room);
        return available;
    }

    public boolean createRoom(int roomNumber, String type, int capacity, double pricePerNight) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                System.out.println("Error: Ya existe una habitación con el número " + roomNumber);
                return false;
            }
        }
        Room newRoom = new Room(roomNumber, type, capacity, pricePerNight);
        rooms.add(newRoom);
        System.out.println("Habitación creada exitosamente:");
        System.out.println(newRoom);
        return true;
    }

    public double calculateReservationPrice(int roomNumber, int nights) {
        Room room = findRoomByNumber(roomNumber);
        if (room == null) {
            System.out.println("Error: Habitación no encontrada");
            return -1;
        }
        double totalPrice = nights * room.getPricePerNight();
        System.out.println("\n--- CÁLCULO DE PRECIO ---");
        System.out.println("Habitación: #" + roomNumber);
        System.out.println("Tipo: " + room.getType());
        System.out.println("Noches: " + nights);
        System.out.println("Precio por noche: $" + String.format("%.2f", room.getPricePerNight()));
        System.out.println("TOTAL: $" + String.format("%.2f", totalPrice));
        return totalPrice;
    }

    public boolean makeReservation(int roomNumber, String guestName,
                                   int checkInDay, int checkInMonth, int checkInYear,
                                   int checkOutDay, int checkOutMonth, int checkOutYear) {
        Room room = findRoomByNumber(roomNumber);
        if (room == null) {
            System.out.println("Error: Habitación no encontrada");
            return false;
        }
        if (!room.isAvailable()) {
            System.out.println("Error: La habitación #" + roomNumber + " no está disponible");
            return false;
        }
        try {
            java.time.LocalDate checkIn = java.time.LocalDate.of(checkInYear, checkInMonth, checkInDay);
            java.time.LocalDate checkOut = java.time.LocalDate.of(checkOutYear, checkOutMonth, checkOutDay);
            if (checkOut.isBefore(checkIn) || checkOut.equals(checkIn)) {
                System.out.println("Error: La fecha de salida debe ser posterior a la de entrada");
                return false;
            }
            Reservation reservation = new Reservation(nextReservationId++, room, checkIn, checkOut, guestName);
            reservations.add(reservation);
            room.setAvailable(false);
            System.out.println("\n--- RESERVACIÓN CONFIRMADA ---");
            System.out.println(reservation);
            return true;
        } catch (java.time.DateTimeException e) {
            System.out.println("Error: Fecha inválida. Formato: DD/MM/YYYY");
            return false;
        }
    }

    private Room findRoomByNumber(int roomNumber) {
        for (Room room : rooms) if (room.getRoomNumber() == roomNumber) return room;
        return null;
    }

    public void listAllRooms() {
        System.out.println("\n--- TODAS LAS HABITACIONES ---");
        rooms.forEach(System.out::println);
    }

    public void listReservations() {
        System.out.println("\n--- RESERVACIONES ---");
        if (reservations.isEmpty()) System.out.println("No hay reservaciones registradas.");
        else reservations.forEach(System.out::println);
    }
}
