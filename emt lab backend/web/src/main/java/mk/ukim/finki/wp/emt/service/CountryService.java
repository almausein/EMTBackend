package mk.ukim.finki.wp.emt.service;

import mk.ukim.finki.wp.emt.model.Country;
import mk.ukim.finki.wp.emt.model.dto.CountryDto;


import java.util.List;
import java.util.Optional;

public interface CountryService {

    Optional<Country> findById(Long id);


    List<Country> listAll();


     Optional<Country> create(String name, String continent);

     Optional<Country> save(CountryDto countryDto);
     Optional<Country> edit(Long id, CountryDto countryDto);
     void delete(Long id);
}
