package humansVsGoblins;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class InstructionsWindow extends JFrame implements ActionListener {
	static final int FRAME_X = 600;
	static final int FRAME_Y = 300;
	static final Font HEADER_FONT = new Font("Dialog", Font.BOLD, 48);
	static final Font INSTRUCTION_FONT = new Font("Dialog", Font.BOLD, 24);
	static final Font BUTTON_FONT = new Font("Dialog", Font.BOLD, 24);

	JPanel northPanel, centerPanel;
	JLabel headerLabel, instructionsLabel1, instructionsLabel2;
	JButton startButton;

	public InstructionsWindow() {
		super("Human vs Goblin"); // Sets window text

		// Configure window settings
		setSize(FRAME_X, FRAME_Y);
		setLayout(new BorderLayout());
		setResizable(false); // Disables resizing window
		setLocationRelativeTo(null); // Centers window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Terminates program when window is closed

		// Swing components for north section
		northPanel = new JPanel();
		headerLabel = new JLabel("Human vs Goblin");
		headerLabel.setFont(HEADER_FONT);

		northPanel.add(headerLabel);

		add(northPanel, BorderLayout.NORTH);

		// Swing components for center section
		centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(3, 1));

		instructionsLabel1 = new JLabel("WASD or Arrow Keys to move");
		instructionsLabel2 = new JLabel("I to open Inventory");
		startButton = new JButton("Start");

		instructionsLabel1.setFont(INSTRUCTION_FONT);
		instructionsLabel2.setFont(INSTRUCTION_FONT);
		instructionsLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		instructionsLabel2.setHorizontalAlignment(SwingConstants.CENTER);

		startButton.setPreferredSize(new Dimension(200, 50));
		startButton.setFont(BUTTON_FONT);
		startButton.setFocusable(false); // Disables highlight when button is clicked
		startButton.setRolloverEnabled(false); // Disables hover effect
		startButton.setBackground(Color.WHITE);
		startButton.addActionListener(e -> actionPerformed(e));

		centerPanel.add(instructionsLabel1);
		centerPanel.add(instructionsLabel2);
		centerPanel.add(startButton);

		add(centerPanel, BorderLayout.CENTER);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == startButton) {
			setVisible(false);
			//Launcher.main(null);
			new GameFrame();
		}
	}

}
