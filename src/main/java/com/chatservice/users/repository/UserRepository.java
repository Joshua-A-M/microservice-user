package com.chatservice.users.repository;

import com.chatservice.users.entity.BasicUserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@NoRepositoryBean
public interface UserRepository<T, id> extends CrudRepository<T, id> {

    public Optional<BasicUserEntity> findByPersonalId(String personalId);

    @Transactional @Modifying
    @Query("Update BasicUserEntity u Set u.username = :newUsername Where username = :username")
    public int changeUsername(@Param("username") String username, @Param("newUsername") String newUsername);

    public int deleteByPersonalId(String personalId);
}
