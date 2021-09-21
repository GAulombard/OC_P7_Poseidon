package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * The interface User repository.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    /**
     * Find user by username optional.
     *
     * @param username the username
     * @return the optional
     */
    Optional<User> findUserByUsername(String username);

    /**
     * Find user by full name optional.
     *
     * @param fullName the full name
     * @return the optional
     */
    Optional<User> findUserByFullName(String fullName);

    /**
     * Exists by username boolean.
     *
     * @param username the username
     * @return the boolean
     */
    boolean existsByUsername(String username);

    /**
     * Exists by full name boolean.
     *
     * @param fullName the full name
     * @return the boolean
     */
    boolean existsByFullName(String fullName);

}
