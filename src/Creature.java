public class Creature implements Fighter {
    private String name;
    private int hp;
    private int agility;
    private int exp;
    private int strength;
    private int gold;

    public String getName() {
        return name;
    }

    public Creature(String name, int hp, int agility, int exp, int strength, int gold) {
        this.name = name;
        this.hp = hp;
        this.agility = agility;
        this.exp = exp;
        this.strength = strength;
        this.gold = gold;
    }

    @Override
    public int attack() {
        if (agility * 3 > getRandomValue()) return strength;
        else return 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getRandomValue() {
        return (int) (Math.random() * 100);
    }

    @Override
    public String toString() {
        return String.format(name + ": " + hp + "hp");
    }
}
