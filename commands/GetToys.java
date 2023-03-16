package commands;

import exceptions.EmptyExceptions;
import model.StoreToy;
import model.Toy;
import view.Msg;
import view.View;

public class GetToys extends BaseCommand{
    public GetToys(StoreToy storeToy, View view) {
        super(storeToy, view);
    }

    @Override
    public boolean execute() {
        if (this.storeToy.GetQueueLen() == 0) {
            this.view.ShowMsg(Msg.emptyQueueMsg, 1);
            return false;
        }

        while (this.storeToy.GetQueueLen() > 0) {
            try {
                Toy toy = this.storeToy.Get();
                this.view.ShowMsg(toy.toString());
            } catch (EmptyExceptions e) {
                this.view.ShowMsg(e.getMessage());
                return false;
            }
        }
        this.view.ShowMsg("");
        return true;
    }

    @Override
    public String toString() {
        return "Получить все игрушки";
    }
}
