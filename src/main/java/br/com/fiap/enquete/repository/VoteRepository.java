package br.com.fiap.enquete.repository;

import br.com.fiap.enquete.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    Long countByOptionId(Long optionId); //Query Method
}
