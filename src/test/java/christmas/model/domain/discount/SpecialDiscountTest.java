package christmas.model.domain.discount;

import christmas.model.domain.Reservation;
import christmas.model.vo.Money;
import java.time.LocalDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SpecialDiscountTest {
    SpecialDiscount specialDiscount = new SpecialDiscount();

    @ParameterizedTest
    @CsvSource(value = {"2023,12,3,1000", "2023,12,29,0"})
    public void 스페셜_할인_테스트(int year, int month, int dayOfMonth, int result) {
        //given
        LocalDate testDate = LocalDate.of(year, month, dayOfMonth);
        Reservation reservation = new Reservation(null, null, null, testDate);

        //when
        Discount discountAmount = specialDiscount.getDiscountAmount(reservation);

        //then
        Assertions.assertThat(new Money(result))
                .isEqualTo(discountAmount.getDiscountAmount());
    }

}
