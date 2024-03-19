// Jane Sobeck
// 1/20/2024
// CIS 145 Lab 2
// The purpose of this Java file is to be a Card class for creating card objects

//Card class represents a playing card
public class Card {
    private final String face, suit;

    // two-argument contstructor initializes card's face and suit
    public Card(String cardFace, String cardSuit) {
        this.suit = cardSuit;
        this.face = cardFace;
    }

    // Returns both the suit and face of the card as a string
    public String toString() {
        return face + " of " + suit;
    }

    // Returns the suit of the card as a string
    public String getSuit() {
        return suit;
    }

    // Returns the face of the card as a string
    public String getFace() {
        return face;
    }
}
