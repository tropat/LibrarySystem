import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.sql.*;

public class SignUpForm implements Runnable {
	private JFrame frame;
	private JPanel mainPanel;
	private JButton signUpButton;
	private JTextField loginTextField;
	private JPasswordField passwordTextField;
	private JTextField firstNameTextField, lastNameTextField, addressTextField, emailTextField, phoneNumberTextField;
	private JLabel loginLabel, passwordLabel;
	private JLabel firstNameLabel, lastNameLabel, addressLabel, emailLabel, phoneNumberLabel;
	private JLabel validationInfoLabel;
	
	public void displaySignUpForm() {
		Thread display = new Thread(new SignUpForm());
		display.start();
	}
	
	private boolean validate() {
		if(loginTextField.getText().equals("") ||
			String.valueOf(passwordTextField.getPassword()).equals("") ||
			firstNameTextField.getText().equals("") ||
			lastNameTextField.getText().equals("") ||
			addressTextField.getText().equals("") ||
			emailTextField.getText().equals("") ||
			phoneNumberTextField.getText().equals("")) {
			
			return false;	
		}
		return true;
	}
	
	public void setSignUpButton() {
		signUpButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if(validate()) {
						String login = loginTextField.getText();
						String password = String.valueOf(passwordTextField.getPassword());
						String firstName = firstNameTextField.getText();
						String lastName = lastNameTextField.getText();
						String address = addressTextField.getText();
						String email = emailTextField.getText();
						String phoneNumber = phoneNumberTextField.getText();
						
						try {
							CallableStatement cs = DatabaseConnector.getCon().prepareCall("{call users_insert(?,?,?)}");
							cs.setString(1, login);
							cs.setString(2, password);
							cs.setInt(3, java.sql.Types.INTEGER);
							cs.executeUpdate();
							int id = cs.getInt(3);
							
							PreparedStatement ps = DatabaseConnector.getCon().prepareStatement("INSERT INTO users_data "
									+ "(user_id, first_name, last_name, address, email, phone_number) values (?,?,?,?,?,?)");
							ps.setInt(1, id);
							ps.setString(2, firstName);
							ps.setString(3, lastName);
							ps.setString(4, address);
							ps.setString(5, email);
							ps.setString(6, phoneNumber);
							ps.executeUpdate();
							
							frame.setVisible(false);
							frame.dispose();
							
						} catch (SQLException e1) {}
					} else {
						validationInfoLabel.setText("Incorrect data");
						validationInfoLabel.setForeground(Color.red);	
						frame.setSize(300,320);
					}
			}
		});
	}

	private void setComponents() {
		frame = new JFrame();
		
		signUpButton = new JButton("Sign Up");
		
		setSignUpButton();
		
		loginTextField = new JTextField("");
		passwordTextField = new JPasswordField("");
		
		loginLabel = new JLabel("Login");
		passwordLabel = new JLabel("Password");
		
		firstNameLabel = new JLabel("First Name");
		lastNameLabel = new JLabel("Last Name");
		addressLabel = new JLabel("Address");
		phoneNumberLabel = new JLabel("Phone Number");
		emailLabel = new JLabel("Email");
		
		validationInfoLabel = new JLabel(" ");
		
		firstNameTextField = new JTextField("");
		lastNameTextField = new JTextField("");
		addressTextField = new JTextField("");
		phoneNumberTextField = new JTextField("");
		emailTextField = new JTextField("");
		
		mainPanel = new JPanel();
	}
	
	private void addToMainPanel() {
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		
		mainPanel.add(loginLabel);
		mainPanel.add(loginTextField);

		mainPanel.add(passwordLabel);
		mainPanel.add(passwordTextField);
		
		mainPanel.add(firstNameLabel);
		mainPanel.add(firstNameTextField);
		
		mainPanel.add(lastNameLabel);
		mainPanel.add(lastNameTextField);
		
		mainPanel.add(addressLabel);
		mainPanel.add(addressTextField);
		
		mainPanel.add(phoneNumberLabel);
		mainPanel.add(phoneNumberTextField);
		
		mainPanel.add(emailLabel);
		mainPanel.add(emailTextField);
		
		mainPanel.add(validationInfoLabel);
				
		mainPanel.add(signUpButton);
	}
	
	private void setFrame() {
		frame.getContentPane().add(mainPanel);
		frame.pack();
		frame.setSize(300,320);
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
