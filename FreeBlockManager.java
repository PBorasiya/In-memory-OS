
package filesystem;


import java.util.LinkedList;


public class FreeBlockManager {

   // private UserData[] blocks;
    private LinkedList<Object> freeBlockslist;
    private LinkedList<Object> usedblocks;
    private boolean[] freeBlockFlagArray = new boolean[100]; 
    
    
    public FreeBlockManager(int size) {
        //blocks = new UserData[size];
    	usedblocks = new LinkedList<Object>();
        freeBlockslist = new LinkedList<Object>();
        
        for (int i = 0; i < size; i++) {
        	DirectoryClass  node = new DirectoryClass();
        	//freeBlockslist.add(new DirectoryClass());
        	freeBlockslist.add(node);
        	freeBlockFlagArray[i] = true;
        }
        freeBlockFlagArray[0] = false;

    }
    
    public void read(int size, DataFile data) {
    	
    }
    
    public void write(Object s, int link) {
    	freeBlockFlagArray[link] = false;
    	usedblocks.add(link, s);
    		
    }
    
    public void delete(int link) {
    	usedblocks.remove(link);
    	freeBlockFlagArray[link] = true;
    	freeBlockslist.add(new DirectoryClass());
    	System.out.println(usedblocks.size() + "BLOCK SIZE");
    	System.out.println(freeBlockslist.size() + " freeBlocks BLOCK SIZE");
    }
    
    public Object getNextBlock () {
    	return freeBlockslist.poll();
    }
    public Object getDirectoryNodeByBlockNumber(int link) {
    	return usedblocks.get(link);
    }
    public int getFreeBlockNumber() {
    	for(int i=0; i< freeBlockFlagArray.length; i++) {
    		if(freeBlockFlagArray[i]) {
    			return i;
    		}
    	}
    	return -1;
    } 
    /*public int countFreeSpace() {

        return freeBlockslist.size();

    }*/
}
