/**********************************************************
 * Assignment: Elevens: Activity 4 (Elevens Board Unit Tests)
 *
 * Author: Sun-Jung Yum
 *
 * Description: These unit tests test all of the different functions of an
 * Elevens Board, including knowing when the game is over, being able to
 * properly deal/replace a card, knowing how many cards are left in the deck,
 * and more.
 *
 * Academic Integrity: I pledge that this program represents my own work. I
 * received help from no one in designing and debugging my program.
 **********************************************************/

package activity4;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

import testHelp.*;

public class ElevensBoardUnitTests
{
	@Test
	public void BoardConstructorShouldNotThrow()
	{
		verify.that(() -> new ElevensBoard()).doesNotThrow();
	}

	@Test
	public void BoardShouldHoldNineCards()
	{
		IBoard board = new ElevensBoard();
		verify.that(board.getBoardSize()).isEqualTo(9);
	}

	@Test
	public void BoardSizeShouldBeNineWhenNull()
	{
		Deck empty = new Deck(new String[] {}, new String[] {}, new int[] {});
		IBoard board = new ElevensBoard(empty);
		verify.that(board.getBoardSize()).isEqualTo(9);
	}

	@Test
	public void BoardShouldStartWithNineCards()
	{
		IBoard board = new ElevensBoard();
		verify.that(board.getCardIndices().size()).isEqualTo(9);
	}

	@Test
	public void NewGameShouldReplaceCards()
	{
		IBoard board = new ElevensBoard();
		ArrayList<Card> firstGame = getCards(board);
		board.newGame();
		ArrayList<Card> secondGame = getCards(board);
		verify.that(firstGame).isNotEqualTo(secondGame);
	}

	@Test
	public void NewGameShouldPutNineCardsOnBoard()
	{
		IBoard board = new ElevensBoard();
		board.newGame();
		ArrayList<Card> secondGame = getCards(board);
		verify.that(board.getCardIndices().size()).isEqualTo(9);
	}

	@Test
	public void DealShouldReplaceCardAtFirstIndex()
	{
		IBoard board = new ElevensBoard();
		Card first = board.getCard(0);
		board.deal(0);
		Card second = board.getCard(0);
		verify.that(first).isNotEqualTo(second);
	}

	@Test
	public void DealShouldReplaceCardAtSecondToEighthIndex()
	{
		IBoard board = new ElevensBoard();
		for (int i = 1; i < 8; i++)
		{
			Card first = board.getCard(i);
			board.deal(i);
			Card second = board.getCard(i);
			verify.that(first).isNotEqualTo(second);
		}
	}

	@Test
	public void dealShouldReplaceCardAtLastIndex()
	{
		IBoard board = new ElevensBoard();
		Card first = board.getCard(8);
		board.deal(8);
		Card second = board.getCard(8);
		verify.that(first).isNotEqualTo(second);
	}

	@Test
	public void dealShouldSetToNullIfDeckIsEmpty()
	{
		Deck d = new Deck(new String[] { "rank1", "rank2", "rank3" }, new String[] { "suit1", "suit2", "suit3" },
				new int[] { 1, 2, 3 });
		IBoard board = new ElevensBoard(d); // will have 9 cards on table, none
											// left in deck
		board.deal(0);
		verify.that(board.getCard(0)).isEqualTo(null);
	}

	@Test
	public void getCardsLeftInDeckReturnsZeroWhenEmpty()
	{
		Deck d = new Deck(new String[] { "rank1", "rank2", "rank3" }, new String[] { "suit1", "suit2", "suit3" },
				new int[] { 1, 2, 3 });
		IBoard board = new ElevensBoard(d); // will have 9 cards on table, none
											// left in deck
		verify.that(board.getCardsLeftInDeck()).isEqualTo(0);
	}

	@Test
	public void getCardsLeftInDeckReturnsCorrectNumberWhenNotEmpty()
	{
		Deck d = new Deck(new String[] { "rank1", "rank2", "rank3", "rank4" },
				new String[] { "suit1", "suit2", "suit3" }, new int[] { 1, 2, 3, 4 });
		IBoard board = new ElevensBoard(d); // will have 9 cards on table, 3
											// left in deck
		verify.that(board.getCardsLeftInDeck()).isEqualTo(3);
	}

