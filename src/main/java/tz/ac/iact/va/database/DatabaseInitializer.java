package tz.ac.iact.va.database;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import tz.ac.iact.va.model.Role;
import tz.ac.iact.va.model.User;
import tz.ac.iact.va.repository.RoleRepository;
import tz.ac.iact.va.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final Logger LOG=LoggerFactory.getLogger(DatabaseInitializer.class);

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public DatabaseInitializer(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        //Add roles
        if (roleRepository.findById("USER").isEmpty()){
            Role role1 = Role.builder()
                    .id("USER")
                    .name("USER")
                    .build();


            roleRepository.save(role1);
        }


        if (roleRepository.findById("INTERVIEW").isEmpty()) {
            Role role2 = Role.builder()
                    .id("INTERVIEW")
                    .name("INTERVIEW")
                    .build();


            roleRepository.save(role2);
        }

        if (roleRepository.findById("NOTIFIER").isEmpty()) {
            Role role3 = Role.builder()
                    .id("NOTIFIER")
                    .name("NOTIFIER")
                    .build();


            roleRepository.save(role3);
        }

        if (roleRepository.findById("ADMIN").isEmpty()) {
            Role role4 = Role.builder()
                    .id("ADMIN")
                    .name("ADMIN")
                    .build();
            roleRepository.save(role4);
        }

        LOG.info("Roles initialized.");



        // Check if the admin user already exists
        if (!userRepository.findByEmailOrPhoneNumber("jmichael@ihi.or.tz", "0783069047").isPresent()) {
            // Create and save the admin user
            User adminUser = User.builder()
                    .id("jmichael@ihi.or.tz")
                    .email("jmichael@ihi.or.tz")
                    .phoneNumber("0783069047")
                    .roles(Set.of(roleRepository.findById("ADMIN").get()))
                    .password(passwordEncoder.encode("123456789"))
                    .build();
            userRepository.save(adminUser);
            LOG.info("Admin user initialized.");
        }
    }
}
