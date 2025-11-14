package service;

import model.Monument;
import org.springframework.stereotype.Service;
import repository.MonumentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MonumentService {
    private final MonumentRepository repository;

    public MonumentService(MonumentRepository repository){
        this.repository = repository;
    }

    public List<Monument> getAll(){
        return repository.findAll();
    }

    public Optional<Monument> getById(Long id){
        return repository.findById(id);
    }

    public Monument create(Monument monument){
        return repository.save(monument);
    }

    public Optional<Monument> update(Long id, Monument newData) {
        return repository.findById(id).map(m -> {
            m.setCountryCode(newData.getCountryCode());
            m.setCountryName(newData.getCountryName());
            m.setCityName(newData.getCityName());
            m.setLatitude(newData.getLatitude());
            m.setLongitude(newData.getLongitude());
            m.setMonumentName(newData.getMonumentName());
            m.setDescription(newData.getDescription());
            m.setPhotoUrl(newData.getPhotoUrl());
            return repository.save(m);
        });
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
