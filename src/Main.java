
public class Main {
	private LogInForm logInForm;
	
	public static void main(String[] args) {
		Main mainProgram = new Main();
		DatabaseConnector.connectToDatabase();
		mainProgram.logInForm = new LogInForm();
		mainProgram.logInForm.displayLogInForm();
	}

}
