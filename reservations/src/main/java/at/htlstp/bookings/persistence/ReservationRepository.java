package at.htlstp.bookings.persistence;

import at.htlstp.bookings.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    List<Reservation> findAllByGuest(Guest guest);

    @Query("""
        select reservation
        from Reservation reservation
        where reservation.table =:table AND reservation.time > :start and reservation.time <:endTime
""")
    Reservation findNullReservationIfAcceptable(Table table, LocalDateTime start, LocalDateTime endTime);
}
