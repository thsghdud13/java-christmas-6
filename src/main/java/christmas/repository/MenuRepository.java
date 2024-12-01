package christmas.repository;

import christmas.model.domain.menu.Menu;
import java.util.Optional;

public interface MenuRepository {
    Optional<Menu> findByName(String name);
}
