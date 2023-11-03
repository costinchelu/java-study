package sec14l_channelcopy;

// Using FileChannel to copy files and pipes with threads

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class JavaNIOcopyWithChannel {
    public static void main(String[] args) {

        try {
            RandomAccessFile ra = new RandomAccessFile("JavaNIOfile3.dat", "rwd");
            FileChannel copyFromChannel = ra.getChannel();
                                                    // constructing the channel for the file to be copied from

            RandomAccessFile copyFile = new RandomAccessFile("JavaNIOFileCopy.dat", "rw");
            FileChannel copyToChannel = copyFile.getChannel();
                                                    // constructing the channel for the file to be copied to
            copyFromChannel.position(0);
                                                    // resetting position to 0 (or else, position will be the last position used)
                                                    // in this particular case copyFromChannel was not used before.
                                                    // But in some cases we can use a channel that was used to copy or read something
            long numTransferred = copyToChannel.transferFrom(copyFromChannel, 0, copyFromChannel.size());
                                                    // transfer from the first channel (file), starting with position 0
                                                    // (which is actually the relative position),
                                                    // all the contents of the file (channel.size)
            // equivalent to:
            // long numTransferred = copyFromChannel.transferTO(0, copyFromChannel.size(), copyToChannel);
            System.out.println("Num transferred = " + numTransferred + " bytes");

            copyFromChannel.close();
            copyToChannel.close();
            ra.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
