package mk.ukim.finki.wp.emt.service;

import mk.ukim.finki.wp.emt.model.Book;
import mk.ukim.finki.wp.emt.model.Category;
import mk.ukim.finki.wp.emt.model.dto.BookDto;
import org.springframework.data.domain.Page;


import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> listAllBooks();


    Optional<Book> findById(Long id);


    Optional<Book> create(String name, Category category, Long authorId,Integer availableCopies);


    Optional<Book> update(Long id,String name, Category category, Long authorId,Integer availableCopies);


    void delete(Long id);
    void markAsTaken(Long id);

    Optional<Book> save(BookDto bookDto);
    Optional<Book> edit(Long id, BookDto bookDto);
    Page<Book> findAllWithPagination(org.springframework.data.domain.Pageable pageable);

}
