package at.htlstp.bookings.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import at.htlstp.bookings.domain.Guest;

public interface GuestRepository extends JpaRepository<Guest, Long> {
    Guest findGuestByName(String name);
}
