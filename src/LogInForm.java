import javax.swing.*;

public class LogInForm implements Runnable {
	private JFrame frame;
	private JPanel mainPanel;
	private JButton loginButton, passwordButton;
	
	public void displayLogInForm() {
		Thread display = new Thread(new LogInForm());
		display.start();
	}

	@Override
	public void run() {
			frame = new JFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			loginButton = new JButton("Login");
			passwordButton = new JButton("Password");
			
			mainPanel = new JPanel();
			
			mainPanel.add(loginButton);
			mainPanel.add(passwordButton);
			
			frame.getContentPane().add(mainPanel);
			
			frame.pack();
			frame.setVisible(true);
			
	}
		
}
