package com.sftptest.service;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.sftptest.exception.SftpTestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Properties;

@Slf4j
@RequiredArgsConstructor
public class SftpClient {
    
    private final String server;

    private final int port;

    private final String login;

    private final String password;

    private Session session;

    private Channel channel;

    private ChannelSftp channelSftp;

    private static final String CLOSED_CONNECTION = "Connection to server is closed. Open it first.";

    public void connect() {
        try {
            log.debug("Initializing jsch");
            JSch jsch = new JSch();

            session = jsch.getSession(login, server, port);
            session.setPassword(password.getBytes(StandardCharsets.ISO_8859_1));

            log.debug("Jsch set to StrictHostKeyChecking=no");
            Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            log.info("Connecting to {} : {}", server, port);
            session.connect();
            log.info("Connected !");

            log.debug("Opening a channel ...");
            channel = session.openChannel("sftp");
            channel.connect();
            channelSftp = (ChannelSftp) channel;
            log.debug("Channel sftp opened");

        } catch (JSchException e) {
            log.error("", e);
        }
    }

    /**
     * Uploads a file to the sftp server
     * @param sourceFile String path to sourceFile
     * @param destinationFile String path on the remote server
     * @throws SftpTestException if connection and channel are not available or if an error occurs during upload.
     */
    public void uploadFile(String sourceFile, String destinationFile) throws SftpTestException {
        if (channelSftp == null || session == null || !session.isConnected() || !channelSftp.isConnected()) {
            throw new SftpTestException(CLOSED_CONNECTION);
        }
        
        try {
            log.debug("Uploading file to server");
            channelSftp.put(sourceFile, destinationFile);
            log.info("File uploaded successfully.");
        } catch (SftpException e) {
            throw new SftpTestException(e);
        }
    }

    /**
     * Retrieves a file from the sftp server
     * @param destinationFile String path to the remote file on the server
     * @param sourceFile String path on the local fileSystem
     * @throws SftpTestException if connection and channel are not available or if an error occurs during download.
     */
    public void retrieveFile(String sourceFile, String destinationFile) throws SftpTestException {
        if (channelSftp == null || session == null || !session.isConnected() || !channelSftp.isConnected()) {
            throw new SftpTestException(CLOSED_CONNECTION);
        }

        try {
            log.debug("Downloading file to server");
            channelSftp.get(sourceFile, destinationFile);
            log.info("File downloaded successfully.");
        } catch (SftpException e) {
            throw new SftpTestException(e.getMessage(), e);
        }
    }

    /**
     * Removes a file from the sftp server
     * @param file String path to the remote file on the server
     * @throws SftpTestException if connection and channel are not available or if an error occurs during removal.
     */
    public void deleteFile(String file) throws SftpTestException {
        if (channelSftp == null || session == null || !session.isConnected() || !channelSftp.isConnected()) {
            throw new SftpTestException(CLOSED_CONNECTION);
        }

        try {
            log.debug("Downloading file to server");
            channelSftp.rm(file);
            log.info("File removed from the sftp location.");
        } catch (SftpException e) {
            throw new SftpTestException(e.getMessage(), e);
        }
    }
    
    public void disconnect() {
        if (channelSftp != null) {
            log.debug("Disconnecting sftp channel");
            channelSftp.disconnect();
        }
        if (channel != null) {
            log.debug("Disconnecting channel");
            channel.disconnect();
        }
        if (session != null) {
            log.debug("Disconnecting session");
            session.disconnect();
        }
    }
}