	@Test
	public void getCardReturnsNullWhenEmpty()
	{
		Deck d = new Deck(new String[] { "rank1", "rank2", "rank3" }, new String[] { "suit1", "suit2", "suit3" },
				new int[] { 1, 2, 3 });
		IBoard board = new ElevensBoard(d); // will have 9 cards on table, none
											// left in deck
		board.deal(0);
		verify.that(board.getCard(0)).isEqualTo(null);
	}

	@Test
	public void getCardReturnsCardWhenNotEmpty()
	{
		Deck d = new Deck(new String[] { "rank1", "rank2", "rank3" }, new String[] { "suit1", "suit2", "suit3" },
				new int[] { 1, 2, 3 });
		IBoard board = new ElevensBoard(d); // will have 9 cards on table, none
											// left in deck
		verify.that(board.getCard(0)).isNotEqualTo(null);
	}

	@Test
	public void replaceSelectedCardsShouldResultInDifferentCardsWhenReplacingAll()
	{
		Deck d = new Deck(new String[] { "rank1", "rank2", "rank3", "rank4", "rank5", "rank6" },
				new String[] { "suit1", "suit2", "suit3" }, new int[] { 1, 2, 3, 4, 5, 6 });
		IBoard board = new ElevensBoard(d); // will have 9 cards on table, 9
											// left in deck
		ArrayList<Card> first = getCards(board);

		ArrayList<Integer> replace = new ArrayList<Integer>();
		for (int i = 0; i < 9; i++)
		{
			replace.add(i);
		}
		board.replaceSelectedCards(replace);
		ArrayList<Card> second = getCards(board);
		verify.that(first).isNotEqualTo(second);
	}

	@Test
	public void replaceSelectedCardsShouldResultInSomeSameCardsWhenReplacingSome()
	{
		Deck d = new Deck(new String[] { "rank1", "rank2", "rank3", "rank4", "rank5", "rank6" },
				new String[] { "suit1", "suit2", "suit3" }, new int[] { 1, 2, 3, 4, 5, 6 });
		IBoard board = new ElevensBoard(d); // will have 9 cards on table, 9
											// left in deck
		ArrayList<Card> first = getCards(board);

		ArrayList<Integer> replace = new ArrayList<Integer>();
		for (int i = 0; i < 4; i++)
		{
			replace.add(i);
		}
		System.out.println(replace.toString());
		board.replaceSelectedCards(replace);
		ArrayList<Card> second = getCards(board);

		for (int i = 0; i < 4; i++)
		{
			verify.that(first.get(i)).isNotEqualTo(second.get(i));
		}
		for (int i = 4; i < 9; i++)
		{
			verify.that(first.get(i)).isEqualTo(second.get(i));
		}
	}

	@Test
	public void getCardIndicesShouldReturnsEmptyArrayListIfBoardIsEmpty()
	{
		Deck empty = new Deck(new String[] {}, new String[] {}, new int[] {});
		IBoard board = new ElevensBoard(empty);
		verify.that(board.getCardIndices().size()).isEqualTo(0);
	}

	@Test
	public void TableSlotsAreEmptyWhenDeckRunsOut()
	{
		Deck d = new Deck(new String[] { "rank1", "rank2" }, new String[] { "suit1" }, new int[] { 1, 2 });
		IBoard board = new ElevensBoard(d);
		verify.that(board.getCardsLeftInDeck()).isEqualTo(0);
		verify.that(board.getCardIndices()).isEqualTo(Arrays.asList(new Integer[] { new Integer(0), new Integer(1) }));
	}

	@Test
	public void gameIsWonIfDeckAndTableAreClear()
	{
		Deck empty = new Deck(new String[] {}, new String[] {}, new int[] {});
		IBoard board = new ElevensBoard(empty);
		verify.that(board.gameIsWon()).isTrue();
	}

	@Test
	public void gameIsNotWonIfDeckContainsCards()
	{
		IBoard board = new ElevensBoard();
		verify.that(board.gameIsWon()).isFalse();
	}

