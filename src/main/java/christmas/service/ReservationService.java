package christmas.service;

import christmas.dto.ReservationRequestDto;
import christmas.model.domain.OrderItem;
import christmas.model.domain.Reservation;
import christmas.model.domain.discount.ChristmasDiscount;
import christmas.model.domain.discount.DiscountPolicy;
import christmas.model.domain.discount.SpecialDiscount;
import christmas.model.domain.discount.WeekdayDiscount;
import christmas.model.domain.discount.WeekendDiscount;
import christmas.model.domain.menu.Menu;
import christmas.model.domain.menu.MenuType;
import christmas.model.domain.promotion.PromotionPolicy;
import christmas.model.vo.Money;
import christmas.repository.MenuRepository;
import java.time.LocalDate;
import java.util.List;

public class ReservationService {
    private final MenuRepository menuRepository;


    public ReservationService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Reservation reserve(ReservationRequestDto reservationRequestDto) {
        List<DiscountPolicy> discountPolicies = List.of(new ChristmasDiscount(), new WeekdayDiscount(),
                new WeekendDiscount(), new SpecialDiscount());
        PromotionPolicy promotionPolicy = new PromotionPolicy(new Menu(MenuType.BEVERAGE, new Money(25000), "샴페인"), 1);
        List<OrderItem> orderItems = reservationRequestDto.getOrderItemDtos().stream()
                .map(o -> {
                            Menu menu = menuRepository.findByName(o.getMenuName())
                                    .orElseThrow(() -> new IllegalArgumentException("그런메뉴는없음"));
                            return new OrderItem(menu, o.getCount());
                        }

                ).toList();
        LocalDate reserveDate = LocalDate.of(2023, 12, reservationRequestDto.getDayOfMonth());
        return new Reservation(discountPolicies, promotionPolicy, orderItems, reserveDate);
    }
}
