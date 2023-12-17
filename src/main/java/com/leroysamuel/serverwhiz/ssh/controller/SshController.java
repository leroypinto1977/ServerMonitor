package com.leroysamuel.serverwhiz.ssh.controller;

// Example imports for SSH
import com.jcraft.jsch.*;
import com.leroysamuel.serverwhiz.ssh.model.SshRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("/api")
public class SshController {

    private final Map<String, Session> sshSessions;

    public SshController(Map<String, Session> sshSessions) {
        this.sshSessions = sshSessions;
    }


    @PostMapping("/ssh/connect")
    public ResponseEntity<String> connect(@RequestBody SshRequest request) {
        try {
            JSch jsch = new JSch();
            Session sshSession = jsch.getSession(request.getUsername(), request.getServerIP(), request.getPort());

            if (request.getPassword() != null) {
                sshSession.setPassword(request.getPassword());
            } else if (request.getSshKey() != null) {
                jsch.addIdentity(request.getSshKey());
            } else {
                return ResponseEntity.badRequest().body("Either password or SSH key must be provided.");
            }

            sshSession.setConfig("StrictHostKeyChecking", "no");
            sshSession.connect();

            String sessionId = UUID.randomUUID().toString();

            // Store the session with the unique identifier
            sshSessions.put(sessionId, sshSession);

            // Store the session with a unique identifier
            // sshSessions.put(request.getSessionId(), sshSession);

            // ...

            return ResponseEntity.ok("SSH connection successful. Session ID: " + sessionId);
        } catch (JSchException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("SSH connection failed: " + e.getMessage());
        }
    }

    @PostMapping("/metrics/cpu")
    public ResponseEntity<String> executeTopCommand(@RequestBody ExecuteTopRequest executeTopRequest) {
        try {
            // Retrieve the session from the map using the sessionId
            Session sshSession = sshSessions.get(executeTopRequest.getSessionId());

            if (sshSession == null || !sshSession.isConnected()) {
                return ResponseEntity.badRequest().body("Invalid or expired session ID.");
            }

            // Create a channel for command execution
            ChannelExec channelExec = (ChannelExec) sshSession.openChannel("exec");

            // Set the command to be executed (you can customize this for other commands)
//            channelExec.setCommand("top -b -n 5");
            channelExec.setCommand("top -l 5 | grep 'CPU usage'");


            // Connect and execute the command
            channelExec.connect();

            // Read the command output
            InputStream inputStream = channelExec.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder output = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            // Disconnect the channel
            channelExec.disconnect();

            return ResponseEntity.ok("Top command executed successfully.\n \nOutput:\n" + output.toString());
        } catch (JSchException | IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Command execution failed: " + e.getMessage());
        }
    }
}

