package com.example.springapi.repositories;

import com.example.springapi.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, String> {
    List<Note> findByCreatedBy(Integer userId);
    Long countByCreatedBy(Integer userId);
}
