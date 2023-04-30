package mk.ukim.finki.wp.emt.service;

import mk.ukim.finki.wp.emt.model.Author;
import mk.ukim.finki.wp.emt.model.dto.AuthorDto;


import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> listAllAuthors();


    Optional<Author> findById(Long id);


    Optional<Author> create(String name, String surname,Long countryId);


    Optional<Author> update(Long id, String name, String surname, Long countryId);


    void delete(Long id);

    Optional<Author> save(AuthorDto authorDto);
    Optional<Author> edit(Long id, AuthorDto authorDto);

}
