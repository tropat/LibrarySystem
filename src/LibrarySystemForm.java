import javax.swing.*;

public class LibrarySystemForm  implements Runnable{
	private JFrame frame;
	
	public void displayLibrarySystemForm() {
		Thread display = new Thread(new LibrarySystemForm());
		display.start();
	}

	private void setComponents() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel label = new JLabel("Library System");
		frame.getContentPane().add(label);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	@Override
	public void run() {
		setComponents();
	}
}
