package at.htlstp.bookings.persistence;

import at.htlstp.bookings.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    List<Reservation> findAllByGuest(Guest guest);

}
