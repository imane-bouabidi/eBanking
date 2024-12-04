package com.wora.eBanking.repositories;

import com.wora.eBanking.entites.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}

