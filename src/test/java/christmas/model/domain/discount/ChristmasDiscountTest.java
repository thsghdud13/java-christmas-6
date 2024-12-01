package christmas.model.domain.discount;

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

class ChristmasDiscountTest {
    ChristmasDiscount christmasDiscount = new ChristmasDiscount();

    @ParameterizedTest
    @CsvSource(value = {"12,5,1400", "12,25,3400", "11,5,0", "12,31,0"})
    public void 크리스마스_할인_테스트(int month, int dayOfMonth, int result) {
        //given
        LocalDate testDate = LocalDate.of(2023, month, dayOfMonth);
        Menu menu = new Menu(MenuType.DESSERT, new Money(10000), "케이크");
        OrderItem orderItem = new OrderItem(menu, 1);
        Reservation reservation = new Reservation(null, null, List.of(orderItem), testDate);

        //when
        Discount discountAmount = christmasDiscount.getDiscount(reservation);

        //then
        Assertions.assertThat(new Money(result))
                .isEqualTo(discountAmount.getDiscountAmount());
    }

}
