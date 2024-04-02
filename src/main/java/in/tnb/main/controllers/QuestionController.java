package in.tnb.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.tnb.main.entity.Question;
import in.tnb.main.service.QuestionService;

@RestController
@RequestMapping("/question")
public class QuestionController {

	
	@Autowired
    QuestionService questionService;
	
	
	@GetMapping("/")
	public String openIndex()
	{
		return "Welcome";
	}
	
	@PostMapping("/create")
	public ResponseEntity<Question> createQuestion(@RequestBody Question question)
	{
		return questionService.createQuestion(question);
	}
	
	@GetMapping("/find")
	public ResponseEntity<List<Question>> findAllQuestion()
	{
		return questionService.FindAllQuestion();
	}
	
	@PutMapping("/delete/{id}")
	public ResponseEntity<String> deleteQuestion(@PathVariable int id)
	{
		return questionService.deleteQuestion(id);
    }
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateQuestion(@RequestBody Question question,@PathVariable int id)
	{
		return questionService.updateQuestion(question, id);
	}
	
	@GetMapping("find/{category}")
	public ResponseEntity<List<Question>> findQuestionByCategory(@PathVariable String category)
	{
		return questionService.findQuestionByCategory(category);
	}
	
}
