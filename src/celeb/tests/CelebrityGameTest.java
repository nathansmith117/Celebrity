package celeb.tests;

/*
 * Project imports
 */
import celeb.controller.CelebrityGame;

/*
 * Reflection imports
 */
import java.lang.reflect.*;

/*
 * Testing imports
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CelebrityGameTest
{
	private CelebrityGame testedGame;

	@BeforeEach
	void setUp() throws Exception
	{
		this.testedGame = new CelebrityGame();
	}

	@AfterEach
	void tearDown() throws Exception
	{
		this.testedGame = null;
	}

	@Test
	void testDataMembers()
	{
		Field [] fields = testedGame.getClass().getDeclaredFields();
		assertTrue(fields.length > 2, "You need at least 3 data members in your Controller");
		
		boolean hasCelebrityList = false;
		boolean hasFrame = false;
		boolean hasCelebrity = false;

		for (Field field : fields)
		{
			assertTrue(Modifier.isPrivate(field.getModifiers()), "All data members must be private!");

			String name = field.getType().getSimpleName();
			
			if (name.equals("ArrayList"))
			{
				hasCelebrityList = true;
			}
			else if (name.equals("CelebrityFrame"))
			{
				hasFrame = true;
			}
			else if (name.equals("Celebrity"))
			{
				hasCelebrity = true;
			}
		}
		assertTrue(hasFrame, "You need a DataFrame as a data member");
		assertTrue(hasCelebrityList, "You need an ArrayList as a data member");
		assertTrue(hasCelebrity, "You need an Celebrity as a data member");
	}

	@Test
	void testCelebrityGameMethods()
	{
		Method [] methods = testedGame.getClass().getDeclaredMethods();
		assertTrue(methods.length >= 9, "You need at least nine methods in the controller");
		
		boolean hasPrepareGame = false;
		
		boolean hasProcessGuess = false;
		boolean hasPlay = false;
		boolean hasAddCelebrity = false;
		boolean hasValidateCelebrity = false;
		boolean hasValidateClue = false;
		boolean hasGetCelebrityGameSize = false;
		boolean hasSendClue = false;
		boolean hasSendAnswer = false;
		
		
		for (Method method : methods)
		{
			Type[] types = method.getGenericParameterTypes();
			if (method.getName().equals("processGuess"))
			{
				hasProcessGuess = true;
				assertTrue(Modifier.isPublic(method.getModifiers()), "The " + method.getName()+ " method must be public");
				assertTrue(types[0].getTypeName().equals("java.lang.String"), "The parameter type needs to be: String");
				assertTrue(method.getReturnType().equals(Boolean.TYPE), "The " + method.getName()+ " method needs to be a boolean method!");
			}
			else if (method.getName().equals("prepareGame"))
			{
				hasPrepareGame = true;
				assertTrue(Modifier.isPublic(method.getModifiers()), "The " + method.getName()+ " method must be public");
				assertTrue(types.length == 0, "The " + method.getName()+ " method has no parameters!");
				assertTrue(method.getReturnType().equals(Void.TYPE), "The " + method.getName()+ " method needs to be a void method!");
			}
			else if (method.getName().equals("play"))
			{
				hasPlay = true;
				assertTrue(Modifier.isPublic(method.getModifiers()), "The " + method.getName()+ " method must be public");
				assertTrue(types.length == 0, "The " + method.getName()+ " method has zero parameters!");
				assertTrue(method.getReturnType().equals(Void.TYPE), "The " + method.getName()+ " method needs to be a void method!");
			}
			else if (method.getName().equals("addCelebrity"))
			{
				hasAddCelebrity = true;
				assertTrue(Modifier.isPublic(method.getModifiers()), "The " + method.getName()+ " method must be public");
				assertTrue(types.length == 3, "The " + method.getName()+ " method has three parameter!");
				assertTrue(method.getReturnType().equals(Void.TYPE), "The " + method.getName()+ " method needs to be a void method!");
			}
			else if (method.getName().equals("sendAnswer"))
			{
				hasSendAnswer = true;
				assertTrue(Modifier.isPublic(method.getModifiers()), "The " + method.getName()+ " method must be public");
				assertTrue(types.length == 0, "The " + method.getName()+ " method has no parameter!");
				assertTrue(method.getReturnType().equals(String.class), "The " + method.getName()+ " method should return a String");
			}
			else if (method.getName().contains("getCelebrityGameSize"))
			{
				hasGetCelebrityGameSize = true;
				assertTrue(Modifier.isPublic(method.getModifiers()), "The " + method.getName()+ " method must be public");
				assertTrue(types.length == 0, "The " + method.getName()+ " method has no parameter!");
				assertTrue(method.getReturnType().equals(Integer.TYPE), "The " + method.getName()+ " method needs to be a int method!");
			}
			else if (method.getName().equals("sendClue"))
			{
				hasSendClue = true;
				assertTrue(Modifier.isPublic(method.getModifiers()), "The " + method.getName()+ " method must be public");
				assertTrue(types.length == 0,  "The " + method.getName()+ " method has no parameter!");
				assertTrue(method.getReturnType().equals(String.class), "The " + method.getName()+ " method should return a String");
			}
			else if (method.getName().equals("validateCelebrity"))
			{
				hasValidateCelebrity = true;
				assertTrue(Modifier.isPublic(method.getModifiers()), "The " + method.getName()+ " method must be public");
				assertTrue(types.length == 1,  "The " + method.getName()+ " method has one parameter!");
				assertTrue(method.getReturnType().equals(Boolean.TYPE), "The " + method.getName()+ " method needs to be a boolean method!");
			}
			else if (method.getName().equals("validateClue"))
			{
				hasValidateClue = true;
				assertTrue(Modifier.isPublic(method.getModifiers()), "The " + method.getName()+ " method must be private");
				assertTrue(types.length == 2, "The " + method.getName()+ " method has two parameters!");
				assertTrue(method.getReturnType().equals(Boolean.TYPE), "The " + method.getName()+ " method needs to be a boolean method!");
			}
		}

		assertTrue(hasProcessGuess, "You need a method named processGuess");
		assertTrue(hasPrepareGame, "You need a method named prepareGame");
		assertTrue(hasPlay, "You need a method named play");
		assertTrue(hasAddCelebrity, "You need a method named addCelebrity");
		assertTrue(hasValidateClue, "You need a method named validateClue");
		assertTrue(hasValidateCelebrity, "You need a method named validateCelebrity");
		assertTrue(hasGetCelebrityGameSize, "You need a method named getCelebrityGameSize");
		assertTrue(hasSendClue, "You need a method named sendClue");
		assertTrue(hasSendAnswer, "You need a method named sendAnswer");
	}

	@Test
	void testProcessGuess()
	{
		String answer = "Alan Turing";
		String clue = "WW2 Inventor of digital computer";
		String type = "";
		int startSize = testedGame.getCelebrityGameSize();
		testedGame.addCelebrity(answer, clue, type);
		testedGame.play();
		assertFalse(testedGame.processGuess(""), "Empty string does not match");
		assertFalse(testedGame.processGuess("Alan"), "partial string does not match");
		assertFalse(testedGame.processGuess("Turing"), "partial string does not match");
		assertTrue(testedGame.processGuess("Alan turing"), "case insensitive string does match");
		
		testedGame.addCelebrity(answer, clue, type);
		testedGame.play();
		assertTrue(testedGame.processGuess(answer.toLowerCase()), "case insensitive string does match");
		
		testedGame.addCelebrity(answer, clue, type);
		testedGame.play();
		assertTrue(testedGame.processGuess(answer  + "   "), "extra whitepsace does match");
		
		testedGame.addCelebrity(answer, clue, type);
		testedGame.play();
		assertTrue(testedGame.processGuess("\t" + answer  + "\n"), "extra whitepsace does match");
		
		testedGame.addCelebrity(answer, clue, type);
		testedGame.play();
		assertTrue(testedGame.processGuess(answer), "Proper match");
	}
	
	@Test
	void testAddCelebrity()
	{
		String answer = "Alan Turing";
		String clue = "WW2 Inventor of digital computer";
		String type = "celebrity";
		int startSize = testedGame.getCelebrityGameSize();
		testedGame.addCelebrity(answer, clue, type);
		int updatedSize = testedGame.getCelebrityGameSize();
		assertNotEquals(startSize, updatedSize, "The game size should change when adding a Celebrity");
	}
	
	@Test
	void testValidateCelebrity()
	{
		String goodCelebrity = "P!nk";
		String badCelebrity = "na";
		String nullCelebrity = null;
		
		assertFalse(testedGame.validateCelebrity(nullCelebrity), "A null value should return false");
		assertFalse(testedGame.validateCelebrity(badCelebrity), "A celebrity with less than 4 letters is invalid");
		badCelebrity = "";
		assertFalse(testedGame.validateCelebrity(badCelebrity), "A celebrity with less than 4 letters is invalid");
		badCelebrity = "1";
		assertFalse(testedGame.validateCelebrity(badCelebrity), "A celebrity with less than 4 letters is invalid");
		badCelebrity = "aaa";
		assertFalse(testedGame.validateCelebrity(badCelebrity), "A celebrity with less than 4 letters is invalid");
		
		assertTrue(testedGame.validateCelebrity(goodCelebrity), "A celebrity with 4 letters is valid");
	}
	
	@Test
	void testValidateClue()
	{
		String badClue = "";
		String goodClue = "This is long enough";
		String type = "Celebrity";
		
		assertFalse(testedGame.validateClue(badClue, ""), "Empty string is not a valid clue");
		assertFalse(testedGame.validateClue(null, null), "A null value is not a valid clue or type");
		assertFalse(testedGame.validateClue(null, ""), "A null value is not a valid clue");
		assertFalse(testedGame.validateClue(goodClue, null), "A null value is not a valid type");
		
		assertTrue(testedGame.validateClue(goodClue, type), "A valid clue should work");
		assertTrue(testedGame.validateClue(goodClue, badClue), "A valid clue should work with an empty type");
		
	}
	
	/**
	 * Since not all implementations of the Celebrity project will use LiteratureCelebrity as a type 
	 * I have the test code separate so it is easier to remove/adapt as needed.
	 * You can easily adapt this method to test your classes custom subclass!
	 */
	@Test
	void testValidateLiteratureClue()
	{
		String badClue = "This is long enough but no comma";
		String otherBadClue = "Thi,s is long enough but comma is TOO EARLY";
		String goodClue = "This is long enough, and has a comma";
		String type = "Literature";
		
		assertFalse(testedGame.validateClue("", type), "Empty string is not a valid clue");
		assertFalse(testedGame.validateClue(null, null), "A null value is not a valid clue or type");
		assertFalse(testedGame.validateClue(null, type), "A null value is not a valid clue");
		assertFalse(testedGame.validateClue(goodClue, null), "A null value is not a valid type");
		assertFalse(testedGame.validateClue(badClue, type), "You need a comma");
		assertFalse(testedGame.validateClue(otherBadClue, type), "You need to have the comma after at least 4 letters");
		assertTrue(testedGame.validateClue(goodClue, type), "A valid clue should work!");
	}
	
	@Test
	void testGetCelebrityGameSize()
	{
		int startSize = testedGame.getCelebrityGameSize();
		
		assertEquals(startSize, 0, "The game should have no entries at start");
		String answer = "Alan Turing";
		String clue = "WW2 Inventor of digital computer";
		String type = "celebrity";
		testedGame.addCelebrity(answer, clue, type);
		int updatedSize = testedGame.getCelebrityGameSize();
		assertNotEquals(startSize, updatedSize, "The game size should change when adding a Celebrity");
		assertEquals(updatedSize, 1, "There should be ONE Celebrity after adding a celeb");
		
	}
	
	@Test
	void testSendClue()
	{
		String answer = "Alan Turing";
		String clue = "WW2 Inventor of digital computer";
		String type = "celebrity";
		testedGame.addCelebrity(answer, clue, type);
		testedGame.play();
		
		assertTrue(testedGame.sendClue().equals(clue), "The clue should match");
	}
	
	@Test
	void testSendAnswer()
	{
		String answer = "Alan Turing";
		String clue = "WW2 Inventor of digital computer";
		String type = "celebrity";
		testedGame.addCelebrity(answer, clue, type);
		testedGame.play();
		
		assertTrue(testedGame.sendAnswer().equals(answer), "The answer should match");
	}
}
