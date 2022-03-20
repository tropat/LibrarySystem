import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.ResultSet;

public class LogInForm implements Runnable {
	private JFrame frame;
	private JPanel mainPanel;
	private JButton signInButton, signUpButton;
	private JTextField loginTextField;
	private JPasswordField passwordTextField;
	
	public void displayLogInForm() {
		Thread display = new Thread(new LogInForm());
		display.start();
	}
	
	public void setSignInButton() {
		signInButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String login = loginTextField.getText();
				String password = String.valueOf(passwordTextField.getPassword());
				ResultSet rs = DatabaseConnector.execute("Select password FROM users WHERE login = '" + login + "'");
				try {
					if(!rs.next()) {
						System.out.println("Wrong login");
					} else {	
						if(rs.getString("password").equals(password)) {
							System.out.println("Signed in");
						} else {
							System.out.println("Wrong password");
						}
					}
				} catch (Exception ex) {
					System.out.println(ex);
				}		
			}
		});
	}

	private void setComponents() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		signInButton = new JButton("Sign In");
		signUpButton = new JButton("Sign Up");
		
		setSignInButton();
		
		loginTextField = new JTextField("login");
		passwordTextField = new JPasswordField("password");
		
		loginTextField.setPreferredSize(new Dimension(250,30));
		passwordTextField.setPreferredSize(new Dimension(250,30));

		signInButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		signUpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		mainPanel = new JPanel();
	}
	
	private void addToMainPanel() {
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		
		mainPanel.add(loginTextField);
		mainPanel.add(passwordTextField);
		mainPanel.add(signInButton);
		mainPanel.add(signUpButton);
	}
	
	private void setFrame() {
		frame.getContentPane().add(mainPanel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	@Override
	public void run() {
			setComponents();
			addToMainPanel();
			setFrame();
	}
		
}
