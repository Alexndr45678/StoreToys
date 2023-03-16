package commands;

import model.StoreToy;
import view.Msg;
import view.View;

public class Prepare extends BaseCommand{
    public Prepare(StoreToy storeToy, View view) {
        super(storeToy, view);
    }

    @Override
    public boolean execute() {
        if (!super.execute()) {
            return false;
        }

        this.storeToy.AddToysToQueue();
        this.view.ShowMsg(Msg.allToysAddedToQueueMsg, 1);
        return true;
    }

    @Override
    public String toString() {
        return "Подготовить розыгрыш";
    }
}
