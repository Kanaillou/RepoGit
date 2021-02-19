package org.paymybuddy.Service;

import java.util.List;

import org.paymybuddy.Model.User;
import org.paymybuddy.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    //constructeur utilisé pour le test
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //methode pour retourner tous les utilisateurs
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /* méthode booléenne pour savoir si un utilisateur existe ou pas à traver
   son émail  */
    public boolean userExists(String email) {
        return userRepository.existsByEmail(email);
    }

    /*méthode de creation/enregistrement  d'un compte
    exception si l'émail existe
              si nom ou prenom, email ou password nul
     */
    public User create(User user) {
        if (userExists(user.getEmail())) {
            throw new IllegalStateException("Utilisateur deja existant");
        }

        if (user.getFirstName() == null || user.getLastName() == null || user.getEmail() == null
                || user.getPassword() == null) {
            throw new IllegalStateException("veuillez remplir toutes les coordonnées!");
        }

        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));  //encoder le mdp de l'utilisateur
        user.setRole("ROLE_USER"); //securisation
        user.setEnabled(true);

        return userRepository.save(user);
    }


    public List<User> getUserContactsByUserID(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get().getContacts();
        }
        throw new IllegalStateException("Utilisateur non trouve!!");
    }

    //methode d'ajout de contact à partir de son id
    public void addContact(User user, String email) {
        User contact = userRepository.findByEmail(email);
        if (contact == null) {
            user.getContacts().add(contact);
            userRepository.save(user);
            throw new IllegalStateException("contact inexistant");
        }
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findById(Long receiverId) {
        return userRepository.getOne(receiverId);
    }

    public void delete(User user) {
        if (!userExists(user.getEmail())) {
            throw new IllegalStateException("utilisateur introuvable");
        }
        userRepository.delete(user);
    }
}
