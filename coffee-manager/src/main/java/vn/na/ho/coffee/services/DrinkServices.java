package vn.na.ho.coffee.services;




import vn.na.ho.coffee.exception.ExitsException;
import vn.na.ho.coffee.exception.NotFoundException;
import vn.na.ho.coffee.model.Drink;

import vn.na.ho.coffee.repository.DrinkRepository;
import vn.na.ho.coffee.repository.IDrinkRepository;

import java.util.List;

public class DrinkServices implements IDrinkServices {
    private IDrinkRepository drinkRepository;

    public DrinkServices() {

        drinkRepository = new DrinkRepository();
    }


    @Override
    public Drink getById(int id)  {
        Drink drink = drinkRepository.getById(id);
        if (drink == null)
            throw new NotFoundException("Drink not found");
        return drink;
    }

    @Override
    public List<Drink> getDrinks() {
        return drinkRepository.getDrinks();
    }

    @Override
    public void addDrink(Drink newDrink)  {

        if (drinkRepository.existByName(newDrink.getName()))
            throw new ExitsException("name already exist");
        drinkRepository.add(newDrink);
    }


    @Override
    public Drink updateDrink(Drink drink) {
        if (drinkRepository.exist(drink.getId())){
            if(drinkRepository.existByName(drink.getName()))
                throw new ExitsException("name already exist");
            return drinkRepository.update(drink);
        } else
            throw new NotFoundException("drink not already exists");
    }

    @Override
    public List<Drink> selectAllDrinks() {
        List<Drink> drinkList = drinkRepository.selectAllDrinks();

        if (drinkList == null)
            throw new NotFoundException("drink not already exists");
        drinkRepository.selectAllDrinks();


        return drinkList;
    }
}
