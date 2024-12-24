package com.example.demo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepo extends JpaRepository<AdminTable, Long> {

//	 boolean existsByEmail(String email);
//	  
//	 boolean existsByMobile(String mobile);
//	 AdminTable findByEmailAndPassword(String email, String password);
//
//	AdminTable findByEmail(String email);

	Optional<AdminTable> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByMobile(String mobile);

}
