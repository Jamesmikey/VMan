package tz.ac.iact.va.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import tz.ac.iact.va.model.Notification;

public interface NotificationRepository extends MongoRepository<Notification, String> {


}