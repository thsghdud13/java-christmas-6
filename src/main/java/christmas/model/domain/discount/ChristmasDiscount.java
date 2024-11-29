package christmas.model.domain.discount;

import christmas.model.domain.Reservation;
import christmas.model.vo.Money;
import java.time.LocalDate;

public class ChristmasDiscount implements DiscountPolicy {
    private final LocalDate startDate = LocalDate.of(2023, 12, 1);
    private final LocalDate endDate = LocalDate.of(2023, 12, 25);
    private final Money baseMoney = new Money(1000);
    private final int growthRate = 100;

    @Override
    public Discount getDiscountAmount(Reservation reservation) {
        LocalDate reserveDate = reservation.getReserveDate();
        int count;
        if (reserveDate.isBefore(startDate) || reserveDate.isAfter(endDate)) {
            return new Discount(DiscountType.CHRISTMAS, new Money(0));
        }
        count = reserveDate.getDayOfMonth() - startDate.getDayOfMonth();
        Money addtionalMoney = new Money(count * growthRate);
        return new Discount(DiscountType.CHRISTMAS, baseMoney.add(addtionalMoney));
    }
}
