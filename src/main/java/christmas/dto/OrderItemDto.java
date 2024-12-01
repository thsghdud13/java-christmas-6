package christmas.dto;

public class OrderItemDto {
    private String menuName;
    private int count;

    public OrderItemDto(String menuName, int count) {
        this.menuName = menuName;
        this.count = count;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getCount() {
        return count;
    }
}
