package at.htlstp.geography.presentation;

import at.htlstp.geography.domain.City;
import at.htlstp.geography.domain.Country;
import at.htlstp.geography.persistence.CityRepository;
import at.htlstp.geography.persistence.CountryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("")
public class RestController {
    final CityRepository cityRepository;
    final CountryRepository countryRepository;

    public RestController(CityRepository cityRepository, CountryRepository countryRepository) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }

    @GetMapping("countries/{code}")
    public ResponseEntity<Country> getCountry(@PathVariable String code){
        return countryRepository.findById(code)
                .map(country -> ResponseEntity.ok(country))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("cities")
    public List<Map<String, Object>> getCity(@RequestParam(name = "max", required = false)Integer max,
                                             @RequestParam(name = "min",required = false) Integer min,
                                             @RequestParam(name = "country",required = false) String country) {
        if (country == null) {
            System.out.println(min);
            if (min > 0) {
                System.out.println("ok");
                return cityRepository.findAllByPopulationIsAfteroOrPopulationIs(min);

            }
        }

        return null;
    }
    @PostMapping("countries")
    public ResponseEntity<Country> saveCountry(@RequestBody Country country){
        if (countryRepository.findById(country.getCode()).isPresent()){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        Country saved = countryRepository.save(country);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .build(saved.getCode());
        return ResponseEntity
                .created(uri)
                .body(saved);
    }
}
