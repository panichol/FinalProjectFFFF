package gamePlay;

import java.awt.*;
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
	
	//Image stuff
	private Image playerSprite;
	private Image background;

	public GameGUI() {
		setSize(600,800);
		buttons = new ArrayList<JRadioButton>();
		for (int i=0; i < 4; i++) {
			buttons.add(new JRadioButton());
		}
		
		timeDisp = new JTextField(20);
		timeDisp.setEnabled(false);
		
		questionDisp = new JTextField(20);
		questionDisp.setEnabled(false);
		
		livesDisp = new JTextField(5);
		livesDisp.setEnabled(false);
		
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
		private int playerX = 0;
		private int playerY = 0;
		private MediaTracker tracker;
		private static final int PADDING = 20;
		private int boardWidth;
		private int boardHeight;
		private int playerWidth;
		private int playerHeight;
		
		public ImagePanel() {
				tracker = new MediaTracker(this);
				Image player = getImage("/images/player.png",1);
				Image back = getImage("/images/board.png",0);
				
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
			//System.out.println(boardWidth*11/160 + ", " + boardHeight);
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
		public void updateCurrentRock(int rockNumber) {
			switch (rockNumber) { //TODO add the switch functionality. 
			case 0: playerX = PADDING;
			playerY = PADDING;
			break;
			default: break;
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
		ButtonGroup group = new ButtonGroup();
		
		qPanel.add(questionDisp);
		
		for (JRadioButton button : buttons) {
			group.add(button);
			qPanel.add(button);
		}
		
		JButton submit = new JButton("Submit");
		qPanel.add(submit);

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
	
	public void updateQuestion(Question question) {
		questionDisp.setText(question.getQuestion());
		int i=0;
		for (Fraction answer : question.orderAnswers()) {
			buttons.get(i).setText(answer.toString());
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

}
