package commands;

import model.StoreToy;
import view.Msg;
import view.View;

public class RemoveToy extends BaseCommand{
    public RemoveToy(StoreToy storeToy, View view) {
        super(storeToy, view);
    }

    @Override
    public boolean execute() {
        if (!super.execute()) {
            return false;
        }

        int id = this.view.getInt(Msg.getToyIDMsg);
        if (!this.storeToy.hasToyID(id)) {
            this.view.ShowMsg(Msg.notExistsIDMsg, 1);
            return false;
        }

        this.storeToy.RemoveByID(id);
        this.view.ShowMsg(Msg.toyRemovedMsg, 1);
        return true;
    }

    @Override
    public String toString() {
        return "Удалить игрушку";
    }
}
