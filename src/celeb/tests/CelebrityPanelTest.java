package celeb.tests;

/*
 * Project imports
 */
import celeb.view.CelebrityPanel;
import celeb.controller.CelebrityGame;
/*
 * Reflection imports
 */
import java.lang.reflect.*;
import java.awt.*;
import javax.swing.*;

/*
 * Testing imports
 */
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CelebrityPanelTest
{
	private CelebrityPanel testedPanel;

	@BeforeEach
	void setUp() throws Exception
	{
		this.testedPanel = new CelebrityPanel(new CelebrityGame());
	}

	@AfterEach
	void tearDown() throws Exception
	{
		this.testedPanel = null;
	}

	@Test
	void testPanelStructure()
	{
		assertTrue(testedPanel instanceof JPanel, "CelebrityPanel needs to extend JPanel");

		Field [] dataMembers = testedPanel.getClass().getDeclaredFields();
		
		for (Field field : dataMembers)
		{
			int status = field.getModifiers();
			assertTrue(Modifier.isPrivate(status), "All data members need to be private!");
		}

		assertTrue(testedPanel.getLayout() instanceof SpringLayout, "The DataPanel layout manager should be SpringLayout");

		Component [] contents = testedPanel.getComponents();
		int fieldCount = 0;
		
		int paneCount = 0;
		int buttonCount = 0;
		int labelCount = 0;
		
		for (Component current : contents)
		{
			if (current instanceof JButton)
			{
				buttonCount++;
				JButton tested = (JButton) current;
				assertTrue(tested.getActionListeners().length == 1, "ALL Buttons need listeners!");
				
			}
			else if (current instanceof JTextField)
			{
				fieldCount++;
				JTextField tested = (JTextField) current;
			
				assertTrue(tested.getActionListeners().length >= 0, "The input textfield needs a listener!");
			}
			else if (current instanceof JScrollPane)
			{
				paneCount++;
				JScrollPane tested = (JScrollPane) current;

				assertTrue(tested.getVerticalScrollBarPolicy() == JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, "The vertical Scroll should be as needed");
				assertTrue(tested.getHorizontalScrollBarPolicy() == JScrollPane.HORIZONTAL_SCROLLBAR_NEVER, "The horizontal Scroll should be never");

				assertTrue(tested.getViewport().getView() instanceof JTextArea, "Your Scrollpane needs the JTextArea as a view");
				JTextArea area = (JTextArea)  tested.getViewport().getView();
				assertFalse(area.isEditable(), "The JTextArea should not be enabled");
			}
			else if (current instanceof JLabel)
			{
				labelCount++;

			}
		}
		
		assertTrue(paneCount >= 1, "You need a JScrollPane");
		assertTrue(buttonCount == 2, "You need two buttons, you only have " + buttonCount);
		assertTrue(fieldCount == 1, "You need one JTextField");
		assertTrue(labelCount >= 3, "You need at least 3 JLabel");
	}
		
	@Test
	void testFrameworkMethodsExist()
	{
		Method [] methods = testedPanel.getClass().getDeclaredMethods();
		assertTrue(methods.length >= 6, "You need at least 6 methods in the DataPanel");
		
		boolean hasSetupPanel = false;
		boolean hasSetupListeners = false;
		boolean hasSetupLayout = false;
		
		for (Method method : methods)
		{
			
			if (method.getName().equals("setupPanel"))
			{
				hasSetupPanel = true;
				assertTrue(method.getReturnType().equals(Void.TYPE), "The " + method.getName()+ " method needs to be a void method!");
				assertTrue(Modifier.isPrivate(method.getModifiers()), "The " + method.getName()+ " method must be private");
			}
			else if (method.getName().equals("setupListeners"))
			{
				hasSetupListeners = true;
				assertTrue(method.getReturnType().equals(Void.TYPE), "The " + method.getName()+ " method needs to be a void method!");
				assertTrue(Modifier.isPrivate(method.getModifiers()), "The " + method.getName()+ " method must be private");
				
			}
			else if (method.getName().equals("setupLayout"))
			{
				hasSetupLayout = true;
				assertTrue(method.getReturnType().equals(Void.TYPE), "The " + method.getName()+ " method needs to be a void method!");
				assertTrue(Modifier.isPrivate(method.getModifiers()), "The " + method.getName()+ " method must be private");
			}
			
		}
		
		assertTrue(hasSetupPanel, "You need a method named setupPanel");
		assertTrue(hasSetupListeners, "You need a method named setupListeners");
		assertTrue(hasSetupLayout, "You need a method named setupLayout");
		
	}
	
	@Test
	void testCelebrityMethodsExist()
	{
		Method [] methods = testedPanel.getClass().getDeclaredMethods();
		boolean hasTimer = false;
		boolean hasUpdate = false;
		boolean hasAddClue = false;
		
		for (Method method : methods)
		{
			Type[] types = method.getGenericParameterTypes();
			if (method.getName().equals("timerFires"))
			{
				hasTimer = true;
				assertTrue(Modifier.isPrivate(method.getModifiers()), "The " + method.getName()+ " method must be private");	
				assertTrue(method.getParameterCount() == 0, "The " + method.getName()+ " method needs no parameters");
			}
			else if (method.getName().equals("updateScreen"))
			{
				hasUpdate = true;
				assertTrue(Modifier.isPrivate(method.getModifiers()), "The " + method.getName()+ " method must be private");
				assertTrue(method.getParameterCount() == 0, "The " + method.getName()+ " method needs zero parameters");
			}
			else if (method.getName().equals("addClue"))
			{
				hasAddClue = true;
				assertTrue(Modifier.isPublic(method.getModifiers()), "The " + method.getName()+ " method must be public");
				assertTrue(method.getParameterCount() == 1, "The " + method.getName()+ " method needs one parameter");
				assertTrue(types[0].getTypeName().equals("java.lang.String"), "The replaceScreen parameter needs to be a String");
			}
		}
		
		assertTrue(hasTimer, "You need a method named timerFires");
		assertTrue(hasUpdate, "You need a method named updateScreen");
		assertTrue(hasAddClue, "You need a method named addClue");
	}
	
}
