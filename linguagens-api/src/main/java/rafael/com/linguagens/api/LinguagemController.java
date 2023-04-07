package rafael.com.linguagens.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LinguagemController {

    @Autowired
    private LinguagemRepository repositorio;

    @GetMapping("/linguagens")
    public List<Linguagem> getLinguagens() {
        return repositorio.findAll();
    }

    @PostMapping("/linguagens")
    public Linguagem postLinguagem(@RequestBody Linguagem linguagem) {
        return repositorio.save(linguagem);
    }
}
