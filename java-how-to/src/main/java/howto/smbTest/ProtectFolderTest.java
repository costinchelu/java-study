package howto.smbTest;


import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;


public class ProtectFolderTest {
    private String USER_NAME = null;
    private String PASSWORD = null;
    private String DOMAIN = null;
    private String NETWORK_FOLDER = null;

    public static void main(String args[]) {
        var result = false;
        try {
            String fileContent = "Hi, This is the SmbFile.";
            result = new ProtectFolderTest().copyFiles(fileContent, "SmbFile1.txt");
        } catch (Exception e) {
            System.err.println("Exception caught. Cause: " + e.getMessage());
        }
        System.out.println(result);
    }

    public boolean copyFiles(String fileContent, String fileName) {
        boolean successful = false;
        String path = null;
        NtlmPasswordAuthentication auth = null;
        SmbFile sFile = null;
        SmbFileOutputStream sfos = null;
        try {
            auth = new NtlmPasswordAuthentication(DOMAIN, USER_NAME, PASSWORD);
            path = "smb://127.0.0.1/" + NETWORK_FOLDER + fileName;
            sFile = new SmbFile(path, auth);
            sfos = new SmbFileOutputStream(sFile);
            sfos.write(fileContent.getBytes());
            successful = true;
            System.out.println("File successfully created.");
        } catch (Exception e) {
            successful = false;
            System.err.println("Unable to create file. Cause: "
                    + e.getMessage());
        }
        return successful;
    }
}