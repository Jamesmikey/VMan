package tz.ac.iact.va.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import tz.ac.iact.va.enums.Sex;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    private Sex sex;

    private LocalDate dob;

    private LocalDate dod;

    @DocumentReference(lazy = true)
    private User reportedBy;

    @ReadOnlyProperty
    @DocumentReference(lookup="{'notification':?#{#self._id} }",lazy = true)
    private Interview interview;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Version
    private Integer version;

}
