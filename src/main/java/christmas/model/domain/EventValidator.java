package christmas.model.domain;

import christmas.model.vo.Money;
import java.time.LocalDate;

public class EventValidator {
    private EventValidator() {
    }

    public static boolean validate(LocalDate startDate, LocalDate endDate, Reservation reservation) {
        return !reservation.getReserveDate().isBefore(startDate) && !reservation.getReserveDate().isAfter(endDate)
                && reservation.getTotalOrderMoney().compareTo(new Money(10000)) >= 0;
    }
}
