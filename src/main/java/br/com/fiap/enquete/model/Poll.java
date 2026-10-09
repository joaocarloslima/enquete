package br.com.fiap.enquete.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter @Setter
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    private boolean open = true;

    @OneToMany(cascade = CascadeType.ALL)
    private List<PollOption> options = new ArrayList<>();

    public Poll(String question) {
        this.question = question;
    }

    public void addOption(String option) {
        options.add(new PollOption(option));
    }
}
