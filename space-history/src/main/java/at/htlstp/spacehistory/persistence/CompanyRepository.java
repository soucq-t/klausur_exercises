package at.htlstp.spacehistory.persistence;

import at.htlstp.spacehistory.domain.Company;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, String> {


}