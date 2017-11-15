package filesystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DirectoryManagerClass {
	private static FreeBlockManager fbm = new FreeBlockManager(100);
	private DirectoryClass root;
	private DirectoryClass lastCreatedNode;
	private char mode;
	private DataFile dataNode;
	private int dataPointer = 0;
	private int freeDirectories = 0;
	public DirectoryManagerClass() {
		root = (DirectoryClass) fbm.getNextBlock();
		root.setBacknode(0);
		root.setType('D');
		root.setName("");
		root.setLink(0);
		fbm.write(root, 0);
		root.setNextFreeBlockNo(fbm.getFreeBlockNumber());
	}
        
        public int deleteCommand(String name){
		List<String> arrayList = new ArrayList<>();
                String[] fileOrDirNames = name.split("/");
                arrayList.addAll(Arrays.asList(fileOrDirNames));
		DirectoryClass node = this.findNodeByName(arrayList, root);
		if(node!=null && node.getBacknode() > 0 ) {
			this.deleteDirectoryRec(node, (DirectoryClass) fbm.getDirectoryNodeByBlockNumber(node.getBacknode()));
		}
		
		return 0;
	}
	
        //To delete all the child directories recursively
	private void deleteDirectoryRec(DirectoryClass node, DirectoryClass previousNode) {
		
		if(node != null && node.getType() == 'D') {
			for(DirectoryClass currentNode: node.getDirectoryEntries()) {
				if(currentNode != null) {
					deleteDirectoryRec(currentNode, node);
					fbm.delete(currentNode.getLink());
					
				}
				
			}
			node.setDirectoryEntries(new DirectoryClass[31]);
		}
		System.out.println(root);
	}
	
	
	public int createCommand(String type, String name){
            
            
		String[] paths = name.split("/");
		List<String> arrayList = new ArrayList<>();
                arrayList.addAll(Arrays.asList(paths));
		this.addNodeToPath(arrayList, root, null, type);
		//System.out.println(root);
                System.out.println("Directory/File " + paths[paths.length-1]+ " Created");
		return 0;
	}
        
        //for writing into datafile for specified size
        public int writeCommand(int datasize, String data){
	//collecting data from input	
            if(this.mode == 'I') {
			System.out.println("write command is not permitted in Input mode");
			return 0;
		}
		if(this.lastCreatedNode == null) {
			System.out.println("FILE IS CLOSED OR NOT FOUND");
			return  0;
		}
		if(data.length() < datasize) {
			for(int i=0; data.length() < datasize; i++) {
				data =  data+ " ";
			}
		}
		if( datasize < data.length()) {
			StringBuffer buf = new StringBuffer();
			for(int i =0;i < datasize; i++) {
				buf.append(data.charAt(i));
			}
			data = buf.toString();
		}
		
                //setting the data into structure and updating block structures and arrays
		char[] charData  = data.toCharArray();
		int startIndex = 0;
		DataFile userData = null;
		int backBlockNumber = 0;
		int freeBlockNumber = root.getNextFreeBlockNo();
		char[] fileData = null;
		List<Character> fileDataList =  null;
		for(int counter = 0; counter < charData.length; counter++) {
			if(freeBlockNumber < 0 ) {
				System.out.println("DISK IS FULL PROGRAM WIll EXIT");
				return 0;
			}
			if(startIndex == 0) {
				userData = new DataFile();
				fileData = new  char[504];
				fileDataList = new ArrayList<>();
				userData.setBackFile(backBlockNumber);
				if(counter == 0) {
					this.lastCreatedNode.setLink(freeBlockNumber);
				}
			}
			fileDataList.add(charData[counter]);
			fileData[startIndex] = charData[counter];
			startIndex++;
                        
            //for when input is more than 504 char and reaches the end of one pagesize
			if(startIndex == 503 || (counter + 1) == charData.length) {
				fileData = new char[fileDataList.size()];
				for(int i=0;i < fileDataList.size(); i++) {
					fileData[i] = fileDataList.get(i);
				}
				userData.setData(fileData);
				fbm.getNextBlock();
				fbm.write(userData, freeBlockNumber);
				backBlockNumber = freeBlockNumber;
				root.setNextFreeBlockNo(fbm.getFreeBlockNumber());
				freeBlockNumber = root.getNextFreeBlockNo();
				userData.setFwdFile(root.getNextFreeBlockNo());
				startIndex = 0;
			}
		}
		//for the last datablock used by datafile
		userData.setFwdFile(0);
		this.lastCreatedNode.setSize(userData.getData().length);
                System.out.println("write up complete in current file ");
                return 0;
	}
	
        //to close currently open file
        public void closeCommand(){
		System.out.println("File " + this.lastCreatedNode.getName() + " Closed");
		this.lastCreatedNode = null;
		this.dataNode = null;
		//this.print();
	}
	public int openComand(char mode, String name){
		List<String> arrayList = new ArrayList<>();
		this.mode = mode;
		String[] fileOrDirNames = name.split("/");
                arrayList.addAll(Arrays.asList(fileOrDirNames));
		this.lastCreatedNode =  this.findNodeByName(arrayList, root);
		this.dataNode =  (DataFile)fbm.getDirectoryNodeByBlockNumber(this.lastCreatedNode.getLink());
		if(this.lastCreatedNode != null ) {
			System.out.println(this.lastCreatedNode.getName() + " opened in mode " + mode);
		}
		return 0;
	}
	
        private DirectoryClass findNodeByName(List<String> fileOrDirNames, DirectoryClass parentDir) {
		boolean found = false;
		if(fileOrDirNames.isEmpty()) {
			return parentDir;
		}
		for(DirectoryClass node : parentDir.getDirectoryEntries()) {
			if(node!=null &&node.getName().equalsIgnoreCase(fileOrDirNames.get(0))) {
				fileOrDirNames.remove(0);
				found = true;
			return findNodeByName(fileOrDirNames, node);
			}
		}
		if(!found) {
			System.out.println("FILE NOT FOUND");
		}
		return null;
	}
	
	//to add new entry in dir pathlist
        private DirectoryClass addNodeToPath(List<String> paths, DirectoryClass currentNode, DirectoryClass prevNode, String type) {
		if(paths.size() <= 0) {
			return null;
		}
		
		boolean found  = false;
		DirectoryClass freeNode = (DirectoryClass) fbm.getNextBlock();
		freeNode.setBacknode(currentNode.getLink());
		freeNode.setName(paths.get(0));
		freeNode.setLink(root.getNextFreeBlockNo());
		if(type.toCharArray()[0]=='D' || type.toCharArray()[0]=='d'){
                freeNode.setType('D');
                }
                else {
                freeNode.setType('U');
                }
                
		currentNode.setForwardNode(root.getNextFreeBlockNo());
		DirectoryClass[] directories = currentNode.getDirectoryEntries();
		if(paths.size() == 1) {
			freeNode.setType(type.toCharArray()[0]);
				for(int i=0; i< directories.length; i++) {
					if(directories[i] == null) {
						fbm.write(freeNode, freeNode.getLink());
						root.setNextFreeBlockNo(fbm.getFreeBlockNumber());
						directories[i] = freeNode;
						lastCreatedNode = freeNode;
						currentNode.setDirectoryEntries(directories);
						return null;
					}
				}
			return null;
		}else {
			//to add to existing directory, replace it with new one
			for(DirectoryClass d : currentNode.getDirectoryEntries()) {
				if(d != null && d.getName().equalsIgnoreCase(paths.get(0)) && ( d.getType() == 'D' || d.getType() == 'd' ) ) {
					found = true;
					paths.remove(0);
					addNodeToPath(paths, d, currentNode, type);
					break;
				}
			}
			for(int i=0; i< directories.length; i++) {
				if(directories[i] == null) {
					directories[i] = freeNode;
					currentNode.setDirectoryEntries(directories);
					break;
				}
			}
			fbm.write(freeNode, freeNode.getLink());
			root.setNextFreeBlockNo(fbm.getFreeBlockNumber());
			paths.remove(0);
			addNodeToPath(paths, freeNode, currentNode, type);
			
		}
		return null;
	}	
     
        public int readCommand(int datasize){
	//collecting data from input	
            
             datasize = this.lastCreatedNode.getSize();
                if(this.mode == 'O') {
			System.out.println("Only  Read and Seek commands are not permitted in Output mode");
			return 0;
		}
		if(this.lastCreatedNode == null) {
			System.out.println("FILE IS CLOSED OR NOT FOUND");
			return  0;
		}
                
                this.dataNode =  (DataFile)fbm.getDirectoryNodeByBlockNumber(this.lastCreatedNode.getLink());
                for(int i=0; i< this.dataNode.getData().length;i++){
                    System.out.print(this.dataNode.getData()[i]);
                }
                System.out.println();
                return 0;
	}
}