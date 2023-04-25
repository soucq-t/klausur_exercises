package at.htlstp.spacehistory.persistence;

import at.htlstp.spacehistory.domain.Rocket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RocketRepository extends JpaRepository<Rocket, Long> {
}