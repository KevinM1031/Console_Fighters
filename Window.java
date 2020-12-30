import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Window extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private ArrayList<String> temp = new ArrayList<String>();
	private boolean writing = false;
	
	public void setButtonsToMenu() {
		JButton button1 = new JButton("Assassin"), button2 = new JButton("Martial Artist"), button3 = new JButton("Doctor"), button4 = new JButton("Knight (unused)"), 
				button5 = new JButton("Vampire"), button6 = new JButton("Instructions");
		button1.setBounds(34, 713, 127, 59);
		button2.setBounds(195, 713, 127, 59);
		button3.setBounds(356, 713, 127, 59);
		button4.setBounds(517, 713, 127, 59);
		button5.setBounds(678, 713, 127, 59);
		button6.setBounds(839, 713, 127, 59);
		
		button1.addActionListener( new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if(!writing) {
				ConsoleFighters.stringArray.clear();
				ConsoleFighters.stringArray.add("You chose the Assassin class.");
				ConsoleFighters.classAssassin();
				ConsoleFighters.playerClass = "a";
				remove(button1);
				remove(button2);
				remove(button3);
				remove(button4);
				remove(button5);
				remove(button6);
				ConsoleFighters.reUpdate = true;
				ConsoleFighters.startGame = true;
		    	}
		    }
		});
		button2.addActionListener( new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if(!writing) {
				ConsoleFighters.stringArray.clear();
				ConsoleFighters.stringArray.add("You chose the Martial Artist class.");
				ConsoleFighters.classMartialArtist();
				ConsoleFighters.playerClass = "m";
				remove(button1);
				remove(button2);
				remove(button3);
				remove(button4);
				remove(button5);
				remove(button6);
				ConsoleFighters.reUpdate = true;
				ConsoleFighters.startGame = true;
		    	}
		    }
		});
		button3.addActionListener( new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if(!writing) {
				ConsoleFighters.stringArray.clear();
				ConsoleFighters.stringArray.add("You chose the Doctor class.");
				ConsoleFighters.classDoctor();
				ConsoleFighters.playerClass = "d";
				remove(button1);
				remove(button2);
				remove(button3);
				remove(button4);
				remove(button5);
				remove(button6);
				ConsoleFighters.reUpdate = true;
				ConsoleFighters.startGame = true;
		    	}
		    }
		});
		button4.addActionListener( new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    }
		});
		button5.addActionListener( new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if(!writing) {
				ConsoleFighters.stringArray.clear();
				ConsoleFighters.stringArray.add("You chose the Vampire class.");
				ConsoleFighters.classVampire();
				ConsoleFighters.playerClass = "v";
				remove(button1);
				remove(button2);
				remove(button3);
				remove(button4);
				remove(button5);
				remove(button6);
				ConsoleFighters.reUpdate = true;
				ConsoleFighters.startGame = true;
		    	}
		    }
		});
		button6.addActionListener( new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if(!writing) {
				ConsoleFighters.stringArray.clear();
				ConsoleFighters.stringArray.add("[How To Play]");
				ConsoleFighters.stringArray.add("");
				ConsoleFighters.stringArray.add("");
				ConsoleFighters.stringArray.add("1. Choose a class to begin the game.");
				ConsoleFighters.stringArray.add("");
				ConsoleFighters.stringArray.add("2. Your goal is to kill the enemy. Use the six buttons to select your ability. You only have one life.");
				ConsoleFighters.stringArray.add("");
				ConsoleFighters.stringArray.add("3. Each abilities have accuracy values, showing the likelihood of that ability succeeding.");
				ConsoleFighters.stringArray.add("");
				ConsoleFighters.stringArray.add("4. HP indicates your health. When it reaches zero, you are dead.");
				ConsoleFighters.stringArray.add("");
				ConsoleFighters.stringArray.add("5. Dmg indicates your range of possible damages. For example, if your Dmg is 20-40, a random damage value is selected from that range each time.");
				ConsoleFighters.stringArray.add("");
				ConsoleFighters.stringArray.add("6. Speed determines who is more likely going to use the ability first. For example, when your speed is 30 and enemy's is 15, the ability you chose");
				ConsoleFighters.stringArray.add("    is far more likely going to activate before your enemy's.");
				ConsoleFighters.stringArray.add("");
				ConsoleFighters.stringArray.add("7. When you kill an enemy, your level increases and gives you six options for upgrading your character.");
				ConsoleFighters.stringArray.add("");
				ConsoleFighters.stringArray.add("8. When your level increases, you will encounter a stronger enemy.");
				ConsoleFighters.stringArray.add("");
				ConsoleFighters.stringArray.add("9. The main objective of this game is to go as far as you can without dying.");
				ConsoleFighters.stringArray.add("");
				ConsoleFighters.stringArray.add("");
				ConsoleFighters.stringArray.add("[1] Back to Menu");
				remove(button1);
				remove(button2);
				remove(button3);
				remove(button4);
				remove(button5);
				remove(button6);
				ConsoleFighters.reUpdate = true;
				setButtonsToInfo();
		    	}
		    }
		});
		
		add(button1);
		add(button2);
		add(button3);
		add(button4);
		add(button5);
		add(button6);
		setVisible(false);
		setVisible(true);
	}
	
	public void setButtonsToInfo() {
		JButton button1 = new JButton("Back to Menu");
		button1.setBounds(34, 713, 127, 59);

		button1.addActionListener( new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if(!writing) {
				ConsoleFighters.menuClassDisplay();
				remove(button1);
				ConsoleFighters.reUpdate = true;
				setButtonsToMenu();
		    	}
		    }
		});
		
		add(button1);
		setVisible(false);
		setVisible(true);
	}
	
	public void setButtonsToAbilitiesA() {
		JButton button1 = new JButton("Punch"), button2 = new JButton("Kick"), button3 = new JButton("Uppercut"), button4 = new JButton("Bandage"), 
				button5 = new JButton("Medkit");
		button1.setBounds(34, 713, 127, 59);
		button2.setBounds(195, 713, 127, 59);
		button3.setBounds(356, 713, 127, 59);
		button4.setBounds(517, 713, 127, 59);
		button5.setBounds(678, 713, 127, 59);
		
		button1.addActionListener( new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if(!writing) {
		    		ConsoleFighters.abilityChosen = 1;
		    		ConsoleFighters.resume = true;
					remove(button1);
					remove(button2);
					remove(button3);
					remove(button4);
					remove(button5);
		    	}
		    }
		});
		button2.addActionListener( new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if(!writing) {
		    		ConsoleFighters.abilityChosen = 2;
		    		ConsoleFighters.resume = true;
					remove(button1);
					remove(button2);
					remove(button3);
					remove(button4);
					remove(button5);
		    	}
		    }
		});
		button3.addActionListener( new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if(!writing) {
		    		ConsoleFighters.abilityChosen = 3;
		    		ConsoleFighters.resume = true;
					remove(button1);
					remove(button2);
					remove(button3);
					remove(button4);
					remove(button5);
		    	}
		    }
		});
		button4.addActionListener( new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if(!writing) {
		    		ConsoleFighters.abilityChosen = 4;
		    		ConsoleFighters.resume = true;
					remove(button1);
					remove(button2);
					remove(button3);
					remove(button4);
					remove(button5);
		    	}
		    }
		});
		button5.addActionListener( new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if(!writing) {
		    		ConsoleFighters.abilityChosen = 5;
		    		ConsoleFighters.resume = true;
					remove(button1);
					remove(button2);
					remove(button3);
					remove(button4);
					remove(button5);
		    	}
		    }
		});
		
		add(button1);
		add(button2);
		add(button3);
		add(button4);
		add(button5);
		setVisible(false);
		setVisible(true);
	}
	
	public void setButtonsToAbilitiesM() {
		JButton button1 = new JButton("Punch"), button2 = new JButton("Kick"), button3 = new JButton("Uppercut"), button4 = new JButton("Body Slam"), 
				button5 = new JButton("Bandage");
		button1.setBounds(34, 713, 127, 59);
		button2.setBounds(195, 713, 127, 59);
		button3.setBounds(356, 713, 127, 59);
		button4.setBounds(517, 713, 127, 59);
		button5.setBounds(678, 713, 127, 59);
		
		button1.addActionListener( new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if(!writing) {
		    		ConsoleFighters.abilityChosen = 1;
		    		ConsoleFighters.resume = true;
					remove(button1);
					remove(button2);
					remove(button3);
					remove(button4);
					remove(button5);
		    	}
		    }
		});
		button2.addActionListener( new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if(!writing) {
		    		ConsoleFighters.abilityChosen = 2;
		    		ConsoleFighters.resume = true;
					remove(button1);
					remove(button2);
					remove(button3);
					remove(button4);
					remove(button5);
		    	}
		    }
		});
		button3.addActionListener( new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if(!writing) {
		    		ConsoleFighters.abilityChosen = 3;
		    		ConsoleFighters.resume = true;
					remove(button1);
					remove(button2);
					remove(button3);
					remove(button4);
					remove(button5);
		    	}
		    }
		});
		button4.addActionListener( new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if(!writing) {
		    		ConsoleFighters.abilityChosen = 6;
		    		ConsoleFighters.resume = true;
					remove(button1);
					remove(button2);
					remove(button3);
					remove(button4);
					remove(button5);
		    	}
		    }
		});
		button5.addActionListener( new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if(!writing) {
		    		ConsoleFighters.abilityChosen = 4;
		    		ConsoleFighters.resume = true;
					remove(button1);
					remove(button2);
					remove(button3);
					remove(button4);
					remove(button5);
		    	}
		    }
		});
		
		add(button1);
		add(button2);
		add(button3);
		add(button4);
		add(button5);
		setVisible(false);
		setVisible(true);
	}
	
	public void setButtonsToAbilitiesD() {
		JButton button1 = new JButton("Punch"), button2 = new JButton("Kick"), button3 = new JButton("Bandage"), button4 = new JButton("Medkit"), 
				button5 = new JButton("Spare Organs");
		button1.setBounds(34, 713, 127, 59);
		button2.setBounds(195, 713, 127, 59);
		button3.setBounds(356, 713, 127, 59);
		button4.setBounds(517, 713, 127, 59);
		button5.setBounds(678, 713, 127, 59);
		
		button1.addActionListener( new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if(!writing) {
		    		ConsoleFighters.abilityChosen = 1;
		    		ConsoleFighters.resume = true;
					remove(button1);
					remove(button2);
					remove(button3);
					remove(button4);
					remove(button5);
		    	}
		    }
		});
		button2.addActionListener( new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if(!writing) {
		    		ConsoleFighters.abilityChosen = 2;
		    		ConsoleFighters.resume = true;
					remove(button1);
					remove(button2);
					remove(button3);
					remove(button4);
					remove(button5);
		    	}
		    }
		});
		button3.addActionListener( new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if(!writing) {
		    		ConsoleFighters.abilityChosen = 4;
		    		ConsoleFighters.resume = true;
					remove(button1);
					remove(button2);
					remove(button3);
					remove(button4);
					remove(button5);
		    	}
		    }
		});
		button4.addActionListener( new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if(!writing) {
		    		ConsoleFighters.abilityChosen = 5;
		    		ConsoleFighters.resume = true;
					remove(button1);
					remove(button2);
					remove(button3);
					remove(button4);
					remove(button5);
		    	}
		    }
		});
		button5.addActionListener( new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if(!writing) {
		    		ConsoleFighters.abilityChosen = 7;
		    		ConsoleFighters.resume = true;
					remove(button1);
					remove(button2);
					remove(button3);
					remove(button4);
					remove(button5);
		    	}
		    }
		});
		
		add(button1);
		add(button2);
		add(button3);
		add(button4);
		add(button5);
		setVisible(false);
		setVisible(true);
	}
	
	public void setButtonsToAbilitiesV() {
		JButton button1 = new JButton("Punch"), button2 = new JButton("Kick"), button3 = new JButton("Uppercut"), button4 = new JButton("Bandage"), 
				button5 = new JButton("Fangs");
		button1.setBounds(34, 713, 127, 59);
		button2.setBounds(195, 713, 127, 59);
		button3.setBounds(356, 713, 127, 59);
		button4.setBounds(517, 713, 127, 59);
		button5.setBounds(678, 713, 127, 59);
		
		button1.addActionListener( new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if(!writing) {
		    		ConsoleFighters.abilityChosen = 1;
		    		ConsoleFighters.resume = true;
					remove(button1);
					remove(button2);
					remove(button3);
					remove(button4);
					remove(button5);
		    	}
		    }
		});
		button2.addActionListener( new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if(!writing) {
		    		ConsoleFighters.abilityChosen = 2;
		    		ConsoleFighters.resume = true;
					remove(button1);
					remove(button2);
					remove(button3);
					remove(button4);
					remove(button5);
		    	}
		    }
		});
		button3.addActionListener( new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if(!writing) {
		    		ConsoleFighters.abilityChosen = 3;
		    		ConsoleFighters.resume = true;
					remove(button1);
					remove(button2);
					remove(button3);
					remove(button4);
					remove(button5);
		    	}
		    }
		});
		button4.addActionListener( new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if(!writing) {
		    		ConsoleFighters.abilityChosen = 4;
		    		ConsoleFighters.resume = true;
					remove(button1);
					remove(button2);
					remove(button3);
					remove(button4);
					remove(button5);
		    	}
		    }
		});
		button5.addActionListener( new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if(!writing) {
		    		ConsoleFighters.abilityChosen = 9;
		    		ConsoleFighters.resume = true;
					remove(button1);
					remove(button2);
					remove(button3);
					remove(button4);
					remove(button5);
		    	}
		    }
		});
		
		add(button1);
		add(button2);
		add(button3);
		add(button4);
		add(button5);
		setVisible(false);
		setVisible(true);
	}
	
	public void setButtonsToUpgrade() {
		JButton button1 = new JButton("Choice 1"), button2 = new JButton("Choice 2"), button3 = new JButton("Choice 3"), button4 = new JButton("Choice 4"), 
				button5 = new JButton("Choice 5"), button6 = new JButton("Choice 6");
		button1.setBounds(34, 713, 127, 59);
		button2.setBounds(195, 713, 127, 59);
		button3.setBounds(356, 713, 127, 59);
		button4.setBounds(517, 713, 127, 59);
		button5.setBounds(678, 713, 127, 59);
		button6.setBounds(839, 713, 127, 59);
		
		button1.addActionListener( new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if(!writing) {
		    		ConsoleFighters.upgradeChoice = 1;
					remove(button1);
					remove(button2);
					remove(button3);
					remove(button4);
					remove(button5);
					remove(button6);
					ConsoleFighters.resume = true;
		    	}
		    }
		});
		button2.addActionListener( new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if(!writing) {
		    		ConsoleFighters.upgradeChoice = 2;
					remove(button1);
					remove(button2);
					remove(button3);
					remove(button4);
					remove(button5);
					remove(button6);
					ConsoleFighters.resume = true;
		    	}
		    }
		});
		button3.addActionListener( new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if(!writing) {
		    		ConsoleFighters.upgradeChoice = 3;
					remove(button1);
					remove(button2);
					remove(button3);
					remove(button4);
					remove(button5);
					remove(button6);
					ConsoleFighters.resume = true;
		    	}
		    }
		});
		button4.addActionListener( new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if(!writing) {
		    		ConsoleFighters.upgradeChoice = 4;
					remove(button1);
					remove(button2);
					remove(button3);
					remove(button4);
					remove(button5);
					remove(button6);
					ConsoleFighters.resume = true;
		    	}
		    }
		});
		button5.addActionListener( new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if(!writing) {
		    		ConsoleFighters.upgradeChoice = 5;
					remove(button1);
					remove(button2);
					remove(button3);
					remove(button4);
					remove(button5);
					remove(button6);
					ConsoleFighters.resume = true;
		    	}
		    }
		});
		button6.addActionListener( new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if(!writing) {
		    		ConsoleFighters.upgradeChoice = 6;
					remove(button1);
					remove(button2);
					remove(button3);
					remove(button4);
					remove(button5);
					remove(button6);
					ConsoleFighters.resume = true;
		    	}
		    }
		});
		
		add(button1);
		add(button2);
		add(button3);
		add(button4);
		add(button5);
		add(button6);
		setVisible(false);
		setVisible(true);
	}
	
	public void update() {
		writing = true;
		temp.clear();
    	for(int floop = 0; floop < ConsoleFighters.stringArray.size(); floop++) {
    		temp.add(floop, "");
    		for(int nfloop = 0; nfloop < ConsoleFighters.stringArray.get(floop).length(); nfloop++) {
    			temp.set(floop,temp.get(floop)+ConsoleFighters.stringArray.get(floop).charAt(nfloop));
    			repaint();
        		try {
    				Thread.sleep(1);
    			} catch (InterruptedException e) {}
    		}
    		try {
				Thread.sleep(1);
			} catch (InterruptedException e) {}
    	}
    	writing = false;
	}
	
	@Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	
    	g.setColor(Color.green);
    	g.setFont(Font.getFont("monospace"));
    	for(int floop = 0; floop < temp.size(); floop++) {
            g.drawString(temp.get(floop), 10, 20+18*floop);
    	}    	
    	
	}
	
}
