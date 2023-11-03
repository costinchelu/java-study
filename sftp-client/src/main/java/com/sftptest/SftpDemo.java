package com.sftptest;

import com.sftptest.exception.SftpTestException;
import com.sftptest.service.SftpClient;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SftpDemo {

    public static void main(String[] args) {
        SftpClient client = new SftpClient("127.0.0.1", 222, "costinc2", "CostinC2");
        client.connect();

        try {
            client.uploadFile(SftpDemo.class.getClassLoader().getResource("upload.txt").getFile(), "/sftpshare");

            client.retrieveFile("/sftpshare/upload.txt", "target/");

            client.deleteFile("/sftpshare/upload.txt");

        } catch (SftpTestException | NullPointerException e) {
            log.error("", e);
        } finally {
            client.disconnect();
        }
    }
}
