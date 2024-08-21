package tz.ac.iact.va.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import tz.ac.iact.va.exception.DataNotFoundException;
import tz.ac.iact.va.model.Notification;
import tz.ac.iact.va.model.User;
import tz.ac.iact.va.repository.NotificationRepository;
import tz.ac.iact.va.repository.UserRepository;

@Service
public class NotificationService {

    private final NotificationRepository repository;

    private final UserRepository userRepository;

    public NotificationService(NotificationRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }


    public Notification create(Notification notification){
        //Save the new notification to the database
        User user=userRepository.findByEmail(getCurrentPrincipal().getUsername()).orElseThrow(() -> new DataNotFoundException("User not found"));

        notification.setReportedBy(user);

        //Search person to assign this notification to

        return repository.save(notification);
    }


    public UserDetails getCurrentPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }
}
