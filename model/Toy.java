package model;

public class Toy implements Comparable<Toy> {
    private Integer id;
    private String name;
    private int qty;
    private int chance;

    public Toy(int id, String name, int qty, int chance) {
        this.id = id;
        this.name = name;
        this.qty = qty;
        this.chance = this.checkChance(chance);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQty() {
        return qty;
    }

    public void decrQty() {
        this.qty--;
    }

    public int getChance() {
        return chance;
    }

    public void setChance(int chance) {
        this.chance = this.checkChance(chance);
    }

    private int checkChance(int chance) {
        if (chance < 0 || chance > 100) {
            chance = 50;
        }
        return chance;
    }

    @Override
    public String toString() {
        return String.format(
                "ID %d:\n" +
                        "%s\n" +
                        "Шанс выпадения: %d%%",
                this
                        .getId(), getName(), getChance()
        );
    }

    @Override
    public int compareTo(Toy o) {
        return Integer.compare(this.getId(), o.getId());
    }
}
