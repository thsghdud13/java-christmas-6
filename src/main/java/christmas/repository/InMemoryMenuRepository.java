package christmas.repository;

import christmas.model.domain.menu.Menu;
import christmas.model.domain.menu.MenuType;
import christmas.model.vo.Money;
import christmas.util.CSVData;
import christmas.util.CSVFileLoader;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class InMemoryMenuRepository implements MenuRepository {
    private final HashMap<String, Menu> menuHashMap = new HashMap<>();
    private final String menuInfoFilePath = "src/main/resources/menus.md";
    private static final InMemoryMenuRepository INSTANCE = new InMemoryMenuRepository();

    private InMemoryMenuRepository() {
        CSVData menuData = CSVFileLoader.readCSV(menuInfoFilePath);
        for (List<String> row : menuData.getRows()) {
            menuHashMap.put(row.get(1),
                    new Menu(MenuType.of(row.get(0)), new Money(Integer.parseInt(row.get(2))), row.get(1)));
        }
    }

    public static InMemoryMenuRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<Menu> findByName(String name) {
        return Optional.of(menuHashMap.get(name));
    }

}
