package commands;

import model.StoreToy;
import model.Toy;
import view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShowAllToys extends BaseCommand{
    public ShowAllToys(StoreToy storeToy, View view) {
        super(storeToy, view);
    }

    @Override
    public boolean execute() {
        if (!super.execute()) {
            return false;
        };

        List<Toy> toys = new ArrayList<>();
        for (Toy toy : this.storeToy.getToys().values()) {
            toys.add(toy);
        }
        Collections.sort(toys);
        for (Toy toy : toys) {
            this.view.ShowMsg(toy.toString());
        }
        this.view.ShowMsg("");
        return false;
    }

    @Override
    public String toString() {
        return "Показать все игрушки в магазине";
    }
}
