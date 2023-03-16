package commands;

import model.StoreToy;
import view.Msg;
import view.View;

public class Exit extends BaseCommand{
    public Exit(StoreToy storeToy, View view){
        super(storeToy, view);
    }

    @Override
    public boolean execute() {
        if (this.view.getBoolean(Msg.askSaveDataMsg)){
            this.storeToy.SaveData();
        }
        this.view.ShowMsg(Msg.byeMsg);
        return true;
    }

    @Override
    public String toString() {
        return "Выход";
    }
}
