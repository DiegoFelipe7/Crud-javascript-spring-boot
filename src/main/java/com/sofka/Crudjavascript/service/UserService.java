package com.sofka.Crudjavascript.service;

import com.sofka.Crudjavascript.models.UserModel;
import com.sofka.Crudjavascript.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public ArrayList<UserModel> obtenerUsuarios(){
        return (ArrayList<UserModel>) userRepository.findAll();
    }

    public UserModel guardarUsuario(UserModel usuario){
        return userRepository.save(usuario);
    }

    public Optional<UserModel> obtenerPorId(Long id){
        return userRepository.findById(id);
    }
    public boolean eliminarUsuario(Long id) {
        try{
            userRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }

    public ArrayList<UserModel>  obtenerPorPrioridad(Integer prioridad) {
        return userRepository.findByPrioridad(prioridad);
    }

    public ArrayList<UserModel> obtenerPorEmail(String email){
        return userRepository.findByEmail(email);
    }

    public boolean eliminarEmail(Long id) {
        try{
            userRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }
}
