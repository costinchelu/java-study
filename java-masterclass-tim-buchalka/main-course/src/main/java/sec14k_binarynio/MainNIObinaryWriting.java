package sec14k_binarynio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/*
Java.IO ~~ Java.NIO

So it really doesn't matter which method we use to read or write a file

 */

public class MainNIObinaryWriting {
    public static void main(String[] args) {

        try(FileOutputStream binFile = new FileOutputStream("JavaNIOfile.dat");
            FileChannel binChannel = binFile.getChannel()) {

            byte[] outputBytes = "Hello World!".getBytes();
            ByteBuffer buffer = ByteBuffer.wrap(outputBytes);
                                // WRAP = it will wrap the outputBytes array (of type byte) into the buffer
                                // the buffer will be backed by the byte array
                                // modifications to the buffer will actually change the array and reciprocal
                                    // it sets the position of the buffer to 0 (when we use the buffer the
                                    // read/write will start at position 0)
                                    // buffer's capacity is set to outputBytes.length
                                    // buffer's mark will be undefined (can be set later)
                                    // now we can use the channel to write to the file

            int numBytes = binChannel.write(buffer);
                                        System.out.println("numBytes written was: " + numBytes);
                                                                            // (debugging purposes)

            //if we want to write an integer through the channel:
            ByteBuffer intBuffer = ByteBuffer.allocate(Integer.BYTES);
                                                // we are putting Integer.BYTES as a parameter because we want to write
                                                // an integer (no. of bytes in an integer)
                                                // put = will fill up the ByteBuffer (in this case buffer.size = 4)
            intBuffer.putInt(245);
                                                // After finishing the put (read), if we want to write, we need to flip() the buffer
            intBuffer.flip();
                                                // flip = will set the limit of the buffer to the current position and
                                                // reset the position to zero.
            numBytes = binChannel.write(intBuffer);

                                        System.out.println("numBytes written was: " + numBytes);
                                                                            // (debugging purposes)

            intBuffer.flip();
            intBuffer.putInt(-98765);
            intBuffer.flip();
            numBytes = binChannel.write(intBuffer);
                                                // another write/read/write example

                                        System.out.println("numBytes written was: " + numBytes);
                                                                            // (debugging purposes)
            // if we are going to do multiple writes to a buffer, before writing the buffer to a channel we don't have
            // to call flip() after each write, but we do have to call it whenever we flip from reading to writing

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
