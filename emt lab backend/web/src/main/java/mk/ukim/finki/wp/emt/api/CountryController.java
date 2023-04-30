package mk.ukim.finki.wp.emt.api;

import mk.ukim.finki.wp.emt.model.Country;
import mk.ukim.finki.wp.emt.model.dto.CountryDto;
import mk.ukim.finki.wp.emt.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/countries")
public class CountryController {
    private final CountryService countryService;


    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/list")
    public List<Country> listCountries(){
       return this.countryService.listAll();
    }
    //read
    @GetMapping("/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable Long id){
        return this.countryService.findById(id)
                .map(country -> ResponseEntity.ok().body(country))
                .orElseGet(()->ResponseEntity.notFound().build());
    }
    //create
    @PostMapping("/add")
    public ResponseEntity<Country> saveCountry(@RequestBody CountryDto countryDto){
        return this.countryService.save(countryDto).map(country -> ResponseEntity.ok().body(country))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }
    //update
    @PutMapping("/edit/{id}")
    public ResponseEntity<Country> editCountry(@PathVariable Long id, @RequestBody CountryDto authorDto) {
        return this.countryService.edit(id,authorDto)
                .map(country -> ResponseEntity.ok().body(country))
                .orElseGet(()->ResponseEntity.badRequest().build());

    }
    //delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCountry(@PathVariable Long id){
        this.countryService.delete(id);
        if(this.countryService.findById(id).isEmpty())
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().build();
    }

}
