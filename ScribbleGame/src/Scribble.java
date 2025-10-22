import java.util.InputMismatchException;
import java.util.Scanner;

public class Scribble {
	private int saveQuit;
	private int menuChoice = 0;
	private int selection = 2;
	private int gameType = 1;
	private int t = 5;
	Game g = new Game();
	
	public void getMenuChoice()
	{
		
	while (menuChoice != 4)
	{
		Scanner s = new Scanner(System.in);
		System.out.println("1. Start Game");
		System.out.println("2. Load Game");
		System.out.println("3. Options");
		System.out.println("4. Exit");
		
		System.out.println("Please select a menu choice by entering the corresponding number");
		try{menuChoice = s.nextInt();}
		catch(InputMismatchException e) 
		{
			System.out.println("Please enter a number");
		}
		s.nextLine();
		
		if (menuChoice == 1)
		{
			System.out.println("You selected option 1!");
			
			g.populate();
			g.playerSelection(selection);
			
			if (gameType == 1)
			{
				g.display();
				g.firstTurn();
				g.display();
				for (int x = 1; x < selection; x++)
				{
					g.turnMatch(1 , x);
					g.userInput(x);
					g.scoreCheck(x);
					g.display();
				}
				for (int c = 2; c < t + 1; c++)
				{
					for (int x = 0; x < selection; x++)
					{
						System.out.println("Would you like to save and quit the game?");
						System.out.println("1. Continue");
						System.out.println("2. Save and quit");
						try{saveQuit = s.nextInt();}
						catch(InputMismatchException e) 
						{
							System.out.println("Please enter a number");
						}
						s.nextLine();
						if (saveQuit == 1)
						{
							g.turnMatch(c , x);
							g.userInput(x);
							g.scoreCheck(x);
							g.display();
						}
						else if (saveQuit == 2)
						{
							String fileName = "";
							System.out.println("Please enter a name for the save file.");
							try{fileName = s.nextLine();}
							catch(InputMismatchException e) 
							{
								System.out.println("Please enter a word");
							}
							g.saveAndQuit(fileName , selection , gameType, t);
							break;
						}
					}
					if (saveQuit ==2)
					{
						System.out.println("Now exiting game...");
						break;
					}
				}
				g.endGame(selection);
			}
			else if (gameType == 2)
			{
				int t = 0;
				int x = 0;
				boolean a = false;
				g.display();
				g.firstTurn();
				g.display();
				for (x = 1; x < selection; x++)
				{
					g.userInput(x);
					g.scoreCheck(x);
					g.display();
				}
				x = 0;
				while (a != true)
				{
						g.userInput(x);
						g.scoreCheck(x);
						g.display();
						a = g.checkPointMatch(x, t);
						if(x == selection)
						{
							x = 0;
						}
						else
						{
							x++;
						}
					
				}
				g.endGame(selection);
			}
		}
		else if (menuChoice == 2)
		{
			String loadFile = "";
			System.out.println("You selected option 2!");
			System.out.println("Please enter the name of the game you would like to load.");
			try{loadFile = s.nextLine();}
			catch(InputMismatchException e) 
			{
				System.out.println("Please enter a word");
			}
			g.populate();
			g.loadGame(loadFile);
			g.display();
			int selection = g.getPC();
			int gameType = g.getGT();
			int t = g.getTC();
			if (gameType == 1)
			{
				for (int c = 2; c < t + 1; c++)
				{
					for (int x = 0; x < selection; x++)
					{
						System.out.println("Would you like to save and quit the game?");
						System.out.println("1. Continue");
						System.out.println("2. Save and quit");
						try{saveQuit = s.nextInt();}
						catch(InputMismatchException e) 
						{
							System.out.println("Please enter a number");
						}
						s.nextLine();
						if (saveQuit == 1)
						{
							g.turnMatch(c , x);
							g.userInput(x);
							g.scoreCheck(x);
							g.display();
						}
						else if (saveQuit == 2)
						{
							String fileName = "";
							System.out.println("Please enter a name for the save file.");
							try{fileName = s.nextLine();}
							catch(InputMismatchException e) 
							{
								System.out.println("Please enter a word");
							}
							g.saveAndQuit(fileName , selection , gameType, t);
							break;
						}
					}
					if (saveQuit ==2)
					{
						System.out.println("Now exiting game...");
						break;
					}
				}
				g.endGame(selection);
			}
			else if (gameType == 2)
			{
				
				int x = 0;
				boolean a = false;
				while (a != true)
				{
						g.display();
						g.userInput(x);
						g.scoreCheck(x);
						a = g.checkPointMatch(x, t);
						if(x == selection)
						{
							x = 0;
						}
						else
						{
							x++;
						}
				}
				g.endGame(selection);
			}
		}
		else if (menuChoice == 3)
		{
			int optionsChoice = 0;
			System.out.println("Please select an option from the menu!");
			System.out.println("1. Rules");
			System.out.println("2. Set Player Count");
			System.out.println("3. Set Game Type");
			System.out.println("4. Set max turns/points");
			System.out.println("5. View amount of points per letter");
			System.out.println("6. Return to main menu");
			try{optionsChoice = s.nextInt();}
			catch(InputMismatchException e) 
			{
				System.out.println("Please enter a number");
			}
			s.nextLine();
			
			if (optionsChoice == 1)
			{
				System.out.println("Rules of scribble:");
				System.out.println("1. 2-4 Players");
				System.out.println("2. During the first turn the word must be atleast 2 letters long and will always be placed in the middle of the board");
				System.out.println("3. Players will take turns to place words with a max of 15 letters which must connect to an already existing word");
				System.out.println("4. Each letter is worth a certain amount of points e.g. a = 1");
				System.out.println("5. At the end of th turn each players score is totalled");
				System.out.println("6. The game ends in a turn based game when the specified amonut of turns have taken place and the player with the highest total score wins!");
				System.out.println("7. The game ends in a point based game when a players total points reaches the specified point total");
				System.out.println("8. Enter every letter of the word you are playing");
			}
			else if (optionsChoice == 2)
			{
				System.out.println("How many players would you like? (2-4)");
				try{selection = s.nextInt();}
				catch(InputMismatchException e) 
				{
					System.out.println("Please enter a number");
				}
				g.playerSelection(selection);
			}
			else if (optionsChoice == 3)
			{
				System.out.println("Please enter a number for the corresponding option");
				System.out.println("1. Turn Based game");
				System.out.println("2. Points Based game");
				try{gameType = s.nextInt();}
				catch(InputMismatchException e) 
				{
					System.out.println("Please enter a number");
				}
				s.nextLine();
			}
			else if (optionsChoice == 4)
			{
				if (gameType == 1)
				{
				System.out.println("Please set the amount of turns per player");
				try{t = s.nextInt();}
				catch(InputMismatchException e) 
				{
					System.out.println("Please enter a number");
				}
				}
				else if (gameType == 2)
				{
					while (t <= 99)
					{
						System.out.println("Please set the score needed to end the game (min 100)");
						try{t = s.nextInt();}
						catch(InputMismatchException e) 
						{
							System.out.println("Please enter a number");
						}
					}
				}
			}
			else if (optionsChoice == 5)
			{
				System.out.println("1 point - aeilnorstu");
				System.out.println("2 points - dg");
				System.out.println("3 points - bcmp");
				System.out.println("4 points - fhvwy");
				System.out.println("5 points - k");
				System.out.println("8 points - jx");
				System.out.println("10 points - qz");
			}
			else if (optionsChoice == 6)
			{
				System.out.println("Returning to main menu...");
			}
			else
			{
				System.out.println("Please enter a valid number");
			}
		}
		else if (menuChoice == 4)
		{
			System.out.println("You selected option 4! The program will now exit.");
			System.out.println("Have a good day!");
			s.close();
		}
		else
		{
			System.out.println("That is not a valid Input");
		}
		}
	}
	public static void main(String[] args) 
	{
		Scribble z = new Scribble();
		
		z.getMenuChoice();
	}

}
