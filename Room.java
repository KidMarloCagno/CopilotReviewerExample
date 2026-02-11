public class Room {
    private int roomNumber;
    private String type; // "Single", "Double", "Suite"
    private int capacity;
    private double pricePerNight;
    private boolean available;

    public Room(int roomNumber, String type, int capacity, double pricePerNight) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.capacity = capacity;
        this.pricePerNight = pricePerNight;
        this.available = true;
    }

    public int getRoomNumber() { return roomNumber; }
    public String getType() { return type; }
    public int getCapacity() { return capacity; }
    public double getPricePerNight() { return pricePerNight; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    @Override
    public String toString() {
        return "Habitación #" + roomNumber + 
               " | Tipo: " + type + 
               " | Capacidad: " + capacity + 
               " | Precio: $" + String.format("%.2f", pricePerNight) + 
               " | Estado: " + (available ? "Disponible" : "Ocupada");
    }
}
