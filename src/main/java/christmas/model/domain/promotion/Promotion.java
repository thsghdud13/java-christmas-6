package christmas.model.domain.promotion;

import christmas.model.domain.menu.Menu;
import christmas.model.vo.Money;

public class Promotion {
    private Menu promotionMenu;
    private int count;

    public Promotion(Menu promotionMenu, int count) {
        this.promotionMenu = promotionMenu;
        this.count = count;
    }

    public Money getPromotionAmount() {
        return promotionMenu.getPrice().multiply(count);
    }

    public Menu getPromotionMenu() {
        return promotionMenu;
    }

    public int getCount() {
        return count;
    }
}
