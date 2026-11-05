package cat.itacademy.solid.d;

import cat.itacademy.solid.d.console_menu.BookingConsole;
import cat.itacademy.solid.d.console_menu.ConsoleInput;
import cat.itacademy.solid.d.interfaces.BookingInterface;
import cat.itacademy.solid.d.repository.H2BookingRepository;
import cat.itacademy.solid.d.repository.InMemoryBookingRepository;
import cat.itacademy.solid.d.service.BookingService;

public class BookingApp {

    public static void main(String[] args) {
        ConsoleInput input = new ConsoleInput(System.in, System.out);

        System.out.println("Persistències disponibles:");
        System.out.println("  1. H2 (base de dades en memòria)");
        System.out.println("  2. En memòria (Map)");

        if (input.readIntInRange("Tria persistència (1-2): ", 1, 2) == 2) {
            BookingInterface repositoryInterface = new InMemoryBookingRepository();
                BookingService service = new BookingService(repositoryInterface);

                new BookingConsole(repositoryInterface, service, input, System.out).run();
        } else {
            try (H2BookingRepository h2Repository = new H2BookingRepository("jdbc:h2:mem:bookings")) {
                BookingInterface repositoryInterface = h2Repository;
                BookingService service = new BookingService(repositoryInterface);

                new BookingConsole(repositoryInterface, service, input, System.out).run();
            }
        }
    }
}
