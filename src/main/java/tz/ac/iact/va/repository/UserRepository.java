package tz.ac.iact.va.repository;




import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import tz.ac.iact.va.model.User;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {


  @Query("{'$or' :[{'firstName':  {'$regex':?0,'$options' : 'i'}},{'secondName':  {'$regex':?1,'$options' : 'i'}},{'lastName':  {'$regex':?2,'$options' : 'i'}},{'email':  {'$regex':?3,'$options' : 'i'}}]}")
  Page<User> findAllByNameContaining(String firstName, String secondName, String lastName, String email, Pageable pageable);

  @Query("{'$and':[{'$or' :[{'firstName':  {'$regex':?0,'$options' : 'i'}},{'secondName':  {'$regex':?1,'$options' : 'i'}},{'lastName':  {'$regex':?2,'$options' : 'i'}},{'email':  {'$regex':?4,'$options' : 'i'}}]},{'username': {'$ne': ?3}}]}")
  Page<User> findAllByNameAndUsernameNot(String firstName,String secondName,String lastName,String username,String email, Pageable pageable);

  Optional<User> findByEmail(String email);

  Optional<User> findByEmailOrPhoneNumber(String email,String phoneNumber);

}