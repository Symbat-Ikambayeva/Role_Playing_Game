public class Battle {
    public void figth(Personage player, Personage monster, Realm.FightCallback fightCallback) {
        Runnable runnable=()->{
            int turn=1;
            boolean isFightEnded=false;
            while(!isFightEnded){
                System.out.println("Ход: "+turn+" ----");
                if(turn++ %2!=0){
                    isFightEnded=makeHit(monster, player, fightCallback);
                }else{
                    isFightEnded=makeHit( player, monster, fightCallback);
                }
            }
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        };
        Thread thread=new Thread(runnable);
        thread.start();
    }
    //удар
    private Boolean makeHit(Personage defender, Personage attacker, Realm.FightCallback fightCallback){
        //сила удара
        int hit=attacker.attack();
        /*if(hit<attacker.getEnergy()){
            hit=hit*2;
        }*/
        int deferenderHealth=defender.getHealth()-hit;
        if(hit!=0){
            System.out.println(String.format("%s Нанес удар  в  %d единиц", attacker.getName(), hit));
            System.out.println(String.format("У %s осталось %d единиц здоровья ...", defender.getName(), deferenderHealth));
        }else{
            System.out.println(String.format("%s промахнулся!", attacker.getName()));
        }
        if(deferenderHealth<=0 && defender instanceof Player){
            System.out.println("Извините, вы пали в бою...");
            fightCallback.fightLost();
            return false;
        }else if(deferenderHealth<=0){
            System.out.println(String.format("Враг повержен! Вы получаете %s опыт и %d золота", defender.getEnergy(), defender.getGold()));
            attacker.setEnergy(attacker.getEnergy()+defender.getEnergy());
            attacker.setExperience(attacker.getExperience()+defender.getExperience());
            attacker.setGold(attacker.getGold()+defender.getGold());
            fightCallback.fightWin();
            return true;
        }else {
            defender.setHealth(deferenderHealth);
            return false;
        }
    }
}
