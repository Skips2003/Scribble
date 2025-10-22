
public class Player 
{
	private int playerNum;
	private int playerScore;
	private int turnCount;
	private String playerName;
	private char[] letterRack = new char[8];
	
	
	public Player()
	{
		playerName = "";
		playerNum = 0;
		playerScore = 0;
		turnCount = 0;
		for (int i = 0; i < letterRack.length; i++)
		{
			letterRack[i] = ' ';
		}
	}
	
	public Player(int pnum , int pscore , int tcount , String pname)
	{
		playerNum = pnum;
		playerScore = pscore;
		turnCount = tcount;
		playerName = pname;
	}

		/* GET/SET METHODS*/

	/**
	 * Set the player object's number
	 * 
	 * @param pnum The new number for the player object
	 */
	public void setNumber(int pnum)
	{
		playerNum = pnum;
	}
	/**
	 * Get the player object's number
	 * 
	 * @return playerNum The player object's number
	 */
	public int getNumber()
	{
		return playerNum;
	}
	/**
	 * Set the player object's score
	 * 
	 * @param pscore The new score for the player object
	 */
	public void setScore(int pscore)
	{
		playerScore = pscore;
	}
	/**
	 * Get the player object's score
	 * 
	 * @return playerScore The player object's score
	 */
	public int getScore()
	{
		return playerScore;
	}
	/**
	 * Set the player object's turn count
	 * 
	 * @param tcount The new turn count for the player object
	 */
	public void setTurnCount(int tcount)
	{
		turnCount = tcount;
	}
	/**
	 * Get the player object's turn count
	 * 
	 * @return turnCount The player object's turn count
	 */
	public int getTurnCount()
	{
		return turnCount;
	}
	/**
	 * Set the player object's name
	 * 
	 * @param pname The new name for the player object
	 */
	public void setName(String pname)
	{
		playerName = pname;
	}
	/**
	 * Get the player object's name
	 * 
	 * @return playerName The player object's name
	 */
	public String getName()
	{
		return playerName;
	}
	/**
	 * Set the player object's letter rack
	 * 
	 * @param lrack The new letter rack for the player object
	 */
	public void setLetterRack(char[] lrack)
	{
		letterRack = lrack;
	}
	/**
	 * Get the player object's letter rack
	 * 
	 * @return letterRack The player object's letter rack
	 */
	public char[] getLetterRack()
	{
		return letterRack;
		
	}
}
