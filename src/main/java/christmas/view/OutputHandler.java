package christmas.view;

import christmas.model.domain.OrderItem;
import christmas.model.domain.Receipt;
import christmas.model.domain.discount.Discount;
import christmas.model.domain.promotion.Promotion;
import christmas.model.vo.Money;
import java.text.NumberFormat;
import java.util.List;

public class OutputHandler {
    private static final String MENU_OUTPUT_MESSAGE = "<주문 메뉴>";
    private static final String TOTAL_ORDER_BEFORE_DISCOUNT_MESSAGE = "<할인 전 총주문 금액>";
    private static final String PROMOTION_MENU_MESSAGE = "<증정 메뉴>";
    private static final String DISCOUNT_HISTORY_MESSAGE = "<혜택 내역>";
    private static final String TOTAL_DISCOUNT_MESSAGE = "<총혜택 금액>";
    private static final String TOTAL_PAYMENT_MESSAGE = "<할인 후 예상 결제 금액>";
    private static final String BADGE_MESSAGE = "<12월 이벤트 배지>";
    private static final String ORDER_UNIT = "개";
    private static final String MONEY_UNIT = "원";

    private OutputHandler() {
    }

    public static void printReceipt(Receipt receipt) {
        OutputView.print(MENU_OUTPUT_MESSAGE);
        receipt.getOrderItems().forEach(o -> OutputView.print(orderItemToString(o)));
        OutputView.print(TOTAL_ORDER_BEFORE_DISCOUNT_MESSAGE);
        OutputView.print(moneyToString(receipt.getTotalMoneyNoBenefit()));
        OutputView.print(PROMOTION_MENU_MESSAGE);
        OutputView.print(promotionToString(receipt.getPromotion()));
        OutputView.print(DISCOUNT_HISTORY_MESSAGE);
        printDiscountHistory(receipt.getDiscounts(), receipt.getPromotion());
        OutputView.print(TOTAL_DISCOUNT_MESSAGE);
        OutputView.print(discountMoneyToString(receipt.getTotalBenefit()));
        OutputView.print(TOTAL_PAYMENT_MESSAGE);
        OutputView.print(moneyToString(receipt.getTotalPayment()));
        OutputView.print(BADGE_MESSAGE);
        OutputView.print(receipt.getBadge().getName());
    }

    private static void printDiscountHistory(List<Discount> discounts, Promotion promotion) {
        if (discounts.isEmpty() && !checkPromotion(promotion)) {
            OutputView.print("없음");
            return;
        }
        discounts.forEach(d -> OutputView.print(discountToString(d)));
        OutputView.print(promotionDisocuntToString(promotion));
    }

    private static String promotionDisocuntToString(Promotion promotion) {
        if (checkPromotion(promotion)) {
            return "없음";
        }
        return "증정 이벤트: " + discountMoneyToString(promotion.getPromotionAmount());
    }

    private static String discountToString(Discount discount) {
        return discount.getDiscountTypeName() + ": " + discountMoneyToString(discount.getDiscountAmount());
    }

    private static String orderItemToString(OrderItem orderItem) {
        return orderItem.getMenuName() + " " + orderItem.getCount() + ORDER_UNIT;
    }

    private static boolean checkPromotion(Promotion promotion) {
        return !promotion.getPromotionAmount().equals(Money.of(0));
    }

    private static String promotionToString(Promotion promotion) {
        if (checkPromotion(promotion)) {
            return "없음";
        }
        return promotion.getPromotionMenu().getName() + " " + promotion.getCount() + ORDER_UNIT;
    }

    private static String moneyToString(Money money) {
        NumberFormat currencyInstance = NumberFormat.getInstance();
        return currencyInstance.format(money.value()) + MONEY_UNIT;
    }

    private static String discountMoneyToString(Money money) {
        if (money.equals(Money.of(0))) {
            return moneyToString(money);
        }
        return "-" + moneyToString(money);
    }

}
