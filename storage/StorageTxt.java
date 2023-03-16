package storage;

import model.Toy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StorageTxt implements Storage {
    private String filepath;

    public StorageTxt(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public Map<Integer, Toy> load() {
        Map<Integer, Toy> toys = new HashMap<Integer, Toy>();
        try {
            Scanner scanner = new Scanner(new File(this.filepath));
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(" ");
                Toy toy = this.parseToy(line);
                if (toy == null) {
                    continue;
                }
                toys.put(toy.getId(), toy);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return toys;
    }

    @Override
    public boolean save(Map<Integer, Toy> toys) {
        File file = new File(this.filepath);
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(prepareData(toys));
            fw.close();
            return true;
        } catch (IOException e) {
            System.out.println(e);
        }
        return false;
    }

    private Toy parseToy(String[] line) {
        try {
            int id = Integer.parseInt(line[0]);
            String name = line[1];
            int qty = Integer.parseInt(line[2]);
            int chance = Integer.parseInt(line[3]);
            Toy toy = new Toy(id, name, qty, chance);
            return toy;
        } catch (NumberFormatException e) {
            System.out.printf("Ошибка: %s\n", e);
        }
        return null;
    }

    private String prepareData(Map<Integer, Toy> toys) {
        StringBuilder sb = new StringBuilder();
        for (Toy toy : toys.values()) {
            sb.append(String.format(
                            "d %s %d %d\n",
                            toy.getId(),
                            toy.getName(),
                            toy.getQty(),
                            toy.getChance()
                    )
            );
        }
        return sb.toString();
    }
}
