public abstract class Personage implements Fighter{
    private String name;//имя персонажа

    private int skill; //ловкость  dexterity
    private int health;//здоровье healthPoints
    private int experience;//опыт strength

    private int gold;//золото
    private int energy;//сила xp

    public Personage(String name, int skill, int health, int experience, int gold, int energy){
        this.name=name;
        this.skill=skill;
        this.health=health;
        this.experience=experience;
        this.gold=gold;
        this.energy=energy;
    }

    public int attack(){
        if(skill*3>getRandomVlue()) return skill;
        else return 0;
    }
    private int getRandomVlue(){
        return (int)(Math.random()*100);
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public int getSkill(){
        return skill;
    }

    public void setSkill(int skill){
        this.skill=skill;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public String toString(){
        return String.format("%s здоровье: %d", name, health);
    }
}
