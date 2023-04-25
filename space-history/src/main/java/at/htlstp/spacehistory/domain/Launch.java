package at.htlstp.spacehistory.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Objects;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table
@ToString

public class Launch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column
    @NotNull(message = "must  not be null")
    @PastOrPresent(message = "passt or  present")
    private LocalDate date;

    @ManyToOne
    private Rocket rocket;

    @Column
    private boolean success;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Launch launch = (Launch) o;
        return Objects.equals(id, launch.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    //
//    public Launch(String companyName, LocalDate date, String rocket, boolean success) {
//
//    }


}
