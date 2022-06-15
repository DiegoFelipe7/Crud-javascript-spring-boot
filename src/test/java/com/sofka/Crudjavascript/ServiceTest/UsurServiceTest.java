package com.sofka.Crudjavascript.ServiceTest;

import com.sofka.Crudjavascript.models.UserModel;
import com.sofka.Crudjavascript.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsurServiceTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void testGuardarUsuario() {
        UserModel userModel = new UserModel("aquaman", "aqua@gmail.com", 99);
        UserModel usuarioModelRegistrado = userRepository.save(userModel);
        assertNotNull(usuarioModelRegistrado);
    }

    @Test
    public void testBuscarUsuarioPorId() {
        Long idBuscado = 22L;
        Optional<UserModel> usuarioModelBuscado = userRepository.findById(idBuscado);
        assertThat(usuarioModelBuscado.get().getId()).isEqualTo(idBuscado);
    }

    @Test
    public void testListarUsuarios() {
        List<UserModel> usuarioModelList = (List<UserModel>) userRepository.findAll();
        assertThat(usuarioModelList).size().isGreaterThan(0);
    }

    @Test
    public void testBuscarEmail() {
        String email = "diego@gmail.com";
        List<UserModel> usuarioModelBuscado = userRepository.findByEmail(email);
        assertThat(usuarioModelBuscado.get(0).getEmail()).isEqualTo(email);
    }

    @Test
    public void eliminarPorCorreo() {
        String email = "diego@gmail.com";
        List<UserModel> usuarioModelBuscado = userRepository.findByEmail(email);
        userRepository.deleteById(usuarioModelBuscado.get(0).getId());
        assertNotNull(usuarioModelBuscado.get(0).getId());

    }
}
