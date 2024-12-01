package christmas.model.domain.discount;

import christmas.model.domain.EventValidator;
import christmas.model.domain.OrderItem;
import christmas.model.domain.Reservation;
import christmas.model.domain.menu.MenuType;
import christmas.model.vo.Money;
import java.time.LocalDate;
import java.util.List;

public class WeekendDiscount implements DiscountPolicy {
    private final LocalDate startDate = LocalDate.of(2023, 12, 1);
    private final LocalDate endDate = LocalDate.of(2023, 12, 31);
    private final Money discountAmount = new Money(2023);

    @Override
    public Discount getDiscount(Reservation reservation) {
        LocalDate reserveDate = reservation.getReserveDate();
        if (!EventValidator.validate(startDate, endDate, reservation)) {
            return new Discount(DiscountType.WEEKEND, new Money(0));
        }
        if (reserveDate.getDayOfWeek().getValue() <= 4 || reserveDate.getDayOfWeek().getValue() == 7) {
            return new Discount(DiscountType.WEEKEND, new Money(0));
        }
        int mainCount = getMainCount(reservation.getOrderItems());
        return new Discount(DiscountType.WEEKEND, discountAmount.multiply(mainCount));
    }

    private int getMainCount(List<OrderItem> orderItems) {
        return orderItems.stream()
                .mapToInt(o -> {
                    if (o.getMenuType().equals(MenuType.MAIN)) {
                        return o.getCount();
                    }
                    return 0;
                }).sum();
    }
}
