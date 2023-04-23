package at.htlstp.bookings.persistence;

import at.htlstp.bookings.domain.Table;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository<Table,Long> {
}
