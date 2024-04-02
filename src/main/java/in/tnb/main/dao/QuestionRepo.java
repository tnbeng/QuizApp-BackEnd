package in.tnb.main.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.tnb.main.entity.Question;


public interface QuestionRepo extends JpaRepository<Question, Integer> {

	List<Question> findByCategory(String category);
	
    @Query("SELECT q FROM Question q WHERE q.category = :category ORDER BY FUNCTION('RAND') LIMIT :numOfQ")//JPQL Query
	//@Query(value = "SELECT * FROM Question q Where q.category=:category ORDER BY RAND() LIMIT :numOfQ", nativeQuery = true) 
    List<Question> findRandomQuestionsByCatagory(String category,int numOfQ);
}
