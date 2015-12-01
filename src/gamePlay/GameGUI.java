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
	private JPanel graphicsPanel;
	private JPanel questionPanel;
	private JPanel playerStatus;
	private JTextField timeDisp;
	private JTextField questionDisp;
	private JTextField livesDisp;
	private ArrayList<JRadioButton> buttons;
	
	//Image stuff
	Image playerSprite;
	Image background;

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
		graphicsPanel = createGraphicsPanel();
		add(graphicsPanel);
		JPanel bottom = new JPanel();
		bottom.setLayout(new GridLayout(1,0));
		playerStatus = createPlayerStatusPanel();
		bottom.add(playerStatus);
		questionPanel = createQuestionPanel();
		bottom.add(questionPanel);
		add(bottom);
	}

	private Image getImage(String pathName) {
		URL url = getClass().getResource(pathName);
		Image image = Toolkit.getDefaultToolkit().getImage(url);
		return image;
	}

	private ImagePanel createGraphicsPanel() {
		ImagePanel graphicsPanel = new ImagePanel();
		return graphicsPanel;
	}
	
	public class ImagePanel extends JPanel{
		public ImagePanel() {
				MediaTracker tracker = new MediaTracker(this);
				Image player = getImage("/images/player.png");
				Image back = getImage("/images/board.png");
				
				tracker.addImage(background, 0);
				try {
					tracker.waitForID(0);
				} catch (InterruptedException e) {  return; }
				tracker.addImage(playerSprite, 1);
				try {
					tracker.waitForID(0);
				} catch (InterruptedException e) {  return; }
				
				background = back.getScaledInstance(1600, 1000, Image.SCALE_FAST);
				playerSprite = player.getScaledInstance(110, 170,  Image.SCALE_FAST);
		}

		public void paintComponent(Graphics g) {
			int PADDING = 20;
			g.drawImage(background, PADDING, PADDING, 1600, 1000, null);
			g.drawImage(playerSprite, 0, 0, 110, 170, null);
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
