package kg.peaksoft.peaksoftlmsm1.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kg.peaksoft.peaksoftlmsm1.db.entity.FilePath;
import kg.peaksoft.peaksoftlmsm1.db.entity.Lesson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "task")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_gen")
    @SequenceGenerator(name = "task_gen",sequenceName = "task_seq",allocationSize = 1)
    private Long id;
    private String name;
    private String text;
    private String link;
    private String image;
    private String code;

    @JsonIgnore
    @OneToOne(mappedBy = "task")
    private Lesson lesson;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "file_id")
    private FilePath file;
}