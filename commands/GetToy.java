package commands;

import exceptions.EmptyExceptions;
import model.StoreToy;
import model.Toy;
import view.View;

public class GetToy extends BaseCommand{
    public GetToy(StoreToy storeToy, View view) {
        super(storeToy, view);
    }

    @Override
    public boolean execute() {
        try {
            Toy toy = this.storeToy.Get();
            this.view.ShowMsg(toy.toString(), 1);
        } catch (EmptyExceptions e) {
            this.view.ShowMsg(e.getMessage(), 1);
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Получить игрушку";
    }
}
