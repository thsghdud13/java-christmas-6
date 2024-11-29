package christmas.model.domain.promotion;

import christmas.model.domain.Reservation;

public interface PromotionPolicy {
    Promotion getPromotion(Reservation reservation);
}
