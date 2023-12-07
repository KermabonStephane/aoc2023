package org.demis27.aoc2023.day07;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hand implements Comparable<Hand> {

    private Card[] cards = new Card[5];

    private long bid = 0;

    private HandType handType = HandType.HIGH_CARD;

    public Hand(String line, boolean withJoker) {
        String[] split = line.split((" "));
        this.cards = Card.convert(split[0].trim().toCharArray(), withJoker);
        this.bid = Long.parseLong(split[1].trim());
        this.handType = detectValue(cards, withJoker);
    }

    private HandType detectValue(Card[] cards, boolean jokers) {
        Object[] sortedCards = Arrays.stream(cards).sorted().toArray();
        HandType result = HandType.HIGH_CARD;

        if (detectFive(sortedCards)) {
            result = HandType.FIVE;
        } else if (detectFour(sortedCards)) {
            result = HandType.FOUR;
        } else if (detectFull(sortedCards)) {
            result = HandType.FULL_HOUSE;
        } else if (detectThree(sortedCards)) {
            result = HandType.THREE;
        } else if (detectTwoTwo(sortedCards)) {
            result = HandType.TWO_PAIR;
        } else if (detectTwo(sortedCards)) {
            result = HandType.PAIR;
        } else {
            result = HandType.HIGH_CARD;
        }

        if (jokers) {
            int jokersNumbers = numberOfJockers(cards);
            switch (jokersNumbers) {
                case 0: break;
                case 1: {
                    switch (result) {
                        case HIGH_CARD: result = HandType.PAIR; break;
                        case PAIR: result = HandType.THREE;break;
                        case TWO_PAIR: result = HandType.FULL_HOUSE; break;
                        case THREE: result = HandType.FOUR; break;
                        case FOUR: result = HandType.FIVE; break;
                        default: break;
                    }
                    break;
                }
                case 2: {
                    switch (result) {
                        case PAIR: result = HandType.THREE; break;
                        case TWO_PAIR: result = HandType.FOUR; break;
                        case THREE: result = HandType.FIVE; break;
                        case FULL_HOUSE: result = HandType.FIVE; break;
                        default: break;
                    }
                    break;
                }
                case 3: {
                    switch (result) {
                        case THREE: result = HandType.FOUR; break;
                        case FULL_HOUSE: result = HandType.FIVE; break;
                        default: break;
                    }
                    break;
                }
                case 4: {
                    switch (result) {
                        case FOUR: result = HandType.FIVE; break;
                        default: break;
                    }
                    break;
                }
                default: break;
            }
        }

        return result;
    }

    private int numberOfJockers(Card[] cards) {
        return (int) Arrays.stream(cards).filter(c -> c == Card.JOKER).count();
    }

    private boolean detectTwo(Object[] cards) {
        return (cards[0] == cards[1]) ||
                (cards[1] == cards[2]) ||
                (cards[2] == cards[3]) ||
                (cards[3] == cards[4]);
    }

    private boolean detectTwoTwo(Object[] cards) {
        return (cards[0] == cards[1] && cards[2] == cards[3])
                ||
                (cards[0] == cards[1] && cards[3] == cards[4])
                ||
                (cards[1] == cards[2] && cards[3] == cards[4]);
    }

    private boolean detectThree(Object[] cards) {
        return ((cards[0] == cards[1] && cards[1] == cards[2])
                ||
                (cards[1] == cards[2] && cards[2] == cards[3])
                ||
                (cards[2] == cards[3] && cards[3] == cards[4]));
    }

    private boolean detectFull(Object[] cards) {
        return ((cards[0] == cards[1] && cards[1] == cards[2]) && (cards[3] == cards[4])
                ||
                (cards[0] == cards[1]) && (cards[2] == cards[3] && cards[3] == cards[4]));
    }

    private boolean detectFour(Object[] cards) {
        return (cards[0] == cards[1] && cards[1] == cards[2] && cards[2] == cards[3])
                || (cards[1] == cards[2] && cards[2] == cards[3] && cards[3] == cards[4]);
    }

    private boolean detectFive(Object[] cards) {
        return (cards[0] == cards[1] && cards[1] == cards[2] && cards[2] == cards[3] && cards[3] == cards[4]);
    }

    @Override
    public String toString() {
        return "Hand{" +
                "cards=" + Arrays.toString(cards) +
                ", bid=" + bid +
                ", value=" + handType +
                '}';
    }

    @Override
    public int compareTo(Hand o) {
        if (this.getHandType() == o.getHandType()) {
            if (this.getCards()[0].rank == o.getCards()[0].rank) {
                if (this.getCards()[1].rank == o.getCards()[1].rank) {
                    if (this.getCards()[2].rank == o.getCards()[2].rank) {
                        if (this.getCards()[3].rank == o.getCards()[3].rank) {
                            if (this.getCards()[4].rank == o.getCards()[4].rank) {
                                return 0;
                            } else {
                                return (int) (this.getCards()[4].rank - o.getCards()[4].rank);
                            }

                        } else {
                            return (int) (this.getCards()[3].rank - o.getCards()[3].rank);
                        }

                    } else {
                        return (int) (this.getCards()[2].rank - o.getCards()[2].rank);
                    }

                } else {
                    return (int) (this.getCards()[1].rank - o.getCards()[1].rank);
                }

            } else {
                return (int) (this.getCards()[0].rank - o.getCards()[0].rank);
            }
        } else {
            return (int) (this.getHandType().value - o.handType.value);
        }
    }
}
