package christmas.model.domain.menu;

public enum MenuType {
    APPETIZER,
    MAIN,
    DESSERT,
    BEVERAGE;

    public static MenuType of(String typeName) {
        if (typeName.equals("애피타이저")) {
            return APPETIZER;
        }
        if (typeName.equals("메인")) {
            return MAIN;
        }
        if (typeName.equals("디저트")) {
            return DESSERT;
        }
        if (typeName.equals("음료")) {
            return BEVERAGE;
        }
        throw new IllegalArgumentException("그런 메뉴 타입은 없음");
    }
}
