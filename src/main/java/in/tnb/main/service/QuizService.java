package in.tnb.main.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import in.tnb.main.dao.QuestionRepo;
import in.tnb.main.dao.QuizRepo;
import in.tnb.main.entity.Question;
import in.tnb.main.entity.Quiz;
import in.tnb.main.foruser.QuestionWrapper;
import in.tnb.main.foruser.UserResponse;

@Service
public class QuizService {
	
	@Autowired
	QuizRepo quizRepo;
	
	@Autowired
	QuestionRepo questionRepo;
	
	public ResponseEntity<String> createQuiz(Quiz quiz,String category,int numOfQ)
	{
	   List<Question> questions=questionRepo.findRandomQuestionsByCatagory(category, numOfQ);
	   
	   quiz.setQuesions(questions);
	   
	   quizRepo.save(quiz);
	   
	   return new ResponseEntity<String>("Quiz created successfully ",HttpStatus.CREATED);
	}
	
	public ResponseEntity<List<Quiz>> findAllQuiz()
	{
		return new ResponseEntity<List<Quiz>>(quizRepo.findAll(),HttpStatus.OK);
	}
	
	public ResponseEntity<Quiz> findQuizById(int quizId)
	{
	
	    if(quizRepo.findById(quizId).isEmpty())
	    {
	    	return new ResponseEntity<Quiz>(HttpStatus.BAD_REQUEST);
	    }
	    else
	    {
	    	return new ResponseEntity<Quiz>(quizRepo.findById(quizId).get(),HttpStatus.OK);
	    }
	}
	
	public ResponseEntity<List<QuestionWrapper>> quizQuestionForUser(int quizId)
	{
		Quiz quiz=quizRepo.findById(quizId).get();
		List<Question> questions=quiz.getQuesions();
		
		List<QuestionWrapper> qrs=new ArrayList<>();
		for(Question question:questions)
		{
			QuestionWrapper qw=new QuestionWrapper();
			
			qw.setId(question.getId());
			qw.setQuestion(question.getQuestion());
			qw.setOption1(question.getOption1());
			qw.setOption2(question.getOption2());
			qw.setOption3(question.getOption3());
			qw.setOption4(question.getOption4());
			
			qrs.add(qw);
		}
		
		return new ResponseEntity<List<QuestionWrapper>>(qrs,HttpStatus.OK);
	}
	
	public ResponseEntity<Integer> calculateResult(int quizId, List<UserResponse> userResponses)
	{
		Quiz quiz=quizRepo.findById(quizId).get();
		
		List<Question> questions=quiz.getQuesions();
		
		int i=0;
		int right=0;
		for(Question question: questions)
		{
		    if(question.getAnswer().equals(userResponses.get(i).getResponse()))
		    {
		    	right++;
		    }
		    i++;
		}
		return new ResponseEntity<Integer>(right,HttpStatus.OK);
	}
}
