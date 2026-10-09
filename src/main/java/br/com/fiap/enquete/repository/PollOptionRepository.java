package br.com.fiap.enquete.repository;


import br.com.fiap.enquete.model.PollOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollOptionRepository extends JpaRepository<PollOption, Long> {
}
