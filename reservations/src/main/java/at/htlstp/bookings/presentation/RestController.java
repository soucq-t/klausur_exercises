package at.htlstp.bookings.presentation;

import at.htlstp.bookings.advice.ReservationNotFoundException;
import at.htlstp.bookings.domain.Reservation;
import at.htlstp.bookings.persistence.GuestRepository;
import at.htlstp.bookings.persistence.ReservationRepository;
import at.htlstp.bookings.persistence.TableRepository;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("reservation/{id}")
    public Reservation findReservationById(@PathVariable Long id ) throws ReservationNotFoundException {
      /*  Optional<Reservation> reservation=reservationRepository.findById(id);
        if (reservation.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(reservation.get(),HttpStatus.OK);


       */
        return reservationRepository.findById(id).orElseThrow(ReservationNotFoundException::new);
    }

    @PostMapping("reservations")
    public void saveReservation(Reservation reservation){
        if ()
        reservationRepository.save(reservation);
    }
}
