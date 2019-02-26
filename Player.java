import java.util.*;
import java.util.Random;
import java.util.Scanner;
public class Player {
    Map map;
    public static void main(String[] args){
        ArrayList<Integer> stats = new ArrayList<Integer>();
        Random randStats = new Random();
            for (int i = 0; i <= 5; i++) {
                int n = randStats.nextInt(10);
                n += 1;
                stats.add(n);
            }

            System.out.println("Strength:" + stats.get(0));
            System.out.println("Defense:" + stats.get(1));
            System.out.println("Attack:" + stats.get(2));
            System.out.println("Speed:" + stats.get(3));
            System.out.println("Luck:" + stats.get(4));

    }
    public Player(Map map){
        this.map = map;
    }
}
