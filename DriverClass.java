
package filesystem;

import java.util.Scanner;
public class DriverClass {
	
	public static void main(String[] args) {
		// add logic for initialization and various inputs
		String[] commands = new String[]{"CREATE","OPEN","CLOSE","DELETE","READ","WRITE","SEEK","QUIT"};
		
		
		System.out.println("Welcome to Pranav's Virtual file system.");
		System.out.println("Please insert appropriate command. Type quit to finish");
		System.out.println("Avalible commands are: ");
		
		for(int i=0;i<commands.length;i++){
			System.out.print(commands[i]+ " ");
		}
		System.out.println();
		
		Scanner scan = new Scanner(System.in);
		DirectoryManagerClass dmc = new DirectoryManagerClass();
	        try {
	            while (scan.hasNextLine()){

	                String cmd = scan.nextLine().trim();
	                if(cmd.toUpperCase().equals("QUIT")) {
	                	break;
	                }
	                String[] cmdsubstr = cmd.split(" ");
	                if(cmdsubstr[0].toUpperCase().equals("CREATE")) {
	                	dmc.createCommand(cmdsubstr[1], cmdsubstr[2]);
	                }
	                else if(cmdsubstr[0].toUpperCase().equals("OPEN")) {
	                	dmc.openComand(cmdsubstr[1].toCharArray()[0], cmdsubstr[2]);
	                }
	                else if(cmdsubstr[0].toUpperCase().equals("CLOSE")) {
	                	dmc.closeCommand();
	                }
	                else if(cmdsubstr[0].toUpperCase().equals("DELETE")) {
	                	//dmc.deleteCommand(tokens[1]);
	                }
	                else if(cmdsubstr[0].toUpperCase().equals("READ")) {
	                	dmc.readCommand(Integer.parseInt(cmdsubstr[1]));
	                }
                        else if(cmdsubstr[0].toUpperCase().equals("WRITE")) {
	                	String input = "";
	                	for(int i=2;i< cmdsubstr.length;i++) {
	                		input = input+cmdsubstr[i]+" ";
	                	}
	                	input = input.trim();
	                	dmc.writeCommand(Integer.parseInt(cmdsubstr[1]), input);
	                }
	                
	                else if(cmdsubstr[0].toUpperCase().equals("SEEK")) {
	                	//dmc.seek(tokens[1], Integer.parseInt(tokens[2]));
                        }
	                
	            }

	        } finally {
	            scan.close();
	        }
	}

}
