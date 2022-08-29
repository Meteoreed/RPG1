public class Battle {
    public static void fight(Character player, Creature monster, Realm.FightCallback fightCallback) {
        Runnable runnable = () -> {
            int turn = 1;
            boolean fightIsEnd = false;
            while (!fightIsEnd) {
                System.out.println("---- turn" + turn + " ----");
                if (turn++ % 2 != 0) {
                    fightIsEnd = makeHit(player, monster, fightCallback);
                } else {
                    fightIsEnd = makeHit(monster, player, fightCallback);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    private static boolean makeHit(Creature monster, Creature character, Realm.FightCallback fightCallback) {
        int hit = character.attack();
        int defenderHealth = monster.getHp() - hit;
        if (hit != 0) {
            System.out.println(String.format("%s damage %d points!", character.getName(), hit));
            System.out.println(String.format("%s have %d hp", monster.getName(), defenderHealth));
        } else {
            System.out.println(String.format("%s miss", character.getName()));
        }
        if (defenderHealth <= 0 && monster instanceof Character) {
            System.out.println("Hero dead");
            fightCallback.fightLost();
            return true;
        } else if (defenderHealth <= 0) {
            character.setExp(character.getExp() + monster.getExp());
            character.setGold(character.getGold() + monster.getGold());
            fightCallback.fightWin();
            return true;
        } else {
            monster.setHp(defenderHealth);
            return false;
        }
    }

}