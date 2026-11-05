package cat.itacademy.solid.d.interfaces;

import cat.itacademy.solid.d.model.Booking;

import java.util.List;
import java.util.Optional;

public interface BookingInterface {
    Booking save(Booking booking);
    Optional<Booking> findById(Long bookingId);
    List<Booking> findAllOrderedByStart();
}
