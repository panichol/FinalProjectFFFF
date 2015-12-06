package gamePlay;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class GameGUI extends JFrame {
	private ImagePanel graphicsPanel;				//The graphical panel that has the actual images. 
	private JPanel questionPanel;					//The questions panel that displays the question and possible answers.
	private JPanel playerStatus;					//The panel that shows the player's information. 
	private JTextField timeDisp;					//The area that displays the time left. 
	private JTextField questionDisp;				//The actual individual question area. 
	private JTextField livesDisp;					//The lives display area. 
	private JTextField answerOutcome;
	private ArrayList<JRadioButton> buttons;		//The buttons of the answers.
	private ButtonGroup group;						//The button group that will hold the answer buttons. 
	private JButton submit;							//The submit button
	private String answer = "";						//Temporary variable that holds the answer that was selected. 
	private boolean correct;						//The boolean that tells if the player has submitted a correct answer. 
	private boolean clicked;						//The boolean that tells if the submit button has been clicked.
	private ArrayList<Question> gameGUIQuestions;	//The list of questions.
	private static Player player;
	public int rock;

	//Image stuff
	private Image playerSprite;						//The Player's Sprite. 
	private Image background;						//The background image.
	private Image waves;							//The waves

	/**
	 * Default initializer. 
	 * Sets up all the different fields and places them. 
	 */
	public GameGUI(Player p) {
		player = p;
		rock = 0;
		correct = false;
		setSize(600,800);
		buttons = new ArrayList<JRadioButton>();
		for (int i=0; i < 4; i++) {
			buttons.add(new JRadioButton());
		}

		timeDisp = new JTextField(20);
		timeDisp.setEnabled(false);
		timeDisp.setDisabledTextColor(Color.BLACK);

		questionDisp = new JTextField(20);
		questionDisp.setEnabled(false);
		questionDisp.setDisabledTextColor(Color.BLACK);

		livesDisp = new JTextField(5);
		livesDisp.setEnabled(false);
		livesDisp.setDisabledTextColor(Color.BLACK);
		
		answerOutcome = new JTextField(5);
		answerOutcome.setEnabled(false);
		answerOutcome.setDisabledTextColor(Color.BLACK);

		setLayout(new GridLayout(0,1));
		graphicsPanel = new ImagePanel();
		graphicsPanel.updateCurrentRock(0);
		add(graphicsPanel);

		JPanel bottom = new JPanel();
		bottom.setLayout(new GridLayout(1,0));
		playerStatus = createPlayerStatusPanel();
		bottom.add(playerStatus);
		questionPanel = createQuestionPanel();
		bottom.add(questionPanel);
		add(bottom);
	}

	public class ImagePanel extends JPanel{
		private int playerX;
		private int playerY;
		private MediaTracker tracker;
		private static final int PADDING = 20;
		private int boardWidth;
		private int boardHeight;
		private int playerWidth;
		private int playerHeight;
		private boolean startingLevel;

		/**
		 * Default constructor. 
		 * Places the images on the screen. 
		 */
		public ImagePanel() {
			playerX = 90;
			playerY = 90;
			startingLevel = true;
			//System.out.println("THIS IS PLAYER LOCATION: " + playerX + ", " + playerY);

			tracker = new MediaTracker(this);
			playerSprite = getImage("/images/player1.png",1);
			background = getImage("/images/board0.png",0);
			waves = getImage("/images/wave.png", 2);

			background = background.getScaledInstance(160, 100, Image.SCALE_FAST);
			playerSprite = playerSprite.getScaledInstance(11, 17,  Image.SCALE_FAST);
		}

		/**
		 * Paints the actual components. If there is a graphical derp it probably happened in here. 
		 * This is called when repaint is called.
		 * @param g The graphical component that does the drawing. 
		 */
		@Override
		public void paintComponent(Graphics g) {
			boardWidth = this.getWidth()-2*PADDING;
			boardHeight = this.getHeight()-2*PADDING;
			playerWidth = boardWidth*11/160;
			playerHeight = boardHeight*17/100;

			if(startingLevel){
				updateCurrentRock(0);
				startingLevel = !startingLevel;
			}

			g.drawImage(background, PADDING, PADDING, boardWidth, boardHeight, null);
			g.drawImage(playerSprite, playerX, playerY, playerWidth, playerHeight, null);
		}

		/**
		 * Used if needing to actively change the pixel location of the player.
		 * It is recommended to use the updateCurrentRock so that the scaling for different window sizes can take affect. 
		 * 
		 * @param x X pixel location
		 * @param y Y pixel location
		 */
		public void updatePlayerLocation(int x, int y) {
			playerX = x;
			playerY = y;
		}

		/**
		 * Used to change the player location based off the current rock. 
		 * This will scale for window size. 
		 * 
		 * @param rockNumber The number rock the player is on. 0 and 11 for the banks if wanted. 
		 */
		private void updateCurrentRock(int rockNumber) {
			switch (rockNumber) {
			case 0: playerX = PADDING + boardWidth * 1/16;
			playerY = PADDING + boardHeight * 3/8;
			break;
			case 1: playerX = PADDING + boardWidth * 1/5;
			playerY = PADDING + boardHeight * 3/8;
			break;
			case 2: playerX = PADDING + boardWidth * 11/40;
			playerY = PADDING + boardHeight * 9/20;
			break;
			case 3: playerX = PADDING + boardWidth * 13/40;
			playerY = PADDING + boardHeight * 13/40;
			break;
			case 4: playerX = PADDING + boardWidth * 3/8;
			playerY = PADDING + boardHeight * 17/40;
			break;
			case 5: playerX = PADDING + boardWidth * 9/20;
			playerY = PADDING + boardHeight * 3/10;
			break;
			case 6: playerX = PADDING + boardWidth * 41/80;
			playerY = PADDING + boardHeight * 17/40;
			break;
			case 7: playerX = PADDING + boardWidth * 3/5;
			playerY = PADDING + boardHeight * 13/40;
			break;
			case 8: playerX = PADDING + boardWidth * 53/80;
			playerY = PADDING + boardHeight * 19/40;
			break;
			case 9: playerX = PADDING + boardWidth * 59/80;
			playerY = PADDING + boardHeight * 7/20;
			break;
			case 10: playerX = PADDING + boardWidth * 4/5;
			playerY = PADDING + boardHeight * 19/40;
			break;
			case 11: playerX = PADDING + boardWidth * 71/80;
			playerY = PADDING + boardHeight * 3/8;
			JOptionPane.showMessageDialog(graphicsPanel,"Congratulations! You won Fraction Flash Flood!"
					,"Congratulations!",JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
			break;

			default: playerX = PADDING;
			playerY = PADDING;
			break;
			}
			repaint();
		}

		/**
		 * Used to load a image from a given path. 
		 * 
		 * @param pathName The location of the image in the file tree.
		 * @param id The order you want the images to be added to the Media Tracker. 
		 * @return the image.
		 */
		private Image getImage(String pathName, int id) {
			URL url = getClass().getResource(pathName);
			Image image = Toolkit.getDefaultToolkit().getImage(url);
			tracker.addImage(image, id);
			try {
				tracker.waitForID(id);
			} catch (InterruptedException e) {  return image; }
			return image;
		}
	}

	/**
	 * Sets up the panel for the question.
	 * 
	 * @return the panel to be placed on the frame. 
	 */
	private JPanel createQuestionPanel() {
		JPanel qPanel = new JPanel();
		qPanel.setLayout(new GridLayout(0,1));
		TitledBorder questionBorder = new TitledBorder("Question");
		qPanel.setBorder(questionBorder);

		group = new ButtonGroup();

		qPanel.add(questionDisp);

		for (JRadioButton button : buttons) {
			button.setActionCommand(button.getText());
			group.add(button);
			qPanel.add(button);
		}

		submit = new JButton("Submit");
		submit.setActionCommand("Submit");
		qPanel.add(submit);
		
		return qPanel;
	}

	/**
	 * Creates the panel that displays player status. 
	 * 
	 * @return the panel to be displayed on the frame. 
	 */
	private JPanel createPlayerStatusPanel() {
		JPanel sPanel = new JPanel();
		sPanel.setLayout(new GridLayout(0,1));

		TitledBorder statusBorder = new TitledBorder("Player Status");
		JLabel timeLabel = new JLabel("Time Remaining");
		JLabel livesLabel = new JLabel("Lives Remaining");
		JLabel correct = new JLabel("Answer Outcome");
		sPanel.setBorder(statusBorder);
		sPanel.add(timeLabel);
		sPanel.add(timeDisp);
		sPanel.add(livesLabel);
		sPanel.add(livesDisp);
		sPanel.add(correct);
		sPanel.add(answerOutcome);

		return sPanel;
	}

	/**
	 * Given a time, this will update the time field on the player panel. 
	 * 
	 * @param time The time to be displayed. 
	 */
	public void updateTime(int time) {
		timeDisp.setText(Integer.toString(time));		
		repaint();
	}
	public void updateAnswerOutcome(boolean correct, String answer){
		if (correct){
			answerOutcome.setText("Correct!");
		}
		else{
			answerOutcome.setText("Incorrect! Correct answer is " + answer + ".");
		}
	}

	/**
	 * Updates the current question from the list of questions. 
	 * 
	 * @param q The list of questions to take the first question from. This is edited to remove the first question. 
	 * @return the question to be displayed. 
	 */
	public Question pickQuestion(ArrayList<Question> q){
		Question questionReturned = new Question();
		Random r = new Random();
		Collections.rotate(q,r.nextInt(q.size()));
		if (gameGUIQuestions.size() > 0){
			questionReturned = gameGUIQuestions.get(0);
			gameGUIQuestions.remove(0);
		}
		return questionReturned;
	}

	/**
	 * Adds the functionality for the question button. 
	 * 
	 * @param q The list of questions. 
	 */
	public void updateQuestion(ArrayList<Question> q) {
		gameGUIQuestions = q;
		clicked = false;
		Question currentQuestion = pickQuestion(gameGUIQuestions);
		updateQuestionField(currentQuestion);
		//System.out.println(currentQuestion);
		System.out.println("Correct answer: " + currentQuestion.getCorrectAnswer()); //TODO remove this for final presentation
		submit.addActionListener(new questionListener());
	}
	
	/**
	 * The action listener for the Submit button. 
	 */
	private class questionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			//System.out.println("THIS IS HAPPENEING");
			if (group.getSelection() == null){
				return;
			}
			String pressed = group.getSelection().getActionCommand();
			System.out.println("Button pressed " + pressed);
			if (pressed.equals(answer)){	
				//TODO: add in player status updates
				System.out.println("Correct"); //TODO remove this for final presentation
				updateAnswerOutcome(correct, answer);
				updateQuestionField(pickQuestion(gameGUIQuestions));
				correct = true;
				rock++;
				//System.out.println(rock);
				updatePlayerRock(rock);
			}
			else {
				player.loseLife();
				updateAnswerOutcome(correct, answer);
				updateQuestionField(pickQuestion(gameGUIQuestions));
				correct = false;
			}
			updateStatus();
		}
	}
	
	/**
	 * Updates the question field to have the question.
	 * 
	 * @param question the Question to be displayed. 
	 */
	public void updateQuestionField(Question question) {
		System.out.println(question);
		group.clearSelection();
		questionDisp.setText(question.getQuestion());
		int i=0;
		answer = question.getCorrectAnswer().toString();
		for (Fraction answer : question.orderAnswers()) {
			buttons.get(i).setText(question.sequencedAnswers.get(i).toString());
			buttons.get(i).setActionCommand(buttons.get(i).getText());
			i++;
		}
		repaint();
	}

	/**
	 * Update Lives and Location, or end game if Player has run out of lives. 
	 * 
	 * @param player The player object whose information is displayed. 
	 */
	public void updateStatus() {
		livesDisp.setText(Integer.toString(player.getLivesRemaining()));
		if (player.getLivesRemaining() == 0) {
			JOptionPane.showMessageDialog(getParent(), "Sorry, you have run out of lives!"
					, "Game Over", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
		repaint();
	}

	/**
	 * Used to change the player location based off the current rock. 
	 * This will scale for window size. 
	 * 
	 * @param rockNumber The number rock the player is on. 0 and 11 for the banks if wanted. 
	 */
	public void updatePlayerRock(int rockNumber) {
		graphicsPanel.updateCurrentRock(rockNumber);
	}

	/**
	 * Used to start the player off in the initial location when reloading for a new level
	 */
	public void startLevel() {
		graphicsPanel.startingLevel = true;
	}

}
