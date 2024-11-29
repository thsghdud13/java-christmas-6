package christmas.model.domain.promotion;

import christmas.model.domain.EventValidator;
import christmas.model.domain.Reservation;
import christmas.model.domain.menu.Menu;
import christmas.model.vo.Money;
import java.time.LocalDate;

public class PromotionPolicy {
    private final LocalDate startDate = LocalDate.of(2023, 12, 1);
    private final LocalDate endDate = LocalDate.of(2023, 12, 31);
    private final Money criteria = new Money(120000);
    private final Menu promotionMenu;
    private final int promotionCount;

    public PromotionPolicy(Menu promotionMenu, int promotionCount) {
        this.promotionMenu = promotionMenu;
        this.promotionCount = promotionCount;
    }

    public Promotion getPromotion(Reservation reservation) {
        if (!EventValidator.validate(startDate, endDate, reservation)) {
            return new Promotion(promotionMenu, 0);
        }
        Money totalOrderMoney = reservation.getTotalOrderMoney();
        if (totalOrderMoney.compareTo(criteria) >= 0) {
            return new Promotion(promotionMenu, promotionCount);
        }
        return new Promotion(promotionMenu, 0);
    }
}
