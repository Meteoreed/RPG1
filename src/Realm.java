import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Realm {
    private static BufferedReader br;
    private static Character player = null;
    private static Battle battle = null;

    public static void main(String[] args) {
        br = new BufferedReader(new InputStreamReader(System.in));
        battle = new Battle();
        System.out.println("Enter name of character");
        try {
            command(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void command(String string) throws IOException {
        if (player == null) {
            player = new Character(string, 100, 10, 0, 20, 0);
            System.out.println(String.format("Hello %s! Let's go to the adventure", player.getName()));
            printNavigation();
        }
        switch (string) {
            case "1": {
                System.out.println("Tradesman");
                command(br.readLine());
            }
            break;
            case "2": {
                commitFight();
            }
            break;
            case "3":
                System.exit(1);
                break;
            case "yes":
                command("2");
            case "no": {
                printNavigation();
                command(br.readLine());
            }
        }
        command(br.readLine());
    }

    private static void printNavigation() {
        System.out.println("Where are you going?");
        System.out.println("1. To tradesman");
        System.out.println("2. To dungeon");
        System.out.println("3. Exit");
    }

    public static void commitFight() {
        Battle.fight(player, createMonster(), new FightCallback() {

            @Override
            public void fightLost() {
                System.exit(1);

            }

            @Override
            public void fightWin() {
                System.out.println(String.format("%s win! You get %d exp and %d gold", player.getName(), player.getExp(), player.getGold()));
                System.out.println("Continue or return to the village? (yes/no)");
                try {
                    command(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static Creature createMonster() {
        int random = (int) (Math.random() * 10);
        if (random % 2 == 0) return new Goblin("Goblin", 30, 20, 10, 10, 100);
        else return new Skeleton("Skeleton", 20, 10, 10, 5, 50);
    }

    public interface FightCallback {
        void fightLost();

        void fightWin();
    }
}
