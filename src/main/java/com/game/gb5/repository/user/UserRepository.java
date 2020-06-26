package com.game.gb5.repository.user;

import com.game.gb5.model.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    public User getUserById(Long id);

    public Optional<User> findByCode(String code);
}
