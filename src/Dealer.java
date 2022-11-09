public class Dealer implements Seller{
    private String name;
    private int count_potion;
    private int price;

    public Dealer(String name, int count_potion, int price){
        this.name=name;
        this.count_potion=count_potion;
        this.price=price;
    }
    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }

    public void setCount_potion(int count_potion){
        this.count_potion=count_potion;
    }

    public int getCount_potion(){
        return count_potion;
    }

    public void setPrice(int price){
        this.price=price;
    }

    public int getPrice(){
        return price;
    }

    public String sell(Goods goods){
        String result="";
        if(goods==Goods.POTION){
            result="potion";
        }
        return result;
    }

    public enum Goods{
        POTION
    }
}
