package tz.ac.iact.va.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "interviewers")
@TypeAlias("interviewer")
public class Interviewer {
  @Id
  private String id;

  @DocumentReference(lazy = true)
  private Ward ward;

  @DocumentReference(lazy = true)
  private User user;

  @DocumentReference(lazy = true, lookup = "{ 'interviewer' : ?#{#self._id} }")
  @ReadOnlyProperty
  List<Interview> interviews;

  @DocumentReference(lazy = true)
  private User createdBy;

  @CreatedDate
  private LocalDateTime createdAt;

  @LastModifiedDate
  private LocalDateTime updatedAt;

  @Version
  private Integer version;

}