	@Test
	public void gameIsNotWonIfTableHasCards()
	{
		Deck d = new Deck(new String[] { "rank1", "rank2" }, new String[] { "suit1" }, new int[] { 1, 2 });
		IBoard board = new ElevensBoard(d); // will have 2 cards on table, none
											// left in deck
		verify.that(board.gameIsWon()).isFalse();
	}

	@Test
	public void isEmptyReturnsFalseWhenBoardIsPartlyFull()
	{
		Deck d = new Deck(new String[] { "rank1", "rank2" }, new String[] { "suit1" }, new int[] { 1, 2 });
		IBoard board = new ElevensBoard(d);
		verify.that(board.isEmpty()).isFalse();
	}

	@Test
	public void isEmptyReturnsFalseWhenBoardIsCompletelyFull()
	{
		IBoard board = new ElevensBoard();
		verify.that(board.isEmpty()).isFalse();
	}

	@Test
	public void isEmptyReturnsTrueWhenBoardStartsEmpty()
	{
		Deck empty = new Deck(new String[] {}, new String[] {}, new int[] {});
		IBoard board = new ElevensBoard(empty);
		verify.that(board.isEmpty()).isTrue();
	}

	@Test
	public void isEmptyReturnsTrueWhenAllCardsOnBoardAreTakenOff()
	{
		Deck d = new Deck(new String[] { "rank1", "rank2" }, new String[] { "suit1" }, new int[] { 1, 2 });
		IBoard board = new ElevensBoard(d);
		ArrayList<Integer> replace = new ArrayList<Integer>();
		replace.add(0);
		replace.add(1);
		board.replaceSelectedCards(replace);
		verify.that(board.isEmpty()).isTrue();
	}

	@Test
	public void isLegalReturnsFalseForIndicesLessThanZero()
	{
		Deck d = new Deck(new String[] { "rank1", "rank2" }, new String[] { "suit1" }, new int[] { 1, 2 });
		IBoard board = new ElevensBoard(d);
		ArrayList<Integer> test = new ArrayList<Integer>();
		test.add(-3);
		verify.that(board.isLegal(test)).isFalse();
	}

	@Test
	public void isLegalReturnsFalseForIndicesGreaterThanEight()
	{
		Deck d = new Deck(new String[] { "rank1", "rank2" }, new String[] { "suit1" }, new int[] { 1, 2 });
		IBoard board = new ElevensBoard(d);
		ArrayList<Integer> test = new ArrayList<Integer>();
		test.add(9);
		verify.that(board.isLegal(test)).isFalse();
	}

	@Test
	public void isLegalReturnsTrueForIndicesBetweenZeroAndEight()
	{
		Deck d = new Deck(new String[] { "rank1", "rank2" }, new String[] { "suit1" }, new int[] { 1, 2 });
		IBoard board = new ElevensBoard(d);
		ArrayList<Integer> test = new ArrayList<Integer>();
		for (int i = 0; i < 9; i++)
		{
			test.add(i);
		}
		verify.that(board.isLegal(test)).isTrue();
	}

	@Test
	public void anotherPlayIsPossibleReturnsTrueIfNumbersAddUpToEleven()
	{
		Deck d = new Deck(new String[] { "Ace", "Ten" }, new String[] { "Hearts" }, new int[] { 1, 10 });
		IBoard board = new ElevensBoard(d);
		verify.that(board.anotherPlayIsPossible()).isTrue();
	}

	@Test
	public void anotherPlayIsPossibleReturnsTrueIfTrioFaceCards()
	{
		Deck d = new Deck(new String[] { "Jack", "Queen", "King" }, new String[] { "Hearts" },
				new int[] { 11, 12, 13 });
		IBoard board = new ElevensBoard(d);
		verify.that(board.anotherPlayIsPossible()).isTrue();
	}

	@Test
	public void anotherPlayIsPossibleReturnsFalseIfNoPossibilities()
	{
		Deck d = new Deck(new String[] { "Ace" }, new String[] { "Hearts", "Diamonds" }, new int[] { 1 });
		IBoard board = new ElevensBoard(d);
		verify.that(board.anotherPlayIsPossible()).isFalse();
	}

	private static ArrayList<Card> getCards(IBoard board)
	{
		ArrayList<Card> cards = new ArrayList<Card>();
		for (Integer i : board.getCardIndices())
			cards.add(board.getCard(i));
		return cards;
	}
}