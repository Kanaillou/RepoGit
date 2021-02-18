package com.paymybuddy.Service;


import com.paymybuddy.Model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.paymybuddy.Repository.UserRepository;

import java.util.ArrayList;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    UserService userService;

    BCryptPasswordEncoder passwordEncoder;
    List<User> users;


    @BeforeEach
    void setUp() {
        users = new ArrayList<>();
        passwordEncoder = new BCryptPasswordEncoder();
        userService = new UserService(userRepository, passwordEncoder);
    }


    @Test
    void addContactTest() {

        User james = new User();

        when(userRepository.findByEmail(anyString())).thenAnswer(parameters -> james);

        User jason = new User("Jason", "Bourne", "jason@gmail.com", "superPassword", new ArrayList<>());
        //on va ajouter james à partir de son email mocké à la liste de contact de jason
        userService.addContact(jason, "james@gmail.com");

        assertThat(jason.getContacts().size()).isEqualTo(0);
    }


    @Test
    void findByEmail() {
        User user = new User("Jason", "Burnes","jason@mail" ,"Passdesténèbres" ,new ArrayList<>() );

        when(userRepository.findByEmail(anyString())).thenAnswer(parameters -> user);

        User userByEmail = userService.findByEmail("jason@mail");

        assertEquals(userByEmail.getEmail(), "jason@mail");
    }
    @Test
    void findById() {
        User user = new User("Jason", "Burnes", "jason@mail", "Passdesténèbres", new ArrayList<>());
        user.setId(1L);

        when(userRepository.getOne(anyLong())).thenAnswer(parameters -> user);

        User userById = userService.findById(1L);

        assertThat(userById.getId().equals(1L));
    }

}
