import java.util.Scanner;

public class DriverClass {

	private static Scanner scan;

	public static void main(String[] args) {
		
		directoryClass d = new directoryClass();
		scan = new Scanner(System.in);
		String[] commands = new String[]{"CREATE","OPEN","CLOSE","DELETE","READ","WRITE","SEEK","QUIT"};
		String userCommand="";
		System.out.println("Welcome to Pranav's Virtual file system.");
		System.out.println("You are in Root directory.");
		System.out.println("Please insert appropriate command. Type quit to finish");
		System.out.println("avalible commands are:");
		
		for(int i=0; i<commands.length; i++){
			System.out.println(commands[i]);
		}
		
		//userCommand = scan.nextLine();
		
		
		while(!userCommand.toUpperCase().equals("QUIT")){
		

		//System.out.println("inside while loop...");
		
		
		userCommand = scan.nextLine();
		System.out.println(userCommand.toUpperCase());
		/*
		if(userCommand.toUpperCase()== commands[0] ){
			System.out.println("file/directory created");
		}
		else{
			System.out.println("nothing");
		}*/
		if(userCommand.toUpperCase().equals(commands[0])){
			System.out.println("your selected comand: " +userCommand.toUpperCase() );
			d.createDir("first dir");
			d.createDir("seconddir");
		}
		else if(userCommand.toUpperCase().equals(commands[1])){
			System.out.println("your selected comand: " +userCommand.toUpperCase() );
		}
		else if(userCommand.toUpperCase().equals(commands[2])){
			System.out.println("your selected comand: " +userCommand.toUpperCase() );
		}
		else if(userCommand.toUpperCase().equals(commands[3])){
			System.out.println("your selected comand: " +userCommand.toUpperCase() );
		}
		else if(userCommand.toUpperCase().equals(commands[4])){
			System.out.println("your selected comand: " +userCommand.toUpperCase() );
		}
		else if(userCommand.toUpperCase().equals(commands[5])){
			System.out.println("your selected comand: " +userCommand.toUpperCase() );
		}
		else if(userCommand.toUpperCase().equals(commands[6])){
			System.out.println("your selected comand: " +userCommand.toUpperCase() );
		}
		else if(userCommand.toUpperCase().equals(commands[7])){
			System.out.println("your selected comand: " +userCommand.toUpperCase() );
		}
		
		else{
			System.out.println("Make sure you typed command correctly");
			//creteauserCommand=scan.nextLine();
			System.out.println(userCommand);
		}
	}
	System.out.println("DONE");
	}		
	
}
