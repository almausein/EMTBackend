package mk.ukim.finki.wp.emt.api;

import mk.ukim.finki.wp.emt.model.Category;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/categories")
public class CategoryController {
    @GetMapping
    public List<Category> listAll(){
        return Arrays.asList(Category.values());

    }
}
