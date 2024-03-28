package celeb.model;

import java.util.ArrayList;

public class LiteratureCelebrity extends Celebrity
{
	private ArrayList<String> clueList;
	
	public LiteratureCelebrity(String answer, String clue)
	{
		super(answer, clue);
		processClues();
	}
	
	private void processClues()
	{
		String[] clues = super.getClue().split(",");
		this.clueList = new ArrayList<String>();
		
		for (String clue : clues)
		{
			clueList.add(clue);
		}
	}
	
	@Override
	public String getClue()
	{
		if (clueList.size() == 0)
		{
			processClues();
		}
		
		String clue = clueList.remove(0);
		return clue;
	}
	
	@Override
	public String toString()
	{
		String description = "This is a literature celebrity";
		description += "\nWith an answer of " + getAnswer();
		description += "\nand clues of: ";
		
		for (String clue : super.getClue().split(","))
		{
			description += "\n\t" + clue;
		}
		
		return description;
	}
}
