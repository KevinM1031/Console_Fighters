// Console Fighters
// Made by Kevin Min
// Version 1.3.3

/*
 * NOTICE
 * Some changes were made to make the game more presentable.
 * Such changes only include the new GUI using swing.
 */


import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JFrame;

public class ConsoleFighters
{
	// initializing all in-game values
			// player/opponent stats
			public static int playerHealth = 100;
			public static int opponentHealth = 10;
			public static int playerMaxHealth = 100;
			public static int opponentMaxHealth = 10;
			public static int playerMinDmg = 10;
			public static int playerMaxDmg = 20;
			public static int opponentMinDmg = 10;
			public static int opponentMaxDmg = 20;
			public static int playerAtkSpeed = 10;
			public static int opponentAtkSpeed = 10;
			public static int playerLevel = 1;
			public static int opponentLevel = 1;
			public static int playerPrime = 0;
			public static int opponentPrime = 0;
			
			// gameplay control variables
			public static int abilityChosen = 0;
			public static int damageDealt = 0;
			public static boolean continueGame = true;
			public static int upgradeChoice = 0;
			public static String choiceInput = "";
			public static boolean opponentAtkSuccessful = false;
			
			// classes and amplifiers
			public static String playerClass = "";
			public static double playerPunchDmgAmplifier = 1;
			public static double playerKickDmgAmplifier = 1.5;
			public static double playerUppercutDmgAmplifier = 3;
			public static double playerBandageDmgAmplifier = 0.15;
			public static double playerMedkitDmgAmplifier = 0.35;
			public static double playerBodySlamDmgAmplifier = 4;
			public static double playerSpareOrgansDmgAmplifier = 0.8;
			public static double playerShieldDmgAmplifier = 0.4;
			public static double playerShieldDefAmplifier = 1;
			public static double playerFangsDmgAmplifier = 0.2;
			public static double playerPunchAccuracyAmplifier = 100;
			public static double playerKickAccuracyAmplifier = 70;
			public static double playerUppercutAccuracyAmplifier = 30;
			public static double playerBandageAccuracyAmplifier = 90;
			public static double playerMedkitAccuracyAmplifier = 40;
			public static double playerBodySlamAccuracyAmplifier = 30;
			public static double playerSpareOrgansAccuracyAmplifier = 25;
			public static double playerShieldAccuracyAmplifier = 60;
			public static double playerFangsAccuracyAmplifier = 60;
			public static double playerAccuracyAmplifier = 0;
			public static boolean playerShieldSuccessful = false;
			
			// miscellaneous
			static ArrayList<String> stringArray = new ArrayList<String>();
			public static Random R = new Random();
			static JFrame f = new JFrame("Console Fighters");
			static Window window = new Window();
			static boolean running = false, reUpdate = false, startGame = false, resume = false;
			
