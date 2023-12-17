package com.leroysamuel.serverwhiz.ssh.model;

public class SshRequest {
    private String username;
    private String password;
    private String serverIP;
    private int port;

    private String sshKey;
    private String aliasName;
    private String sessionId;


    public String getUsername() {
        return username;
    }

    public String getServerIP() {
        return serverIP;
    }

    public int getPort() {
        return port;
    }

    public String getPassword() {
        return password;
    }

    public String getSshKey() {
        return sshKey;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
