package commands;

import model.StoreToy;
import view.Msg;
import view.View;

public class ChangeChanceToy extends BaseCommand{
    public ChangeChanceToy(StoreToy storeToy, View view) {
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

        int chance = this.view.getPercent(Msg.getToyChanceMsg);
        this.storeToy.ChangeChance(id, chance);
        this.view.ShowMsg(Msg.changeToyChanceMsg, 1);
        return true;
    }

    @Override
    public String toString() {
        return "Изменить шанс выпадения игрушки";
    }
}
