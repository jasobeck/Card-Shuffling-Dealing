
//DeckOfCards class represents a deck of playing cards
import java.security.SecureRandom;
import java.util.Arrays;

public class DeckOfCards {
    private static final SecureRandom randomNumbers = new SecureRandom();
    private static final int NUMBER_OF_CARDS = 52;

    private Card[] deck = new Card[NUMBER_OF_CARDS];
    private int currentCard = 0;

    // constructor fills deck of Cards
    public DeckOfCards() {
        String[] faces = { "Ace", "Deuce", "Three", "Four", "Five", "Six",
                "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King" };
        String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };

        // populate deck with Card objects
        for (int count = 0; count < deck.length; count++) {
            deck[count] = new Card(faces[count % 13], suits[count / 13]);
        }
    }

    // shuffle deck of Cards with one-pass algorithm
    public void shuffle() {
        // next call to method dealCard should start at deck[0] again
        currentCard = 0;

        // for each card, pick another random Card (0-51) and swap them
        for (int first = 0; first < deck.length; first++) {
            // select a random number between 0 and 51
            int second = randomNumbers.nextInt(NUMBER_OF_CARDS);

            // swap current Card with randomly selected Card
            Card temp = deck[first];
            deck[first] = deck[second];
            deck[second] = temp;
        }
    }

    // deal one Card
    public Card dealCard() {
        if (currentCard < deck.length) {
            return deck[currentCard++];
        } else {
            return null; // returns null to indicate all cards are dealt
        }
    }

    // Checks hand if it contains a pair and returns the value of the cards that
    // make up the pair
    public static int hasPair(Card[] hand) {
        for (int i = 0; i < hand.length; i++) {
            for (int j = 0; j < hand.length; j++) {
                String faceOne = hand[i].getFace();
                String faceTwo = hand[j].getFace();
                if (faceOne.equals(faceTwo) && i != j) {
                    return CardValue.valueOf(faceOne.toUpperCase()).getCardValue();
                }
            }
        }
        return 0;
    }

    // Checks hand if it contains two pairs and
    // returns the values of the cards making up the pairs in an array.
    public static int[] hasTwoPair(Card[] hand) {
        int pairOne = hasPair(hand);
        int pairTwo = 0;
        int[] handValues = handInValues(hand);
        if (pairOne > 0) {
            for (int i = 0; i < hand.length; i++) {
                if (handValues[i] == pairOne) {
                    handValues[i] = -1;
                }
            }
            Arrays.sort(handValues);
            for (int i = 2; i < hand.length; i++) {
                for (int j = 2; j < hand.length; j++) {
                    if (i != j && handValues[i] == handValues[j]) {
                        pairTwo = handValues[i];
                        if (pairOne > pairTwo) {
                            return new int[] { pairOne, pairTwo };
                        } else {
                            return new int[] { pairTwo, pairOne };
                        }
                    }
                }
            }
            return new int[] { 0, 0 };
        }
        return new int[] { 0, 0 };
    }

    // Checks hand if it contains three of a kind in cards, returns the value of the
    // card with 3 of a kind
    public static int hasThree(Card[] hand) {
        for (int i = 0; i < 5; i++) {
            int count = 0;
            for (int j = 0; j < 5; j++) {
                String faceOne = hand[i].getFace();
                String faceTwo = hand[j].getFace();
                if (faceOne.equals(faceTwo) && i != j) {
                    count++;
                }
                if (count == 2) {
                    return CardValue.valueOf(faceOne.toUpperCase()).getCardValue();
                }
            }
        }
        return 0;
    }

    // Checks hand if it contains four of a kind in cards, returns the value of the
    // card with 4 of a kind
    public static int hasFour(Card[] hand) {
        for (int i = 0; i < 5; i++) {
            int count = 0;
            for (int j = 0; j < 5; j++) {
                String faceOne = hand[i].getFace();
                String faceTwo = hand[j].getFace();
                if (faceOne.equals(faceTwo) && i != j) {
                    count++;
                }
                if (count == 3) {
                    return CardValue.valueOf(faceOne.toUpperCase()).getCardValue();
                }
            }
        }
        return 0;
    }

    // Checks hand to see if it contains a flush, returns highest value card in
    // flush
    public static int hasFlush(Card[] hand) {
        for (int i = 0; i < 5; i++) {
            int count = 0;
            for (int j = 0; j < 5; j++) {
                String suitOne = hand[i].getSuit();
                String suitTwo = hand[j].getSuit();
                if (suitOne.equals(suitTwo) && i != j) {
                    count++;
                }
                if (count == 4) {
                    return Arrays.stream(handInValues(hand)).max().getAsInt();
                }
            }
        }
        return 0;
    }

    // Determines whether the hand contains a straight, and returns the highest card
    // value in that straight
    public static int hasStraight(Card[] hand) {
        int[] handValues = handInValues(hand);
        Arrays.sort(handValues);
        int check = -1;

        // Determines whether to treat an Ace as a 1 or a 14
        if (handValues[4] == 14 && handValues[0] == 2) {
            handValues[4] = 1;
            Arrays.sort(handValues);
        }

        for (int i = 0; i < hand.length; i++) {
            if (check == -1 || (check + 1) == handValues[i]) {
                check = handValues[i];
            } else {
                return 0;
            }
        }
        return handValues[5];
    }

    // Determines whether the given hand contains a full house
    public static int hasHouse(Card[] hand) {
        int hasThree = hasThree(hand);
        int[] handValues = handInValues(hand);
        if (hasThree > 0) {
            for (int i = 0; i < 5; i++) {
                if (handValues[i] == hasThree) {
                    handValues[i] = -1;
                }
            }
            Arrays.sort(handValues);
            if (handValues[3] == handValues[4]) {
                return hasThree;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    // Takes in an array of cards objects (or a hand) and returns them as an array
    // of just the card values as ints
    public static int[] handInValues(Card[] hand) {
        int[] handValues = new int[hand.length];
        for (int i = 0; i < hand.length; i++) {
            handValues[i] = CardValue.valueOf(hand[i].getFace().toUpperCase()).getCardValue();
        }
        return handValues;
    }

    // reports the rank of the user's current hand to the console
    public static void reportHandRank(Card[] hand) {
        if (DeckOfCards.hasStraight(hand) == 14 && DeckOfCards.hasFlush(hand) == 14) {
            System.out.println("Wow!! You have a royal flush!!");
        } else if (DeckOfCards.hasStraight(hand) > 0 && DeckOfCards.hasFlush(hand) > 0) {
            System.out.println("Wow!! You have a straight flush!!");
        } else if (DeckOfCards.hasFour(hand) > 0) {
            System.out.println("You have four of a kind.");
        } else if (DeckOfCards.hasHouse(hand) > 0) {
            System.out.println("You have a full house.");
        } else if (DeckOfCards.hasFlush(hand) > 0) {
            System.out.println("You have a flush.");
        } else if (DeckOfCards.hasStraight(hand) > 0) {
            System.out.println("You have a straight.");
        } else if (DeckOfCards.hasThree(hand) > 0) {
            System.out.println("You have three of a kind.");
        } else if (DeckOfCards.hasTwoPair(hand)[0] > 0) {
            System.out.println("You have two pairs.");
        } else if (DeckOfCards.hasPair(hand) > 0) {
            System.out.println("You have a pair.");
        } else {
            System.out.println("You have a high card.");
        }
    }
}
