package com.example.springapi.controllers;

import com.example.springapi.models.Note;
import com.example.springapi.services.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/note")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("Ping from secured End Point 🔐");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable("id") String id) {
        return new ResponseEntity<>(noteService.getNoteById(id), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Note>> getNotesByUser() {
        return new ResponseEntity<>(noteService.getNotesByUser(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNoteById(@RequestParam String id) {
        noteService.deleteNote(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping("/post")
    public ResponseEntity<Note> addNote(@RequestBody Note note) {
        noteService.addNote(note);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(noteService.addNote(note));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@RequestBody final Note note,
                                           @RequestParam final String id) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(noteService.updateNote(note));
    }
}
