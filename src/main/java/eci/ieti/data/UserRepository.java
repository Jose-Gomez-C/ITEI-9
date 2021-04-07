package eci.ieti.data;

import org.springframework.data.repository.CrudRepository;

import eci.ieti.data.model.User;

public interface UserRepository   extends CrudRepository<User, Integer> {

}
