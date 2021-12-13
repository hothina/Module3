package vn.na.ho.coffee.sort;


import vn.na.ho.coffee.model.Drink;

import java.util.Comparator;

public class SortDrink implements Comparator<Drink> {
    @Override
    public int compare(Drink o1, Drink o2) {
        if (o1.getId()> o2.getId()){
            return -1;
        } else {
            return 1;
        }
    }
}
