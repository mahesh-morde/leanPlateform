package com.leanPlateform.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sessions")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private User client;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "consultant_id", nullable = false)
    private User consultant;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    public Session() {
    }

    public Session(User client, User consultant, LocalDateTime dateTime) {
        this.client = client;
        this.consultant = consultant;
        this.dateTime = dateTime;
    }

    // Getter and Setter methods

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public User getConsultant() {
        return consultant;
    }

    public void setConsultant(User consultant) {
        this.consultant = consultant;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
