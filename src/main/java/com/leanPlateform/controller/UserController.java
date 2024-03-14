package com.leanPlateform.controller;

import com.leanPlateform.entity.Review;
import com.leanPlateform.entity.Session;
import com.leanPlateform.entity.User;
import com.leanPlateform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User registeredUser = userService.saveUser(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String username, @RequestParam String password) {
        if (userService.doesUserExist(username)) {
            if (userService.isValidPassword(username, password)) {
                return new ResponseEntity<>("Login successful", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/searchConsultants")
    public ResponseEntity<List<User>> searchConsultants() {
        List<User> consultants = userService.getConsultants();
        return new ResponseEntity<>(consultants, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        User existingUser = userService.getUserById(id);
        if (existingUser != null) {
            updatedUser.setId(id);
            User savedUser = userService.saveUser(updatedUser);
            return new ResponseEntity<>(savedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        if (userService.getUserById(id) != null) {
            userService.deleteUser(id);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }
    

    @PostMapping("/bookSession")
    public ResponseEntity<String> bookSession(@RequestParam Long clientId,
                                              @RequestParam Long consultantId,
                                              @RequestParam LocalDateTime dateTime) {
        userService.bookSession(clientId, consultantId, dateTime);
        return ResponseEntity.ok("Session booked successfully");
    }

    @GetMapping("/sessionDetails/client/{clientId}")
    public ResponseEntity<List<Session>> getClientSessionHistory(@PathVariable Long clientId) {
        List<Session> sessions = userService.getClientSessionHistory(clientId);
        return ResponseEntity.ok(sessions);
    }

    @GetMapping("/sessionDetails/consultant/{consultantId}")
    public ResponseEntity<List<Session>> getConsultantUpcomingSessions(@PathVariable Long consultantId) {
        List<Session> sessions = userService.getConsultantUpcomingSessions(consultantId);
        return ResponseEntity.ok(sessions);
    }

    @PostMapping("/preSessionActivities")
    public ResponseEntity<String> selectDocumentsForSession(@RequestParam Long consultantId,
                                                            @RequestParam List<String> documentList) {
        userService.selectDocumentsForSession(consultantId, documentList);
        return ResponseEntity.ok("Documents selected for session");
    }

    @GetMapping("/preSessionActivities/client/{clientId}")
    public ResponseEntity<List<String>> getDocumentsForSession(@PathVariable Long clientId) {
        List<String> documentList = userService.getDocumentsForSession(clientId);
        return ResponseEntity.ok(documentList);
    }

    @PostMapping("/uploadDocuments")
    public ResponseEntity<String> uploadDocuments(@RequestParam Long clientId,
                                                  @RequestParam List<String> documents) {
        userService.uploadDocuments(clientId, documents);
        return ResponseEntity.ok("Documents uploaded successfully");
    }

    @GetMapping("/uploadDocuments/consultant/{consultantId}")
    public ResponseEntity<List<String>> getUploadedDocuments(@PathVariable Long consultantId) {
        List<String> documents = userService.getUploadedDocuments(consultantId);
        return ResponseEntity.ok(documents);
    }

    @PostMapping("/uploadFeedback")
    public ResponseEntity<String> uploadFeedback(@RequestParam Long consultantId,
                                                 @RequestParam Long clientId,
                                                 @RequestParam String feedback,
                                                 @RequestParam Long sessionId) {
        userService.uploadFeedback(consultantId, clientId, feedback, sessionId);
        return ResponseEntity.ok("Feedback uploaded successfully");
    }

    @GetMapping("/uploadFeedback/client/{clientId}")
    public ResponseEntity<List<Review>> getClientFeedback(@PathVariable Long clientId) {
        List<Review> feedbackList = userService.getClientFeedback(clientId);
        return ResponseEntity.ok(feedbackList);
    }
}
