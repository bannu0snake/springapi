package com.example.springapi.services;

import com.example.springapi.models.Note;
import com.example.springapi.models.User;
import com.example.springapi.repositories.NoteRepository;
import com.example.springapi.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;
    private final EntityManager entityManager;

    public Note getNoteById(String id) {
        return noteRepository.findById(id).orElse(null);
    }
    public List<Note> getNotesByUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Session session = entityManager.unwrap(Session.class);
        User user =  session.bySimpleNaturalId(User.class)
                .loadOptional(username).orElse(null);
        if (user == null) return new ArrayList<Note>();

        return noteRepository.findByCreatedBy(user.getId());

    }

    public void deleteNote(String id){
        noteRepository.deleteById(id);
    }
    public Note addNote(Note note) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Session session = entityManager.unwrap(Session.class);
       User user =  session.bySimpleNaturalId(User.class)
                .loadOptional(username).orElse(null);
        if(user == null){
            return null;
        }
        note.setUser(user);

        return noteRepository.save(note);
    }

    public Note updateNote(Note note) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Session session = entityManager.unwrap(Session.class);
       User user =  session.bySimpleNaturalId(User.class)
                .loadOptional(username).orElse(null);
        if(user == null){
            return null;
        }

        return noteRepository.save(note);
    }

}
