package com.leanPlateform.entity;

import jakarta.persistence.*;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role; // "Client" or "Consultant"

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Session> clientSessions;

    @OneToMany(mappedBy = "consultant", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Session> consultantSessions;

    @OneToMany(mappedBy = "consultant", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Review> consultantReviews;

    
    public User() {
    }

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getter and Setter methods

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Session> getClientSessions() {
        return clientSessions;
    }

    public void setClientSessions(Set<Session> clientSessions) {
        this.clientSessions = clientSessions;
    }

    public Set<Session> getConsultantSessions() {
        return consultantSessions;
    }

    public void setConsultantSessions(Set<Session> consultantSessions) {
        this.consultantSessions = consultantSessions;
    }

    public Set<Review> getConsultantReviews() {
        return consultantReviews;
    }

    public void setConsultantReviews(Set<Review> consultantReviews) {
        this.consultantReviews = consultantReviews;
    }
}
