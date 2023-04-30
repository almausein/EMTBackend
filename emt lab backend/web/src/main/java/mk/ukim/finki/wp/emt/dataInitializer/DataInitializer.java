package mk.ukim.finki.wp.emt.dataInitializer;

import mk.ukim.finki.wp.emt.model.Author;
import mk.ukim.finki.wp.emt.model.Book;
import mk.ukim.finki.wp.emt.model.Category;
import mk.ukim.finki.wp.emt.model.Country;
import mk.ukim.finki.wp.emt.repository.AuthorRepository;
import mk.ukim.finki.wp.emt.repository.BookRepository;
import mk.ukim.finki.wp.emt.repository.CountryRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {
    private final BookRepository booksRepository;
    private final AuthorRepository authorsRepository;
    private final CountryRepository countryRepository;

    public DataInitializer(BookRepository booksRepository, AuthorRepository authorsRepository, CountryRepository countryRepository) {
        this.booksRepository = booksRepository;
        this.authorsRepository = authorsRepository;
        this.countryRepository = countryRepository;
    }

    @PostConstruct
    public void initData() {
        Country country1 = new Country("North Macedonia","Europe");
        Country country2 = new Country("USA","North America");
        Country country3 = new Country("Japan","Asia");
        Country country4 = new Country("Ethiopia","Africa");
        countryRepository.save(country1);
        countryRepository.save(country2);
        countryRepository.save(country3);
        countryRepository.save(country4);
        Author author1 = new Author("Alma","Usein",country1);
        Author author2 = new Author("Simona","Tonevska",country2);
        Author author3 = new Author("Ping","Pang",country3);
        Author author4 = new Author("Abel","Tesfaye",country4);
        authorsRepository.save(author1);
        authorsRepository.save(author2);
        authorsRepository.save(author3);
        authorsRepository.save(author4);
        booksRepository.save(new Book("Zlostorstvo i kazna",Category.CLASSICS, author1, 10));
        booksRepository.save(new Book("Starecot i moreto",Category.FANTASY, author2, 15));
        booksRepository.save(new Book("Evgenij Onegin",Category.DRAMA, author3, 12));
        booksRepository.save(new Book("NekojasiKniga",Category.HISTORY, author4, 3));

    }
}
