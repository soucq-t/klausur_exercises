package at.htlstp.bookings.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ResponseBody
@RestControllerAdvice
public class ReservationRestAdvice {
    @ExceptionHandler(ReservationNotFoundException.class)
    ProblemDetail handleStudentNotFound(ReservationNotFoundException ex) {
        return ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
    }
}
