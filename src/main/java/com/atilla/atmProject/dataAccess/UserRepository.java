package com.atilla.atmProject.dataAccess;


import com.atilla.atmProject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
