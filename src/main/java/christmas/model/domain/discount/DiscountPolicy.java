package christmas.model.domain.discount;

import christmas.model.domain.Reservation;

public interface DiscountPolicy {
    Discount getDiscount(Reservation reservation);
}
