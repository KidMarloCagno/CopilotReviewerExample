import java.util.Scanner;

public class Main {
    private static HotelManager hotelManager;
    private static Scanner scanner;

    public static void main(String[] args) {
        hotelManager = new HotelManager();
        scanner = new Scanner(System.in);

        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("Selecciona una opción: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1: hotelManager.listAvailableRooms(); break;
                    case 2: createRoom(); break;
                    case 3: calculateReservationPrice(); break;
                    case 4: makeReservation(); break;
                    case 5: hotelManager.listAllRooms(); break;
                    case 6: hotelManager.listReservations(); break;
                    case 7: running = false; System.out.println("\n¡Hasta luego!"); break;
                    default: System.out.println("Opción no válida. Intenta de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingresa un número válido.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n========================================");
        System.out.println("   SISTEMA DE GESTIÓN DE HOTEL");
        System.out.println("========================================");
        System.out.println("1. Listar habitaciones disponibles");
        System.out.println("2. Crear una habitación");
        System.out.println("3. Calcular precio de reservación");
        System.out.println("4. Realizar una reservación");
        System.out.println("5. Ver todas las habitaciones");
        System.out.println("6. Ver todas las reservaciones");
        System.out.println("7. Salir");
        System.out.println("========================================");
    }

    private static void createRoom() {
        System.out.println("\n--- CREAR NUEVA HABITACIÓN ---");
        try {
            System.out.print("Número de habitación: ");
            int roomNumber = Integer.parseInt(scanner.nextLine());
            System.out.print("Tipo de habitación (Single/Double/Suite): ");
            String type = scanner.nextLine();
            System.out.print("Capacidad (número de personas): ");
            int capacity = Integer.parseInt(scanner.nextLine());
            System.out.print("Precio por noche ($): ");
            double price = Double.parseDouble(scanner.nextLine());
            hotelManager.createRoom(roomNumber, type, capacity, price);
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresa valores numéricos válidos.");
        }
    }

    private static void calculateReservationPrice() {
        System.out.println("\n--- CALCULAR PRECIO DE RESERVACIÓN ---");
        try {
            System.out.print("Número de habitación: ");
            int roomNumber = Integer.parseInt(scanner.nextLine());
            System.out.print("Número de noches: ");
            int nights = Integer.parseInt(scanner.nextLine());
            if (nights <= 0) { System.out.println("Error: El número de noches debe ser mayor a 0."); return; }
            hotelManager.calculateReservationPrice(roomNumber, nights);
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresa valores numéricos válidos.");
        }
    }

    private static void makeReservation() {
        System.out.println("\n--- REALIZAR RESERVACIÓN ---");
        try {
            System.out.print("Nombre del huésped: ");
            String guestName = scanner.nextLine();
            if (guestName.trim().isEmpty()) { System.out.println("Error: El nombre no puede estar vacío."); return; }
            System.out.print("Número de habitación: ");
            int roomNumber = Integer.parseInt(scanner.nextLine());
            System.out.println("Fecha de entrada (DD/MM/YYYY):");
            System.out.print("Día: "); int checkInDay = Integer.parseInt(scanner.nextLine());
            System.out.print("Mes: "); int checkInMonth = Integer.parseInt(scanner.nextLine());
            System.out.print("Año: "); int checkInYear = Integer.parseInt(scanner.nextLine());
            System.out.println("Fecha de salida (DD/MM/YYYY):");
            System.out.print("Día: "); int checkOutDay = Integer.parseInt(scanner.nextLine());
            System.out.print("Mes: "); int checkOutMonth = Integer.parseInt(scanner.nextLine());
            System.out.print("Año: "); int checkOutYear = Integer.parseInt(scanner.nextLine());
            hotelManager.makeReservation(roomNumber, guestName,
                                        checkInDay, checkInMonth, checkInYear,
                                        checkOutDay, checkOutMonth, checkOutYear);
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresa valores numéricos válidos.");
        }
    }
}
