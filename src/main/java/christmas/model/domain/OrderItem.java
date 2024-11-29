package christmas.model.domain;

import christmas.model.domain.menu.Menu;
import christmas.model.domain.menu.MenuType;
import christmas.model.vo.Money;

public class OrderItem {
    private Menu menu;
    private int count;

    public OrderItem(Menu menu, int count) {
        this.menu = menu;
        this.count = count;
    }

    public String getMenuName() {
        return menu.getName();
    }

    public Money getMenuPrice() {
        return menu.getPrice();
    }

    public int getCount() {
        return count;
    }

    public MenuType getMenuType() {
        return menu.getMenuType();
    }
}
