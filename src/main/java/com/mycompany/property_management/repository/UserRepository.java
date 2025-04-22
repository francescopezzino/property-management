package com.mycompany.property_management.repository;

import com.mycompany.property_management.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    // Owner and Email should match (caitalized) the attributes in the UserEntity
    //  findBy    OwnerEmail   And    Password - let us create a custom jpa query
    Optional<UserEntity> findByOwnerEmailAndPassword(String email, String password);

    // same as abobe principle
    Optional<UserEntity> findByPhone(String phone);

    Optional<UserEntity> findByOwnerEmail(String email);


}
