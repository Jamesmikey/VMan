package tz.ac.iact.va.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import tz.ac.iact.va.enums.InterviewStatus;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "interviews")
@TypeAlias("interview")
public class Interview {

  @Id
  private String id;

  @DocumentReference(lazy = true)
  private Assignment assignment;

  @DocumentReference(lazy = true)
  private Notification notification;

  @ReadOnlyProperty
  @DocumentReference(lookup="{'interview':?#{#self._id} }",lazy = true)
  private VAReport vaReport;

  private InterviewStatus status;

  @CreatedDate
  private LocalDateTime createdAt;

  @LastModifiedDate
  private LocalDateTime updatedAt;

  @Version
  private Integer version;

}