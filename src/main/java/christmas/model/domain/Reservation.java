package christmas.model.domain;

import christmas.model.domain.discount.Discount;
import christmas.model.domain.discount.DiscountPolicy;
import christmas.model.domain.menu.MenuType;
import christmas.model.domain.promotion.Promotion;
import christmas.model.domain.promotion.PromotionPolicy;
import christmas.model.vo.Money;
import java.time.LocalDate;
import java.util.List;

public class Reservation {
    private List<DiscountPolicy> discountPolicies;
    private PromotionPolicy promotionPolicy;
    private List<OrderItem> orderItems;
    private LocalDate reserveDate;

    public Reservation(List<DiscountPolicy> discountPolicies, PromotionPolicy promotionPolicy,
                       List<OrderItem> orderItems, LocalDate reserveDate) {
        this.discountPolicies = discountPolicies;
        this.promotionPolicy = promotionPolicy;
        this.orderItems = orderItems;
        this.reserveDate = reserveDate;
        validateMenuType();
        validateMenuSize();
    }

    public Receipt getReceipt() {
        List<Discount> discountResults = discountPolicies.stream()
                .map(d -> d.getDiscount(this))
                .filter(d -> !d.getDiscountAmount().equals(new Money(0)))
                .toList();
        Promotion promotion = promotionPolicy.getPromotion(this);
        return new Receipt(orderItems, discountResults, promotion);
    }

    public void validateMenuType() {
        if (orderItems.stream()
                .allMatch(o -> o.getMenuType().equals(MenuType.BEVERAGE))) {
            throw new IllegalArgumentException("음료만 주문 불가");
        }
    }

    public void validateMenuSize() {
        if (orderItems.stream()
                .mapToInt(o -> o.getCount())
                .sum() > 20
        ) {
            throw new IllegalArgumentException("20개 초과 주문 불가");
        }
    }

    public LocalDate getReserveDate() {
        return reserveDate;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public Money getTotalOrderMoney() {
        return new Money(orderItems.stream()
                .mapToInt(
                        o ->
                                o.getMenuPrice().multiply(o.getCount()).value()
                ).sum());
    }
}
