package gamePlay;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GameGUI extends JFrame {
private JPanel graphicsPanel;
private JPanel questionPanel;
private JPanel playerStatus;
private JTextField timeDisp;
private JTextField questiondisp;
private JTextField livesDisp;

public GameGUI() {
	timeDisp = new JTextField(20);
	questiondisp = new JTextField(20);
	livesDisp = new JTextField(5);
	graphicsPanel = createGraphicsPanel();
	questionPanel = createQuestionPanel();
	playerStatus = createPlayerStatusPanel();
}

private JPanel createGraphicsPanel() {
	return null;
}

private JPanel createQuestionPanel() {
	return null;
}

private JPanel createPlayerStatusPanel() {
	return null;
}

public void update(int time, int lives, Question question) {
	timeDisp.setText(Integer.toString(time));
	//questionDisp.setText(question string junk stuff); /************TODO - after question implemented more*/
	livesDisp.setText(Integer.toString(lives));
}

}
