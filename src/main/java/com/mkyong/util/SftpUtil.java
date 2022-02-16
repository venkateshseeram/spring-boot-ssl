package com.mkyong.util;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.ProxyHTTP;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;

/**
 *
 * @author Dinesh.Lomte
 */
public class SftpUtil {

    /**
     *
     */
    public static void upload(String source,String destination)
            throws Exception {
        Session session = null;
        Channel channel = null;
        ChannelSftp channelSftp = null;
        try {
            // Creating and instantiating the jsch specific instance
            JSch jsch = new JSch();
            // Fetching and setting the parameters like: user name, host and port
            // from the properties file
            session = jsch.getSession("demo",
                    "test.rebex.net",
                    Integer.valueOf("22"));
            // Fetching and setting the password as configured in properties files
            session.setPassword("password");
            // Setting the configuration specific properties
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            // Validating if proxy is enabled to access the sftp
            isSftpProxyEnabled(session);
            // Execution start time
            long lStartTime = new Date().getTime();
            System.out.println("Connecting to the sftp...");
            // Connecting to the sftp
            session.connect();
            System.out.println("Connected to the sftp.");
            // Execution end time
            long lEndTime = new Date().getTime();
            System.out.println("---------------------------------------------");
            System.out.println("Connected to SFTP in : " + (lEndTime - lStartTime));
            // Setting the channel type as sftp
            channel = session.openChannel("sftp");
            // Establishing the connection
            channel.connect();
            channelSftp = (ChannelSftp) channel;
            // Setting the folder location of the external system as configured
            // Creating the file instance
            File file = new File(source);
            // Creating an fileInputStream instance
            FileInputStream fileInputStream = new FileInputStream(file);
            // Transfering the file from it source to destination location via sftp
            channelSftp.put(fileInputStream, destination);
            // Closing the fileInputStream instance
            fileInputStream.close();
            // De-allocating the fileInputStream instance memory by assigning null
            fileInputStream = null;
        } catch (Exception exception) {
            throw exception;
        } finally {
            // Validating if channel sftp is not null to exit
            if (channelSftp != null) {
                channelSftp.exit();
            }
            // Validating if channel is not null to disconnect
            if (channel != null) {
                channel.disconnect();
            }
            // Validating if session instance is not null to disconnect
            if (session != null) {
                session.disconnect();
            }
        }
    }

    /**
     *
     * @param session
     */
    private static void isSftpProxyEnabled(Session session) {
        // Fetching the sftp proxy flag set as part of the properties file
        boolean isSftpProxyEnabled = Boolean.valueOf("<sftp.proxy.enable>");
        // Validating if proxy is enabled to access the sftp
        if (isSftpProxyEnabled) {
            // Setting host and port of the proxy to access the SFTP
            session.setProxy(new ProxyHTTP("<sftp.proxy.host>",
                    Integer.valueOf("<sftp.proxy.port>")));
        }
        System.out.println("Proxy status: " + isSftpProxyEnabled);
    }

    /**
     *
     *
     */
    public static void download(String source, String destination) {

        String method = "download(String folder, String event, String locale)";
        Session session = null;
        Channel channel = null;
        ChannelSftp channelSftp = null;
        try {
            // Creating and instantiating the jsch specific instance
            JSch jsch = new JSch();
            // Fetching and setting the parameters like: user name, host and port
            // from the properties file
            session = jsch.getSession("demo",
                    "test.rebex.net",
                    Integer.valueOf("22"));
            // Fetching and setting the password as configured in properties files
            session.setPassword("password");
            // Setting the configuration specific properties
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            // Validating if proxy is enabled to access the sftp
            isSftpProxyEnabled(session);
            // Execution start time
            long lStartTime = new Date().getTime();
            System.out.println("Connecting to the sftp...");
            // Connecting to the sftp
            session.connect();
            System.out.println("Connected to the sftp.");
            // Execution end time
            long lEndTime = new Date().getTime();
            System.out.println("---------------------------------------------");
            System.out.println("Connected to SFTP in : " + (lEndTime - lStartTime));
            // Setting the channel type as sftp
            channel = session.openChannel("sftp");
            // Establishing the connection
            channel.connect();
            channelSftp = (ChannelSftp) channel;

            channelSftp.get(source, destination);

        } catch (Exception exception) {
            System.out.println("Failed to download the file(s) from SFTP.");
        } finally {
            // Validating if channel sftp is not null to exit
            if (channelSftp != null) {
                channelSftp.exit();
            }
            // Validating if channel is not null to disconnect
            if (channel != null) {
                channel.disconnect();
            }
            // Validating if session instance is not null to disconnect
            if (session != null) {
                session.disconnect();
            }
        }
    }
}
