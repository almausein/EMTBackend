package mk.ukim.finki.wp.emt.api;

import mk.ukim.finki.wp.emt.model.Author;
import mk.ukim.finki.wp.emt.model.dto.AuthorDto;
import mk.ukim.finki.wp.emt.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/authors")

public class AuthorController {
    private final AuthorService authorService;


    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/list")
    public List<Author> listAuthors(){
        return this.authorService.listAllAuthors();
    }
    //read
    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id){
        return this.authorService.findById(id)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(()->ResponseEntity.notFound().build());
    }
    //create
    @PostMapping("/add")
    public ResponseEntity<Author> saveAuthor(@RequestBody AuthorDto authorDto){
        return this.authorService.save(authorDto)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(()->ResponseEntity.badRequest().build());

    }
    //update
    @PutMapping("/edit/{id}")
    public ResponseEntity<Author> editAuthor(@PathVariable Long id, @RequestBody AuthorDto authorDto) {
        return this.authorService.edit(id,authorDto)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(()->ResponseEntity.badRequest().build());

    }
    //delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteAuthor(@PathVariable Long id){
        this.authorService.delete(id);
        if(this.authorService.findById(id).isEmpty())
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().build();
    }



}
