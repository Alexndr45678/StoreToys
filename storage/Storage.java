package storage;

import model.Toy;

import java.util.Map;

public interface Storage {
    public Map<Integer, Toy> load();
    public boolean save (Map<Integer, Toy> toys);
}
