package log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SimpleLog implements Logger{
    private String filepath;

    public SimpleLog(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public void Log(String message) {
        File file = new File(this.filepath);
        try {
            FileWriter fw = new FileWriter(file, true);
            fw.write(String.format("%s\n", message));
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
