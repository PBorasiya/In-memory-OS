import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class directoryClass {

	public int createDir( String name){
		
		
		
			try {
				Files.createDirectories(Paths.get(name));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("dir created");
			return 0;
		
		
		
	}
	
}
