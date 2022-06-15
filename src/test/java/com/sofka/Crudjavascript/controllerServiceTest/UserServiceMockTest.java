package com.sofka.Crudjavascript.controllerServiceTest;

import com.sofka.Crudjavascript.repository.UserRepository;
import com.sofka.Crudjavascript.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceMockTest {

    @MockBean
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Test
    public void testUsuarioMock(){
        when(userRepository.findAll()).thenReturn(new ArrayList<>());
        assertThat(userService.obtenerUsuarios()).isEmpty();
        verify(userRepository).findAll();
    }
}
