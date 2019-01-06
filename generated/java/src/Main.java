import cli.Interface;

public class Main {

	public static void main(String[] args) {
		Interface lel = new Interface();
		if(lel.setup()) {
			lel.stateMachine();
			lel.close();
		}
	}

}
