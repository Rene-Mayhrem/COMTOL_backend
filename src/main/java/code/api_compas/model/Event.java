package code.api_compas.model;

import java.security.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "events")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {
    @Id
    private String id;
    private String title;
    private String description;
    @CreatedDate
    private Date created_at;
    @LastModifiedDate
    private Date last_modified_date;    
    private Date date_event;
    private String start_time;
    private String end_time;
    private List<Tag> tags;
    private String location;
    private List<User> interested_attendes;
    private List<User> confirmed_attendes;
    private User created_by;
}
