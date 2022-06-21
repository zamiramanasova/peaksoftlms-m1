package kg.peaksoft.peaksoftlmsm1.quizPackage.quizDb.quizService;

import kg.peaksoft.peaksoftlmsm1.db.entity.User;
import kg.peaksoft.peaksoftlmsm1.db.repository.UserRepository;
import kg.peaksoft.peaksoftlmsm1.exception.ResourceNotFoundException;
import kg.peaksoft.peaksoftlmsm1.quizPackage.QuizRepository.OptionRepository;
import kg.peaksoft.peaksoftlmsm1.quizPackage.QuizRepository.ResultRepository;
import kg.peaksoft.peaksoftlmsm1.quizPackage.QuizRepository.TestRepository;
import kg.peaksoft.peaksoftlmsm1.quizPackage.quizDb.quizMappers.ResultViewMapper;
import kg.peaksoft.peaksoftlmsm1.quizPackage.quizDb.request.AnswerRequest;
import kg.peaksoft.peaksoftlmsm1.quizPackage.quizDb.response.ResultResponse;
import kg.peaksoft.peaksoftlmsm1.quizPackage.quizEntity.Question;
import kg.peaksoft.peaksoftlmsm1.quizPackage.quizEntity.Result;
import kg.peaksoft.peaksoftlmsm1.quizPackage.quizEntity.Test;
import kg.peaksoft.peaksoftlmsm1.quizPackage.quizEnum.AccessTest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static kg.peaksoft.peaksoftlmsm1.quizPackage.quizEnum.EQuestionType.MULTI_TYPE;
import static kg.peaksoft.peaksoftlmsm1.quizPackage.quizEnum.EQuestionType.SINGLE_TYPE;

@Service
@RequiredArgsConstructor
public class ResultService {

    private final ResultViewMapper resultViewMapper;
    private final ResultRepository resultRepository;
    private final OptionRepository optionRepository;
    private final UserRepository userRepository;
    private final TestRepository testRepository;

    public ResultResponse saveResult(AnswerRequest answerRequest, Long studentId) {

        Result result = new Result();

        Test test = testRepository.findById(answerRequest.getTestId()).orElseThrow(() ->
                new ResourceNotFoundException("Entity not found exception"));
        User student = userRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Entity not found with: " + studentId));
        result.setTest(test);
        result.setUser(student);
        result.setTime(LocalDateTime.now());
        result.setAccessTest(AccessTest.TEST_ON);

        int countQuestionsOfTest = test.getQuestions().size();
        double correctAnswers = 0;
        int indexOfQuestionFromStudent = 0;


        for (Question question : test.getQuestions()) {
            correctAnswers += calculateCountCorrectAnswersOfQuestion(question, answerRequest.getQuestionAnswerRequestList()
                    .get(indexOfQuestionFromStudent).getSelectedAnswerId());
            indexOfQuestionFromStudent++;
        }
        result.setCorrectAnswers(correctAnswers);
        result.setIncorrectAnswers(countQuestionsOfTest - correctAnswers);
        result.setPercentOfResult(toStringInPercent(correctAnswers * 100 / countQuestionsOfTest));

        return resultViewMapper.mapToResponse(resultRepository.save(result));

    }

    public List<ResultResponse> getAllResultByTestId(Long testId) {
        Test test = testRepository.findById(testId).orElseThrow();
        return resultViewMapper.map(resultRepository.findAllByTest(test));
    }

    private double calculateCountCorrectAnswersOfQuestion(Question question, List<Long> selectedAnswerId) {

        double correctAnswers = 0;
        if (question.getQuestionType().equals(SINGLE_TYPE)) {
            for (Long aLong : selectedAnswerId) {
                if (optionRepository.findById(aLong).isPresent()) {
                    if (optionRepository.findById(aLong).get().isCorrect()) {
                        correctAnswers++;
                    }
                }
            }
        } else if (question.getQuestionType().equals(MULTI_TYPE)) {
            for (Long aLong : selectedAnswerId) {
                if (optionRepository.findById(aLong).isPresent()) {
                    if (optionRepository.findById(aLong).get().isCorrect()) {
                        correctAnswers += 0.5;
                    }
                }
            }
        }
        return correctAnswers;
    }

    private String toStringInPercent(double number) {
        return (int) number + "%";
    }

}
