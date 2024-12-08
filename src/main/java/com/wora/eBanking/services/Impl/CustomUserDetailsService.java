package com.wora.eBanking.services.Impl;

import com.wora.eBanking.dtos.role.CreateRoleDTO;
import com.wora.eBanking.entites.Role;
import com.wora.eBanking.entites.User;
import com.wora.eBanking.mappers.RoleMapper;
import com.wora.eBanking.repositories.RoleRepository;
import com.wora.eBanking.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé avec le nom d'utilisateur : " + username));
        Role role = roleRepository.findByName("ROLE_USER")
                .orElseGet(() -> {
                    Role newRole = roleMapper.toEntity(new CreateRoleDTO("ROLE_USER"));
                    return roleRepository.save(newRole);
                });

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                AuthorityUtils.createAuthorityList(role.getName()));
    }
}

