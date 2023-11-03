package sec14g_randomaccess;

public class IndexRecord {
    private int startByte;
    private int length;

    public IndexRecord(int startByte, int length) {
        this.startByte = startByte;
        this.length = length;
    }

    public int getStartByte() {
        return startByte;
    }

    public void setStartByte(int startByte) {
        this.startByte = startByte;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    /*
    * index record will contain location ID, start offset and length
    * into memory, the location ID will act as a map key and that's why
    * we don't have to store it in the class
    *
     */
}
