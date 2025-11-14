package Controller;

import model.Monument;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.MonumentService;

import java.util.List;

@RestController
@RequestMapping("/monument")
public class MonumentController {

    private final MonumentService service;

    public MonumentController(MonumentService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Monument>> getAll(){
        List<Monument> list = service.getAll();
        if(list.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Monument> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<Monument> create(@RequestBody Monument monument) {
        if (monument.getMonumentName() == null || monument.getCountryCode() == null) {
            return ResponseEntity.badRequest().build();
        }
        Monument saved = service.create(monument);
        return ResponseEntity.created(URI.create("/monument/" + saved.getId())).body(saved);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Monument> update(@PathVariable Long id, @RequestBody Monument data) {
        if (data.getCountryCode() == null || data.getMonumentName() == null) {
            return ResponseEntity.badRequest().build();
        }
        return service.update(id, data)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.getById(id).isEmpty()) return ResponseEntity.notFound().build();
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
