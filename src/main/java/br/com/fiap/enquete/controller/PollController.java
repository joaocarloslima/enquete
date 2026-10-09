package br.com.fiap.enquete.controller;

import br.com.fiap.enquete.model.Poll;
import br.com.fiap.enquete.model.Vote;
import br.com.fiap.enquete.repository.PollOptionRepository;
import br.com.fiap.enquete.repository.PollRepository;
import br.com.fiap.enquete.repository.VoteRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/polls")
public class PollController {

    private final PollRepository pollRepository;
    private final VoteRepository voteRepository;
    private final PollOptionRepository pollOptionRepository;

    public PollController(PollRepository pollRepository, VoteRepository voteRepository, PollOptionRepository pollOptionRepository) {
        this.pollRepository = pollRepository;
        this.voteRepository = voteRepository;
        this.pollOptionRepository = pollOptionRepository;
    }

    public record CreatePollRequest(
            @NotBlank String question,
            @NotNull @Size(min = 2, max = 10) List<String> options
    ){}

    public record VoteRequest(
            @NotNull Long optionId,
            @NotBlank String nickname
    ){}

    public record Result(
            String option,
            Long votes
    ){}

    //GET
    @GetMapping
    public List<Poll> getAllPolls(){
        return pollRepository.findAll();
    }

    //POST
    @PostMapping
    public Poll createPoll(@RequestBody @Valid CreatePollRequest request){
        var poll = new Poll(request.question);
        request.options().forEach(option -> poll.addOption(option));
        return pollRepository.save(poll);
    }

    //GET By ID
    @GetMapping("{pollId}")
    public Poll getPollById(@PathVariable Long pollId){
        return pollRepository.findById(pollId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Enquete não encontrada")
        );
    }

    @PostMapping("{pollId}/votes")
    public Vote createVote(@PathVariable Long pollId,  @RequestBody @Valid VoteRequest request){
        var poll = pollRepository.findById(pollId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Enquete não encontrada")
        );

        var option = pollOptionRepository.findById(request.optionId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Opção não encontrada")
        );
        return voteRepository.save(new Vote(poll, option, request.nickname));

    }

    @GetMapping("{pollId}/results")
    public List<Result> result(@PathVariable Long pollId){
        var poll = pollRepository.findById(pollId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Enquete não encontrada")
        );

        return poll.getOptions()
                .stream()
                .map(option -> new Result(option.getLabel(), voteRepository.countByOptionId(option.getId())))
                .toList();
    }

}
