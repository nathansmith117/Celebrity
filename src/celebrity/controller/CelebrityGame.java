package celebrity.controller;

import java.util.ArrayList;

import celebrity.model.Celebrity;
import celebrity.model.LiteratureCelebrity;
import celebrity.view.CelebrityFrame;

import javax.swing.JOptionPane;


/**
 * The framework for the Celebrity Game project
 * 
 * @author cody.henrichsen
 * @version 2.3 25/09/2018 refactored the prepareGame and play methods
 */
public class CelebrityGame
{
	/**
	 * A reference to a Celebrity or subclass instance.
	 */
	private Celebrity gameCelebrity;

	/**
	 * The GUI frame for the Celebrity game.
	 */
	private CelebrityFrame gameWindow;

	/**
	 * The ArrayList of Celebrity values that make up the game
	 */
	private ArrayList<Celebrity> celebGameList;

	/**
	 * Builds the game and starts the GUI
	 */
	public CelebrityGame()
	{
		this.gameCelebrity = new Celebrity("", "");
		this.gameWindow = new CelebrityFrame(this);
		this.celebGameList = new ArrayList<Celebrity>();
		prepareGame();
	}

	/**
	 * Prepares the game to start by re-initializing the celebGameList and having the gameFrame open the start screen.
	 */
	public void prepareGame()
	{
		gameCelebrity = new Celebrity("", "");
		celebGameList.clear();
		gameWindow.replaceScreen("START");
	}

	/**
	 * Determines if the supplied guess is correct.
	 * 
	 * @param guess
	 *            The supplied String
	 * @return Whether it matches regardless of case or extraneous external
	 *         spaces.
	 */
	public boolean processGuess(String guess)
	{
		if (gameCelebrity.getAnswer().trim().equalsIgnoreCase(guess.trim()))
		{
			celebGameList.remove(0);
			
			if (celebGameList.size() != 0)
			{
				gameCelebrity = celebGameList.get(0);
			}
			
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Asserts that the list is initialized and contains at least one Celebrity.
	 * Sets the current celebrity as the first item in the list. Opens the game
	 * play screen.
	 */
	public void play()
	{
		if (celebGameList.size() <= 0)
		{
			JOptionPane.showMessageDialog(gameWindow, "No celebrities added to list", "", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		gameCelebrity = celebGameList.get(0);
		gameWindow.replaceScreen("GAME");
	}

	/**
	 * Adds a Celebrity of specified type to the game list
	 * 
	 * @param name
	 *            The name of the celebrity
	 * @param guess
	 *            The clue(s) for the celebrity
	 * @param type
	 *            What type of celebrity
	 */
	public void addCelebrity(String name, String guess, String type)
	{
		if (validateCelebrity(name) && validateClue(guess, type))
		{
			if (type.equalsIgnoreCase("celebrity") || type.equals(""))
			{
				celebGameList.add(new Celebrity(name, guess));
			}
			else if (type.equalsIgnoreCase("literature"))
			{
				celebGameList.add(new LiteratureCelebrity(name, guess));
			}
		}
	}

	/**
	 * Validates the name of the celebrity. It must have at least 4 characters.
	 * @param name The name of the Celebrity
	 * @return If the supplied Celebrity is valid
	 */
	public boolean validateCelebrity(String name)
	{
		return name.length() >= 4;
	}

	/**
	 * Checks that the supplied clue has at least 10 characters or is a series of clues
	 * This method would be expanded based on your subclass of Celebrity.
	 * @param clue The text of the clue(s)
	 * @param type Supports a subclass of Celebrity 
	 * @return If the clue is valid.
	 */
	public boolean validateClue(String clue, String type)
	{
		boolean isValid = false;
		
		if (type != null && clue != null)
		{
			if ((type.equalsIgnoreCase("celebrity") || type.equals("")) && clue.trim().length() >= 10)
			{
				isValid = true;
			}
			else if (type.equalsIgnoreCase("Literature"))
			{
				if (clue.trim().length() >= 10 && clue.indexOf(",") > 4)
				{
					isValid = true;
				}
			}
		}
		
		return isValid;
	}

	/**
	 * Accessor method for the current size of the list of celebrities
	 * 
	 * @return Remaining number of celebrities
	 */
	public int getCelebrityGameSize()
	{
		return celebGameList.size();
	}

	/**
	 * Accessor method for the games clue to maintain low coupling between
	 * classes
	 * 
	 * @return The String clue from the current celebrity.
	 */
	public String sendClue()
	{
		return gameCelebrity.getClue();
	}

	/**
	 * Accessor method for the games answer to maintain low coupling between
	 * classes
	 * 
	 * @return The String answer from the current celebrity.
	 */
	public String sendAnswer()
	{
		return gameCelebrity.getAnswer();
	}
}
