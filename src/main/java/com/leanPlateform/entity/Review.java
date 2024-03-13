package com.leanPlateform.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "consultant_id", nullable = false)
    @JsonIgnore
    private User consultant;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    @JsonIgnore
    private User client;
    
	@ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;
	

    @Column(name = "feedback")
    private String feedback;

    public Review() {
    }

  

    // Getter and Setter methods

    public Long getId() {
        return id;
    }

    public Review(Long id, User consultant, User client, Session session, String feedback) {
		super();
		this.id = id;
		this.consultant = consultant;
		this.client = client;
		this.session = session;
		this.feedback = feedback;
	}

	public void setId(Long id) {
        this.id = id;
    }

    public User getConsultant() {
        return consultant;
    }

    public void setConsultant(User consultant) {
        this.consultant = consultant;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
    
    public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
}
