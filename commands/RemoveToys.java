package commands;

import model.StoreToy;
import view.Msg;
import view.View;

public class RemoveToys extends BaseCommand{
    public RemoveToys(StoreToy storeToy, View view) {
        super(storeToy, view);
    }

    @Override
    public boolean execute() {
        if (!super.execute()) {
            return false;
        };

        if (this.view.getBoolean(Msg.askRemoveDataMsg)) {
            this.storeToy.RemoveAll();
            this.view.ShowMsg(Msg.allToysRemovedMsg, 1);
            return true;
        }
        this.view.ShowMsg(Msg.cancelledMsg, 1);
        return false;
    }

    @Override
    public String toString() {
        return "Удалить все игрушки из магазина";
    }
}
