package commands;

import model.StoreToy;
import view.Msg;
import view.View;

public class AddToys extends BaseCommand{
    public AddToys(StoreToy storeToy, View view) {
        super(storeToy, view);
    }

    @Override
    public boolean execute() {
        if (this.storeToy.LoadData()) {
            this.view.ShowMsg(Msg.toysAddedMsg, 1);
            return true;
        }
        this.view.ShowMsg(Msg.toysNotAddedMsg, 1);
        return false;
    }

    @Override
    public String toString() {
        return "Добавить новую игрушку(из файла)";
    }
}
