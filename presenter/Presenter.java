package presenter;

import commands.Command;
import view.Msg;
import view.View;

import java.util.Map;

public class Presenter {
    private View view;
    private Map<Integer, Command> commands;

    public Presenter (View view, Map<Integer, Command> commands) {
        this.view = view;
        this.commands = commands;
    }

    public void Run() {
        this.view.ShowMsg(Msg.welcomeMsg);
        int cmd = -1;
        while (cmd != 0) {
            this.view.MainMenu();
            cmd = this.view.getInt(Msg.getIndexMsg);
            if (this.commands.containsKey(cmd)) {
                this.commands.get(cmd).execute();
            } else {
                this.view.ShowMsg(Msg.notImplementedMsg);
            }
        }
    }
}
