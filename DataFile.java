
package filesystem;



public class DataFile {
	private int backFile;
	private int fwdFile;
	private  char[] data;
	public int getBackFile() {
		return backFile;
	}
	public void setBackFile(int backFile) {
		this.backFile = backFile;
	}
	public int getFwdFile() {
		return fwdFile;
	}
	public void setFwdFile(int fwdFile) {
		this.fwdFile = fwdFile;
	}
	public char[] getData() {
		return data;
	}
	public void setData(char[] data) {
		this.data = data;
	}
	
}
