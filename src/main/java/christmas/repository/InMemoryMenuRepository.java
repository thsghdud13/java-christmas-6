package christmas.repository;

import christmas.model.domain.menu.Menu;
import java.util.HashMap;
import java.util.Optional;

public class InMemoryMenuRepository implements MenuRepository {
    private final HashMap<String, Menu> menuHashMap = new HashMap<>();

    @Override
    public void save(Menu menu) {
        menuHashMap.put(menu.getName(), menu);
    }

    @Override
    public Optional<Menu> findByName(String name) {
        return Optional.of(menuHashMap.get(name));
    }

    @Override
    public void clear() {
        menuHashMap.clear();
    }

}
