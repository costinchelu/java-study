package sec14k_binarynio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


// A "cleaner" method of writing a file with java.NIO (chained put method)


public class MainNIObinaryChained {
    public static void main(String[] args) {

// chained put method:

        try (FileOutputStream binFile = new FileOutputStream("JavaNIOfile2.dat");
             FileChannel binChannel = binFile.getChannel()) {

            ByteBuffer writeBuffer = ByteBuffer.allocate(100);

            byte[] outputBytes1 = "Hello World!".getBytes();
            byte[] outputBytes2 = "Nice to meet you!".getBytes();
            writeBuffer.put(outputBytes1).putInt(245).putInt(-98765).put(outputBytes2).putInt(1000);

 // or unchained:
//            writeBuffer.put(outputBytes1);
//            writeBuffer.putInt(245);
//            writeBuffer.putInt(-98765);
//            writeBuffer.put(outputBytes2);
//            writeBuffer.putInt(1000);

            writeBuffer.flip();

            binChannel.write(writeBuffer);


            RandomAccessFile ra = new RandomAccessFile("JavaNIOfile2.dat", "rwd");
            FileChannel channel = ra.getChannel();
            ByteBuffer readBuffer = ByteBuffer.allocate(100);
            channel.read(readBuffer);

            readBuffer.flip();      //switch from writing to the buffer to reading from the buffer

            byte[] inputString1 = new byte[outputBytes1.length];
            byte[] inputString2 = new byte[outputBytes2.length];
            readBuffer.get(inputString1);
            System.out.println("inputString1 = " + new String(inputString1));
            System.out.println("int1 = " + readBuffer.getInt());
            System.out.println("int2 = " + readBuffer.getInt());
            readBuffer.get(inputString2);
            System.out.println("inputString2 = " + new String(inputString2));
            System.out.println("int3 = " + readBuffer.getInt());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
