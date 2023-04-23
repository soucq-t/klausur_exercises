package at.htlstp.bookings.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@jakarta.persistence.Table(name = "reservations")
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "table_id")
    private Table table;

    @NotNull
    @JoinColumn(name = "guest_id")
    @ManyToOne
    private Guest guest;


    @Future
    @Column(name = "reservation_time")
    private LocalDateTime localDateTime;

    @Column(name = "group_size")
    @Positive
    private int anzahlPerson;
}
