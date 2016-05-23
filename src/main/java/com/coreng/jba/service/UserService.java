package com.coreng.jba.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coreng.jba.entities.Role;
import com.coreng.jba.entities.User;
import com.coreng.jba.repositories.RoleRepository;
import com.coreng.jba.repositories.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRep;

	@Autowired
	private RoleRepository roleRep;

	public void save(User user) {
		// Init Role
		user.setRoles(new ArrayList<Role>());

		// Set default Role (USER)
		user.addRole(roleRep.findByName("ROLE_USER"));
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		userRep.save(user);
	}

	public void saveUserWithRole(User user, Role role) {
		user.addRole(role);
		userRep.save(user);
	}

	public List<Role> findAllRoles() {
		return roleRep.findAll();

	}

	public Role findRoleById(Long id) {
		return roleRep.findOne(id);
	}

	public Role findByName(String name) {
		return roleRep.findByName(name);
	}

	public List<User> findAllUsers() {
		return userRep.findAll();
	}

}
