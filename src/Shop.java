public class Shop {

    public void shopping(Dealer dealer, Personage player, Realm.ShopCallback shopCallback) {

        if ((player.getGold() >= dealer.getPrice()) && (dealer.getCount_potion() > 0)) {
            dealer.setCount_potion(dealer.getCount_potion() - 1);
            player.setGold(player.getGold() - dealer.getPrice());
            player.setHealth(player.getHealth() + 50);
            shopCallback.shopYes();
        } else {
            shopCallback.shopNo();
        }

    }


}
