// Jane Sobeck
// 1/20/2024
// CS 145 Lab 2
//The purpose of this java file is to be an enum representing card value and name

//An enum to allow retreiving of the cards value based off of the name of the face

public enum CardValue {
    DEUCE(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(11),
    QUEEN(12),
    KING(13),
    ACE(14);

    private int cardValue;

    // sets the enum's cardValue variable to the value of the chosen card
    private CardValue(int value) {
        this.cardValue = value;
    }

    // returns the card's value as an int, using the card name in an enum
    public int getCardValue() {
        return cardValue;
    }
}
