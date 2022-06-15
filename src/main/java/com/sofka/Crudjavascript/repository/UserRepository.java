package com.sofka.Crudjavascript.repository;

import com.sofka.Crudjavascript.models.UserModel;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface UserRepository extends CrudRepository<UserModel , Long> {
    ArrayList<UserModel> findByPrioridad(Integer prioridad);
    ArrayList<UserModel> findByEmail(String email);

}
