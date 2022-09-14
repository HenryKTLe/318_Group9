package group09.InventoryService.Services;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import group09.InventoryService.Entities.Part;
import group09.InventoryService.Repositories.PartRepository;
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
public class PartService {

    private final PartRepository partRepository;

    @Autowired
    private final RestTemplate restTemplate;

    public PartService(PartRepository partRepository, RestTemplate restTemplate) {//, RestTemplate restTemplate) {
        this.partRepository = partRepository;
        this.restTemplate = restTemplate;
    }

    public List<Part> getParts() {
        return partRepository.findAll();
    }

    public List<Part> getPart(Long id) {
        return partRepository.findAllById(Collections.singleton(id));
    }

    public Part createPart(Part part) {
        return partRepository.save(part);
    }

    public Optional<Part> updatePart(Part newPart, Long id) {
        return partRepository.findById(id)
                .map(part -> {
                    part.setPartName(newPart.getPartName());
                    part.setDescription(newPart.getDescription());
                    part.setSupplierId(newPart.getSupplierId()); //new addition
                    return partRepository.save(part);
                });
    }

    public void deletePart(Long id) {
        partRepository.deleteById(id);
    }

    //add supplier to part for USE CASE 6.
    public void addSupplierToPart(@PathVariable Long partId, @PathVariable Long supplierId) {
        //create an instance of RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> request = new HttpEntity<>(headers);
        //make an HTTP GET request to check if there is a supplier with given ID in the Procurement Microservice
        ResponseEntity<Boolean> validateSupplier = restTemplate.exchange("http://localhost:8081/suppliers/suppliersValidate/" + supplierId.toString(),
                HttpMethod.GET, request, Boolean.class);
        if (validateSupplier.getBody()) {
            System.out.println("Supplier with ID " + supplierId + " is VALID");
            Part part = partRepository.findById(partId).get();
            part.setSupplierId(supplierId);
            partRepository.save(part);
            System.out.println("Supplier with ID " + supplierId + " has been successfully added to part with ID " + part.getPartId());

//            Supplier supplier = restTemplate.getForObject("http://localhost:8081/suppliers/" + supplierId.toString(), Supplier.class);
//            System.out.println("SUPPLIER ID = " + supplier.getSupplierId());
//            supplierRepository.save(supplier);
//            supplierRepository.findBySupplierId(supplierId).get();
//            Part part = partRepository.findById(partId).get();
//            part.setSupplier(supplier);
//            partRepository.save(part);
        } else {
            throw new IllegalStateException("Supplier with ID " + supplierId + " does not exist");
        }
    }

    public Part lookUpSupplierByPart(@PathVariable Long supplierId) {
        Part part = partRepository.findById(supplierId).get();
        System.out.println("Supplier with ID " + supplierId + " is related to part with ID " + part.getPartId());
        return part;
    }
}