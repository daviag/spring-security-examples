package com.daviag.security.examples.jwt.service;

import com.daviag.security.examples.jwt.model.SecuredUser;
import com.daviag.security.examples.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsManager implements UserDetailsManager {

    private final UserRepository userRepository;

    @Override
    public void createUser(UserDetails user) {
        userRepository.save((SecuredUser)user);
    }

    @Override
    public void updateUser(UserDetails user) {
        SecuredUser oldSecuredUser = (SecuredUser) user;
        SecuredUser entity = userRepository.findByEmail(user.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("The user does not exist!"));
        entity.setFirstName(oldSecuredUser.getFirstName());
        entity.setLastName(oldSecuredUser.getLastName());
        entity.setRoles(oldSecuredUser.getRoles());
        userRepository.save(entity);
    }

    @Override
    public void deleteUser(String username) {
        SecuredUser entity = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("The user does not exist!"));
        userRepository.delete(entity);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        SecuredUser securedUser = userRepository.findByPassword(oldPassword)
                .orElseThrow(() -> new UsernameNotFoundException("The user does not exist!"));
        securedUser.setPassword(newPassword);
        userRepository.save(securedUser);
    }

    @Override
    public boolean userExists(String username) {
        return userRepository.existsByEmail(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("The user does not exist!"));
    }
}
