package group09.SalesService.Services;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import group09.SalesService.Entities.Store;
import group09.SalesService.Repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;


import java.sql.SQLOutput;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@JsonIgnoreProperties(ignoreUnknown = true)

public class StoreService {
    private final StoreRepository storeRepository;

    @Autowired
    private final RestTemplate restTemplate;

    public StoreService(StoreRepository storeRepository, RestTemplate restTemplate) {//, RestTemplate restTemplate) {
        this.storeRepository = storeRepository;
        this.restTemplate = restTemplate;
    }

    public List<Store> getStores() {
        return storeRepository.findAll();
    }

    public List<Store> getStore(Long id) {
        return storeRepository.findAllById(Collections.singleton(id));
    }

    public Store createStore(Store store) {
        return storeRepository.save(store);
    }

    public Optional<Store> updateStore(Store newStore, Long id) {
        return storeRepository.findById(id)
                .map(store -> {
                    store.setAddress(newStore.getAddress());
                    store.setName(newStore.getName());
                    store.setManager(newStore.getManager()); //new addition
                    store.setSales(newStore.getSales());
                    return storeRepository.save(store);
                });
    }

    public void deleteStore(Long id) {
        storeRepository.deleteById(id);
    }
}
