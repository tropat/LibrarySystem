import javax.swing.*;
import java.awt.*;

public class LibrarySystemForm  implements Runnable{
	private Boolean adminMode = false;
	private String userLogin = "";
	
	private JFrame frame;
	private JPanel mainPanel;
	private JButton profileButton;
	private JButton logOutButton;
	private JButton addBookButton;
	private JTextField titleTextField;
	private JTextField authorTextField;
	private JButton searchButton;
	private JScrollPane booksScrollPane;
	
	public LibrarySystemForm(Boolean admin, String login) {
		adminMode = admin;
		userLogin = login;
	}
	
	public void displayLibrarySystemForm() {
		Thread display = new Thread(new LibrarySystemForm(adminMode, userLogin));
		display.start();
	}

	private void setComponents() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainPanel = new JPanel();
		addBookButton = new JButton("Add new Book");
		profileButton = new JButton("Profile");
		logOutButton = new JButton("Log Out");
		titleTextField = new JTextField("Title");
		authorTextField = new JTextField("Author");
		searchButton = new JButton("Search");
		booksScrollPane = new JScrollPane();
		
		if(!adminMode) {
			addBookButton.setEnabled(false);
		}
	}
	
	private void setMainPanelStart() {
		JPanel pageStartPanel = new JPanel();
		JPanel profilePanel = new JPanel();
		profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));
		profilePanel.add(profileButton);
		profilePanel.add(logOutButton);
		pageStartPanel.setLayout(new BorderLayout());
		pageStartPanel.add(addBookButton,BorderLayout.LINE_START);
		pageStartPanel.add(profilePanel,BorderLayout.LINE_END);
		mainPanel.add(pageStartPanel, BorderLayout.PAGE_START);
	}
	
	private void setMainPanelCenter() {
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.add(titleTextField);
		centerPanel.add(authorTextField);
		centerPanel.add(searchButton);
		centerPanel.add(booksScrollPane);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
	}
	
	private void addToMainPanel() {		
		mainPanel.setLayout(new BorderLayout());
		setMainPanelStart();
		setMainPanelCenter();
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
