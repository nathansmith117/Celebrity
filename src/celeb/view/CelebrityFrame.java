package celeb.view;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import celeb.controller.CelebrityGame;

/**
 * GUI Frame class for the Celebrity Game
 * @author cody.henrichsen
 * @version 2.1 17/09/2018
 */
public class CelebrityFrame extends JFrame
{
	//Data members for the JFrame subclass instance 
	
	/**
	 * The instance of the panel used for playing the game.
	 */
	private CelebrityPanel gamePanel;
	
	/**
	 * Blank panel used for the CardLayout switching screens feature.
	 */
	private JPanel panelCards;
	
	/**
	 * The instance variable used to add a celebrity to the game.
	 */
	private StartPanel startPanel;
	
	/**
	 * A reference to the CelebrityGame instance to allow for minimized coupling in the object structure.
	 */
	private CelebrityGame controller;
	
	
	/**
	 * Builds an instance of the CelebFrame with a reference to the CelebrityGame instance.
	 * @param controller A reference to a CelebrityGame to share with the CelebPanel instance.
	 */
	public CelebrityFrame(CelebrityGame controllerRef)
	{
		//The first line of any subclass should ALWAYS be a correct call to the super constructor.
		super();
		
		this.controller = controllerRef;
		this.gamePanel = new CelebrityPanel(controller);
		this.panelCards = new JPanel(new CardLayout());
		this.startPanel = new StartPanel(controller);
		
		setupFrame();
	
	}
	
	/**
	 * Configures the JFrame window subclass to add the panel and set size based information.
	 */
	private void setupFrame()
	{
		setSize(800, 800);
		setTitle("Celebrity game");
		add(panelCards);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		panelCards.add(gamePanel, "GAME");
		panelCards.add(startPanel, "START");
		
		setVisible(true);
	}
	
	/**
	 * Swaps the screen between the existing panels based on the String parameter.
	 * @param screen The name of the screen to open.
	 */
	public void replaceScreen(String screen)
	{
		if (screen.equals("GAME"))
		{
			gamePanel.addClue(controller.sendClue());
		}
		
		((CardLayout)panelCards.getLayout()).show(panelCards, screen);
	}
}
