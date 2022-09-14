package group09.SalesService.Services;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import group09.SalesService.Entities.Sale;
import group09.SalesService.Repositories.SaleRepository;
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

public class SalesService {
    private final SaleRepository saleRepository;

    @Autowired
    private final RestTemplate restTemplate;

    public SalesService(SaleRepository saleRepository, RestTemplate restTemplate) {//, RestTemplate restTemplate) {
        this.saleRepository = saleRepository;
        this.restTemplate = restTemplate;
    }

    public List<Sale> getSales() {
        return saleRepository.findAll();
    }

    public List<Sale> getSale(Long id) {
        return saleRepository.findAllById(Collections.singleton(id));
    }

    public Sale createSale(Sale sale) {
        return saleRepository.save(sale);
    }

    public Optional<Sale> updateSale(Sale newSale, Long id) {
        return saleRepository.findById(id)
                .map(sale -> {
                    sale.setProductName(newSale.getProductName());
                    sale.setQuantity(newSale.getQuantity());
                    sale.setDateAndTime(newSale.getDateAndTime()); //new addition
                    return saleRepository.save(sale);
                });
    }

    public void deleteSale(Long id) {
        saleRepository.deleteById(id);
    }
}
