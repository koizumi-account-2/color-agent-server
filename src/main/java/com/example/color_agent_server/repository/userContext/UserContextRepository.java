package com.example.color_agent_server.repository.userContext;

import com.example.color_agent_server.repository.user.UserRecord;
import org.apache.ibatis.annotations.*;

import java.util.Optional;

@Mapper
public interface UserContextRepository {
    @Select("SELECT * FROM t_user_context WHERE id=#{id}")
    Optional<UserContextRecord> findById(Long id);

    @Insert("""
INSERT INTO t_user_context (id,user_context,total_tokens) VALUES(#{userContext.id},#{userContext.userContext},#{userContext.totalTokens})""")
    void create(@Param("userContext") UserContextRecord userContext);

    @Update("""
            <script>
                UPDATE t_user_context
                <set>
                    <if test="userContext.userContext != null">
                        user_context=#{userContext.userContext},
                    </if>
                    <if test="userContext.totalTokens != null">
                        total_tokens=#{userContext.totalTokens},
                    </if>
                </set>
                WHERE id = #{userContext.id}
            </script>
            """
            )
    void update(@Param("userContext") UserContextRecord userContext);
}