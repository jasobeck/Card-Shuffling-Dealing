//Jane Sobeck
//1/20/2024
//Lab 2: Card Shuffling and Dealing Program
//CS145 2958




/*The purpose of this program is to deal the user a hand
 *of 5 cards from a shuffled deck of 52 cards.
 *Then Determines the ranking of the poker hand
 *and reports the ranking to the user.
*/


//Main initializes Deck object and hand array, 
//then populates the hand by dealing card objects.
public class Test {
    public static void main(String[] args) throws Exception {
        DeckOfCards myDeckOfCards = new DeckOfCards();
        int handSize = 5;
        Card[] hand = new Card[handSize];
        myDeckOfCards.shuffle();
        dealHand(myDeckOfCards, hand);
        DeckOfCards.reportHandRank(hand);
    }

    // Deals a hand of five cards off the top of the deck to the user
    public static void dealHand(DeckOfCards myDeckOfCards, Card[] hand) {
        for (int i = 0; i < hand.length; i++) {
            hand[i] = myDeckOfCards.dealCard();
            System.out.println(hand[i].toString());
        }
    }

}
