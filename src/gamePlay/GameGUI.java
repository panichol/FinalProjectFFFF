package gamePlay;

import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

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
		
		//graphicsPanel = createGraphicsPanel();
		//add(graphicsPanel);
		questionPanel = createQuestionPanel();
		add(questionPanel);
		//playerStatus = createPlayerStatusPanel();
		//add(playerStatus);
	}

	private JPanel createGraphicsPanel() {
		return null;
	}

	private JPanel createQuestionPanel() {
		JPanel qPanel = new JPanel();
		qPanel.setLayout(new GridLayout(0,1));
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
		return null;
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
