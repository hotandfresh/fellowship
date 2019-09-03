package com.bomibear.fellowship.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
public class ApplicationUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String username;

    private String firstName;
    private String lastName;
    private String password;
    private String bio;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "applicationUser")
    private List<Post> posts;

    @ManyToMany
    @JoinTable(
            name = "follows",
            joinColumns = @JoinColumn(name = "follower_id") ,
            inverseJoinColumns = @JoinColumn(name="followed_id")
    )
    Set<ApplicationUser> following;

    @ManyToMany(mappedBy = "following")
    Set<ApplicationUser> followedBy;

    public ApplicationUser(){}

    public ApplicationUser(String username, String firstName, String lastName, String password, String bio) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.bio = bio;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBio() {
        return bio;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public Set<ApplicationUser> getFollowing() {
        return following;
    }

    public Set<ApplicationUser> getFollowedBy() {
        return followedBy;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
