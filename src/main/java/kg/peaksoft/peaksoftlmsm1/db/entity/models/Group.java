package kg.peaksoft.peaksoftlmsm1.db.entity.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "groups")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_gen")
    @SequenceGenerator(name = "group_gen",sequenceName = "group_seq",allocationSize = 1)
    private Long id;
    @Column(name = "group_name")
    private String groupName;
    @Column(name = "start_date")
    private Date startDate;
    private String description;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

}