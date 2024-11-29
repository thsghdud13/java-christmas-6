package christmas.model.domain;

import christmas.model.domain.menu.Menu;

public class OrderItem {
    private Menu menu;
    private int count;

    public OrderItem(Menu menu, int count) {
        this.menu = menu;
        this.count = count;
    }
}
