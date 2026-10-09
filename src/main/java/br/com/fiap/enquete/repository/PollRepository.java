package br.com.fiap.enquete.repository;

import br.com.fiap.enquete.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollRepository extends JpaRepository<Poll, Long> {

}
