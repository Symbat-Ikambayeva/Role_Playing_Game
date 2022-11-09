import javax.print.DocFlavor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Realm  {
    private static BufferedReader br;
    private static Personage player = null;
    private static Battle battleScene = null;
    private static Dealer dealer=null;
    private static Shop shopScene=null;

    public static void main(String[] args) {
        br=new BufferedReader(new InputStreamReader(System.in));
        battleScene=new Battle();
        shopScene=new Shop();
        System.out.println("Введите имя персонажа:");
        try {
            command(br.readLine());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void command(String string) throws IOException{
        if(player==null){
            player=new Player(
                    string,
                    100,
                    20,
                    20,
                    0,
                    0
            );
            System.out.println(String.format("Спасти наш мир от драконов вызвался %s! Да будет его броня крепка и бицепс кругл!", player.getName()));
            printNavigation();
        }
        switch (string){
            case "1":{
                /*System.out.println("Торговец еще не приехал");
                command(br.readLine());*/
                commitShop();
            }
            break;
            case "2":{
                commitFight();
            }
            break;
            case "3":
                System.exit(1);
                break;
            case "да":
                command("2");
                break;
            case "нет":{
                printNavigation();
                command(br.readLine());
            }
            break;
            case "yes":
                command("1");
                break;
            case "no":{
                printNavigation();
                command(br.readLine());
            }
            break;

        }
        command(br.readLine());
    }

    private static void printNavigation(){
        System.out.println("Куда вы хотите пойти?");
        System.out.println("1. к торговцу");
        System.out.println("2. в темный лес");
        System.out.println("3. выход");
    }

    private static void commitFight(){
        Realm.FightCallback fightCallback=new FightCallback();
        battleScene.figth(player, createMonster(), fightCallback);
    }

    private static void commitShop(){
        Realm.ShopCallback shopCallback= new ShopCallback();
        Dealer dealer=new Dealer("Торговец зелий", 5, 100);

        shopScene.shopping(dealer, player, shopCallback);
    }

    private static Personage createMonster(){
        int random=(int)(Math.random()*10);
        if(player.getExperience()>50){
            return new Dragon("Дракон", 110, 200, 30, 200, 30);
        }else if(random % 2 ==0) return new Goblin("Гоблин", 50, 10, 10 , 100, 20);
        else return new Skelet("Скелет", 25, 20, 20, 100, 10);
    }

    public static class FightCallback {
        public void fightWin() {
            System.out.println(String.format("%s победил! Теперь у вас %d опыта и %d золота, а также осталось %d единиц здоровья.", player.getName(), player.getEnergy(), player.getGold(),player.getHealth()));
            System.out.println("Желаете продолжить поход или вернуться в город? (да/нет)");
            try{
                command(br.readLine());
            }catch (IOException e){
                e.printStackTrace();
            }
        }


        public void fightLost() {
            try{
                command("no");
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public static class ShopCallback {
        public void shopYes() {
            System.out.println(String.format("%s купил оздоровительное зелье! Теперь у вас %d единиц здоровья и %d золота.", player.getName(), player.getHealth(), player.getGold()));
            System.out.println("Желаете продолжить или вернуться в город? (yes/no)");
            try{
                command(br.readLine());
            }catch (IOException e){
                e.printStackTrace();
            }
        }


        public void shopNo() {
            System.out.println("У вас не достаточно золота!");
            try{
                command("no");
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
