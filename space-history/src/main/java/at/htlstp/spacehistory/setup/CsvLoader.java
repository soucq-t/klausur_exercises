package at.htlstp.spacehistory.setup;

import at.htlstp.spacehistory.domain.Launch;
import at.htlstp.spacehistory.persistence.CompanyRepository;
import at.htlstp.spacehistory.persistence.LaunchRepository;
import at.htlstp.spacehistory.persistence.RocketRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Dient dazu, Startdaten in die Datenbank zu speichern.
 * Initial werden nur 4 Launches eingetragen, es sollten allerdings alle aus space-history.csv geladen werden.
 */
@Component
public record CsvLoader(CsvParser parser, LaunchRepository launchRepository, RocketRepository rocketRepository,
                        CompanyRepository companyRepository) implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        List<Launch> launches = Files.lines(Paths.get("space-history/src/main/resources/space-history.csv"))
                .skip(1)
                .map(parser::parseString).toList();
        launches.forEach(launch -> {
            companyRepository.save(launch.getRocket().getCompanyName());
            rocketRepository().save(launch.getRocket());
        });
        launchRepository.saveAll(launches);
    }
}
