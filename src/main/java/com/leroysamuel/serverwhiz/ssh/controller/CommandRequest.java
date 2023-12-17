package com.leroysamuel.serverwhiz.ssh.controller;

public class CommandRequest {
    private String sessionId;
    private String command;

    // Constructors, getters, and setters

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
