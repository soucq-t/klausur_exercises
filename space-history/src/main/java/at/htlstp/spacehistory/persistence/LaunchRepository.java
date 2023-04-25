package at.htlstp.spacehistory.persistence;

import at.htlstp.spacehistory.domain.Launch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface LaunchRepository extends JpaRepository<Launch, Long> {

    @Query("""
select l
from Launch l 
order by l.date asc
""")
    List<Launch> findAllOrderByDateDateAsc();

  //  List<Launch> findAllByRocketCompanyNameOrderByDateDateAsc(String name,);

    @Query("""
select l
from Launch l join l.rocket rocket
where rocket.companyName.companyName =:name
order by l.date asc
""")
    List<Launch> findAllCompanyNameOrderByDateDateAsc(String name);
}