package com.example.color_agent_server.repository.user;
import java.sql.Timestamp;

public record UserRecord(
        Long id,
        String email,
        String password,
        Timestamp createdAt,
        Timestamp updatedAt
) {

}