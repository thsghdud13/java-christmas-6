package christmas.model.domain.promotion;

import christmas.model.domain.OrderItem;
import christmas.model.domain.Reservation;
import christmas.model.domain.menu.Menu;
import christmas.model.domain.menu.MenuType;
import christmas.model.vo.Money;
import java.time.LocalDate;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PromotionPolicyTest {
    PromotionPolicy promotionPolicy = new PromotionPolicy(new Menu(MenuType.BEVERAGE, new Money(25000), "샴페인"), 1);

    @ParameterizedTest
    @CsvSource(value = {"2023,12,7,12,1", "2023,12,3,11,0", "2023,10,8,13,0", "2024,1,1,3,0"})
    public void 증정_할인_테스트(int year, int month, int dayOfMonth, int menuCount, int count) {
        //given
        LocalDate testDate = LocalDate.of(year, month, dayOfMonth);
        Menu menu = new Menu(MenuType.DESSERT, new Money(10000), "케이크");
        OrderItem orderItem = new OrderItem(menu, menuCount);
        Reservation reservation = new Reservation(null, null, List.of(orderItem), testDate);

        //when
        Promotion promotion = promotionPolicy.getPromotion(reservation);

        //then
        Assertions.assertThat(new Money(count * 25000))
                .isEqualTo(promotion.getPromotionAmount());
    }

}
