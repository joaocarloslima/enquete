package br.com.fiap.enquete.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"poll_id", "nickname"}))
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Poll poll;

    @ManyToOne
    private PollOption option;

    private String nickname;

    public Vote(Poll poll, PollOption option, String nickname) {
        this.poll = poll;
        this.option = option;
        this.nickname = nickname;
    }
}
