package mk.ukim.finki.wp.emt.service.Impl;

import mk.ukim.finki.wp.emt.model.Country;
import mk.ukim.finki.wp.emt.model.dto.CountryDto;
import mk.ukim.finki.wp.emt.model.exceptions.InvalidCountryIdException;
import mk.ukim.finki.wp.emt.repository.CountryRepository;
import mk.ukim.finki.wp.emt.service.CountryService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Optional<Country> findById(Long id) {
        return this.countryRepository.findById(id);

    }

    @Override
    public List<Country> listAll() {
        return this.countryRepository.findAll();

    }

    @Override
    @Transactional
    public Optional<Country> create(String name, String continent) {
        Country country = new Country(name, continent);
        return Optional.of(this.countryRepository.save(country));
    }

    @Override
    public Optional<Country> save(CountryDto countryDto) {
        Country country = new Country();
        country.setName(countryDto.getName());
        country.setContinent(countryDto.getContinent());
        return Optional.of(this.countryRepository.save(country));
    }

    @Override
    @Transactional
    public Optional<Country> edit(Long id, CountryDto countryDto) {
        Country country = this.countryRepository.findById(id).orElseThrow(()->new InvalidCountryIdException(id));
        country.setName(countryDto.getName());
        country.setContinent(countryDto.getContinent());
        return Optional.of(this.countryRepository.save(country));
    }

    @Override
    public void delete(Long id) {
        Country country  = this.countryRepository.findById(id).orElseThrow(()->new InvalidCountryIdException(id));
        this.countryRepository.delete(country);
    }
}
