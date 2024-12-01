package christmas.model.domain.discount;

import christmas.model.domain.EventValidator;
import christmas.model.domain.OrderItem;
import christmas.model.domain.Reservation;
import christmas.model.domain.menu.MenuType;
import christmas.model.vo.Money;
import java.time.LocalDate;
import java.util.List;

public class WeekdayDiscount implements DiscountPolicy {
    private final LocalDate startDate = LocalDate.of(2023, 12, 1);
    private final LocalDate endDate = LocalDate.of(2023, 12, 31);
    private final Money discountAmount = new Money(2023);

    @Override
    public Discount getDiscount(Reservation reservation) {
        LocalDate reserveDate = reservation.getReserveDate();
        if (!EventValidator.validate(startDate, endDate, reservation)) {
            return new Discount(DiscountType.WEEKDAY, new Money(0));
        }
        if (reserveDate.getDayOfWeek().getValue() >= 5 && reserveDate.getDayOfWeek().getValue() <= 6) {
            return new Discount(DiscountType.WEEKDAY, new Money(0));
        }
        int dessertCount = getDessertCount(reservation.getOrderItems());
        return new Discount(DiscountType.WEEKDAY, discountAmount.multiply(dessertCount));
    }

    private int getDessertCount(List<OrderItem> orderItems) {
        return orderItems.stream()
                .mapToInt(o -> {
                    if (o.getMenuType().equals(MenuType.DESSERT)) {
                        return o.getCount();
                    }
                    return 0;
                }).sum();
    }
}
