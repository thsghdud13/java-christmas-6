package christmas.controller;

import christmas.dto.OrderItemDto;
import christmas.dto.ReservationRequestDto;
import christmas.model.domain.Receipt;
import christmas.service.ReservationService;
import christmas.view.InputHandler;
import christmas.view.OutputHandler;
import java.util.List;

public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    public void reserve() {
        int day = InputHandler.getDay();
        List<OrderItemDto> orders = InputHandler.getOrders();
        ReservationRequestDto reservationRequestDto = new ReservationRequestDto(orders, day);
        Receipt receipt = reservationService.reserve(reservationRequestDto).getReceipt();
        OutputHandler.printReceipt(receipt);
    }
}
