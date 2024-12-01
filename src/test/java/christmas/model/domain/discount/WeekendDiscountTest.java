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

class WeekendDiscountTest {
    WeekendDiscount weekendDiscount = new WeekendDiscount();

    @ParameterizedTest
    @CsvSource(value = {"2023,12,7,2,0", "2023,12,3,1,0", "2023,12,8,2,4046", "2023,10,8,13,0", "2024,1,1,20,0"})
    public void 평일_할인_테스트(int year, int month, int dayOfMonth, int dessertCount, int result) {
        //given
        LocalDate testDate = LocalDate.of(year, month, dayOfMonth);
        Menu menu = new Menu(MenuType.MAIN, new Money(10000), "케이크");
        OrderItem orderItem = new OrderItem(menu, dessertCount);
        Reservation reservation = new Reservation(null, null, List.of(orderItem), testDate);

        //when
        Discount discount = weekendDiscount.getDiscount(reservation);

        //then
        Assertions.assertThat(new Money(result))
                .isEqualTo(discount.getDiscountAmount());
    }

}
