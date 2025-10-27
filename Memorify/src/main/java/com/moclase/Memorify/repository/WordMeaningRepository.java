package com.moclase.Memorify.repository;

import com.moclase.Memorify.model.WordMeaning;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordMeaningRepository extends JpaRepository<WordMeaning,Integer> {
     WordMeaning findByWord(String word);
}
