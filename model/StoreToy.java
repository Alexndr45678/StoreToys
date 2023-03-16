package model;

import exceptions.EmptyExceptions;
import log.Logger;
import storage.Storage;

import java.util.*;

public class StoreToy {
    private Map<Integer, Toy> toys;
    private int toysQty;
    private Queue<Toy> queue;
    private Random random;
    private Storage storage;
    private Logger logger;

    public StoreToy(Storage storage, Logger logger) {
        this.storage = storage;
        this.logger = logger;
        this.toys = new HashMap<Integer, Toy>();
        this.queue = new LinkedList<Toy>();
        this.random = new Random();
        this.toysQty = 0;
    }

    public boolean LoadData() {
        Map<Integer, Toy> loadedToys = storage.load();
        if (loadedToys.isEmpty()) {
            return false;
        }
        this.toys = loadedToys;
        for (Toy toy : this.toys.values()) {
            this.toysQty += toy.getQty();
        }
        return true;
    }

    public boolean SaveData() {
        return this.storage.save(this.toys);
    }

    public Map<Integer, Toy> getToys() {
        return this.toys;
    }

    public void Put(Toy toy) {
        this.toys.put(toy.getId(), toy);
        this.toysQty += toy.getQty();
    }

    public void RemoveByID(int id) {
        int qty = this.toys.get(id).getQty();
        this.toys.remove(id);
        this.toysQty -= qty;
    }

    public void RemoveAll() {
        this.toys.clear();
        this.toysQty = 0;
    }

    public boolean ChangeChance(int id, int chance) {
        if (this.toys.containsKey(id)) {
            this.toys.get(id).setChance(chance);
            this.storage.save(this.toys);
            return true;
        }
        return false;
    }

    public void AddToysToQueue() {
        List<Integer> sortedToysIDs = this.getSortedIDs();
        List<Integer> chanceList = this.getChanceList(sortedToysIDs);

        while (this.toysQty > 0) {
            Toy toy = this.getRandomToy(chanceList, sortedToysIDs);
            if (toy.getQty() > 0) {
                this.queue.add(toy);
                toy.decrQty();
                this.toysQty--;
            }
        }
        this.toys.clear();
    }
    private List<Integer> getSortedIDs() {
        List<Integer> sortedToysIDs = new ArrayList<Integer>();
        for (Integer id : this.toys.keySet()) {
            sortedToysIDs.add(id);
        }
        Collections.sort(sortedToysIDs);
        return sortedToysIDs;
    }
    private List<Integer> getChanceList(List<Integer> ids) {
        List<Integer> chanceList = new ArrayList<Integer>();
        int stepChanceBorder = 0;
        for (Integer id : ids) {
            if (chanceList.size() != 0) {
                stepChanceBorder += chanceList.get(chanceList.size() - 1);
            }
            chanceList.add(this.toys.get(id).getChance() + stepChanceBorder);
        }
        return chanceList;
    }
    private Toy getRandomToy(List<Integer> chanceList, List<Integer> sortedToysIDs) {
        int randInt = random.nextInt(chanceList.get(chanceList.size() - 1));
        int idx = 0;
        for (Integer step : chanceList) {
            if (randInt > step) {
                idx++;
            }
        }
        int id = sortedToysIDs.get(idx);
        return this.toys.get(id);
    }
    public int GetQueueLen() {
        return this.queue.size();
    }
    public Toy Get() throws EmptyExceptions {
        if (this.GetQueueLen() == 0) {
            throw new EmptyExceptions();
        }
        Toy toy = this.queue.poll();
        this.logger.Log(toy.toString());
        return toy;
    }
    public boolean hasToyID(int id) {
        return this.toys.containsKey(id);
    }
}
