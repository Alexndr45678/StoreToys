package view;

public interface View {
    public void MainMenu();
    public int getInt(String message);
    public int getPercent(String message);
    public String getString(String message);
    public boolean getBoolean(String message);
    public void ShowMsg(String msg);
    public void ShowMsg(String msg, int newLines);
}
