package in.tnb.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import in.tnb.main.dao.QuestionRepo;
import in.tnb.main.entity.Question;

@Service
public class QuestionService {
   
	@Autowired
	QuestionRepo questionRepo;
	
	public ResponseEntity<Question> createQuestion(Question question)
	{
		Question created_question=questionRepo.save(question);
		
		return new ResponseEntity<>(created_question,HttpStatus.CREATED);
	}
	
	public ResponseEntity<List<Question>> FindAllQuestion()
	{
		return new ResponseEntity<>(questionRepo.findAll(),HttpStatus.OK);
	}
	
	@PutMapping("/delete/{id}")
	public ResponseEntity<String> deleteQuestion(int id)
	{
		Optional<Question> exist_question_opt=questionRepo.findById(id);
		if(exist_question_opt.isEmpty())
		{
			return new ResponseEntity<String>("Question deleted with id "+id,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Question does not exist with id "+id,HttpStatus.BAD_REQUEST);
		}
	
	}
	
	public ResponseEntity<String> updateQuestion(Question question,int id)
	{
		Optional<Question> exist_question_opt=questionRepo.findById(id);
		if(exist_question_opt.isEmpty())
		{
			return new ResponseEntity<>("Question does not exist with id "+id, HttpStatus.BAD_REQUEST);
		}
		else
		{
			Question exist_question=exist_question_opt.get();
			
			if(question.getQuestion()!=null)
			{
				exist_question.setQuestion(question.getQuestion());
			}
			if(question.getOption1()!=null)
			{
				exist_question.setOption1(question.getOption1());
			}
			if(question.getOption2()!=null)
			{
				exist_question.setOption2(question.getOption2());
			}
			if(question.getOption3()!=null)
			{
				exist_question.setOption3(question.getOption3());
			}
			if(question.getOption4()!=null)
			{
				exist_question.setOption4(question.getOption4());
			}
			if(question.getAnswer()!=null)
			{
				exist_question.setAnswer(question.getAnswer());
			}
			
			if(question.getCategory()!=null)
			{
				exist_question.setCategory(question.getCategory());
			}
			
			questionRepo.save(exist_question);
			
			return new ResponseEntity<String>("Question updated successfully ",HttpStatus.OK);
			
		}
	}
	
	
	public ResponseEntity<List<Question>> findQuestionByCategory(String category)
	{
		return new ResponseEntity<List<Question>>(questionRepo.findByCategory(category),HttpStatus.OK);
	}
	
}
