package com.leanPlateform.service;

import com.leanPlateform.entity.Review;
import com.leanPlateform.entity.User;
import com.leanPlateform.entity.Session;
import com.leanPlateform.repository.UserRepository;
import com.leanPlateform.repository.ReviewRepository;
import com.leanPlateform.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class UserService {

	@Autowired
    private final UserRepository userRepository;

    @Autowired
    private final SessionRepository sessionRepository;

    @Autowired
    private final ReviewRepository reviewRepository;
    
    public UserService(UserRepository userRepository, SessionRepository sessionRepository, ReviewRepository reviewRepository) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.reviewRepository = reviewRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public boolean doesUserExist(String username) {
        return userRepository.existsByUsername(username);
    }

    public List<User> getConsultants() {
        return userRepository.findByRole("Consultant");
    }

    public boolean isValidPassword(String username, String password) {
        User user = userRepository.findByUsername(username);
        // Need to Implement password validation logic (e.g., using BCryptPasswordEncoder)
        // For simplicity, this example assumes a plain text password
        return user != null && user.getPassword().equals(password);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    
    public void bookSession(Long clientId, Long consultantId, LocalDateTime dateTime) {
        User client = userRepository.findById(clientId).orElse(null);
        User consultant = userRepository.findById(consultantId).orElse(null);

        if (client != null && consultant != null) {
            Session session = new Session();
            session.setClient(client);
            session.setConsultant(consultant);
            session.setDateTime(dateTime);

            sessionRepository.save(session);
        }
    }

    public List<Session> getClientSessionHistory(Long clientId) {
        return sessionRepository.findByClientId(clientId);
    }

    public List<Session> getConsultantUpcomingSessions(Long consultantId) {
        return sessionRepository.findByConsultantIdAndDateTimeAfter(consultantId, LocalDateTime.now());
    }

    public void selectDocumentsForSession(Long consultantId, List<String> documentList) {
        // Need To Implement logic to store selected documents for a session
    }

    public List<String> getDocumentsForSession(Long clientId) {
        // Need To Implement logic to retrieve selected documents for a session
        return Collections.emptyList();
    }

    public void uploadDocuments(Long clientId, List<String> documents) {
        // Need To Implement logic to store uploaded documents
    }

    public List<String> getUploadedDocuments(Long consultantId) {
        // Need To Implement logic to retrieve uploaded documents
        return Collections.emptyList();
    }

    public void uploadFeedback(Long consultantId, Long clientId, String feedback, Long sessionId) {
        User consultant = userRepository.findById(consultantId).orElse(null);
        User client = userRepository.findById(clientId).orElse(null);

        if (consultant != null && client != null) {
            Session session = sessionRepository.findById(sessionId).orElse(null);

            if (session != null) {
                Review review = new Review();
                review.setConsultant(consultant);
                review.setClient(client);
                review.setFeedback(feedback);
                review.setSession(session);

                reviewRepository.save(review);
            } else {
                // Need To Handle the case where the session is not found
                // want to log an error or throw an exception
            }
        }
    
    }


    public List<Review> getClientFeedback(Long clientId) {
        return reviewRepository.findByClientId(clientId);
    }
    
    
}
