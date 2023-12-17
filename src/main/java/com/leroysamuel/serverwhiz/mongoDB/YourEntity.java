package com.leroysamuel.serverwhiz.mongoDB;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "servers")
public class YourEntity {
    @Id
    private String id;
    public String serverName;
    public int sshPort;
    public String username;
    private String password;
    private String serverIP;

    public void setId(String id) {
        this.id = id;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public void setSshPort(int sshPort) {
        this.sshPort = sshPort;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setServerIP(String serverIP) {
        this.serverIP = serverIP;
    }
}
