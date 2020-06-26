package com.game.gb5.service.user;

import com.game.gb5.repository.inventory.InventoryRepository;
import com.game.gb5.model.user.User;
import com.game.gb5.repository.user.UserRepository;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
	private final UserRepository userRepository;
	private final InventoryRepository inventoryRepository;

	public UserService(UserRepository userRepository, InventoryRepository inventoryRepository) {
		this.userRepository = userRepository;
		this.inventoryRepository = inventoryRepository;
	}

	public User getById(Long id) {
		return userRepository.getUserById(id);
	}

	public User create(String userName, String userId) {
		User user = new User(userName, userId);
		return userRepository.save(user);
	}

	public User update(User user) {
		return userRepository.save(user);
	}

	public void delete(User user) {
		userRepository.delete(user);
	}

    public Optional<User> getByCode(String code) {
		return userRepository.findByCode(code);
    }
}
