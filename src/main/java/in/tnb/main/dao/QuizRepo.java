package in.tnb.main.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.tnb.main.entity.Quiz;


public interface QuizRepo extends JpaRepository<Quiz, Integer> {
 
}
