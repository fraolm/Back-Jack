//Fraol Dechasa
//fmdechasa18@ole.augie.edu
//BlackJack.java

import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

public class BlackJack {
    private DeckOfCards deck;
    private Vector<String> dealer;
    private Vector<String> player;


    //Post: The BlackJack object properly initialized.  In particular, the deck is shuffled, the
    //      dealer's hand is initialized, the player's hand is initialized, 2 cards were dealt to
    //      the player, 2 cards were dealt to the dealer.
    public BlackJack() {
        deck= new DeckOfCards();
        deck.shuffle();
        dealer = new Vector<String>();
        player = new Vector<String>();
        player.add(deck.deal());
        dealer.add(deck.deal());
        player.add(deck.deal());
        dealer.add(deck.deal());
    }

    //Desc: Plays 1 game of BlackJack.
    //Input: 'H' or 'S' from the user to indicate whether the user wants to hit or stay
    //Output: Various messages indicating the progress of the game
    public void play()
    {
        Scanner f = new Scanner(System.in);
        displayPlayer();
        displayDealer(true);
        while (true){
        System.out.print("You have "+total(player)+". Hit or stay?(h/s) ");
        String s = f.next();

        if ("h".equalsIgnoreCase(s))
        {

            player.add(deck.deal());
            displayPlayer();
            if (total(player)>21)
            {System.out.println("You Busted. Bye Bye"); break;}
        }
        else
            {
                while (total(dealer)<17)
                {
                    dealer.add(deck.deal());
                    if (total(dealer)>21)
                        {
                        System.out.println("Dealer busted. You won. bye bye");
                        break;
                        }
                }

                displayDealer(false);
                break;
            }
        }

        finalprintout();

    }

    //Output: Player's hand displayed on the screen
    private void displayPlayer()
    {
            System.out.println("Your hand");
            for (int i =0; i<player.size();++i)
            {
                    System.out.println("       "+player.get(i));
            }
    }

    //output: prints out appropriate prompt for the outcome of the game.
    private void finalprintout(){

        if(total(player)>total(dealer)&& total(player)<=21)
            System.out.println("The dealer has "+total(dealer)+". You Won. bye bye.");

        else if (total(player)<21&&total(player)<total(dealer))
            System.out.println("Dealer has "+total(dealer)+". You lost. Bye Bye");

        else if (total(player)==total(dealer))
            System.out.println("Its a tie!");

    }

    //Output: Dealer's hand displayed on the screen with 1 card hidden if first is true; all
    //cards of the dealer's hand displayed on the screen if first is false
    private void displayDealer(boolean first)
    {
        System.out.println("Dealers Hand");
        Iterator <String> itr= dealer.iterator();

        if (first){
            System.out.println("      "+itr.next());
            System.out.println("       ********");
        }
        else if (!first)
        {
            while (itr.hasNext())
            {
                System.out.println("  "+itr.next());
            }
        }
    }

    //pre: s contains a string of card names.
    //return: corresponding integer of the string
    public static int numberchange(String s){
        int num =0;
        // For this program i altered Ace card to use 11 for all situations
        if (s.startsWith("Ace"))  num = 11;
        else if (s.startsWith("Two")) num=2;
        else if (s.startsWith("Three")) num =3;
        else if (s.startsWith("Four"))  num=4;
        else if (s.startsWith("Five"))  num=5;
        else if (s.startsWith("Six"))  num =6;
        else if (s.startsWith("Seven"))  num=7;
        else if (s.startsWith("Eight"))  num=8;
        else if (s.startsWith("Nine"))  num=9;
        else if (s.startsWith("Ten")) num=10;
        else if (s.startsWith("Jack"))  num=10;
        else if (s.startsWith("Queen"))  num=10;
        else if (s.startsWith("King"))  num=10;

        return num;
    }

    //Pre: v contains playing cards
    //Return: The numeric total of all the cards in v
    private int total(Vector<String> v)
    {
        int num =0,total=0;
        for (int i =0; i<v.size();++i)
        {
            num =numberchange(v.get(i));
            total +=num;
        }
        return total;
    }


}