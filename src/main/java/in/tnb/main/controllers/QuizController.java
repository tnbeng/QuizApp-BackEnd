package in.tnb.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.tnb.main.entity.Quiz;
import in.tnb.main.foruser.QuestionWrapper;
import in.tnb.main.foruser.UserResponse;
import in.tnb.main.service.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController {
	
	@Autowired
	QuizService quizService;
	
	@PostMapping("/create/{category}/{numOfQ}")
	public ResponseEntity<String> createQuiz(@RequestBody Quiz quiz, @PathVariable String category,@PathVariable int numOfQ)
	{
		return quizService.createQuiz(quiz, category, numOfQ);
	}
	
	@GetMapping("/find")
	public ResponseEntity<List<Quiz>> findAllQuiz()
	{
		return quizService.findAllQuiz();
	}
	
	@GetMapping("/find/{quizId}")
	public ResponseEntity<Quiz> findById(@PathVariable int quizId)
	{
		return quizService.findQuizById(quizId);
	}
	
	@GetMapping("/{quizId}/exam")
	public ResponseEntity<List<QuestionWrapper>> giveExam(@PathVariable int quizId)
	{
		return quizService.quizQuestionForUser(quizId);
	}

	@PostMapping("/{quizId}/answer")
	public ResponseEntity<Integer> submitResponse(@PathVariable int quizId, @RequestBody List<UserResponse> userResponses)
	{
		return quizService.calculateResult(quizId, userResponses);
	}
}
