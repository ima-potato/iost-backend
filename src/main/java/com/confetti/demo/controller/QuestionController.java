package com.confetti.demo.controller;

import com.confetti.demo.controller.dto.AnswerDTO;
import com.confetti.demo.controller.dto.ChoiceDTO;
import com.confetti.demo.controller.dto.QuestionDTO;
import com.confetti.demo.model.Choice;
import com.confetti.demo.model.Question;
import com.confetti.demo.repository.ChoiceRepository;
import com.confetti.demo.repository.QuestionRepository;
import com.confetti.demo.service.IOSTService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/quizzes/")
public class QuestionController extends BaseAPIController {

    private QuestionRepository questionRepository;
    private ChoiceRepository choiceRepository;
    private IOSTService iostService;

    public QuestionController(QuestionRepository questionRepository,
                              ChoiceRepository choiceRepository,
                              IOSTService iostService) {
        this.questionRepository = questionRepository;
        this.choiceRepository = choiceRepository;
        this.iostService = iostService;
    }

    @GetMapping("{id}/questions")
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

    @GetMapping("{quizId}/questions/{questionNumber}")
    public ResponseEntity<Integer> getAnswer(@PathVariable("quizId") Long quizId,
                                             @PathVariable("questionNumber") Long questionNumber) {
        Question question = questionRepository.findAllByQuiz_IdAndQuestionNumber(quizId, questionNumber.intValue());
        List<Choice> correctChoiceList = choiceRepository.findAllByQuestion_IdAndCorrectIsTrue(question.getId());
        Choice correctChoice = correctChoiceList.get(0);
        return ResponseEntity.ok(correctChoice.getId().intValue());
    }

    @PostMapping("{quizId}/questions/{questionNumber}")
    public ResponseEntity<Void> answerQuestion(@PathVariable("quizId") Long quizId,
                                               @PathVariable("questionNumber") Long questionNumber,
                                               @RequestBody AnswerDTO answerDTO) {
        Question question = questionRepository.findAllByQuiz_IdAndQuestionNumber(quizId, questionNumber.intValue());
        List<Choice> correctChoiceList = choiceRepository.findAllByQuestion_IdAndCorrectIsTrue(question.getId());
        Choice correctChoice = correctChoiceList.get(0);

        if (answerDTO.getChoiceId() == correctChoice.getId().intValue()) {
            iostService.recordCorrectAnswer(question, answerDTO.getAccountName());
        }

        return ResponseEntity.ok(null);
    }

}
