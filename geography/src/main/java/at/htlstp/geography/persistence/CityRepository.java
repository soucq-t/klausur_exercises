package at.htlstp.geography.persistence;

import at.htlstp.geography.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface CityRepository extends JpaRepository<at.htlstp.geography.domain.City, String> {

    @Query("""
  SELECT new map(c.city_name as name, c.population as population)
from City c
where c.population >=:pop
""")
    List<Map<String, Object>> findAllByPopulationIsAfteroOrPopulationIs(Integer pop);
}