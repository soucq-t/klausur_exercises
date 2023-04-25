package at.htlstp.bookings.presentation;

import at.htlstp.bookings.advice.ReservationNotAcceptException;
import at.htlstp.bookings.advice.ReservationNotFoundException;
import at.htlstp.bookings.domain.Reservation;
import at.htlstp.bookings.domain.Table;
import at.htlstp.bookings.persistence.GuestRepository;
import at.htlstp.bookings.persistence.ReservationRepository;
import at.htlstp.bookings.persistence.TableRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("")
public class RestController {
    private final GuestRepository guestRepository;
    private final TableRepository tableRepository;
    private final ReservationRepository reservationRepository;

    public RestController (GuestRepository guestRepository, TableRepository tableRepository, ReservationRepository reservationRepository){
        this.guestRepository=guestRepository;
        this.tableRepository=tableRepository;
        this.reservationRepository=reservationRepository;
    }

    @GetMapping("reservations")
    public List<Reservation> getAllReservationFromGuest(@RequestParam(name = "name",required = true) String name){
        return reservationRepository.findAllByGuest(guestRepository.findGuestByName(name));
    }

    @GetMapping("reservations/{id}")
    public Reservation findReservationById(@PathVariable Long id ) throws ReservationNotFoundException {


        return reservationRepository.findById(id).orElseThrow(ReservationNotFoundException::new);
    }

    @PostMapping("reservations")
    public ResponseEntity<Reservation> saveReservation(@RequestBody Reservation reservation) throws ReservationNotAcceptException {
        Table table = tableRepository.findById(reservation.getTable().getId()).orElseThrow(ReservationNotAcceptException::new);
        if (reservation.getGroupSize() > table.getSize()
                || reservationRepository.findNullReservationIfAcceptable(
                        reservation.getTable(),
                        reservation.getTime().plusSeconds(1).minusHours(2),
                        reservation.getTime().minusSeconds(1).plusHours(2))!=null)
        {

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        Reservation saved = reservationRepository.save(reservation);
        /*
                Reservation saved = reservationRepository.save(reservation);

        return new ResponseEntity<>(saved, HttpStatus.CREATED);
         */
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .build(saved.getId());
        return ResponseEntity
                .created(uri)
                .body(saved);
    }
}
