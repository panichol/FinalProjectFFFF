package gamePlay;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
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

	public GameGUI(Player player) {
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
		
		graphicsPanel = createGraphicsPanel(player);
		add(graphicsPanel);
		questionPanel = createQuestionPanel();
		add(questionPanel);
		playerStatus = createPlayerStatusPanel(player);
		add(playerStatus);
	}

	private JPanel createGraphicsPanel(Player player) {
		return null;
	}

	private JPanel createQuestionPanel() {
		JPanel qPanel = new JPanel();
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

	private JPanel createPlayerStatusPanel(Player player) {
		return null;
	}

	public void updateTime(int time) {
		timeDisp.setText(Integer.toString(time));		
		repaint();
	}
	
	public void updateLives(int lives) {
		livesDisp.setText(Integer.toString(lives));
		repaint();
	}
	
	public void updateQuestion(Question question) {
		//questionDisp.setText(question.get some string stuff);
		repaint();
	}
	
	public void updateStatus(Player player) {
		//Update Location, or death
		repaint();
	}

}
