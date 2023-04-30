package mk.ukim.finki.wp.emt.service.Impl;

import mk.ukim.finki.wp.emt.model.Author;
import mk.ukim.finki.wp.emt.model.Country;
import mk.ukim.finki.wp.emt.model.dto.AuthorDto;
import mk.ukim.finki.wp.emt.model.exceptions.InvalidAuthorIdException;
import mk.ukim.finki.wp.emt.model.exceptions.InvalidCountryIdException;
import mk.ukim.finki.wp.emt.repository.AuthorRepository;
import mk.ukim.finki.wp.emt.repository.CountryRepository;
import mk.ukim.finki.wp.emt.service.AuthorService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Author> listAllAuthors() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);

    }

    @Override
    @Transactional
    public Optional<Author> create(String name, String surname, Long countryId) {
        Country country = this.countryRepository.findById(countryId)
                .orElseThrow(()->new InvalidCountryIdException(countryId));
        Author author = new Author(name, surname,country);
        return Optional.of(this.authorRepository.save(author));
    }

    @Override
    @Transactional
    public Optional<Author> update(Long id, String name, String surname, Long countryId) {
        Author author = this.findById(id).orElseThrow(()->new InvalidAuthorIdException(id));

        Country country = this.countryRepository.findById(countryId).orElseThrow(()->new InvalidCountryIdException(countryId));
        author.setName(name);
        author.setSurname(surname);
        author.setCountry(country);
        return Optional.of(this.authorRepository.save(author));
    }

    @Override
    public void delete(Long id) {
        Author author = this.findById(id).orElseThrow(()->new InvalidAuthorIdException(id));
        this.authorRepository.delete(author);

    }

    @Override
    public Optional<Author> save(AuthorDto authorDto) {
        Author authorToBeAdded = new Author();
        Country country = this.countryRepository.findById(authorDto.getCountryId())
                .orElseThrow(()-> new InvalidCountryIdException(authorDto.getCountryId()));

        authorToBeAdded.setName(authorDto.getName());
        authorToBeAdded.setSurname(authorDto.getSurname());
        authorToBeAdded.setCountry(country);

        return Optional.of(this.authorRepository.save(authorToBeAdded));
    }

    @Override
    public Optional<Author> edit(Long id, AuthorDto authorDto) {
        Author author = this.authorRepository.findById(id).orElseThrow(()->new InvalidAuthorIdException(id));
        Country country = this.countryRepository.findById(authorDto.getCountryId())
                .orElseThrow(()-> new InvalidCountryIdException(authorDto.getCountryId()));

        author.setCountry(country);
        author.setName(authorDto.getName());
        author.setSurname(authorDto.getSurname());

        return Optional.of(this.authorRepository.save(author));
    }
}
