
package filesystem;


public class DirectoryClass {
	private int backnode;
	private int forwardNode;
	private int link;
	private char type;
	private String name;	
	private int nextFreeBlockNo;
	private char[] filler = new char[4];
	private int size;
	private DirectoryClass[] directoryEntries = new DirectoryClass[31];
	public int getBacknode() {
		return backnode;
	}
	public void setBacknode(int backnode) {
		this.backnode = backnode;
	}
	public int getForwardNode() {
		return forwardNode;
	}
	public void setForwardNode(int forwardNode) {
		this.forwardNode = forwardNode;
	}
	public int getLink() {
		return link;
	}
	public void setLink(int link) {
		this.link = link;
	}
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNextFreeBlockNo() {
		return nextFreeBlockNo;
	}
	public void setNextFreeBlockNo(int nextFreeBlockNo) {
		this.nextFreeBlockNo = nextFreeBlockNo;
	}
	public char[] getFiller() {
		return filler;
	}
	public void setFiller(char[] filler) {
		this.filler = filler;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public DirectoryClass[] getDirectoryEntries() {
		return directoryEntries;
	}
	public void setDirectoryEntries(DirectoryClass[] directoryEntries) {
		this.directoryEntries = directoryEntries;
	}
}
