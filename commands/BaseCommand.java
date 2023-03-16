package commands;

import model.StoreToy;
import view.Msg;
import view.View;

public class BaseCommand implements Command{
    protected StoreToy storeToy;
    protected View view;

    protected BaseCommand (StoreToy storeToy, View view){
        this.storeToy = storeToy;
        this.view = view;
    }

    @Override
    public boolean execute() {
        if (this.storeToy.getToys().isEmpty()) {
            this.view.ShowMsg(Msg.emptyStoreMsg, 1);
            return false;
        }
        return true;
    }
}
