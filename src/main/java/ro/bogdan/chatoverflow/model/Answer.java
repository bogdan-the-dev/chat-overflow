package ro.bogdan.chatoverflow.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "answer", schema = "chatoverflow")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "answer_author_id")
    private User author;

    @Column(name = "body")
    private String answerBody;

    @Column(name = "date")
    private Date date;

    @Column(name = "edited")
    private boolean edited;

    @Column(name = "score")
    private int score;
}


