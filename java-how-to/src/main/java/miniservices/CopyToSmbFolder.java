package miniservices;


import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;

import java.net.MalformedURLException;


public class CopyToSmbFolder {
    private final String USER_NAME = "value";
    private final String PASSWORD = "value";
    private final String DOMAIN = "value";
    private final String NETWORK_FOLDER = "value";
    private static final String FILE_NAME = "sambaFile.txt";

    public static void main(String[] args) {
        var result = false;
        try {
            String fileContent = "Hi, This is the SmbFile.";
            result = new CopyToSmbFolder().copyFiles(fileContent, FILE_NAME);
        } catch (Exception e) {
            System.err.println("Exception caught. Cause: " + e.getMessage());
        }
        System.out.println(result);
    }

    public boolean copyFiles(String fileContent, String fileName) throws MalformedURLException {
        boolean successful;
        NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(DOMAIN, USER_NAME, PASSWORD);
        String path = "smb://127.0.0.1/" + NETWORK_FOLDER + fileName;
        SmbFile sFile = new SmbFile(path, auth);

        try(SmbFileOutputStream sfos = new SmbFileOutputStream(sFile)) {
            sfos.write(fileContent.getBytes());
            successful = true;
            System.out.println("File successfully created.");
        } catch (Exception e) {
            successful = false;
            System.err.println("Unable to create file. Cause: " + e.getMessage());
        }
        return successful;
    }
}