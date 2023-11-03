package sec14k_binarynio;


import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

// using file written in MainNIObinaryWriting

public class MainNIObinaryReading {
    public static void main(String[] args) {

        try {

            byte[] outputBytes = "Hello World!".getBytes();
            RandomAccessFile ra = new RandomAccessFile("JavaNIOfile.dat", "rwd");


            // file read using java.nio
            ByteBuffer buffer = ByteBuffer.wrap(outputBytes);
            ByteBuffer intBuffer = ByteBuffer.allocate(Integer.BYTES);
            FileChannel channel = ra.getChannel();

            // buffer.flip();
            long numBytesRead = channel.read(buffer);
            if(buffer.hasArray()) {
                System.out.println(new String(buffer.array()));
            }

// Method 1 (absolute read)

            numBytesRead = channel.read(intBuffer);
            System.out.println(intBuffer.getInt(0));
            intBuffer.flip();
            numBytesRead = channel.read(intBuffer);
            System.out.println(intBuffer.getInt(0));
            // when using an absolute read, position of the buffer pointer will not update

            channel.close();
            ra.close();

// Method 2 (relative read)
//                                         numBytesRead = channel.read(intBuffer);
//                                         intBuffer.flip();
//                                         System.out.println(intBuffer.getInt());
//                                         intBuffer.flip();
//                                         numBytesRead = channel.read(intBuffer);
//                                         intBuffer.flip();
//                                         System.out.println(intBuffer.getInt());



// file read using java.io
//                                            byte[] b = new byte[outputBytes.length];
//                                            ra.read(b);
//                                            System.out.println(new String(b));
//
//                                            int int1 = ra.readInt();
//                                            int int2 = ra.readInt();
//                                            System.out.println(int1 + "\t" + int2);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}


/*

            WHEN SOMETHING'S GOT WRONG... FLIP()

 */