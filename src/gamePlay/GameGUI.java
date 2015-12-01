package gamePlay;

import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.HeadlessException;
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

	public GameGUI() {
		setSize(600,800);
		int ascii = 65; //first radio button will be 'a'
		String c;
		buttons = new ArrayList<JRadioButton>();
		for (int i=0; i < 4; i++) {
			
			c = Character.toString((char)(ascii + i));
			buttons.add(new JRadioButton(c));
			
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

	private JPanel createGraphicsPanel() {
		JPanel gPanel = new JPanel();
		
		return gPanel;
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
