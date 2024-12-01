package christmas.model.domain;

import christmas.model.domain.discount.ChristmasDiscount;
import christmas.model.domain.discount.DiscountPolicy;
import christmas.model.domain.discount.SpecialDiscount;
import christmas.model.domain.discount.WeekdayDiscount;
import christmas.model.domain.discount.WeekendDiscount;
import christmas.model.domain.menu.Menu;
import christmas.model.domain.menu.MenuType;
import christmas.model.domain.promotion.PromotionPolicy;
import christmas.model.vo.Money;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ReservationTest {

    @ParameterizedTest
    @CsvSource(value = {"2023,12,25,3,55600,4400", "2023,12,25,6,115600,29400"})
    void getReceipt(int year, int month, int dayOfMonth, int menuCount, int payment, int benefit) {
        //given
        List<DiscountPolicy> discountPolicies = List.of(new ChristmasDiscount(), new WeekdayDiscount(),
                new WeekendDiscount(), new SpecialDiscount());
        PromotionPolicy promotionPolicies =
                new PromotionPolicy(new Menu(MenuType.BEVERAGE, new Money(25000), "샴페인"), 1);
        Menu menu = new Menu(MenuType.MAIN, new Money(20000), "고기");
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderItem(menu, menuCount));
        Reservation testReservation = new Reservation(discountPolicies, promotionPolicies, orderItems,
                LocalDate.of(year, month, dayOfMonth));

        //when
        Receipt receipt = testReservation.getReceipt();

        //then
        Assertions.assertThat(new Money(payment))
                .isEqualTo(receipt.getTotalPayment());
        Assertions.assertThat(new Money(benefit))
                .isEqualTo(receipt.getTotalBenefit());
    }

    @Test
    void 음료만_주문하면_주문_불가() {
        //given
        List<DiscountPolicy> discountPolicies = List.of(new ChristmasDiscount(), new WeekdayDiscount(),
                new WeekendDiscount(), new SpecialDiscount());
        PromotionPolicy promotionPolicies =
                new PromotionPolicy(new Menu(MenuType.BEVERAGE, new Money(25000), "샴페인"), 1);
        Menu menu = new Menu(MenuType.BEVERAGE, new Money(20000), "술");
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderItem(menu, 3));

        //when, then
        Assertions.assertThatThrownBy(
                () -> new Reservation(discountPolicies, promotionPolicies, orderItems,
                        LocalDate.of(2023, 12, 3))
        ).isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    void _20개_초과하면_주문_불가() {
        //given
        List<DiscountPolicy> discountPolicies = List.of(new ChristmasDiscount(), new WeekdayDiscount(),
                new WeekendDiscount(), new SpecialDiscount());
        PromotionPolicy promotionPolicies =
                new PromotionPolicy(new Menu(MenuType.BEVERAGE, new Money(25000), "샴페인"), 1);
        Menu menu = new Menu(MenuType.MAIN, new Money(20000), "술");
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderItem(menu, 21));

        //when, then
        Assertions.assertThatThrownBy(
                () -> new Reservation(discountPolicies, promotionPolicies, orderItems,
                        LocalDate.of(2023, 12, 3))
        ).isInstanceOf(IllegalArgumentException.class);

    }

}
