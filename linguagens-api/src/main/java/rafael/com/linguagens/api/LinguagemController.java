package rafael.com.linguagens.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LinguagemController {
    
    @GetMapping(value = "/ola-mundo")
    public String olaMundo() {
        return "Olá, Mundo!";
    }
}
