package mk.ukim.finki.wp.emt.service.Impl;

import mk.ukim.finki.wp.emt.model.Author;
import mk.ukim.finki.wp.emt.model.Book;
import mk.ukim.finki.wp.emt.model.Category;
import mk.ukim.finki.wp.emt.model.dto.BookDto;
import mk.ukim.finki.wp.emt.model.exceptions.InvalidAuthorIdException;
import mk.ukim.finki.wp.emt.model.exceptions.InvalidBookIdException;
import mk.ukim.finki.wp.emt.model.exceptions.NoMoreCopiesException;
import mk.ukim.finki.wp.emt.repository.AuthorRepository;
import mk.ukim.finki.wp.emt.repository.BookRepository;
import mk.ukim.finki.wp.emt.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> listAllBooks() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);

    }

    @Override
    @Transactional
    public Optional<Book> create(String name, Category category, Long authorId, Integer availableCopies) {
        Author author = this.authorRepository.findById(authorId)
                .orElseThrow(()->new InvalidAuthorIdException(authorId));
        Book book = new Book(name, category, author,availableCopies);
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    @Transactional
    public Optional<Book> update(Long id, String name, Category category, Long authorId, Integer availableCopies) {
        Book book = this.bookRepository.findById(id).orElseThrow(()->new InvalidBookIdException(id));
        Author author = this.authorRepository.findById(authorId).orElseThrow(()->new InvalidAuthorIdException(authorId));

        book.setAuthor(author);
        book.setName(name);
        book.setCategory(category);
        book.setAvailableCopies(availableCopies);
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public void delete(Long id) {
        Book book = this.bookRepository.findById(id).orElseThrow(()->new InvalidBookIdException(id));
        this.bookRepository.delete(book);
    }

    @Override
    public void markAsTaken(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(()-> new InvalidBookIdException(id));

        Integer availableCopies = book.getAvailableCopies()-1;
        if(availableCopies<0)
            throw new NoMoreCopiesException(id);
        book.setAvailableCopies(availableCopies);
        this.bookRepository.save(book);
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        Book bookToBeAdded = new Book();

        Author author = this.authorRepository.findById(bookDto.getAuthorId())
                .orElseThrow(()->new InvalidAuthorIdException(bookDto.getAuthorId()));

        bookToBeAdded.setCategory(bookDto.getCategory());
        bookToBeAdded.setAuthor(author);
        bookToBeAdded.setName(bookDto.getName());
        bookToBeAdded.setAvailableCopies(bookDto.getAvailableCopies());
        return Optional.of(this.bookRepository.save(bookToBeAdded));
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {

        Book book = this.bookRepository.findById(id).orElseThrow(()->new InvalidBookIdException(id));
        Author author = this.authorRepository.findById(bookDto.getAuthorId())
                .orElseThrow(()->new InvalidAuthorIdException(bookDto.getAuthorId()));

        book.setCategory(bookDto.getCategory());
        book.setAuthor(author);
        book.setName(bookDto.getName());
        book.setAvailableCopies(bookDto.getAvailableCopies());
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Page<Book> findAllWithPagination(org.springframework.data.domain.Pageable pageable) {
        return this.bookRepository.findAll(pageable);
    }



}
