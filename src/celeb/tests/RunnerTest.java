package celeb.tests;

/**
 * Project imports
 */
import celeb.controller.CelebrityRunner;
/**
 * Reflection imports
 */
import java.lang.reflect.*;

/**
 * Testing imports
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class RunnerTest
{

	private CelebrityRunner testedRunner;

	@BeforeEach
	public void setUp() throws Exception
	{
		testedRunner = new CelebrityRunner();
	}

	@AfterEach
	public void tearDown() throws Exception
	{
		testedRunner = null;
	}

	@Test
	public void testMain()
	{
		Method[] runnerMethods = testedRunner.getClass().getDeclaredMethods();
		assertTrue(runnerMethods.length == 1, "There should be one method in your Runner class");
		
		int returnType = runnerMethods[0].getModifiers();
		assertTrue(Modifier.isPublic(returnType), "The method must be public");
		assertTrue(Modifier.isStatic(returnType), "The method needs to be static!");
		
		assertTrue(runnerMethods[0].getReturnType().equals(Void.TYPE), "The method needs to be a void method!");
		
		assertTrue(runnerMethods[0].getName().equals("main"), "The method must be named main!");
		assertTrue(runnerMethods[0].getParameterCount() == 1, "The main method must have exactly 1 parameter");
		
		Type[] types = runnerMethods[0].getGenericParameterTypes();
		assertTrue(types[0].getTypeName().equals("java.lang.String[]"), "The parameter type needs to be: String []");
	}

}
