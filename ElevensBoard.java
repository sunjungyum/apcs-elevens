/**********************************************************
 * Assignment: Elevens: Activity 4 (Elevens Board)
 *
 * Author: Sun-Jung Yum
 *
 * Description: This class represents an Elevens Board for playing the game
 * Elevens. It has a deck, as well as an array of Cards that represents the
 * nine cards that are on the board. It can create a new game, get the board's
 * size, know when it's empty, deal a new card, get the number of cards left
 * in the deck, get the value of a certain card, replace multiple cards, get
 * the valid cards' indices, know when the game is over, know if a selection of
 * cards is legla, and know if another play is possible.
 *
 * Academic Integrity: I pledge that this program represents my own work. I
 * received help from no one in designing and debugging my program.
 **********************************************************/

package activity4;
import java.util.ArrayList;
import java.util.List;

import testHelp.verify;

public class ElevensBoard implements IBoard
{
    private Deck cards;
    private Card[] onBoard = new Card[9];

    public ElevensBoard()
    {
        String[] suits = {"Diamonds", "Hearts", "Spades", "Clubs"};
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        cards = new Deck(ranks, suits, values);
        newGame();
    }

    public ElevensBoard(Deck d)
    {
        cards = d;
        newGame();
    }

	@Override
	public void newGame()
	{
		cards.shuffle();
		for (int i = 0; i < 9; i++)
		{
			onBoard[i] = cards.deal();
		}
	}

	@Override
	public int getBoardSize()
	{
		return onBoard.length;
	}

	@Override
	public boolean isEmpty()
	{
		return getCardIndices().size() == 0;
	}

	@Override
	public void deal(int k)
	{
		onBoard[k] = cards.deal();
	}

	@Override
	public int getCardsLeftInDeck()
	{
		return cards.getCardsLeft();
	}

	@Override
	public Card getCard(int k)
	{
		return onBoard[k];
	}

	@Override
	public void replaceSelectedCards(List<Integer> selectedCards)
	{
		if (isLegal(selectedCards) == false)
		{
			throw new IllegalArgumentException("The selected cards are not valid for removal.");
		}
		for (int i = 0; i < selectedCards.size(); i++)
		{
			onBoard[selectedCards.get(i)] = cards.deal();
		}	
	}

	@Override
	public List<Integer> getCardIndices()
	{
		ArrayList<Integer> indices = new ArrayList<Integer>();
		for (int i = 0; i < 9; i++)
		{
			if (onBoard[i] != null)
			{
				indices.add(i);
			}
		}
		return indices;
	}

	@Override
	public boolean gameIsWon()
	{
		return getCardIndices().size() == 0 && cards.isEmpty();
	}

	@Override
	public boolean isLegal(List<Integer> selectedCards)
	{
		int counter = 0;
		for (int i = 0; i < selectedCards.size(); i++)
		{
			if (selectedCards.get(i) < 0 || selectedCards.get(i) > 8)
			{
				counter++;
			}
		}
		return counter == 0;
	}

	@Override
	public boolean anotherPlayIsPossible()
	{
		int jack = 0;
		int queen = 0;
		int king = 0;
		List<Integer> possibilities = getCardIndices();
		for (int i = 0; i < possibilities.size(); i++)
		{
			for (int j = 0; j < possibilities.size(); j++)
			{
				if (getCard(possibilities.get(i)).getValue() + getCard(possibilities.get(j)).getValue() == 11)
				{
					return true;
				}
			}
			if (getCard(i).getRank() == "Jack")
			{
				jack++;
			}
			if (getCard(i).getRank() == "Queen")
			{
				queen++;
			}
			if (getCard(i).getRank() == "King")
			{
				king++;
			}
		}
		if (jack > 0 && queen > 0 && king > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public static void main(String[] args)
	{
		Deck d = new Deck(new String[] {"Jack", "Queen", "King"}, new String[] {"Hearts"}, new int[] {11, 12, 13});
        IBoard board = new ElevensBoard(d);
        if (board.anotherPlayIsPossible())
        {
        	System.out.println("hey");
        }
	}
	

}