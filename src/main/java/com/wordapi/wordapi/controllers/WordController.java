package com.wordapi.wordapi.controllers;

import com.wordapi.wordapi.model.WordContainer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class WordController {
    private List<WordContainer> words = new ArrayList<>();
    private AtomicLong nextId = new AtomicLong();

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/words/add")
    public WordContainer createNewWord(@RequestBody WordContainer word) {
        word.setId(nextId.incrementAndGet());
        words.add(word);
        return word;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/words")
    public List<WordContainer> getAllWords() {
        return words;
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/words/delete/{id}")
    public WordContainer deleteWord(@PathVariable("id") long wordId) {
        for (WordContainer word : words) {
            if (word.getId() == wordId) {
                words.remove(word);
                return word;
            }
        }
        throw new IllegalArgumentException();

    }

    @PostMapping("/words/edit/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public WordContainer editWord(@PathVariable("id") long wordId, @RequestBody WordContainer newWord) {
        for (WordContainer word : words) {
            if (word.getId() == wordId) {
                words.remove(word);
                newWord.setId(wordId);
                words.add(newWord);
                return word;
            }
        }
        throw new IllegalArgumentException();

    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/words/find")
    public String findWord(@RequestBody WordContainer wordId) {
        int i = 0;
        for (WordContainer word : words) {
            if (word.getWord().equals(wordId.getWord())) {
                i++;

           }
        }
        return "found it "+i+" times. Word you were searching for: "+wordId.getWord();

    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/words/palindrome")
    public List<String> findPolindrome(){
        List<String> palindromeList = new ArrayList<>();
        for (WordContainer word : words){
            if (word.getWord() == null || word.getWord().isEmpty()){
                palindromeList.add("You didnt enter a single letter :| ");
                return palindromeList;
            }
           if (new StringBuilder(word.getWord()).reverse().toString().equals(word.getWord())){
               palindromeList.add(word.getWord());
           }


        }
        return palindromeList;

    }

    //Create Exception handler

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Request ID not found.")
    @ExceptionHandler(IllegalArgumentException.class)
    public void badIdExceptionHandler() {

    }


}


