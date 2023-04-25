package at.htlstp.spacehistory.setup;

import at.htlstp.spacehistory.domain.Company;
import at.htlstp.spacehistory.domain.Launch;
import at.htlstp.spacehistory.domain.Rocket;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * Parst Launches in einem Format ähnlich wie in space-history.csv.
 */
@Component
record CsvParser() {

    /**
     * NICHT ANFASSEN, kann wie deklariert verwendet werden
     * E -> weekday
     * MMM -> month
     * dd -> day
     * uuuu -> year
     * [] -> optional
     * HH -> hour
     * mm -> minute
     * z -> Zeitzone
     * NICHT ANFASSEN, kann wie deklariert verwendet werden
     */
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E MMM dd uuuu[ HH:mm z]", Locale.US);

    /**
     * Dient nur zum Laden einiger Initialdaten, kann gelöscht werden, wenn das Parsing aus der csv-Datei funktioniert.
     *
     * @return DemoDaten
     */
//    Collection<Launch> getStartingData() {
//        return List.of(
//                new Launch(
//                        "SpaceX",
//                        LocalDate.parse("Fri Aug 07 2020 05:12 UTC", formatter),
//                        "Falcon 9 Block 5",
//                        true),
//                new Launch(
//                        "CASC",
//                        LocalDate.parse("Thu Aug 06 2020 04:01 UTC", formatter),
//                        "Long March 2D",
//                        true),
//                new Launch(
//                        "ISA",
//                        LocalDate.parse("Sun Feb 09 2020 15:48 UTC", formatter),
//                        "Simorgh",
//                        false),
//                new Launch(
//                        "SpaceX",
//                        LocalDate.parse("Tue Aug 04 2020 23:57 UTC", formatter),
//                        "Starship Prototype",
//                        true)
//        );
//    }

    public Launch parseString(String s) {
        String[] splitted = s.split(",");
        Rocket rocket= new Rocket(null,splitted[3].split("\\|")[0],new Company(splitted[0]));
        return new Launch(null,LocalDate.parse(splitted[2], formatter) ,rocket, Objects.equals(splitted[6], "Success"));

    }
}
