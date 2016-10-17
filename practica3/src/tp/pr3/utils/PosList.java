package tp.pr3.utils;

import java.util.Random;

/**
 * PosList class
 * <p>
 * Creates a list of positions.
 */
public class PosList {
	private final static int MAX = 20;
	
	private Position[] posList;

	// Indicates the number of elements.
	// Coincides with the first free position in the array.
	private int counter = 0;

	/**
	 * PosList constructor
	 */
	public PosList() {
		posList = new Position[MAX];
	}

	/**
	 * Returns true if the list is full of elements.
	 * 
	 * @return If the list is full
	 */
	public boolean full() {
		return counter == MAX;
	}

	/**
	 * Returns true if the list of elements is empty.
	 * 
	 * @return If the list is empty
	 */
	public boolean empty() {
		return counter == 0;
	}

	/**
	 * Returns the length of the list that we control with the integer counter.
	 * 
	 * @return The length of the list
	 */
	public int length() {
		return counter;
	}

	/**
	 * Add a given element in a given position of the list.
	 * 
	 * @param p
	 *            The element we want to add to the list
	 * @param pos
	 *            The position of the list in which we are going to add the
	 *            element
	 * @return If the element was added succesfully
	 */
	public boolean add(Position p, int pos) {
		if (counter == MAX)
			return false;

		else {
			for (int i = counter - 1; i > pos; i--) {
				posList[i] = posList[i - 1];
			}

			posList[pos] = p;

			counter++;
			return true;
		}
	}

	/**
	 * Delete an element from a given position of the list.
	 * 
	 * @param pos
	 *            The position of the list from which we want to delete the
	 *            element
	 * @return If the element was removed succesfully
	 */
	public boolean remove(int pos) {
		if ((pos < 0) || (pos > counter - 1))
			return false;

		else {
			for (int i = pos; i < counter - 1; i++) {
				posList[i] = posList[i + 1];
			}
			counter--;
			return true;
		}
	}

	/**
	 * Accesor method that returns the element saved in a given position.
	 * 
	 * @param pos
	 *            The position of the list from which we want to take the
	 *            element
	 * @return The element saved in the given position passed by parameter
	 */
	public Position get(int pos) {
		if ((pos < 0) || (pos > counter - 1))
			return null;
		else
			return posList[pos];
	}
	
	/**
	 * Swaps randomly the position of two cells of the given posList.
	 * 
	 * @param posList
	 *            A given posList we have to reorder randomly
	 * @param index
	 *            The position of the posList we swap with another randomly
	 */
	public void swap(int index1, int index2) {
		// Select a random position of the posList:
		// Swap the position of the selected ones:
		Position aux = get(index2);
		posList[index2] = get(index1);
		posList[index1] = aux;
	}

	/**
	 * Shuffles all the positions - with the auxiliar method "swap" - of the
	 * given posList.
	 * 
	 * @param posList
	 *            A given posList we have to reorder randomly
	 */
	public void shuffle() {
		Random rand = new Random();
		for (int i = length() - 1; i > 0; i--) {
			swap(i, rand.nextInt(length()));
		}
	}

	/*
	 * Sets a list with all the elements that contains through the console.
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String stringOut = "Elements of the list:\n\n";

		for (int i = 0; i < counter; i++) {
			stringOut += posList[i] + "\n";
		}

		return stringOut;
	}
}
