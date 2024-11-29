package christmas.model.domain.promotion;

import christmas.model.domain.menu.Menu;

public class Promotion {
    private Menu promotionMenu;
    private int count;

    public Promotion(Menu promotionMenu, int count) {
        this.promotionMenu = promotionMenu;
        this.count = count;
    }
}
