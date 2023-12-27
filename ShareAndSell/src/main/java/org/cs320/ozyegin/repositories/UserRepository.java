package org.cs320.ozyegin.repositories;

import org.cs320.ozyegin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT user FROM User user WHERE user.email = :email")
	User findByEmail(@Param("email") String email);

	@Query("SELECT user FROM User user ")
	List<User> findAllUsers();
}
