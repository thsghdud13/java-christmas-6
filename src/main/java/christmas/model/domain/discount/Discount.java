package christmas.model.domain.discount;

import christmas.model.vo.Money;

public class Discount {
    private DiscountType discountType;
    private Money discountAmount;

    public Discount(DiscountType discountType, Money discountAmount) {
        this.discountType = discountType;
        this.discountAmount = discountAmount;
    }

    public Money getDiscountAmount() {
        return discountAmount;
    }

    public String getDiscountTypeName() {
        return discountType.getName();
    }
}
