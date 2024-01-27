package com.gcu.model;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.gcu.security.CustomUserDetails;

@Entity
@Table(name = "users")
public class MyUser {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    // constructor
    public MyUser() {
    }
    
    // constructor
    public MyUser(String username, String password, Set<Role> roles) {
        this.setUsername(username);
        this.setPassword(password);
        this.setRoles(roles);
    }
    
    public UserDetails toUserDetails() {
    	return CustomUserDetails.build(this);
    }

    // getters and setters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Set<Role> getRoles() {
        return roles;
    }
	
	public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
	
}
