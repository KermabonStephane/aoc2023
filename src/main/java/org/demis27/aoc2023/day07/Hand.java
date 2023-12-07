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

    private long value = 0;

    private long rank = 0;

    public Hand(String line) {
        String[] split = line.split((" "));
        String cards = split[0].trim();
        this.cards = Card.convert(cards.toCharArray(), false);
        this.bid = Long.parseLong(split[1].trim());
        value = detectValue(this.cards, false);
    }

    public Hand(String line, boolean joker) {
        String[] split = line.split((" "));
        String cards = split[0].trim();
        this.cards = Card.convert(cards.toCharArray(), joker);
        this.bid = Long.parseLong(split[1].trim());
        value = detectValue(this.cards, joker);

    }

    public Hand(Card[] cards, long bid) {
        this.cards = cards;
        this.bid = bid;
        value = detectValue(cards, false);
    }

    public Hand(Card[] cards, long bid, boolean jokers) {
        this.cards = cards;
        this.bid = bid;
        value = detectValue(cards, jokers);
    }
    private long detectValue(Card[] cards, boolean jokers) {
        Object[] sortedCards = Arrays.stream(cards).sorted().toArray();
        int result = 0;

        if (detectFive(sortedCards)) {
            result = 6;
        } else if (detectFour(sortedCards)) {
            result = 5;
        } else if (detectFull(sortedCards)) {
            result = 4;
        } else if (detectThree(sortedCards)) {
            result = 3;
        } else if (detectTwoTwo(sortedCards)) {
            result = 2;
        } else if (detectTwo(sortedCards)) {
            result = 1;
        } else {
            result = 0;
        }

        if (jokers) {
            int jokersNumbers = numberOfJockers(cards);
            switch (jokersNumbers) {
                case 0: break;
                case 1: {
                    switch (result) {
                        case 0: result = 1; break;
                        case 1: result = 3;break;
                        case 2: result = 4; break;
                        case 3: result = 5; break;
                        case 5: result = 6; break;
                        default: break;
                    }
                    break;
                }
                case 2: {
                    switch (result) {
                        case 1: result = 3; break;
                        case 2: result = 5; break;
                        case 3: result = 6; break;
                        case 4: result = 6; break;
                        default: break;
                    }
                    break;
                }
                case 3: {
                    switch (result) {
                        case 3: result = 5; break;
                        case 4: result = 6; break;
                        default: break;
                    }
                    break;
                }
                case 4: {
                    switch (result) {
                        case 5: result = 6; break;
                        default: break;
                    }
                    break;
                }
                default: break;
            }
        }

        return (long)result;
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
                ", value=" + value +
                ", rank=" + rank +
                '}';
    }

    @Override
    public int compareTo(Hand o) {
        if (this.getValue() == o.getValue()) {
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
            return (int) (this.getValue() - o.value);
        }
    }


}
