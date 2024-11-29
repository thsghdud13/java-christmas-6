package christmas.model.domain.menu;

import christmas.model.vo.Money;

public class Menu {
    private MenuType menuType;
    private Money price;
    private String name;

    public Menu(MenuType menuType, Money price, String name) {
        this.menuType = menuType;
        this.price = price;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }
}
