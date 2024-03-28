package celeb.tests;

/*
 * Project imports
 */
import celeb.view.*;
import celeb.controller.CelebrityGame;

/*
 * Reflection imports
 */
import java.lang.reflect.*;

import javax.swing.JFrame;

/*
 * Testing imports
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FrameTest
{
	private CelebrityFrame testedFrame;

	@BeforeEach
	void setUp() throws Exception
	{
		this.testedFrame = new CelebrityFrame(new CelebrityGame());
	}

	@AfterEach
	void tearDown() throws Exception
	{
		this.testedFrame = null;
	}

	@Test
	void testDataFrame()
	{
		assertTrue(testedFrame instanceof JFrame, "CelebrityFrame needs to extend JFrame");
		
		
		assertTrue(testedFrame.getTitle().length() > 5, "Your title needs at least 6 letters");
		assertTrue(!testedFrame.isResizable(), "Your CelebrityFrame should NOT be resizable!");
		assertTrue(testedFrame.getTitle().toLowerCase().contains("celebrity"), "Your title needs to have Celebrity in it");
	}
	
	@Test
	void testFrameMethods()
	{
		Method[] methods = testedFrame.getClass().getDeclaredMethods();
		assertTrue(methods.length == 2, "You need exactly 2 methods in the DataFrame");
		boolean hasSetup = false;
		boolean hasReplace = false;
		
		for (Method method : methods)
		{
			Type[] types = method.getGenericParameterTypes();
			
			assertTrue(method.getReturnType().equals(Void.TYPE), "The " + method.getName()+ " method needs to be a void method!");
			
			if (method.getName().equals("setupFrame"))
			{
				hasSetup = true;
				assertTrue(types.length == 0, "The replaceScreen method needs no parameter");
				assertTrue(Modifier.isPrivate(method.getModifiers()), "The " + method.getName()+ " method must be private");
			}
			else if (method.getName().equals("replaceScreen"))
			{
				hasReplace = true;
				assertTrue(Modifier.isPublic(method.getModifiers()), "The " + method.getName()+ " method must be public");
				assertTrue(types.length == 1, "The replaceScreen method needs a single parameter");
				assertTrue(types[0].getTypeName().equals("java.lang.String"), "The replaceScreen parameter needs to be a String");
			}
		}
		assertTrue(hasSetup, "The CelebrityFrame needs to have a method named setupFrame");
		assertTrue(hasReplace, "The CelebrityFrame needs to have a method named replaceScreen");
	}

}
