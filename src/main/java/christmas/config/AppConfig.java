package christmas.config;

import christmas.controller.ReservationController;
import christmas.repository.InMemoryMenuRepository;
import christmas.service.ReservationService;

public class AppConfig {
    public ReservationController reservationController() {
        return new ReservationController(reservationService());
    }

    private ReservationService reservationService() {
        return new ReservationService(InMemoryMenuRepository.getInstance());
    }
}
