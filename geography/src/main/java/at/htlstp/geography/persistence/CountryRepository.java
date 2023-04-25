package at.htlstp.geography.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<at.htlstp.geography.domain.Country, String> {
}