import javax.xml.stream.events.EndElement;
import java.util.*;
import java.util.Random;
import java.util.Scanner;
public class Player {
    int[] stats = new int[2];
    ArrayList<Item> inventory = new ArrayList<Item>();
    int health = 100;// default health
    int attack = 50;//default attack damage
    Map currentRoom;
    /*
      Generate 5 random stats for each category:
      Strength, Defense, Attack, Luck, Speed
     */
    public void generateStats() {
        Random randStat = new Random();
        for (int i = 0; i < 2; i++) {
            int randomStat = randStat.nextInt(10);
            randomStat += 1;
            stats[i] = randomStat;

        }

    }

    public void printStats(int[] stats){
        System.out.println("Attack:"+stats[0]);
        System.out.println("Defense:"+stats[1]);
        //System.out.println("Speed:"+stats[2]);
        //System.out.println("Luck:"+stats[3]);
        //System.out.println("Strength:"+stats[4]);
    }

    public void addItemToInventory(Item item){
        System.out.println("you picked up:" + item);
        inventory.add(item);

    }

    //public void addItemStatToPlayer(Item itemValue){

    //}


    public void updateHealth(){
        // Adds health depending on the stats that you get in the beginning
        int newHealth = health + (stats[1]*10);
    }

    public void updateAttack(){
        // Adds damage depending on the stats that you get in the beginning
        int newAttack = attack + (stats[0]);
    }

    public void attack(Enemy enemy){
        enemy.health = enemy.health - updateAttack();
        System.out.println("You did " + attack + " damage to the " + enemy.name);
        System.out.println("The " + enemy.name + " has " + enemy.health + " health left.");
    }

    public Map getCurrentRoom(){
        return this.currentRoom;
    }

    public void setCurrentRoom(Map currentRoom){
        this.currentRoom = currentRoom;
    }










































}