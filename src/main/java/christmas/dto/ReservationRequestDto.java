package christmas.dto;

import java.util.List;

public class ReservationRequestDto {
    List<OrderItemDto> orderItemDtos;
    int dayOfMonth;

    public ReservationRequestDto(List<OrderItemDto> orderItemDtos, int dayOfMonth) {
        this.orderItemDtos = orderItemDtos;
        this.dayOfMonth = dayOfMonth;
    }

    public List<OrderItemDto> getOrderItemDtos() {
        return orderItemDtos;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }
}
