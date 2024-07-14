package com.cognizant.loanapplication.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.loanapplication.entities.Users;


@Repository
public interface UserRepository extends CrudRepository<Users,String>{

}
