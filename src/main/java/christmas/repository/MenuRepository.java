package christmas.repository;

import christmas.model.domain.menu.Menu;
import java.util.Optional;

public interface MenuRepository {
    void save(Menu menu);

    Optional<Menu> findByName(String name);

    void clear();
}
