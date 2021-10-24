package com.sv.supportPortal.service;

import com.sv.supportPortal.domain.User;
import com.sv.supportPortal.exception.domain.EmailExistException;
import com.sv.supportPortal.exception.domain.EmailNotFoundException;
import com.sv.supportPortal.exception.domain.NotAnImageFileException;
import com.sv.supportPortal.exception.domain.UsernameExistException;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface UserService {

    User register(String firstName, String LastName, String username, String email) throws UsernameExistException, EmailExistException, MessagingException;

    List<User> getusers();

    User findUserByUsername(String username);

    User findUserByEmail(String email);

    User addNewUser(String firstName, String lastName, String username,String email, String role , boolean isNotLocked, boolean isActive, MultipartFile profileImage) throws UsernameExistException, EmailExistException, IOException, NotAnImageFileException;

    User updateUser(String currentUserName ,String newfirstName, String newLastName, String newUsername,String email, String role , boolean isNotLocked, boolean isActive, MultipartFile profileImage) throws UsernameExistException, EmailExistException, IOException, NotAnImageFileException;

    void deleteUser(String username) throws IOException;

    void resetPassword(String email) throws EmailNotFoundException, MessagingException;

    User updateProfileImage(String username, MultipartFile profileImage) throws UsernameExistException, EmailExistException, IOException, NotAnImageFileException;
}
