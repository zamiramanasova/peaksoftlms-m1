package kg.peaksoft.peaksoftlmsm1.db.entity.testEntity;

import kg.peaksoft.peaksoftlmsm1.db.entity.User;
import kg.peaksoft.peaksoftlmsm1.db.enums.AccessTest;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "results")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "result_gen")
    @SequenceGenerator(name = "result_gen", sequenceName = "result_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private double correctAnswers;
    private double incorrectAnswers;
    private String percentOfResult;
    private LocalDateTime time;
    @Enumerated(EnumType.STRING)
    private AccessTest accessTest;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

}
