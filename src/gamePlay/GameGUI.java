package gamePlay;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class GameGUI extends JFrame {
	private ImagePanel graphicsPanel;
	private JPanel questionPanel;
	private JPanel playerStatus;
	private JTextField timeDisp;
	private JTextField questionDisp;
	private JTextField livesDisp;
	private ArrayList<JRadioButton> buttons;
	private String answer = "";
	//Image stuff
	private Image playerSprite;
	private Image background;
	private JButton submit;
	private ButtonGroup group;
	private boolean correct = false;
	private boolean clicked;
	private ArrayList<Question> gameGUIQuestions;
	private int questionCounter; //counter used between loops in submit function

	public GameGUI() {
		questionCounter = 0;
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

		public ImagePanel() {
			 playerX = 90;
			 playerY = 90;
			
			tracker = new MediaTracker(this);
			Image player = getImage("/images/player1.png",1);
			Image back = getImage("/images/board0.png",0);

			background = back.getScaledInstance(160, 100, Image.SCALE_FAST);
			playerSprite = player.getScaledInstance(110, 170,  Image.SCALE_FAST);
		}

		@Override
		public void paintComponent(Graphics g) {
			boardWidth = this.getWidth()-2*PADDING;
			boardHeight = this.getHeight()-2*PADDING;
			playerWidth = boardWidth*11/160;
			playerHeight = boardHeight*17/100;

			g.drawImage(background, PADDING, PADDING, boardWidth, boardHeight, null);
			//System.out.println(playerX + ", " + playerY);
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
			switch (rockNumber) { //TODO add the switch functionality. 
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
			tracker.addImage(image, id); //TODO ensure that this will always allow for the images to be displayed. 
			try {
				tracker.waitForID(id);
			} catch (InterruptedException e) {  return image; }
			return image;
		}
	}

	private JPanel createQuestionPanel() {
		JPanel qPanel = new JPanel();
		qPanel.setLayout(new GridLayout(0,1));
		TitledBorder questionBorder = new TitledBorder("Question");
		qPanel.setBorder(questionBorder);

		group = new ButtonGroup();
		
		final ButtonGroup group = new ButtonGroup();

		qPanel.add(questionDisp);

		for (JRadioButton button : buttons) {
			button.setActionCommand(button.getText());
			System.out.println("button " + button.getText());
			group.add(button);
			qPanel.add(button);
		}
		
		submit = new JButton("Submit");
		submit.setActionCommand("Submit");
		qPanel.add(submit);

//		submit.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e){
//				String pressed = group.getSelection().getActionCommand();
//				System.out.println("Button pressed " + pressed);
//				if (pressed.equals(answer)){	
//					//To Do: add in player status updates
//					System.out.println("correct");
//				}
//				else {
//					//To Do: add in player status updates
//					System.out.println("incorrect");
//				}
//				
//			}
//		});


		JButton submit = new JButton("Submit");
		submit.setActionCommand("Submit");
		qPanel.add(submit);

		submit.addActionListener(new ActionListener() {
			//		@Overrride
			public void actionPerformed(ActionEvent e){
				String pressed = group.getSelection().getActionCommand();
				System.out.println("Button pressed " + pressed);
				if (pressed.equals(answer)){
					System.out.println("correct");
					return;
				}
				else {
					System.out.println("incorrect");
					return;
				}

			}
		});

		return qPanel;
	}

	private JPanel createPlayerStatusPanel() {
		JPanel sPanel = new JPanel();
		sPanel.setLayout(new GridLayout(0,1));

		TitledBorder statusBorder = new TitledBorder("Player Status");
		JLabel timeLabel = new JLabel("Time Remaining");
		JLabel livesLabel = new JLabel("Lives Remaining");
		sPanel.setBorder(statusBorder);
		sPanel.add(timeLabel);
		sPanel.add(timeDisp);
		sPanel.add(livesLabel);
		sPanel.add(livesDisp);

		return sPanel;
	}

	public void updateTime(int time) {
		timeDisp.setText(Integer.toString(time));		
		repaint();
	}

	public Question pickQuestion(ArrayList<Question> q){
		Question questionReturned = new Question();
		if (gameGUIQuestions.size() > 0){
			questionReturned = gameGUIQuestions.get(0);
			gameGUIQuestions.remove(0);
		}
		return questionReturned;
	}
	public void updateQuestion(ArrayList<Question> q) {
		gameGUIQuestions = q;
		clicked = false;
		updateQuestion(pickQuestion(gameGUIQuestions));
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				String actionCommand = ((JButton) e.getSource()).getActionCommand();
				System.out.println("Action command for pressed button: " + actionCommand);
				clicked = true;
				if (actionCommand.equals("Submit")){
					String pressed = group.getSelection().getActionCommand();
					System.out.println("Button pressed " + pressed);
					if (pressed.equals(answer)){	
						//To Do: add in player status updates
						System.out.println("correct" + questionCounter);
//						updateQuestion(pickQuestion(gameGUIQuestions));
						correct = true;
					}
					else {
						//To Do: add in player status updates
//						updateQuestion(pickQuestion(gameGUIQuestions));
						correct = false;
					}
				}
			}
		});
	}

	public void updateQuestion(Question question) {
		questionDisp.setText(question.getQuestion());
		int i=0;
		answer = question.getCorrectAnswer().toString();
		for (Fraction answer : question.orderAnswers()) {
			buttons.get(i).setText(question.sequencedAnswers.get(i).toString());
			buttons.get(i).setActionCommand(buttons.get(i).getText());
			System.out.println("button " + buttons.get(i).getText());
			i++;
		}
		repaint();
	}

	public void updateStatus(Player player) {
		// Update Lives and Location, or end game if Player has run out of lives
		if (player.getLivesRemaining() > 0) {
			livesDisp.setText(Integer.toString(player.getLivesRemaining()));
		}
		else {
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

}