	public static void main(String[] args) throws InterruptedException
	{
		f.setVisible(true);
		f.setSize(1000,800);
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBackground(Color.black);
		f.getContentPane().add(window);
		window.setLayout(null);
		
		// game introduction and choosing class
		menuClassDisplay();
		window.update();
		window.setButtonsToMenu();
		
		while(true) {
			
			Thread.sleep(100);
			
			if(reUpdate) {
				window.update();
				reUpdate = false;
				
			// start game
			} else if(startGame) {
				for (boolean playerDead = false; playerDead == false;) {
					
					// reset primary attacker
					playerPrime = 0;
					opponentPrime = 0;
					
					// player and opponent display/info
					Thread.sleep(1000);
					mainDisplay();
					abilityDisplay();
					window.update();
										
					// choosing what ability to use
					switch(playerClass) {
					case "a": window.setButtonsToAbilitiesA(); break;
					case "m": window.setButtonsToAbilitiesM(); break;
					case "d": window.setButtonsToAbilitiesD(); break;
					case "v": window.setButtonsToAbilitiesV(); break;
					}
					
					
					while(true) {
						if(resume) {
							resume = false;
							break;
						}
						Thread.sleep(500);
					}
					
					// choosing who attacks first
					choosePrimaryAttacker();
					
					if (playerClass.equals("k") && choiceInput.equals("s") && R.nextInt(100) < playerShieldAccuracyAmplifier)
					{
						playerShieldSuccessful = true;
						stringArray.add("[t] Shield Activated.");
					}
						// if player attacks first
					if (continueGame && playerPrime >= opponentPrime && playerShieldSuccessful == false)
					{
						
						// player's turn
						stringArray.clear();
						stringArray.add("You were faster!");
						
						// testfor chosen ability & running chosen ability
						Thread.sleep(500);
						playerAbilityUse();
						stringArray.add("");
						abilityChosen = 0;
												
						// check if opponent is dead
						if (opponentHealth == 0)
						{
							// exiting current round
							stringArray.add("Your opponent has been defeated, and you won!");
							continueGame = false;
							
							// upgrading opponent
							opponentUpgrades();
							playerLevel++;
							opponentLevel++;
							opponentHealth = opponentMaxHealth;
											
							stringArray.add("");
							stringArray.add("");
							stringArray.add("A new Lv." + opponentLevel + " opponent appeared.");
							
							// upgrade options display
							upgradesDisplay();
							window.update();
							
							// choosing what to upgrade
							window.setButtonsToUpgrade();
							while(true) {
								if(resume) {
									resume = false;
									break;
								}
								Thread.sleep(500);
							}
							playerUpgrades();
						}
						
						if(continueGame) {
							// opponent's turn
							// custom AI for choosing opponent's ability
							opponentAttackAI();
							stringArray.add("");
							window.update();
							Thread.sleep(2500);
						}
					}
					
					// checking if player is dead
					if (playerHealth == 0)
					{
						Thread.sleep(1000);
						// ending game
						continueGame = false;
						playerDead = true;
						stringArray.clear();
						stringArray.add("You have been defeated, and you lost!");
						stringArray.add("");
						stringArray.add("==================================================================================================");
						stringArray.add("");
						stringArray.add("          GAME          OVER");
						stringArray.add("");
						stringArray.add("          You died at Lv." + playerLevel + "!");
						stringArray.add("");
						stringArray.add("==================================================================================================");
						window.update();
						resetAllData();
					}
					
					// if opponent starts first
					if ((continueGame && playerPrime < opponentPrime) || playerShieldSuccessful == true)
					{
						stringArray.clear();
						stringArray.add("Your opponent was faster!");
						
						// custom AI for choosing opponent's ability
						opponentAttackAI();
						stringArray.add("");
						
												
						// checking if player is dead
						if (playerHealth == 0)
						{
							window.update();
							Thread.sleep(1250);
							
							// ending game
							continueGame = false;
							playerDead = true;
							stringArray.clear();
							stringArray.add("You have been defeated, and you lost!");
							stringArray.add("");
							stringArray.add("==================================================================================================");
							stringArray.add("");
							stringArray.add("          GAME          OVER");
							stringArray.add("");
							stringArray.add("          You died at Lv." + playerLevel + "!");
							stringArray.add("");
							stringArray.add("==================================================================================================");
							window.update();
							resetAllData();
						}
						
						// player's turn
						if(continueGame)
						{
							Thread.sleep(500);
							playerAbilityUse();
							stringArray.add("");
							abilityChosen = 0;
						}
						
						// check if opponent is dead
						if (opponentHealth == 0)
						{
							// ending game
							continueGame = false;
							stringArray.add("Your opponent has been defeated, and you won!");
							
							// opponent upgrades
							opponentUpgrades();
							opponentHealth = opponentMaxHealth;
							playerLevel++;
							opponentLevel++;
							
							stringArray.add("");
							stringArray.add("");
							stringArray.add("A new Lv." + opponentLevel + " opponent appeared.");
							
							// upgrade options display
							upgradesDisplay();
							window.update();
							
							// choosing what to upgrade
							window.setButtonsToUpgrade();
							while(true) {
								if(resume) {
									resume = false;
									break;
								}
								Thread.sleep(500);
							}
							playerUpgrades();
						} else {
							window.update();
							Thread.sleep(2500);
						}
					}

					
					// reset gameplay stats
					continueGame = true;
					playerPrime = 0;
					opponentPrime = 0;
					playerShieldSuccessful = false;
				}
			}
		}
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// main display
	public static void mainDisplay()
	{
		stringArray.clear();
		stringArray.add("==================================================================================================");
		stringArray.add("");
		stringArray.add("                                                                                                                                                                     [ : ]");
		stringArray.add("                                                                                                                                                                    \\ | /               Lv." + opponentLevel);
		stringArray.add("                                                                                                                                                                       |");
		stringArray.add("                                                                                                                                                                     /  \\");
		stringArray.add("");
		stringArray.add("                                                                                                                                                                  Opponent HP: " + opponentHealth + "/" + opponentMaxHealth);
		stringArray.add("                                                                                                                                                                  Opponent Dmg: " + opponentMinDmg + "-" + opponentMaxDmg);
		stringArray.add("                                                                                                                                                                  Opponent Speed: " + opponentAtkSpeed);
		stringArray.add("            ( : )");
		stringArray.add("           / | \\               Lv." + playerLevel);
		stringArray.add("              |");
		stringArray.add("            /  \\");
		stringArray.add("");
		stringArray.add("         Your HP: " + playerHealth + "/" + playerMaxHealth);
		stringArray.add("         Your Dmg: " + playerMinDmg + "-" + playerMaxDmg);
		stringArray.add("         Your Speed: " + playerAtkSpeed);
		stringArray.add("         Your Accuracy: +" + (int)playerAccuracyAmplifier + "%");
		stringArray.add("==================================================================================================");
	}
	
	// ability display
	public static void abilityDisplay()
	{
		if (playerClass.equals("a"))
		{
			stringArray.add("          [1] Punch             [Dmg: 100%]             (Accuracy: 100%)");
			stringArray.add("          [2] Kick               [Dmg: 150%]              (Accuracy: 70%)");
			stringArray.add("          [3] Uppercut        [Dmg: 300%]              (Accuracy: 30%)");
			stringArray.add("          [4] Bandage          [HP: +15%]                (Accuracy: 90%)");
			stringArray.add("          [5] Medkit            [HP: +35%]                (Accuracy: 40%)");
			stringArray.add("==================================================================================================");
		}
		else if (playerClass.equals("m"))
		{
			stringArray.add("          [1] Punch           [Dmg: 100%]              (Accuracy: 100%)");
			stringArray.add("          [2] Kick            [Dmg: 150%]              (Accuracy: 90%)");
			stringArray.add("          [3] Uppercut        [Dmg: 300%]              (Accuracy: 60%)");
			stringArray.add("          [4] Body Slam       [Dmg: 400%]              (Accuracy: 30%)");
			stringArray.add("          [5] Bandage         [HP: +15%]               (Accuracy: 90%)");
			stringArray.add("==================================================================================================");
		}
		else if (playerClass.equals("d"))
		{
			stringArray.add("          [1] Punch           [Dmg: 100%]              (Accuracy: 90%)");
			stringArray.add("          [2] Kick            [Dmg: 150%]              (Accuracy: 65%)");
			stringArray.add("          [3] Bandage         [HP: +20%]               (Accuracy: 100%)");
			stringArray.add("          [4] Medkit          [HP: +40%]               (Accuracy: 40%)");
			stringArray.add("          [5] Spare Organs    [HP: +80%]               (Accuracy: 25%)");
			stringArray.add("==================================================================================================");
		}
		else if (playerClass.equals("k"))
		{
			stringArray.add("          [1] Punch           [Dmg: 100%]              (Accuracy: 100%)");
			stringArray.add("          [2] Kick            [Dmg: 150%]              (Accuracy: 80%)");
			stringArray.add("          [3] Uppercut        [Dmg: 300%]              (Accuracy: 30%)");	
			stringArray.add("          [4] Shield          [Refl: 40%] [Def: 100%]  (Accuracy: 65%)");
			stringArray.add("          [5] Bandage         [HP: +15%]               (Accuracy: 90%)");
			stringArray.add("==================================================================================================");
		}
		else if (playerClass.equals("v"))
		{
			stringArray.add("          [1] Punch           [Dmg: 100%]              (Accuracy: 90%)");
			stringArray.add("          [2] Kick            [Dmg: 170%]              (Accuracy: 65%)");
			stringArray.add("          [3] Uppercut        [Dmg: 300%]              (Accuracy: 20%)");	
			stringArray.add("          [4] Bandage         [HP: +20%]               (Accuracy: 80%)");
			stringArray.add("          [5] Fangs           [HP Steal: 20%]          (Accuracy: 60%)");
			stringArray.add("==================================================================================================");
		}
	}
	
	// upgrades display
	public static void upgradesDisplay()
	{
		stringArray.add("==================================================================================================");
		stringArray.add("          Choice 1: Max. Damage +10");
		stringArray.add("          Choice 2: Min. Damage +10");
		stringArray.add("          Choice 3: Max. Damage +5 & Min. Damage +5");
		stringArray.add("          Choice 4: Speed +10 & Accuracy +3%");
		stringArray.add("          Choice 5: Max. HP +30");
		stringArray.add("          Choice 6: +100% HP & Accuracy +6%");
		stringArray.add("==================================================================================================");
		stringArray.add("What do you want to upgrade? Choose one of the above:");
	}
	
	// game menu & class display
	public static void menuClassDisplay()
	{
		stringArray.clear();
		stringArray.add("==================================================================================================");
		stringArray.add("                                                                                      .-=[ Console Fighters v1.3 ]=-. by Kevin1031");
		stringArray.add("==================================================================================================");
		stringArray.add("");
		stringArray.add("[1] Assassin          \"Has one job: kill.\"");
		stringArray.add("                              [Punch:        100% Dmg, 100% Accuracy]");
		stringArray.add("                              [Kick:         150% Dmg, 70% Accuracy]");
		stringArray.add("                              [Uppercut:     300% Dmg, 30% Accuracy]");
		stringArray.add("                              [Bandage:      +15% HP,  90% Accuracy]");
		stringArray.add("                              [Medkit:       +35% HP,  40% Accuracy]");
		stringArray.add("");
		stringArray.add("[2] Martial Artist    \"Specially trained for accuracy and power.\"");
		stringArray.add("                              [Punch:        100% Dmg, 100% Accuracy]");
		stringArray.add("                              [Kick:         150% Dmg, 90% Accuracy]");
		stringArray.add("                              [Uppercut:     300% Dmg, 60% Accuracy]");
		stringArray.add("                              [Body Slam:    400% Dmg, 30% Accuracy]");
		stringArray.add("                              [Bandage:      +15% HP,  90% Accuracy]");
		stringArray.add("");
		stringArray.add("[3] Doctor             \"Majored in medicine, minored in wrestling.\"");
		stringArray.add("                              [Punch:        100% Dmg, 90% Accuracy]");
		stringArray.add("                              [Kick:         150% Dmg, 65% Accuracy]");
		stringArray.add("                              [Bandage:      +20% HP,  100% Accuracy]");
		stringArray.add("                              [Medkit:       +40% HP,  60% Accuracy]");
		stringArray.add("                              [Spare Organs: +80% HP,  25% Accuracy]");
		stringArray.add("");
		stringArray.add("[4] Knight              \"Time traveler from the mideval world of war and death.\"");
		stringArray.add("(unused)");
		stringArray.add("");
		stringArray.add("[5] Vampire            \"The well-known blood sucking man in a tuxedo top.\"");
		stringArray.add("                              [Punch:        100% Dmg, 90% Accuracy]");
		stringArray.add("                              [Kick:         170% Dmg, 65% Accuracy]");
		stringArray.add("                              [Uppercut:     300% Dmg, 20% Accuracy]");
		stringArray.add("                              [Bandage:      +15% HP,  80% Accuracy]");
		stringArray.add("                              [Fangs:        20% HP steal, 60% Accuracy]");
		stringArray.add("");
		stringArray.add("[6] Instructions for playing the game");
		stringArray.add("");
		stringArray.add("");
		stringArray.add(">> Choose your class");
	}
	
	// choosing ability
	public static void chooseAbility()
	{
		stringArray.add("What will you do? Choose one of the above: ");
		
		int a = 1;
		while (a == 1)
		{
			Scanner choice = new Scanner(System.in);
			choiceInput = choice.next();
			choice.close();
			
			// assigning int value for each chosen ability
			if (playerClass.equals("a"))
			{
				if (choiceInput.equals("p"))
				{
					abilityChosen = 1;
					a++;
				}
				else if (choiceInput.equals("k"))
				{
					abilityChosen = 2;
					a++;
				}
				else if (choiceInput.equals("u"))
				{
					abilityChosen = 3;
					a++;
				}
				else if (choiceInput.equals("b"))
				{
					abilityChosen = 4;
					a++;
				}
				else if (choiceInput.equals("m"))
				{
					abilityChosen = 5;
					a++;
				}
				else
				{
					stringArray.add("Invalid choice. Please choose again.");
				}
			}
			else if (playerClass.equals("m"))
			{
				if (choiceInput.equals("p"))
				{
					abilityChosen = 1;
					a++;
				}
				else if (choiceInput.equals("k"))
				{
					abilityChosen = 2;
					a++;
				}
				else if (choiceInput.equals("u"))
				{
					abilityChosen = 3;
					a++;
				}
				else if (choiceInput.equals("s"))
				{
					abilityChosen = 6;
					a++;
				}
				else if (choiceInput.equals("b"))
				{
					abilityChosen = 4;
					a++;
				}
				else
				{
					stringArray.add("Invalid choice. Please choose again.");
				}
			}
			else if (playerClass.equals("d"))
			{
				if (choiceInput.equals("p"))
				{
					abilityChosen = 1;
					a++;
				}
				else if (choiceInput.equals("k"))
				{
					abilityChosen = 2;
					a++;
				}
				else if (choiceInput.equals("b"))
				{
					abilityChosen = 4;
					a++;
				}
				else if (choiceInput.equals("m"))
				{
					abilityChosen = 5;
					a++;
				}
				else if (choiceInput.equals("s"))
				{
					abilityChosen = 7;
					a++;
				}
				else
				{
					stringArray.add("Invalid choice. Please choose again.");
				}
			}
			else if (playerClass.equals("k"))
			{
				if (choiceInput.equals("p"))
				{
					abilityChosen = 1;
					a++;
				}
				else if (choiceInput.equals("k"))
				{
					abilityChosen = 2;
					a++;
				}
				else if (choiceInput.equals("u"))
				{
					abilityChosen = 3;
					a++;
				}
				else if (choiceInput.equals("s"))
				{
					abilityChosen = 8;
					a++;
				}
				else if (choiceInput.equals("b"))
				{
					abilityChosen = 4;
					a++;
				}
				else
				{
					stringArray.add("Invalid choice. Please choose again.");
				}
			}
			else if (playerClass.equals("v"))
			{
				if (choiceInput.equals("p"))
				{
					abilityChosen = 1;
					a++;
				}
				else if (choiceInput.equals("k"))
				{
					abilityChosen = 2;
					a++;
				}
				else if (choiceInput.equals("u"))
				{
					abilityChosen = 3;
					a++;
				}
				else if (choiceInput.equals("b"))
				{
					abilityChosen = 4;
					a++;
				}
				else if (choiceInput.equals("f"))
				{
					abilityChosen = 9;
					a++;
				}
				else
				{
					stringArray.add("Invalid choice. Please choose again.");
				}
			}
		}
	}
	
	// primary attacker choosing
	public static void choosePrimaryAttacker()
	{
		for (int b = 1; b <= playerAtkSpeed; b++)
		{
			playerPrime += R.nextInt(2);
		}
		for (int b = 1; b <= opponentAtkSpeed; b++)
		{
			opponentPrime += R.nextInt(2);
		}
	}
	
	// testfor & running chosen ability
	public static void playerAbilityUse()
	{
		switch(abilityChosen)
		{
		case 1:
			playerPunch();
			break;
		case 2:
			playerKick();
			break;
		case 3:
			playerUppercut();
			break;
		case 4:
			playerBandage();
			break;
		case 5:
			playerMedkit();
			break;
		case 6:
			playerBodySlam();
			break;
		case 7:
			playerSpareOrgans();
			break;
		case 8:
			playerShield();
			break;
		case 9:
			playerFangs();
			break;
		}
	}
	
	// testfor chosen upgrade
	public static void playerChosenUpgrade()
	{
		int x = 1;
		while (x == 1)
		{
			Scanner abilityUpgradeInput = new Scanner(System.in);
			String abilityUpgrade = abilityUpgradeInput.next();
			abilityUpgradeInput.close();
			
			if (abilityUpgrade.equals("1"))
			{
				upgradeChoice = 1;
				x++;
			}
			else if (abilityUpgrade.equals("2"))
			{
				upgradeChoice = 2;
				x++;
			}
			else if (abilityUpgrade.equals("3"))
			{
				upgradeChoice = 3;
				x++;
			}
			else if (abilityUpgrade.equals("4"))
			{
				upgradeChoice = 4;
				x++;
			}
			else if (abilityUpgrade.equals("5"))
			{
				upgradeChoice = 5;
				x++;
			}
			else if (abilityUpgrade.equals("6"))
			{
				upgradeChoice = 6;
				x++;
			}
			else
			{
				stringArray.add("Invalid choice. Please choose again.");
			}
			
			playerHealth += (int) (playerMaxHealth * 0.35);
			if (playerHealth > playerMaxHealth)
			{
				playerHealth = playerMaxHealth;
			}
		
		}
	}
	
	// testfor and running chosen class
	public static void classChosen() throws InterruptedException
	{
		int y = 1;
		while (y == 1)
		{
			Scanner chooseClassInput = new Scanner(System.in);
			String classInput = chooseClassInput.next();
			chooseClassInput.close();
			
			if (classInput.equals("a"))
			{
				delayTicks();
				delayTicks();
				stringArray.add("You chose the Assassin class.");
				classAssassin();
				playerClass = "a";
				y++;
			}
			else if (classInput.equals("m"))
			{
				delayTicks();
				delayTicks();
				stringArray.add("You chose the Martial Artist class.");
				classMartialArtist();
				playerClass = "m";
				y++;
			}
			else if (classInput.equals("d"))
			{
				delayTicks();
				delayTicks();
				stringArray.add("You chose the Doctor class.");
				classDoctor();
				playerClass = "d";
				y++;
			}
			else if (classInput.equals("k"))
			{
				stringArray.add("This class is under development and marked as unused. Please choose an another class.");
				delayTicks();
				delayTicks();
				stringArray.add("You chose the Knight class.");
				classKnight();
				playerClass = "k";
				y++;
			}
			else if (classInput.equals("v"))
			{
				delayTicks();
				delayTicks();
				stringArray.add("You chose the Vampire class.");
				classVampire();
				playerClass = "v";
				y++;
			}
			else
			{
				stringArray.add("Invalid Choice. Please choose again.");
			}
		}
	}
	// player moves/upgrades
	// punch
	public static void playerPunch()
	{
		if (R.nextInt(100) < playerPunchAccuracyAmplifier + playerAccuracyAmplifier)
		{
			damageDealt = (int) ((R.nextInt(playerMaxDmg - playerMinDmg + 1) + playerMinDmg) * playerPunchDmgAmplifier);
			stringArray.add("You used Punch.");
			opponentHealth -= damageDealt;
			if (opponentHealth < 0)
			{
				opponentHealth = 0;
			}
			stringArray.add("Your opponent lost " + damageDealt + "HP, and now have " + opponentHealth + "/" + opponentMaxHealth + "HP.");
		}
		else
		{
			stringArray.add("You were unlucky and failed to use Punch.");
			stringArray.add("Nothing happened.");
		}
	}
	
	// kick
	public static void playerKick()
	{
		if (R.nextInt(100) < playerKickAccuracyAmplifier + playerAccuracyAmplifier)
		{
			damageDealt = (int) ((R.nextInt(playerMaxDmg - playerMinDmg + 1) + playerMinDmg) * playerKickDmgAmplifier);
			stringArray.add("You used Kick.");
			opponentHealth -= damageDealt;
			if (opponentHealth < 0)
			{
				opponentHealth = 0;
			}
			stringArray.add("Your opponent lost " + damageDealt + "HP, and now have " + opponentHealth + "/" + opponentMaxHealth + "HP.");
		}
		else
		{
			stringArray.add("You were unlucky and failed to use Kick.");
			stringArray.add("Nothing happened.");
		}
	}
	
	// uppercut
	public static void playerUppercut()
	{
		if (R.nextInt(100) < playerUppercutAccuracyAmplifier + playerAccuracyAmplifier)
		{
			damageDealt = (int) ((R.nextInt(playerMaxDmg - playerMinDmg + 1) + playerMinDmg) * playerUppercutDmgAmplifier);
			stringArray.add("You used Uppercut.");
			opponentHealth -= damageDealt;
			if (opponentHealth < 0)
			{
				opponentHealth = 0;
			}
			stringArray.add("Your opponent lost " + damageDealt + "HP, and now have " + opponentHealth + "/" + opponentMaxHealth + "HP.");
		}
		else
		{
			stringArray.add("You were unlucky and failed to use Uppercut.");
			stringArray.add("Nothing happened.");
		}
	}
	
	// bandage
	public static void playerBandage()
	{
		if (R.nextInt(100) < playerBandageAccuracyAmplifier + playerAccuracyAmplifier)
		{
			damageDealt = (int) (playerMaxHealth * playerBandageDmgAmplifier);
			stringArray.add("You used Bandage.");
			playerHealth += damageDealt;
			if (playerHealth > playerMaxHealth)
			{
				playerHealth = playerMaxHealth;
			}
			stringArray.add("You gained " + damageDealt + "HP, and now have " + playerHealth + "/" + playerMaxHealth + "HP.");
		}
		else
		{
			stringArray.add("You were unlucky and failed to use Bandage.");
			stringArray.add("Nothing happened.");
		}
	}
	
	// medkit
	public static void playerMedkit()
	{
		if (R.nextInt(100) < playerMedkitAccuracyAmplifier + playerAccuracyAmplifier)
		{
			damageDealt = (int) (playerMaxHealth * playerMedkitDmgAmplifier);
			stringArray.add("You used Medkit.");
			playerHealth += damageDealt;
			if (playerHealth > playerMaxHealth)
			{
				playerHealth = playerMaxHealth;
			}
			stringArray.add("You gained " + damageDealt + "HP, and now have " + playerHealth + "/" + playerMaxHealth + "HP.");
		}
		else
		{
			stringArray.add("You were unlucky and failed to use Medkit.");
			stringArray.add("Nothing happened.");
		}
	}
	
	// body slam
	public static void playerBodySlam()
		{
			if (R.nextInt(100) < playerBodySlamAccuracyAmplifier + playerAccuracyAmplifier)
			{
				damageDealt = (int) ((R.nextInt(playerMaxDmg - playerMinDmg + 1) + playerMinDmg) * playerBodySlamDmgAmplifier);
				stringArray.add("You used Body Slam.");
				opponentHealth -= damageDealt;
				if (opponentHealth < 0)
				{
					opponentHealth = 0;
				}
				stringArray.add("Your opponent lost " + damageDealt + "HP, and now have " + opponentHealth + "/" + opponentMaxHealth + "HP.");
			}
			else
			{
				stringArray.add("You were unlucky and failed to use Body Slam.");
				stringArray.add("Nothing happened.");
			}
		}
		
	// spare organs
	public static void playerSpareOrgans()
		{
			if (R.nextInt(100) < playerSpareOrgansAccuracyAmplifier + playerAccuracyAmplifier)
			{
				damageDealt = (int) (playerMaxHealth * playerSpareOrgansDmgAmplifier);
				stringArray.add("You used Spare Organs.");
				playerHealth += damageDealt;
				if (playerHealth > playerMaxHealth)
				{
					playerHealth = playerMaxHealth;
				}
				stringArray.add("Your gained " + damageDealt + "HP, and now have " + playerHealth + "/" + playerMaxHealth + "HP.");
			}
			else
			{
				stringArray.add("You were unlucky and failed to use Spare Organs.");
				stringArray.add("Nothing happened.");
			}
		}
		
	// shield
	public static void playerShield()
		{
			if (playerShieldSuccessful == true && opponentAtkSuccessful == true)
			{
				damageDealt = (int) (damageDealt * playerShieldDmgAmplifier);
				stringArray.add("You used Shield and reflected your opponent's attack.");
				opponentHealth -= damageDealt;
				if (opponentHealth < 0)
				{
					opponentHealth = 0;
				}
				stringArray.add("Your opponent lost " + damageDealt + "HP, and now have " + opponentHealth + "/" + opponentMaxHealth + "HP.");
			}
			else if (playerShieldSuccessful == true && opponentAtkSuccessful == false)
			{
				stringArray.add("There was nothing to reflect.");
				stringArray.add("Nothing happened.");
			}
			else
			{
				stringArray.add("You were unlucky and failed to use Shield.");
				stringArray.add("Nothing happened.");
			}
		}
		
	// fangs
	public static void playerFangs()
		{
			if (R.nextInt(100) < playerFangsAccuracyAmplifier + playerAccuracyAmplifier)
			{
				damageDealt = (int) (opponentHealth * playerFangsDmgAmplifier);
				stringArray.add("You used Fangs.");
				opponentHealth -= damageDealt;
				playerHealth += damageDealt;
				if (opponentHealth < 0)
				{
					opponentHealth = 0;
				}
				if (playerHealth > playerMaxHealth)
				{
					playerHealth = playerMaxHealth;
				}
				stringArray.add("Your opponent lost " + damageDealt + "HP, and now have " + opponentHealth + "/" + opponentMaxHealth + "HP.");
				stringArray.add("Your took " + damageDealt + "HP from your opponent, and now have " + playerHealth + "/" + playerMaxHealth + "HP.");
			}
			else
			{
				stringArray.add("You were unlucky and failed to use Fangs.");
				stringArray.add("Nothing happened.");
			}
		}
	
	// player upgrades
	public static void playerUpgrades()
	{
		switch (upgradeChoice)
		{
		case 1:
			playerMaxDmg += 10;
			stringArray.add("You chose Choice 1");
			break;
		case 2:
			playerMinDmg += 10;
			stringArray.add("You chose Choice 2");
			if (playerMinDmg > playerMaxDmg)
			{
				playerMinDmg = playerMaxDmg;
			}
			break;
		case 3:
			playerMaxDmg += 5;
			playerMinDmg += 5;
			stringArray.add("You chose Choice 3");
			break;
		case 4:
			playerAtkSpeed += 10;
			playerAccuracyAmplifier += 3;
			stringArray.add("You chose Choice 4");
			break;
		case 5:
			playerMaxHealth += 30;
			playerHealth += 30;
			stringArray.add("You chose Choice 5");
			break;
		case 6:
			playerHealth = playerMaxHealth;
			playerAccuracyAmplifier += 6;
			stringArray.add("You chose Choice 6");
			if (playerMinDmg > playerMaxDmg)
			{
				playerMinDmg = playerMaxDmg;
			}
			break;
		}
		stringArray.add("Your recieved +35% HP!");
	}
	
	// opponent moves
	// punch
	public static void opponentPunch()
	{
		if (playerShieldSuccessful == false)
		{
			damageDealt = R.nextInt(opponentMaxDmg - opponentMinDmg + 1) + opponentMinDmg;
			stringArray.add("Your opponent used Punch.");
			playerHealth -= damageDealt;
			if (playerHealth < 0)
			{
				playerHealth = 0;
			}
			stringArray.add("You lost " + damageDealt + "HP, and now have " + playerHealth + "/" + playerMaxHealth + "HP.");
			opponentAtkSuccessful = true;
		}
		else if (playerShieldSuccessful == true)
		{
			stringArray.add("Your opponent used Punch.");
			stringArray.add("You blocked your opponent's attack.");
			opponentAtkSuccessful = true;
		}
		else
		{
			stringArray.add("You blocked your opponent's attack.");
			stringArray.add("Nothing happened.");
			opponentAtkSuccessful = false;
		}
	}
	
	// kick
	public static void opponentKick()
	{
		if (R.nextInt(10) > 2 && playerShieldSuccessful == false)
		{
			damageDealt = (int) ((R.nextInt(opponentMaxDmg - opponentMinDmg + 1) + opponentMinDmg) * 1.5);
			stringArray.add("Your opponent used Kick.");
			playerHealth -= damageDealt;
			if (playerHealth < 0)
			{
				playerHealth = 0;
			}
			stringArray.add("You lost " + damageDealt + "HP, and now have " + playerHealth + "/" + playerMaxHealth + "HP.");
			opponentAtkSuccessful = true;
		}
		else if (playerShieldSuccessful == true)
		{
			stringArray.add("Your opponent used Punch.");
			stringArray.add("You blocked your opponent's attack.");
			opponentAtkSuccessful = true;
		}
		else
		{
			stringArray.add("Your opponent was unlucky and failed to use Kick.");
			stringArray.add("Nothing happened.");
			opponentAtkSuccessful = false;
		}
	}
	
	// uppercut
	public static void opponentUppercut()
	{
		if (R.nextInt(10) > 6 && playerShieldSuccessful == false)
		{
			damageDealt = (int) ((R.nextInt(opponentMaxDmg - opponentMinDmg + 1) + opponentMinDmg) * 3);
			stringArray.add("Your opponent used Uppercut.");
			playerHealth -= damageDealt;
			if (playerHealth < 0)
			{
				playerHealth = 0;
			}
			stringArray.add("You lost " + damageDealt + "HP, and now have " + playerHealth + "/" + playerMaxHealth + "HP.");
			opponentAtkSuccessful = true;
		}
		else if (playerShieldSuccessful == true)
		{
			stringArray.add("Your opponent used Punch.");
			stringArray.add("You blocked your opponent's attack.");
			opponentAtkSuccessful = true;
		}
		else
		{
			stringArray.add("Your opponent was unlucky and failed to use Uppercut.");
			stringArray.add("Nothing happened.");
			opponentAtkSuccessful = false;
		}
	}
	
	// bandage
	public static void opponentBandage()
	{
		if (R.nextInt(10) > 0 && playerShieldSuccessful == false)
		{
			damageDealt = (int) (opponentMaxHealth * 0.15);
			stringArray.add("Your opponent used Bandage.");
			opponentHealth += damageDealt;
			if (opponentHealth > opponentMaxHealth)
			{
				opponentHealth = opponentMaxHealth;
			}
			stringArray.add("Your opponent gained " + damageDealt + "HP, and now have " + opponentHealth + "/" + opponentMaxHealth + "HP.");
			opponentAtkSuccessful = false;
		}
		else if (playerShieldSuccessful == true)
		{
			stringArray.add("Your opponent used Punch.");
			stringArray.add("You blocked your opponent's attack.");
			opponentAtkSuccessful = true;
		}
		else
		{
			stringArray.add("Your opponent was unlucky and failed to use Bandage.");
			stringArray.add("Nothing happened.");
			opponentAtkSuccessful = false;
		}
	}
	
	// medkit
	public static void opponentMedkit()
	{
		if (R.nextInt(10) > 5 && playerShieldSuccessful == false)
		{
			damageDealt = (int) (opponentMaxHealth * 0.35);
			stringArray.add("Your opponent used Medkit.");
			opponentHealth += damageDealt;
			if (opponentHealth > opponentMaxHealth)
			{
				opponentHealth = opponentMaxHealth;
			}
			stringArray.add("Your opponent gained " + damageDealt + "HP, and now have " + opponentHealth + "/" + opponentMaxHealth + "HP.");
			opponentAtkSuccessful = false;
		}
		else if (playerShieldSuccessful == true)
		{
			stringArray.add("Your opponent used Punch.");
			stringArray.add("You blocked your opponent's attack.");
			opponentAtkSuccessful = true;
		}
		else
		{
			stringArray.add("Your opponent was unlucky and failed to use Medkit.");
			stringArray.add("Nothing happened.");
			opponentAtkSuccessful = false;
		}
	}
	
	// opponent upgrades
	public static void opponentUpgrades()
	{
		opponentMaxHealth += 20;
		switch (R.nextInt(6))
		{
		case 0:
			if (opponentMinDmg == opponentMaxDmg)
			{
				opponentMaxDmg += 8;
			}
			
			opponentMinDmg += 8;
			if (opponentMinDmg > opponentMaxDmg)
			{
				opponentMinDmg = opponentMaxDmg;
			}
			break;
		case 1:
			opponentMaxDmg += 8;
			break;
		case 2:
			opponentMaxDmg += 4;
			opponentMinDmg += 4;
			break;
		case 3:
			opponentAtkSpeed += 4;
			break;
		case 4:
			opponentAtkSpeed += 2;
			opponentMaxDmg += 4;
			break;
		case 5:
			if (opponentMinDmg == opponentMaxDmg)
			{
				opponentMaxDmg += 4;
			}
			
			opponentAtkSpeed += 2;
			opponentMinDmg += 4;
			if (opponentMinDmg > opponentMaxDmg)
			{
				opponentMinDmg = opponentMaxDmg;
			}
			break;
		}
	}
	
	// time delay
	public static void delayTicks() throws InterruptedException
	{
		Thread.sleep(500);
	}
	
	// opponent AI for attacking
	public static void opponentAttackAI()
	{
		if (continueGame && opponentHealth > (int) (opponentMaxHealth * 0.7) && playerHealth > (int) (playerMaxHealth * 0.7))
		{
			switch (R.nextInt(5))
			{
			case 0:
				opponentPunch();
				break;
			case 1:
				opponentPunch();
				break;
			case 2:
				opponentKick();
				break;
			case 3:
				opponentKick();
				break;
			case 4:
				opponentUppercut();
				break;
			}
		}
		else if (continueGame && opponentHealth > (int) (opponentMaxHealth * 0.7) && playerHealth > (int) (playerMaxHealth * 0.3))
		{
			switch (R.nextInt(3))
			{
			case 0:
				opponentPunch();
				break;
			case 1:
				opponentKick();
				break;
			case 2: 
				opponentUppercut();
				break;
			}
		}
		else if (continueGame && opponentHealth > (int) (opponentMaxHealth * 0.7) && playerHealth <= (int) (playerMaxHealth * 0.3))
		{
			switch (R.nextInt(2))
			{
			case 0:
				opponentPunch();
				break;
			case 1:
				opponentKick();
				break;
			}
		}
		else if (continueGame && opponentHealth <= (int) (opponentMaxHealth * 0.3) && playerHealth > (int) (playerMaxHealth * 0.7))
		{
			switch (R.nextInt(7))
			{
			case 0:
				opponentPunch();
				break;
			case 1:
				opponentPunch();
				break;
			case 2:
				opponentKick();
				break;
			case 3:
				opponentBandage();
				break;
			case 4: 
				opponentMedkit();
				break;
			case 5:
				opponentMedkit();
				break;
			case 6:
				opponentMedkit();
			}
		}
		else if (continueGame && opponentHealth <= (int) (opponentMaxHealth * 0.3) && playerHealth <= (int) (playerMaxHealth * 0.3))
		{
			switch (R.nextInt(5))
			{
			case 0:
				opponentPunch();
				break;
			case 1:
				opponentKick();
				break;
			case 2:
				opponentBandage();
				break;
			case 3:
				opponentMedkit();
				break;
			case 4:
				opponentMedkit();
				break;
			}
		}
		else if (continueGame)
		{
			switch (R.nextInt(4))
			{
			case 0:
				opponentPunch();
				break;
			case 1:
				opponentPunch();
				break;
			case 2: 
				opponentKick();
				break;
			case 3:
				opponentBandage();
				break;
			}
		}
	}
	
	// assassin class
	public static void classAssassin()
	{
		// empty
	}
	
	// martial artist class
	public static void classMartialArtist()
	{
		playerKickAccuracyAmplifier = 80;
		playerUppercutAccuracyAmplifier = 60;
	}
	
	// doctor class
	public static void classDoctor()
	{
		playerPunchAccuracyAmplifier = 90;
		playerKickAccuracyAmplifier = 65;
		playerBandageAccuracyAmplifier = 100;
		playerBandageDmgAmplifier = 0.2;
		playerMedkitDmgAmplifier = 0.6;
	}
	
	// knight class
	public static void classKnight()
	{
		playerKickAccuracyAmplifier = 80;
	}
	
	// vampire class
	public static void classVampire()
	{
		playerPunchAccuracyAmplifier = 90;
		playerKickAccuracyAmplifier = 65;
		playerKickDmgAmplifier = 1.7;
		playerUppercutAccuracyAmplifier = 20;
		playerBandageAccuracyAmplifier = 80;
	}
	
	public static void resetAllData() {
		 playerHealth = 100;
		 opponentHealth = 10;
		 playerMaxHealth = 100;
		 opponentMaxHealth = 10;
		 playerMinDmg = 10;
		 playerMaxDmg = 20;
		 opponentMinDmg = 10;
		 opponentMaxDmg = 20;
		 playerAtkSpeed = 10;
		 opponentAtkSpeed = 10;
		 playerLevel = 1;
		 opponentLevel = 1;
		 playerPrime = 0;
		 opponentPrime = 0;
		
		// gameplay control variables
		 abilityChosen = 0;
		 damageDealt = 0;
		 continueGame = true;
		 upgradeChoice = 0;
		 choiceInput = "";
		 opponentAtkSuccessful = false;
		
		// classes and amplifiers
		 playerPunchDmgAmplifier = 1;
		 playerKickDmgAmplifier = 1.5;
		 playerUppercutDmgAmplifier = 3;
		 playerBandageDmgAmplifier = 0.15;
		 playerMedkitDmgAmplifier = 0.35;
		 playerBodySlamDmgAmplifier = 4;
		 playerSpareOrgansDmgAmplifier = 0.8;
		 playerShieldDmgAmplifier = 0.4;
		 playerShieldDefAmplifier = 1;
		 playerFangsDmgAmplifier = 0.2;
		 playerPunchAccuracyAmplifier = 100;
		 playerKickAccuracyAmplifier = 70;
		 playerUppercutAccuracyAmplifier = 30;
		 playerBandageAccuracyAmplifier = 90;
		 playerMedkitAccuracyAmplifier = 40;
		 playerBodySlamAccuracyAmplifier = 30;
		 playerSpareOrgansAccuracyAmplifier = 25;
		 playerShieldAccuracyAmplifier = 60;
		 playerFangsAccuracyAmplifier = 60;
		 playerAccuracyAmplifier = 0;
		 playerShieldSuccessful = false;
		 stringArray.clear();
	}
}












