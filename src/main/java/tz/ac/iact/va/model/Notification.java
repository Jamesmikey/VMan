package tz.ac.iact.va.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "notifications")
@TypeAlias("notification")
public class Notification {

    @Id
    private String id;

    private String name;

    @DocumentReference(lazy = true)
    private Ward ward;

    private String sex;

    private LocalDate dob;

    private LocalDate dod;

    @DocumentReference(lazy = true)
    private User reportedBy;

    @DocumentReference(lazy = true)
    private User assignedTo;

    @DocumentReference(lazy = true)
    private User assignedBy;


    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Version
    private Integer version;

}
