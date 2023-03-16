package commands;

import model.StoreToy;
import model.Toy;
import view.Msg;
import view.View;

public class AddToy extends BaseCommand{
    public AddToy(StoreToy storeToy, View view) {
        super(storeToy, view);
    }

    @Override
    public boolean execute() {
        int id = this.view.getInt(Msg.getToyIDMsg);
        if (this.storeToy.hasToyID(id)) {
            this.view.ShowMsg(Msg.existsIDMsg);
            return false;
        }

        String name = this.view.getString(Msg.getToyNameMsg);
        int qty = this.view.getInt(Msg.getToyQtyMsg);
        int chance = this.view.getPercent(Msg.getToyChanceMsg);
        Toy toy = new Toy(id, name, qty, chance);
        this.storeToy.Put(toy);
        this.view.ShowMsg(Msg.toysAddedMsg, 1);
        return false;
    }

    @Override
    public String toString() {
        return "Добавить новую игрушку(вручную)";
    }
}
