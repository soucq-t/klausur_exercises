package at.htlstp.bookings.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ResponseBody
@RestControllerAdvice
public class ReservationRestAdvice {
    @ExceptionHandler(ReservationNotFoundException.class)
    ProblemDetail handleReservationNotFound(ReservationNotFoundException ex) {
        return ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ReservationNotAcceptException.class)
    ProblemDetail handleReservationNotAccept(ReservationNotFoundException ex) {
        return ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
    }
}
