package com.jb.loginreg.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jb.loginreg.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	User findByEmail(String email);
	User findByUsername(String username);
	List<User> findAll();
	Optional<User> findById(Long id);
	void deleteById(Long id);
	List<User> findByFirstNameContains(String string);
	boolean existsUserByEmail(String email);
	boolean existsUserByUsername(String username);
}
