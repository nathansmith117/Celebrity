package celeb.tests;

/*
 * Project imports
 */
import celeb.model.LiteratureCelebrity;


/*
 * Reflection imports
 */
import java.lang.reflect.*;
/*
 * Testing imports
 */
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LiteratureCelebrityTest
{
	private LiteratureCelebrity testedCelebrity;

	@BeforeEach
	public void setUp() throws Exception
	{
		this.testedCelebrity = new LiteratureCelebrity("Lady Ada Lovelace","first programmer,has a programming language named after her");
	}

	@AfterEach
	public void tearDown() throws Exception
	{
		this.testedCelebrity = null;
	}

	@Test
	public void testToString()
	{
		String result = testedCelebrity.toString();
		assertNotNull(result,"The toString method should not return null");
		assertTrue(result.indexOf("Celebrity@") == -1, "The toString method must be overwritten");
		String clue = testedCelebrity.getClue();
		assertTrue(result.indexOf(clue) >= 0,"At least one clue should be in the toString");
	}
	
	@Test
	public void testDataMembers()
	{
		Field [] fields = testedCelebrity.getClass().getDeclaredFields();
		assertTrue(fields.length == 1, "The LiteratureCelebrity class needs ONE data member");
		for (Field field : fields)
		{
			assertTrue(Modifier.isPrivate(field.getModifiers()), "All data members must be private!");
			assertTrue(field.getType().getSimpleName().equals("ArrayList"), "The data member of LiteratureCelebrity is an ArrayList<String>");
		}
	}
	
	@Test
	public void testSetAnswer()
	{
		String updatedAnswer = "Grace Hopper";
		testedCelebrity.setAnswer(updatedAnswer);
		assertTrue(testedCelebrity.getAnswer().equals(updatedAnswer), "The answer should be changed");
		
	}
	
	@Test
	public void testGetClue()
	{
		assertTrue(testedCelebrity.getClue().equals("first programmer"), "first part of second parameter should be returned");
		assertTrue(testedCelebrity.getClue().equals("has a programming language named after her"), "second part of second parameter should be returned");
	}
	
	@Test
	public void testGetAnswer()
	{
		assertTrue(testedCelebrity.getAnswer().equals("Lady Ada Lovelace"), "first constructor parameter should be returned");
	}

	@Test
	public void testConstructor()
	{
		Constructor [] celebrityConstructors = testedCelebrity.getClass().getDeclaredConstructors();
		assertTrue(celebrityConstructors.length == 1, "You have one constructor");
		for (Constructor current : celebrityConstructors)
		{
			int params = current.getParameterCount();
			assertTrue(params == 2, "You need two parameters in the Celebrity constructor");
		}
	}
	
	@Test
	public void testStructure()
	{
		Method [] methods = testedCelebrity.getClass().getDeclaredMethods();
		assertTrue(methods.length == 3, "You need 3 methods in your LiteratureCelebrity!");

		assertTrue(testedCelebrity instanceof Object, "Your LiteratureCelebrity must be an Object");
	}
}
