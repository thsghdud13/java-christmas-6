package christmas;

import christmas.config.AppConfig;

public class Application {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        appConfig.reservationController().reserve();
    }
}
