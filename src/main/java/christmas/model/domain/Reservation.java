package christmas.model.domain;

import christmas.model.domain.discount.Discount;
import christmas.model.domain.discount.DiscountPolicy;
import christmas.model.domain.promotion.Promotion;
import christmas.model.domain.promotion.PromotionPolicy;
import java.time.LocalDate;
import java.util.List;

public class Reservation {
    private List<DiscountPolicy> discountPolicies;
    private List<PromotionPolicy> promotionPolicies;
    private List<OrderItem> orderItems;
    private LocalDate reserveDate;

    public Receipt getReceipt() {
        List<Discount> discountResults = discountPolicies.stream()
                .map(d -> d.getDiscountAmount(this))
                .toList();
        List<Promotion> promotionResults = promotionPolicies.stream()
                .map(d -> d.getPromotion(this))
                .toList();
        return new Receipt(orderItems, discountResults, promotionResults);
    }
}