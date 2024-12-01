package christmas.view;


import christmas.dto.OrderItemDto;
import christmas.util.StringParser;
import java.util.List;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputHandler {
    public static int getDay() {
        return handleUserInput(InputHandler::getReservationDate);
    }

    private static int getReservationDate() {
        int day = StringParser.parseInt(InputView.readDate());
        validateDate(day);
        return day;
    }

    private static void validateDate(int day) {
        if (day > 31 || day < 1) {
            throw new IllegalArgumentException("일자 다시 입력하셈");
        }
    }

    public static List<OrderItemDto> getOrders() {
        return handleUserInput(InputHandler::getOrderItemDto);
    }

    private static List<OrderItemDto> getOrderItemDto() {
        String input = InputView.readOrders();
        validateOrder(input);
        return toOrderItemDtos(input);
    }

    private static void validateOrder(String input) {
        Matcher matcher = Pattern.compile("[가-힣a-zA-Z]+-\\d+(,[가-힣a-zA-Z]+-\\d+)*").matcher(input);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("형식 제대로 입력");
        }
    }

    private static List<OrderItemDto> toOrderItemDtos(String input) {
        List<String> splitInput = List.of(input.split(","));
        return splitInput.stream()
                .map(
                        s -> {
                            List<String> split = List.of(s.split("-"));
                            return new OrderItemDto(split.get(0), StringParser.parseInt(split.get(1)));
                        }
                ).toList();
    }

    private static <T> T handleUserInput(Supplier<T> inputSupplier) {
        while (true) {
            try {
                return inputSupplier.get();
            } catch (IllegalArgumentException illegalArgumentException) {
                System.out.println("다시입력");
            }
        }
    }
}
