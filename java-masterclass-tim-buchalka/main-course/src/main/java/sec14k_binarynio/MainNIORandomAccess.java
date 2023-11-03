package sec14k_binarynio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/* NIO operations on random access files


            COMMONLY USED METHODS

read(ByteBuffer) - reads bytes beginning at the channel's current position, and after the read,
                   updates the position accordingly.
                   Note that now we're talking about the channel's position, not the byte buffer's position.
                   Of course, the bytes will be placed into the buffer starting at its current position.
write(ByteBuffer) - the same as read, except it writes. There's one exception.
                  If a datasource is opened in APPEND mode, then the first write will take place starting
                  at the end of the datasource, rather than at position 0. After the write, the position
                  will be updated accordingly.
position()      - returns the channel's position.
position(long)  - sets the channel's position to the passed value.
truncate(long)  - truncates the size of the attached datasource to the passed value.
size()          - returns the size of the attached datasource
 */


public class MainNIORandomAccess {

    public static void main(String[] args) {

        try (FileOutputStream binFile = new FileOutputStream("JavaNIOfile3.dat");
             FileChannel binChannel = binFile.getChannel()) {

// this will sequentially write to a file:

            byte[] outputBytes1 = "Hello World!".getBytes();
            byte[] outputBytes2 = "Nice to meet you!".getBytes();

            ByteBuffer writeBuffer = ByteBuffer.allocate(100);

            writeBuffer.put(outputBytes1);
            int int1stPos = outputBytes1.length;
                                    // saving position of the first integer as being after the length of the outputBytes1 string
            writeBuffer.putInt(245);
            int int2ndPos = int1stPos + Integer.BYTES;
                                    // the second position we want to hold will be 1st position + the length of an integer
            writeBuffer.putInt(-98765);
            writeBuffer.put(outputBytes2);
            int int3rdPos = int2ndPos + Integer.BYTES + outputBytes2.length;
                                    // 3rd position saved for Random Access of the file
            writeBuffer.putInt(1000);

            writeBuffer.flip();
            binChannel.write(writeBuffer);

// randomly accessing a file permits us to take a selective read:
// for example, to read the integer values in inverse order:

            RandomAccessFile ra = new RandomAccessFile("JavaNIOfile3.dat", "rwd");
            FileChannel channel = ra.getChannel();

            ByteBuffer readBuffer = ByteBuffer.allocate(Integer.BYTES);

// for each integer, we are first calling the position in the file (channel.position),
// then we are reading to the buffer, flip(to and reading from the buffer

            channel.position(int3rdPos);
            channel.read(readBuffer);
            readBuffer.flip();
            System.out.println("int3 = " + readBuffer.getInt());

            readBuffer.flip();
            channel.position(int2ndPos);
            channel.read(readBuffer);
            readBuffer.flip();
            System.out.println("int2 = " + readBuffer.getInt());

            readBuffer.flip();
            channel.position(int1stPos);
            channel.read(readBuffer);
            readBuffer.flip();
            System.out.println("int1 = " + readBuffer.getInt());


// this will randomly write to a file:

            int str1stPos = 0;
            int newInt1stPos = outputBytes1.length;
            int newInt2ndPos = newInt1stPos + Integer.BYTES;
            int str2ndPos = newInt2ndPos + Integer.BYTES;
            int newInt3rdPos = str2ndPos + outputBytes2.length;

            ByteBuffer rndWriteBuffer = ByteBuffer.allocate(Integer.BYTES);

            rndWriteBuffer.putInt(245);
            rndWriteBuffer.flip();
            binChannel.position(newInt1stPos);
            binChannel.write(rndWriteBuffer);

            rndWriteBuffer.flip();
            rndWriteBuffer.putInt(-98765);
            rndWriteBuffer.flip();
            binChannel.position(newInt2ndPos);
            binChannel.write(rndWriteBuffer);

            rndWriteBuffer.flip();
            rndWriteBuffer.putInt(1000);
            rndWriteBuffer.flip();
            binChannel.position(newInt3rdPos);
            binChannel.write(rndWriteBuffer);

            binChannel.position(str1stPos);
            binChannel.write(ByteBuffer.wrap(outputBytes1));        // wrap will take care of creating and flipping the buffer
            binChannel.position(str2ndPos);
            binChannel.write(ByteBuffer.wrap(outputBytes2));

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
