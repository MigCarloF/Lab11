package game;

import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class RPS extends Frame{
	private Label lblChoice;
	private Label lblPlayer;
	private Label lblCom;
	private TextArea txtPlayerScore;
	private TextArea txtCompScore;
	private int playerScore;
	private int compScore;
	private TextArea txtResults;
	private CheckboxGroup grpChoices;
	private Button btnGo;
	private Button btnRestart;
	private Button btnExit;
	private Dialog dlgEnd;
	
	public RPS() {
		playerScore = 0;
		compScore = 0;
		setLayout(new FlowLayout());
		setSize(500,500);
	    setTitle("Rock Paper Scissors Lizard Spock");
	    
		lblChoice = new Label("Your choice:");
		txtPlayerScore = new TextArea("0", 1, 1, 3);
		txtCompScore = new TextArea("0", 1, 1, 3);
		lblPlayer = new Label("Player Score: ");
		lblCom = new Label("Computer Score: ");
		btnGo = new Button("RockPaperScissorsLizardSpock!");
		btnRestart = new Button("Restart");
		btnExit = new Button("QUIT");
		txtResults = new TextArea("Results", 5, 25, 3);
		txtResults.setEditable(false);
		grpChoices = new CheckboxGroup();
		dlgEnd = new Dialog(this);
		Checkbox rock = new Checkbox("Rock", grpChoices, true);
		Checkbox paper = new Checkbox("Paper", grpChoices, false);
		Checkbox scissors = new Checkbox("Scissors", grpChoices, false);
		Checkbox lizard = new Checkbox("Lizard", grpChoices, false);
		Checkbox spock = new Checkbox("Spock", grpChoices, false);
		
		//grpChoices = new grpChoices()
		//lblPlayerScore.setEditable(false);
		
		
		add(lblChoice);
		add(rock);
		add(paper);
		add(scissors);
		add(lizard);
		add(spock);
		add(btnGo);
		add(txtResults);
		add(lblPlayer);
		add(txtPlayerScore);
		txtPlayerScore.setEditable(false);
		add(lblCom);
		add(txtCompScore);
		txtCompScore.setEditable(false);
		setTitle("Rock Paper Scissors Lizard Spock");
		
		setVisible(true);
		// button functions - exit & restart
	    btnExit.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent s){
	          System.exit(0);
	        }
	      });
	    btnRestart.addActionListener( new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	          restart();
	          dlgEnd.setVisible(false);
	        }
	      });
	    
		btnGo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int playerChoice = 0;
				int compChoice = 0;
				Boolean win = false;
				Boolean draw = false;
				String playerChoiceStr = null;
				String compChoiceStr = null;
				Random rand = new Random();
				compChoice = rand.nextInt(5) + 1;
				//Make string for compChoice
				if(compChoice == 1){
					compChoiceStr = new String("Rock");
				}
				else if(compChoice == 2){
					compChoiceStr = new String("Paper");
				}
				else if(compChoice == 3){
					compChoiceStr = new String("Scissors");
				}
				else if(compChoice == 4){
					compChoiceStr = new String("Lizard");
				}
				else if(compChoice == 5){
					compChoiceStr = new String("Spock");
				}
				//Make user input
				if(rock.getState()){
					playerChoice = 1;
					playerChoiceStr = new String("Rock");
				}
				else if(paper.getState()){
					playerChoice = 2;
					playerChoiceStr = new String("Paper");
				}
				else if(scissors.getState()){
					playerChoice = 3;
					playerChoiceStr = new String("Scissors");
				}
				else if(lizard.getState()){
					playerChoice = 4;
					playerChoiceStr = new String("Lizard");
				}
				else if(spock.getState()){
					playerChoice = 5;
					playerChoiceStr = new String("Spock");
				}
				//Compare and score
				
				/*
				 * rock -> Lizard, Scissors = 1 -> 3, 4
				 * paper -> rock, Spock = 2 -> 1, 5
				 * scissors -> lizard, paper = 3 -> 2, 4
				 * lizard -> spock, paper = 4 -> 2, 5
				 * spock -> rock, scissors = 5 -> 1, 3
				 */
				if(playerChoice == compChoice){
					draw = true;
				}
				else {
					win = matchCompare(playerChoice, compChoice);
				}
				
				if(draw){
					txtResults.setText("Player: " + playerChoiceStr + "\nComputer: " + compChoiceStr + "\nDRAW!!!");
				}
				else if(win){
					txtResults.setText("Player: " + playerChoiceStr + "\nComputer: " + compChoiceStr + "\nYOU WIN!!!");
					playerScore++;
					txtPlayerScore.setText(playerScore + "");
				}
				else if(!win){
					txtResults.setText("Player: " + playerChoiceStr + "\nComputer: " + compChoiceStr + "\nYOU LOSE!!!");
					compScore++;
					txtCompScore.setText(compScore + "");
				}
				
				if(playerScore == 5){
					  dlgEnd.setLayout(new FlowLayout());
					  dlgEnd.setVisible(true);
					  dlgEnd.setSize(350, 100); 
					  dlgEnd.add(btnRestart);
					  dlgEnd.add(btnExit);
					  dlgEnd.setTitle("You win!");
				}
				else if(compScore == 5){
					  dlgEnd.setLayout(new FlowLayout());
					  dlgEnd.setVisible(true);
					  dlgEnd.setSize(350, 100); 
					  dlgEnd.add(btnRestart);
					  dlgEnd.add(btnExit);
					  dlgEnd.setTitle("You Lose!!");
				}
			}
		});
		 // close window
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
	}
	
	private void restart(){
		playerScore = 0;
		compScore = 0;
	}
	//compares results
	protected Boolean matchCompare(int playerChoice, int compChoice) {
		Boolean win = false;
		if(playerChoice == 1){
			if(compChoice == 3 || compChoice == 4){
				win = true;
			}
			else{
				win = false;
			}
		}
		else if(playerChoice == 2){
			if(compChoice == 1 || compChoice == 5){
				win = true;
			}
			else{
				win = false;
			}
		}
		else if(playerChoice == 3){
			if(compChoice == 2 || compChoice == 4){
				win = true;
			}
			else{
				win = false;
			}
		}
		else if(playerChoice == 4){
			if(compChoice == 2 || compChoice == 5){
				win = true;
			}
			else{
				win = false;
			}
		}
		else if(playerChoice == 5){
			if(compChoice == 1 || compChoice == 3){
				win = true;
			}
			else{
				win = false;
			}
		}
		return win;
	}

	public static void main(String args[]){
		new RPS();
	}
}
