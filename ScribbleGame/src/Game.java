import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Game 
{
	//Creating fields// 
private Game[][] board;
private char letter;
private String userWordString;
private int userRow;
private int userColumn;
private int userVH;
private int loadTC;
private int loadPC;
private int loadGT;
private char[] userWordChar;
private StringBuilder[] newWordArray = new StringBuilder[100];
private Player[] p = new Player[4];

BufferedReader bufferedReader;
double nextLine;
// Creating Constructors

// Default Constructor
public Game()
{
	board = new Game[15][15];
	letter = '-';
}
// Second overloaded constructor
/*
 * @param pts The points assigned to the game object
 * @param let The letter assigned to the game object
 */
public Game(char let)
{
	letter = let;
}

	/* GET/SET METHODS*/
/**
 * Set the game object's letter
 * 
 * @param let The new letter for the game object
 */
public void setLetter(char let)
{
	letter = let;
}
/**
 * Get the game object's letter
 * 
 * @return letter The game object's letter
 */
public char getLetter()
{
	return letter;
}
//Method for initialising values inside of 2D array
	public void populate()
	
	{
		
		int row, col;
		// Choosing rows in order using the row variable until it reaches the value of the rowCount variable
		for (row = 0; row < 15; row++)
			{ 
				// Choosing columns in order using the col variable until it reaches the value of the columnCount variable
				for (col = 0; col < 15; col++)
					// creates a new Budget object for each selected row and column
					board[row][col] = new Game();
			}
	}
	public void display()
	{
		
		
		System.out.printf("%-3s%-3s%-3s%-3s%-3s%-3s%-3s%-3s%-3s%-3s%-3s%-3s%-3s%-3s%-3s%-3s\n" , " " ,  "1" , "2" , "3" , "4" , "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15");
		for (int row = 0; row < 15; row++)
		{ 
			int rowNum = row + 1;
			System.out.printf("%-3s%-3c%-3c%-3c%-3c%-3c%-3c%-3c%-3c%-3c%-3c%-3c%-3c%-3c%-3c%-3c\n" , rowNum , board[row][0].getLetter() , board[row][1].getLetter() , board[row][2].getLetter() , board[row][3].getLetter() , board[row][4].getLetter() , board[row][5].getLetter() , board[row][6].getLetter() , board[row][7].getLetter() , board[row][8].getLetter() , board[row][9].getLetter() , board[row][10].getLetter() , board[row][11].getLetter() , board[row][12].getLetter() , board[row][13].getLetter() , board[row][14].getLetter());
		}
	}
	public void firstTurn()
	{
		int idk;
		boolean wordIn = false;
		int userVHFirstWord = 0;
		String firstWord = "";
		Scanner f = new Scanner(System.in);
		System.out.println("Welcome to scribble!");
		
		while(wordIn != true)
		{
			System.out.println(p[0].getName()+ " Please enter the first word!");
			try{firstWord = f.nextLine();}
			catch(InputMismatchException e) 
			{
				System.out.println("Please enter a word");
			}
			userWordChar = firstWord.toCharArray();
			if (userWordChar.length <=15 || userWordChar.length > 1)
			{
				wordIn = true;
			}
		}
		System.out.println("Please enter 1 to place vertically and 2 to place horizontally");
		try{userVHFirstWord = f.nextInt();}
		catch(InputMismatchException e) 
		{
			System.out.println("Please enter a number");
		}
		
		if(userVHFirstWord == 1)
		{
			if (userWordChar.length % 2 == 0)
			{
				idk = userWordChar.length / 2;
			}
			else
			{
				idk = userWordChar.length / 2 + 1;
			}
			for (int i = 0; i < userWordChar.length; i++)
			{
				board[8 - idk + i][7].setLetter(userWordChar[i]);
			}
		}
		else if(userVHFirstWord == 2)
		{
			if (userWordChar.length % 2 == 0)
			{
				idk = userWordChar.length / 2;
			}
			else
			{
				idk = userWordChar.length / 2 + 1;
			}
			for (int i = 0; i < userWordChar.length; i++)
			{
				board[7][8 - idk + i].setLetter(userWordChar[i]);
			}
		}
		//Declaring needed FileReaders, BufferedReaders and String variables to store them in
		FileReader fileReader1;
		BufferedReader bufferedReader1;
		String nextLine1;
		int turnScore = 0;
			
		//Reading values from the users selected file
		try
		{
			fileReader1 = new FileReader("letter-scoring.txt");
			bufferedReader1 = new BufferedReader(fileReader1);
			nextLine1 = "";
					
				while (nextLine1 != null)
				{
					nextLine1 = bufferedReader1.readLine();
					if (nextLine1 != null)
					{
						String[] parts = nextLine1.split(",");
						char [] letterPoints = parts[1].toCharArray();
						
						for (int i = 0; i < userWordChar.length; i++)
						{
							for (int z = 0; z < letterPoints.length; z++)
							{
								if (letterPoints[z] == userWordChar[i])
								{
									turnScore = turnScore + Integer.parseInt(parts[0]);
								}
							}
						}
					}
				}
				p[0].setTurnCount(2);
				p[0].setScore(p[0].getScore() + turnScore);
				System.out.println("Your score for this turn was: " + turnScore);
				System.out.println("Your total score is: " + p[0].getScore());
				//Closing Readers
				bufferedReader1.close();
				fileReader1.close();
				}
				catch (IOException e)
				{
					System.out.println("Sorry, an error occurred..");
				}
	}
	public void userInput(int currentPlayer)
	{
		int validPlace = 0;
		int userMenuChoice = 0;
		int counter = 0;
		int counter2 = -1;
		while(validPlace == 0)
		{
			userColumn = -1;
			userRow = -1;
			userVH = 0;
			userWordString = "";
			System.out.println(p[currentPlayer].getName() + " It is your turn");
			Scanner uI = new Scanner(System.in);
			while(userMenuChoice < 1 || userMenuChoice > 2)
			{
				System.out.println("Please choose an option by entering 1 or 2");
				System.out.println("1. Play a word");
				System.out.println("2. Skip your turn");
				try {userMenuChoice = uI.nextInt();} 
				catch(InputMismatchException e) 
				{
					System.out.println("Please enter 1 or 2");
				}
				uI.nextLine();
				if (userMenuChoice < 1 || userMenuChoice > 2)
				{
				}
			}
			if (userMenuChoice == 1)
			{
				while (userWordString.length() < 1 || userWordString.length() > 15)
				{
					System.out.println("Please enter the word you want to play: ");
					try {userWordString = uI.nextLine();}
					catch(InputMismatchException e) 
					{
						System.out.println("Please enter a word");
					}
					userWordChar = userWordString.toCharArray();
					
					if (userWordString.length() < 1 || userWordString.length() > 15)
					{
						System.out.println("Please enter a word with atleast 1 and no more than 15 letters");
					}
				}
				while (userColumn < 0 || userColumn > 15)
				{
					System.out.println("Please enter the column where you would like to place the first letter");
					try{userColumn = uI.nextInt();}
					catch(InputMismatchException e) 
					{
						System.out.println("Please enter a number");
					}
					uI.nextLine();
						if (userColumn < 0 || userColumn > 15)
						{
							System.out.println("Please enter a valid column");
						}
						else
						{
							userColumn = userColumn - 1;
						}
				}
				while (userRow < 0 || userRow > 15)
				{
					System.out.println("Please enter the row where you would like to place the first letter");
					try{userRow = uI.nextInt();}
					catch(InputMismatchException e) 
					{
						System.out.println("Please enter a number");
					}
					uI.nextLine();
					if (userRow < 0 || userRow > 15)
					{
						System.out.println("Please enter a valid row");
					}
					else
					{
						userRow = userRow - 1;
					}
				}
				while (userVH < 1 || userVH > 2)
				{
					System.out.println("Please enter 1 to place vertically and 2 to place horizontally");
					try{userVH = uI.nextInt();}
					catch(InputMismatchException e) 
					{
						System.out.println("Please enter a number");
					}
					uI.nextLine();
					if (userVH < 1 || userVH > 2)
					{
						System.out.println("Please enter 1 or 2");
					}
	
				}
				if (userVH == 1)
				{
					//check above first letter
					if (userRow - 1 == -1)
					{
						System.out.println("@");
					}
					else if (board[userRow - 1][userColumn].getLetter() != '-')
					{
						validPlace++;
						newWordArray[validPlace] = new StringBuilder();
						for (int newRow = userRow - 1; newRow > -1; newRow--)
						{
							System.out.println(newRow);
							if (board[newRow][userColumn].getLetter() != '-')
							{
								newWordArray[validPlace].insert(0, board[newRow][userColumn].getLetter());
							}
							else if (board[newRow][userColumn].getLetter() == '-')
							{
								break;
							}
						}
							newWordArray[validPlace].append(userWordString);			
					}
					//check below last letter
					if (userRow + userWordString.length() == 15)
					{
						
					}
					else if (board[userRow + userWordString.length()][userColumn].getLetter() != '-')
					{
						if (userRow - 1 == -1)
						{
							validPlace++;
							newWordArray[validPlace] = new StringBuilder();
							newWordArray[validPlace].append(userWordString);
						}
						for (int newRow =  userRow + userWordString.length(); newRow < 15; newRow++)
						{
							System.out.println(newRow);
							if (board[newRow][userColumn].getLetter() != '-')
							{
								newWordArray[validPlace].append(board[newRow][userColumn].getLetter());
							}
							else if (board[newRow][userColumn].getLetter() == '-')
							{
								break;
							}
						}
					}
					//check to left and right of all letters
					for (int newRow = userRow; newRow < userRow + userWordString.length(); newRow++)
					{
						//checking above all letters
						if (userColumn - 1 == -1)
						{
							counter = 1;
						}
						else
						{
							for (int newCol = userColumn - 1; newCol > -1; newCol--)
							{
								if (board[newRow][newCol].getLetter() != '-')
								{
									validPlace++;
									newWordArray[validPlace] = new StringBuilder();
									newWordArray[validPlace].insert(0, board[newRow][newCol].getLetter());
								}
								else if (board[newRow][newCol].getLetter() == '-')
								{
									break;
								}	
							}
						}
						if (userColumn + 1 == 15)
						{
							break;
						}
						else
						{
							for (int newCol = userColumn + 1; newCol < 15; newCol++)
							{
								if (userColumn + 1 == 15)
								{
									break;
								}
								else if (board[newRow][newCol].getLetter() != '-')
								{
									if (counter == 1)
									{
										validPlace++;
										newWordArray[validPlace] = new StringBuilder();
										newWordArray[validPlace].insert(0, board[newRow][userColumn].getLetter());
										
										counter--;
									}
									newWordArray[validPlace].append(board[newRow][newCol].getLetter());
								}
								else if (board[newRow][newCol].getLetter() == '-')
								{
									break;
								}
							}
						}
					}
				}
				else if (userVH == 2)
				{
					//check left of first letter
					if (userColumn - 1 == -1)
					{
						
					}
					else if (board[userRow][userColumn - 1].getLetter() != '-')
					{
						validPlace++;
						newWordArray[validPlace] = new StringBuilder();
						System.out.println("check 1");
						for (int newCol = userColumn - 1; newCol > -1; newCol--)
						{
							System.out.println(newCol);
							if (board[userRow][newCol].getLetter() != '-')
							{
								newWordArray[validPlace].insert(0, board[userRow][newCol].getLetter());
							}
							else if (board[userRow][newCol].getLetter() == '-')
							{
								break;
							}
						}
						newWordArray[validPlace].append(userWordString);
					}
					
					//check right of last letter
					if (userColumn + userWordString.length() == 15)
					{
						
					}
					else if (board[userRow][userColumn + userWordString.length()].getLetter() != '-')
					{
						if (userColumn - 1 == -1)
						{
							validPlace++;
							newWordArray[validPlace] = new StringBuilder();
							newWordArray[validPlace].append(userWordString);
						}
						for (int newCol =  userColumn + userWordString.length(); newCol < 15; newCol++)
						{
							System.out.println(newCol);
							if (board[userRow][newCol].getLetter() != '-')
							{
								newWordArray[validPlace].append(board[userRow][newCol].getLetter());
							}
							else if (board[userRow][newCol].getLetter() == '-')
							{
								break;
							}
						}	
					}
					//check above and below all letters
						for (int newCol = userColumn; newCol < userColumn + userWordString.length(); newCol++)
						{
							counter2++;
							//checking above all letters
							if (userRow - 1 == -1)
							{
								counter = 1;
							}
							else if (counter == 0)
							{
								for (int newRow = userRow - 1; newRow > -1; newRow--)
								{
									if (board[newRow][newCol].getLetter() != '-')
									{
										validPlace++;
										newWordArray[validPlace] = new StringBuilder();
										newWordArray[validPlace].insert(0, board[newRow][newCol].getLetter());
									}
									else if (board[newRow][newCol].getLetter() == '-')
									{
										break;
									}
								}
							}
							if (userRow + 1 == 15)
							{
								break;
							}
							else
							{
								for (int newRow = userRow + 1; newRow < 15; newRow++)
								{
									if (board[newRow][newCol].getLetter() != '-')
									{
										if (counter == 1)
										{
											validPlace++;
											newWordArray[validPlace] = new StringBuilder();
											newWordArray[validPlace].insert(0, board[userRow][newCol].getLetter());
										
											counter--;
										}
										newWordArray[validPlace].append(board[newRow][newCol].getLetter());
									}
									else if (board[newRow][newCol].getLetter() == '-')
									{
										break;
									}
								}
							}
						}
					}
				for (int i = 0; i < userWordChar.length; i++)
				{
					if (board[userRow + i][userColumn].getLetter() != '-')
					{
						if (board[userRow + i][userColumn].getLetter() != userWordChar[i])
						{
							validPlace = 0;
							System.out.println("Do not try to replace existing letters with different letters!");
							System.out.println("You tried to replace " + board[userRow + i][userColumn].getLetter() + " with " + userWordChar[i] + ".");
							break;
						}
					}
				}
					if (validPlace > 0)
					{
						if (userVH == 1)
						{
							for (int i = 0 ; i < userWordChar.length; i++)
							{
								board[userRow + i][userColumn].setLetter(userWordChar[i]);
							}
						}
						else if (userVH == 2)
						{
							for (int i = 0 ; i < userWordChar.length ; i++)
							{
								board[userRow][userColumn + i].setLetter(userWordChar[i]);
							}
						}
					}
					else
					{
						System.out.println("That is not a valid input please try again or skip your turn.");
					}
				}
				else if (userMenuChoice == 2)
				{
					System.out.println("Skipping Turn...");
					validPlace = 1;
				}	
		}	
	}
		
	public void scoreCheck(int currentPlayer)
	{
		//Declaring needed FileReaders, BufferedReaders and String variables to store them in
		FileReader fileReader1;
		BufferedReader bufferedReader1;
		String nextLine1;
		int turnScore = 0;
		
		//Reading values from the users selected file
		try
		{
		fileReader1 = new FileReader("letter-scoring.txt");
		bufferedReader1 = new BufferedReader(fileReader1);
		nextLine1 = "";
			
			while (nextLine1 != null)
			{
				nextLine1 = bufferedReader1.readLine();
				if (nextLine1 != null)
				{
					String[] parts = nextLine1.split(",");
					char [] letterPoints = parts[1].toCharArray();
				
					for (int i = 0; i < userWordChar.length; i++)
					{
						for (int z = 0; z < letterPoints.length; z++)
						{
							if (letterPoints[z] == userWordChar[i])
							{
								turnScore = turnScore + Integer.parseInt(parts[0]);
							}
						}
					}
				}
			}
				p[currentPlayer].setScore(p[currentPlayer].getScore() + turnScore);
				System.out.println("Your score for this turn was: " + turnScore);
				System.out.println("Your total score is: " + p[currentPlayer].getScore());
			
		//Closing Readers
		bufferedReader1.close();
		fileReader1.close();
		}
		catch (IOException e)
		{
			System.out.println("Sorry, an error occurred..");
		}
	}
	public void playerSelection(int amountOfPlayers)
	{
		Scanner ps = new Scanner(System.in);
		for (int i = 0; i < amountOfPlayers ; i++)
		{
			p[i] = new Player();
			p[i].setNumber(i + 1);
			System.out.println("Please enter the name of player " + p[i].getNumber());
			try{p[i].setName(ps.nextLine());}
			catch(InputMismatchException e) 
			{
				System.out.println("Please enter a word");
			}
		}
	}
	public void turnMatch(int currentTurn , int currentPlayer)
	{
		p[currentPlayer].setTurnCount(currentTurn);
		System.out.println("Player " + p[currentPlayer].getNumber() + " " + p[currentPlayer].getName());
		System.out.println("Turn number " + p[currentPlayer].getTurnCount());
		System.out.println("Total Score " + p[currentPlayer].getScore());
	}
	public boolean checkPointMatch(int currentPlayer , int pointsNeeded)
	{
		boolean a = false;
		if (p[currentPlayer].getScore() > pointsNeeded)
		{
			a = true;
		}
		
		return a;
	}
	public void saveAndQuit(String saveFile , int playerCount , int gameType , int maxTOrP)
	{
		try
		{
			//Declaring needed FileOutpuStream, PrintWriter and String variables to store them in
			String[] nextLineArray = new String[15];
			String[] nextPlayerArray = new String[4];
			FileOutputStream outputStream;
			PrintWriter printWriter;
			
			//Checking a file has been used to create table inside of the load() method
				//Reading values from the table to store into the users selected file
			outputStream = new FileOutputStream(saveFile);
			printWriter = new PrintWriter(outputStream);

					for (int row = 0; row < 15; row++)
					{
						//Encrypting the values from the table to store into the users selected file
						for (int col = 0; col < 15; col++)
						{
							nextLineArray[col] = Character.toString(board[row][col].getLetter());
						}
						//combine every value of nextLineArray and print to the file
						String nextLine = String.join(",", nextLineArray);
						printWriter.println(nextLine);
					}
					printWriter.println();
					printWriter.println(String.valueOf(playerCount));
					for (int i = 0; i < playerCount; i++)
					{
						nextPlayerArray[0] = p[i].getName();
						nextPlayerArray[1] = String.valueOf(p[i].getNumber());
						nextPlayerArray[2] = String.valueOf(p[i].getScore());
						nextPlayerArray[3] = String.valueOf(p[i].getTurnCount());
						String nextLine = String.join(",", nextPlayerArray);
						printWriter.println(nextLine);
					}
					printWriter.println();
					printWriter.println(playerCount);
					printWriter.println(gameType);
					printWriter.println(maxTOrP);
			//Closing Writers
			printWriter.close();
			outputStream.close();
		}
		//Exception handling logic
		catch (IOException e)
	{
		System.out.println("Sorry, an error occurred..");
	}
	}
	public void loadGame(String loadFile)
	{
		//Declaring needed FileReaders, BufferedReaders and String variables to store them in
		FileReader fileReader1;
		BufferedReader bufferedReader1;
		String nextLine1;
		try
		{
		fileReader1 = new FileReader(loadFile);
		bufferedReader1 = new BufferedReader(fileReader1);
		nextLine1 = bufferedReader1.readLine();
		int row, col;
			
			//Moving information from the file into the 2D Array of objects
			while (nextLine1 != null)
			{
				for (row = 0; row < 15; row++)
				{
					String[] parts = nextLine1.split(",");
					nextLine1 = bufferedReader1.readLine();

						for (col = 0; col < 15; col++)
						{
							board[row][col].setLetter(parts[col].charAt(0));;
						}
			
				}
				nextLine1 = bufferedReader1.readLine();
				int playerCount = Integer.parseInt(nextLine1);
				for (int i = 0; i < playerCount; i++)
				{
					nextLine1 = bufferedReader1.readLine();
					String[] parts = nextLine1.split(",");
					p[i] = new Player();
					p[i].setName(parts[0]);
					p[i].setNumber(Integer.parseInt(parts[1]));
					p[i].setScore(Integer.parseInt(parts[2]));
					p[i].setTurnCount(Integer.parseInt(parts[3]));
				}
				nextLine1 = bufferedReader1.readLine();
				nextLine1 = bufferedReader1.readLine();
				loadPC = Integer.parseInt(nextLine1);
				nextLine1 = bufferedReader1.readLine();
				loadGT = Integer.parseInt(nextLine1);
				nextLine1 = bufferedReader1.readLine();
				loadTC = Integer.parseInt(nextLine1);
				
				break;
			}
		//Closing Readers
		bufferedReader1.close();
		fileReader1.close();
		}
		//Exception handling logic
		catch (IOException e)
		{
			System.out.println("Sorry, an error occurred..");
		}
		
	}
	public int getPC()
	{
		return loadPC;
	}
	public int getGT()
	{
		return loadGT;
	}
	public int getTC()
	{
		return loadTC;
	}
	public void endGame(int playerCount)
	{
		for (int z = 0; z < playerCount; z++)
		{
			for (int i = 0; i < playerCount - 1; i++)
			{
				if (p[i].getScore() < p[i+1].getScore())
				{
					String tempName = p[i].getName();
					int tempNum = p[i].getNumber();
					int tempScore = p[i].getScore();
					int	tempTurn = p[i].getTurnCount();
					
					p[i].setName(p[i+1].getName());
					p[i].setNumber(p[i+1].getNumber());
					p[i].setScore(p[i+1].getScore());
					p[i].setTurnCount(p[i+1].getTurnCount());
					
					p[i+1].setName(tempName);
					p[i+1].setNumber(tempNum);
					p[i+1].setScore(tempScore);
					p[i+1].setTurnCount(tempTurn);
				}
			}
		}
		System.out.println("The win condition has been reached!");
		System.out.printf("%-11s%-12s%-15s\n", " " , "Player" ,"Total Points");
		for (int i = 0; i < playerCount; i++)
		{
			int pos = i + 1;
			System.out.printf("%-1s%-2s%-8s%-12s%-15s\n", pos , "st" , " " , p[i].getName() ,p[i].getScore());
		}
	}
}
