package tz.ac.iact.va.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tz.ac.iact.va.exception.DataNotFoundException;
import tz.ac.iact.va.model.User;
import tz.ac.iact.va.repository.UserRepository;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

/**
 * @author James S. Michael
 * @created 20-09-2022 20:42:11
 */
@Service
public class UserService {

    private final UserRepository repository;

    private final UserDetailServiceImp userDetailServiceImp;

    final PasswordEncoder encoder;

    public UserService(UserRepository repository, UserDetailServiceImp userDetailServiceImp, PasswordEncoder encoder) {
        this.repository = repository;
        this.userDetailServiceImp = userDetailServiceImp;
        this.encoder = encoder;
    }


    public Page<User> findAll(String filterText, boolean includeMe, Pageable pageable) throws DataNotFoundException {

        if(includeMe){
            return repository.findAllByNameContaining(filterText,filterText,filterText,filterText,pageable);
        }else{
            return repository.findAllByNameAndUsernameNot(filterText,filterText,filterText, userDetailServiceImp.getCurrentUser().getUsername(),filterText, pageable);

        }

    }


    public boolean existByUsername(String username){
//        return repository.existsByUsername(username);
        return true;
    }


    public User findById(String id){
        User user=repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        return  user;
    }


    public Optional<User> findByUsername(String id){
        return repository.findById(id);
    }



    @Transactional
    public User register(User user) {
//
//        //Check if email already taken
//        if (repository.existsByUsername(user.getEmail())){
//            throw new DuplicateDataException("Username is already in use!");
//        }
//
//        user.setId(user.getEmail());
//
//        user.setUsername(user.getEmail());
//
//        // Create new user's account
//        user.setPassword(encoder.encode(user.getPassword()));
//
//        user.getRoles().add("ROLE_USER");
//
//        //Generate verification code
//        String randomCode = RandomString.make(64);
//        user.setVerificationCode(randomCode);
//        user.setEmailVerified(false);
//
//        User savedUser=repository.save(user);
//
//        //Send verification email
//        String url = properties.getBaseUrl() + "/auth/verify/email/" + user.getVerificationCode();
//        Context context = new Context();
//        context.setVariable("name",user.getFirstName());
//        context.setVariable("url",url);
//        String htmlContent = templateEngine.process("emails/templates/verify",context);
//
//        Email email=new Email();
//        email.setRecipients(new String[]{savedUser.getEmail()});
//        email.setSubject("Verify your email");
//        email.setMsgBody(htmlContent);
//        emailService.send(email,true);

        return null;
    }


    public User save(User user) {

//        //Check if email already taken
//        if (repository.existsByUsername(user.getEmail())){
//            throw new DuplicateDataException("Username is already in use!");
//        }
//
//        user.setId(user.getEmail());
//
//        user.setUsername(user.getEmail());
//
//        // Create new user's account
//        user.setPassword(encoder.encode(user.getPassword()));
//
//        user.getRoles().add("ROLE_USER");
//
//        //Generate verification code
//        String randomCode = RandomString.make(64);
//        user.setVerificationCode(randomCode);
//        user.setEmailVerified(true);
//
//        User savedUser=repository.save(user);
////        User savedUser=user;
//
//
//        return savedUser;

        return null;
    }




    public void requestPasswordReset(String address) throws DataNotFoundException {

//        User user=repository.findByUsername(address).orElseThrow(() ->new  NotFoundException("Email not found"));
//
//        PasswordResetToken passwordResetToken=new PasswordResetToken();
//        passwordResetToken.setUser(user);
//        passwordResetTokenService.create(passwordResetToken);
//
//        //Send it to the user
//        //Send verification email
//        String url = properties.getBaseUrl()+"/auth/update/password?token="+ passwordResetToken.getToken();
//
//        Context context = new Context();
//        context.setVariable("url",url);
//        String htmlContent = templateEngine.process("emails/templates/reset_password",context);
//
//
//        Email email=new Email();
//        email.setRecipients(new String[]{user.getEmail()});
//        email.setSubject("Reset your password");
//        email.setMsgBody(htmlContent);
//        emailService.send(email,true);

    }


    public void delete(String id){
        User userToDelete=repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        repository.delete(userToDelete);
    }






    public Optional<User> findByVerificationCode(String code){
//        return repository.findByVerificationCode(code);
        return null;
    }







    public void update(String username,User userUpdate) throws DataNotFoundException {
//        User user= repository.findByUsername(username).orElseThrow(() -> new DataNotFoundException("User not found"));
//
//        if(user.getRoles().contains("ROLE_SUPER-ADMIN")){
//            throw new UnsupportedOperationException("Not allowed to update this user");
//        }
//
//        user.setFirstName(userUpdate.getFirstName());
//        user.setSecondName(userUpdate.getSecondName());
//        user.setLastName(userUpdate.getLastName());
//        user.setPhoneNumber(userUpdate.getPhoneNumber());
//        user.setEmailVerified(userUpdate.isEmailVerified());

//        repository.save(user);

    }

    public void updatePassword(String username,String oldPassword,String newPassword) throws DataNotFoundException {

//        User user= repository.findByUsername(username).orElseThrow(() -> new NotFoundException("User not found"));
//
//        if(!encoder.matches(oldPassword,user.getPassword())){
//          throw new InvalidDataException("Incorrect old password");
//        }
//
//        user.setPassword(encoder.encode(newPassword));
//
//
//        repository.save(user);

    }

    public void updatePasswordToDefault(String username,String password) throws DataNotFoundException {


//        User user= repository.findByUsername(username).orElseThrow(() -> new NotFoundException("User not found"));
//
//        if(user.getRoles().contains("ROLE_SUPER-ADMIN")){
//            throw new UnsupportedOperationException("Not allowed to update password for this user");
//        }
//
//        user.setPassword(encoder.encode(password));
//
//        repository.save(user);

    }

    public User toggleEmailVerified(String username) throws DataNotFoundException {

//        User user= repository.findByUsername(username).orElseThrow(() -> new NotFoundException("User not found"));
//
//        user.setEmailVerified(!user.isEmailVerified());
//
//        repository.save(user);
//
//        return user;
        return null;

    }

    public User updateRoles(String username, Set<String> roles) throws DataNotFoundException {

//        User user= repository.findByUsername(username).orElseThrow(() -> new NotFoundException("User not found"));
//
//
//        if(!roles.contains("ROLE_USER")){
//            roles.add("ROLE_USER");
//        }
//
//        user.setRoles(roles);
//
//        repository.save(user);
//
//        return user;
        return null;

    }

    public void updatePassword(String password,String token) throws DataNotFoundException {

//        if(!passwordResetTokenService.isValidToken(token)){
//            throw new InvalidDataException("Invalid token");
//        }
//
//        User user=passwordResetTokenService.findByToken(token).getUser();
//
//        user.setPassword(encoder.encode(password));
//
//        repository.save(user);

    }

    public void verifyPasswordResetToken(String token) throws DataNotFoundException {
//        if(!passwordResetTokenService.isValidToken(token)){
//            throw new InvalidDataException("Invalid token");
//        }
    }
}
