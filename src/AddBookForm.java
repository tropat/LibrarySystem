import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class AddBookForm implements Runnable{
	private JFrame frame;
	private JPanel mainPanel;
	
	
	public void displayAddBookForm() {
		Thread display = new Thread(new AddBookForm());
		display.start();
	}
	
	private void setComponents() {
		frame = new JFrame();
		
		
		
		mainPanel = new JPanel();
	}
	
	private void addToMainPanel() {
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		
		
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
