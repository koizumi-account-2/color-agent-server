package com.example.color_agent_server.repository.user;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserRepository {
    @Select("SELECT * FROM T_USER WHERE email = #{email}")
    Optional<UserRecord> findByEmail(String email);
}
