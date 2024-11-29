package christmas.model.domain.discount;

import christmas.model.domain.Reservation;
import christmas.model.vo.Money;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SpecialDiscount implements DiscountPolicy {
    private final List<LocalDate> specialDays = new ArrayList<>();

    public SpecialDiscount() {
        specialDays.add(LocalDate.of(2023, 12, 3));
        specialDays.add(LocalDate.of(2023, 12, 10));
        specialDays.add(LocalDate.of(2023, 12, 17));
        specialDays.add(LocalDate.of(2023, 12, 24));
        specialDays.add(LocalDate.of(2023, 12, 25));
        specialDays.add(LocalDate.of(2023, 12, 31));
    }

    @Override
    public Discount getDiscountAmount(Reservation reservation) {
        if (specialDays.stream()
                .anyMatch(s -> reservation.getReserveDate().isEqual(s))) {
            return new Discount(DiscountType.SPECIAL, new Money(1000));
        }
        return new Discount(DiscountType.SPECIAL, new Money(0));
    }
}
