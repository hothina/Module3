package vn.na.ho.coffee.repository;



import vn.na.ho.coffee.model.Drink;
import vn.na.ho.coffee.model.User;

import java.util.List;

public interface IDrinkRepository {

    Drink getById(int id);

    List<Drink> getDrinks();

    boolean exist(int id);

    void add(Drink newDrink);

    Drink update(Drink drink);

    boolean existByName(String name);

   List<Drink> selectAllDrinks();
}
