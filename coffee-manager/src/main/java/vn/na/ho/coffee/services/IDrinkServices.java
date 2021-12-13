package vn.na.ho.coffee.services;


import vn.na.ho.coffee.model.Drink;

import java.util.List;

public interface IDrinkServices {
    Drink getById(int id);

    List<Drink> getDrinks();

    void addDrink(Drink newDrink);

    Drink updateDrink(Drink drink);
    List<Drink> selectAllDrinks();


}
