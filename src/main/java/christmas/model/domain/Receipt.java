package christmas.model.domain;

import christmas.model.domain.discount.Discount;
import christmas.model.domain.promotion.Promotion;
import christmas.model.vo.Money;
import java.util.List;

public class Receipt {
    private Money totalMoneyNoBenefit;
    private Money totalBenefit;
    private Money totalPayment;
    private List<Discount> discounts;
    private Promotion promotion;
    private Badge badge;
    private List<OrderItem> orderItems;

    public Receipt(List<OrderItem> orderItems, List<Discount> discounts, Promotion promotion) {
        this.discounts = discounts;
        this.promotion = promotion;
        this.orderItems = orderItems;
        this.totalMoneyNoBenefit = calculateTotalMoneyNoBenefit();
        this.totalBenefit = calculateTotalBenefit();
        this.badge = Badge.getBadge(totalBenefit);
        this.totalPayment = calculateTotalPayment();
    }

    private Money calculateTotalMoneyNoBenefit() {
        return new Money(orderItems.stream()
                .mapToInt(
                        o ->
                                o.getMenuPrice().multiply(o.getCount()).value()
                ).sum());
    }

    private Money calculateTotalBenefit() {
        Money discountAmount = new Money(discounts.stream()
                .mapToInt(
                        d -> d.getDiscountAmount().value()
                ).sum());
        Money promotionAmount = promotion.getPromotionAmount();

        return discountAmount.add(promotionAmount);
    }

    private Money calculateTotalPayment() {
        return totalMoneyNoBenefit.sub(totalBenefit).add(promotion.getPromotionAmount());
    }

    public Money getTotalMoneyNoBenefit() {
        return totalMoneyNoBenefit;
    }

    public Money getTotalBenefit() {
        return totalBenefit;
    }

    public Money getTotalPayment() {
        return totalPayment;
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public Badge getBadge() {
        return badge;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
}
