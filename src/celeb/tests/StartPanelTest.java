package celeb.tests;

/*
 * Project imports
 */
import celeb.view.StartPanel;
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

class StartPanelTest
{
	private StartPanel testedPanel;

	@BeforeEach
	void setUp() throws Exception
	{
		this.testedPanel = new StartPanel(new CelebrityGame());
	}

	@AfterEach
	void tearDown() throws Exception
	{
		this.testedPanel = null;
	}

	@Test
	void testPanelStructure()
	{
		assertTrue(testedPanel instanceof JPanel, "StartPanel needs to extend JPanel");

		Field [] dataMembers = testedPanel.getClass().getDeclaredFields();
		
		for (Field field : dataMembers)
		{
			int status = field.getModifiers();
			assertTrue(Modifier.isPrivate(status), "All data members need to be private!");
		}

		assertTrue(testedPanel.getLayout() instanceof SpringLayout, "The DataPanel layout manager should be SpringLayout");

		Component [] contents = testedPanel.getComponents();
		int fieldCount = 0;
		int buttonCount = 0;
		int labelCount = 0;
		int radioCount = 0;
		
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
			}
			else if (current instanceof JLabel)
			{
				labelCount++;
			}
			else if (current instanceof JRadioButton)
			{
				radioCount++;
			}
		}
		
		assertTrue(buttonCount >= 2, "You need at least two buttons, you only have " + buttonCount);
		assertTrue(fieldCount > 1, "You need at least 2 JTextField");
		assertTrue(labelCount > 1, "You need at least 2 JLabel");
		assertTrue(radioCount > 1, "You need at least 2 JRadioButton");
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
	void testStartMethodsExist()
	{
		Method [] methods = testedPanel.getClass().getDeclaredMethods();
		boolean hasValidate = false;
		boolean hasInvalid = false;
		boolean hasAddToGame = false;
		
		for (Method method : methods)
		{
			Type[] types = method.getGenericParameterTypes();
			if (method.getName().equals("validate"))
			{
				hasValidate = true;
				assertTrue(Modifier.isPrivate(method.getModifiers()), "The " + method.getName()+ " method must be private");	
				assertTrue(method.getParameterCount() == 2, "The " + method.getName()+ " method needs two parameters");
				assertTrue(types[0].getTypeName().equals("java.lang.String"), "The first parameter needs to be a String");
				assertTrue(types[1].getTypeName().equals("java.lang.String"), "The second parameter needs to be a String");
			}
			else if (method.getName().equals("invalidInput"))
			{
				hasInvalid = true;
				assertTrue(Modifier.isPrivate(method.getModifiers()), "The " + method.getName()+ " method must be private");
				assertTrue(method.getParameterCount() == 0, "The " + method.getName()+ " method needs zero parameters");
			}
			else if (method.getName().equals("addToGame"))
			{
				hasAddToGame = true;
				assertTrue(Modifier.isPrivate(method.getModifiers()), "The " + method.getName()+ " method must be private");
				assertTrue(method.getParameterCount() == 0, "The " + method.getName()+ " method needs no parameters");
			}
		}
		
		assertTrue(hasValidate, "You need a method named validate");
		assertTrue(hasInvalid, "You need a method named invalidInput");
		assertTrue(hasAddToGame, "You need a method named addToGame");
	}
	
}
