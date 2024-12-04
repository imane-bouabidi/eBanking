package com.wora.eBanking.repositories;

import com.wora.eBanking.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}

