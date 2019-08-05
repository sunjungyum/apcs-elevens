/**********************************************************
 * Assignment: Elevens: Activity 4 (Deck Class)
 *
 * Author: Sun-Jung Yum
 *
 * Description: This class represents a deck of Cards, and holds variables for
 * the dealt and undealt ArrayLists, the top card, and the size of the deck. 
 * It has a boolean to know if it is empty, a method to get the number of
 * cards left, a method for shuffling the deck, and a method for dealing a card.
 *
 * Academic Integrity: I pledge that this program represents my own work. I
 * received help from no one in designing and debugging my program.
 **********************************************************/

package activity4;

import java.util.ArrayList;

/**
 * The Deck class represents a shuffled deck of cards. It provides several
 * operations including initialize, shuffle, deal, and check if empty.
 */
public class Deck
{
	private ArrayList<Card> dealt;
	private ArrayList<Card> undealt;
	private int size;

	/**
	 * Creates a new Deck instance.
	 * 
	 * It pairs each element of ranks with each element of suits, and produces
	 * one of the corresponding card.
	 * 
	 * @param ranks
	 *            is an array containing all of the card ranks.
	 * @param suits
	 *            is an array containing all of the card suits.
	 * @param values
	 *            is an array containing all of the card point values.
	 */
	public Deck(String[] ranks, String[] suits, int[] values)
	{
		if (ranks.length != values.length)
		{
			throw new IllegalArgumentException("The number of ranks and values do not match.");
		}

		dealt = new ArrayList<Card>();
		undealt = new ArrayList<Card>();

		for (int i = 0; i < ranks.length; i++)
		{
			for (int j = 0; j < suits.length; j++)
			{
				Card a = new Card(ranks[i], suits[j], values[i]);
				undealt.add(a);
			}
		}
		size = undealt.size();
		shuffle();
	}

	/**
	 * Determines if this deck is empty (no undealt cards).
	 * 
	 * @return true if this deck is empty, false otherwise.
	 */
	public boolean isEmpty()
	{
		return undealt.size() == 0;
	}

	/**
	 * Accesses the number of undealt cards in this deck.
	 * 
	 * @return the number of undealt cards in this deck.
	 */
	public int getCardsLeft()
	{
		return undealt.size();
	}

	/**
	 * Randomly permute the given collection of cards and reset the size to
	 * represent the entire deck.
	 */
	public void shuffle()
	{
		resetDeck();
		for (int i = 0; i < undealt.size(); i++)
		{
			Card storage = undealt.get(i);
			int tradingCardPlace = (int) (Math.random() * undealt.size());
			undealt.set(i, undealt.get(tradingCardPlace));
			undealt.set(tradingCardPlace, storage);
		}
	}

	/**
	 * Adds back all of the dealt cards into the deck of undealt cards.
	 */
	private void resetDeck()
	{
		while (dealt.size() > 0)
		{
			undealt.add(dealt.get(0));
			dealt.remove(0);
		}
		size = undealt.size();
	}

	/**
	 * Deals a card from this deck.
	 * 
	 * @return the card just dealt, or null if all the cards have been
	 *         previously dealt.
	 */
	public Card deal()
	{
		if (undealt.isEmpty())
		{
			return null;
		}
		else
		{
			Card temp = undealt.get(0);
			dealt.add(temp);
			undealt.remove(0);
			size = undealt.size();
			return temp;
		}
	}
}
