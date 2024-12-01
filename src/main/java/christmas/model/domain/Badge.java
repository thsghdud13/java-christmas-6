package christmas.model.domain;

import christmas.model.vo.Money;

public enum Badge {
    NONE("없음", new Money(0), new Money(4999)),
    STAR("별", new Money(5000), new Money(9999)),
    TREE("트리", new Money(10000), new Money(19999)),
    SANTA("산타", new Money(20000), Money.max());
    private String name;
    private Money minimumPrice;
    private Money maximumPrice;

    Badge(String name, Money minimumPrice, Money maximumPrice) {
        this.name = name;
        this.minimumPrice = minimumPrice;
        this.maximumPrice = maximumPrice;
    }

    public static Badge getBadge(Money money) {
        for (Badge badge : values()) {
            if (badge.maximumPrice.compareTo(money) >= 0 && money.compareTo(badge.minimumPrice) >= 0) {
                return badge;
            }
        }
        return NONE;
    }

    public String getName() {
        return name;
    }
}
