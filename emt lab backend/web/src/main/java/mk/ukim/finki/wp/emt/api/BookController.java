package mk.ukim.finki.wp.emt.api;

import mk.ukim.finki.wp.emt.model.Book;
import mk.ukim.finki.wp.emt.model.dto.BookDto;
import mk.ukim.finki.wp.emt.service.BookService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/list")
    public List<Book> listAllBooks(){
        return this.bookService.listAllBooks();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id){
        return this.bookService.findById(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(()->ResponseEntity.notFound().build());
    }



    //Create
    @PostMapping("/add")
    public ResponseEntity<Book> saveBook(@RequestBody BookDto bookDto){
        return this.bookService.save(bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> editBook(@PathVariable Long id, @RequestBody BookDto bookDto){
       return this.bookService.edit(id, bookDto)
               .map(book -> ResponseEntity.ok().body(book))
               .orElseGet(()->ResponseEntity.badRequest().build());
    }
    //mozno da ne rabote, plus sekade deka proveruvam za null a frlam exception, treba da se provere
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBook(@PathVariable Long id){
        this.bookService.delete(id);
        if(this.bookService.findById(id).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();


    }

    //markAsTaken
    @PutMapping ("/take/{id}")
    public ResponseEntity<Book> markAsTaken(@PathVariable Long id){

        Optional<Book> book = this.bookService.findById(id);
        if(!book.isEmpty()){
            Integer availableCopies = book.get().getAvailableCopies();
            this.bookService.markAsTaken(id);
            if(availableCopies==book.get().getAvailableCopies())
                return ResponseEntity.badRequest().build();
            else
                return ResponseEntity.ok().build();
        }
        else return ResponseEntity.notFound().build();
    }
    @GetMapping("/pagination")
    public List<Book> listAllBooksWithPagination(Pageable pageable){
        return this.bookService.findAllWithPagination(pageable).getContent();
    }
}
