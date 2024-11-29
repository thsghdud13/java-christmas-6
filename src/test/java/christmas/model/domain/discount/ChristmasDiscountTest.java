package christmas.model.domain.discount;

import christmas.model.domain.Reservation;
import christmas.model.vo.Money;
import java.time.LocalDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ChristmasDiscountTest {
    ChristmasDiscount christmasDiscount = new ChristmasDiscount();

    @ParameterizedTest
    @CsvSource(value = {"12,5,1400", "12,31,4000", "11,5,0"})
    public void 크리스마스_할인_테스트(int month, int dayOfMonth, int result) {
        //given
        LocalDate testDate = LocalDate.of(2023, month, dayOfMonth);
        Reservation reservation = new Reservation(null, null, null, testDate);

        //when
        Discount discountAmount = christmasDiscount.getDiscountAmount(reservation);

        //then
        Assertions.assertThat(new Money(result))
                .isEqualTo(discountAmount.getDiscountAmount());
    }

}
