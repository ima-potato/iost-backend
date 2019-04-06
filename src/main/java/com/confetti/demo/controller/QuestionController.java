package com.confetti.demo.controller;

import com.confetti.demo.controller.dto.ChoiceDTO;
import com.confetti.demo.controller.dto.QuestionDTO;
import com.confetti.demo.model.Question;
import com.confetti.demo.repository.QuestionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/quiz/")
public class QuestionController extends BaseAPIController {

    private QuestionRepository questionRepository;

    public QuestionController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @GetMapping("{id}")
    public ResponseEntity<List<QuestionDTO>> getQuestionsByQuizId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(questionRepository.findAllByQuiz_Id(id)
                .stream()
                .map(q -> new QuestionDTO(q.getQuestion(),
                        q.getQuestionNumber(),
                        q.getChoices()
                                .stream()
                                .map(c -> new ChoiceDTO(c.getId(), c.getDescription()))
                                .collect(Collectors.toList())))
                .collect(Collectors.toList())
        );
    }

}
