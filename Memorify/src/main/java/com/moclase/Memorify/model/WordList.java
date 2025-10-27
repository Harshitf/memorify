package com.moclase.Memorify.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class WordList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String word;
}
