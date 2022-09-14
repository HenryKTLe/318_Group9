package group09.ProcurementService.Services;

import group09.ProcurementService.Entities.Contact;
import group09.ProcurementService.Entities.Supplier;
import group09.ProcurementService.Repositories.ContactRepository;
import group09.ProcurementService.Repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.*;

@Service //or @Component, but @Service is for more semantics and readability
public class SupplierService {
    private final SupplierRepository supplierRepository;
    private final ContactRepository contactRepository;
    //private final PartRepository partRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository, ContactRepository contactRepository, RestTemplate restTemplate){//}, PartRepository partRepository) {
        this.supplierRepository = supplierRepository;
        this.contactRepository = contactRepository;
        //this.partRepository = partRepository;
        this.restTemplate = restTemplate;
    }

    public List<Supplier> getSuppliers()
    {
        return supplierRepository.findAll();
    }

    public List<Supplier> getSupplier(Long supplierId)
    {
        return supplierRepository.findAllById(Collections.singleton(supplierId));
    }

    public void addNewSupplier(Supplier supplier) {
        Optional<Supplier> supplierOptional = supplierRepository.findSupplierBySupplierName(supplier.getSupplierName());
        if(supplierOptional.isPresent())
        {
            throw new IllegalStateException("Supplier Name Taken.");
        }
        supplierRepository.save(supplier);
    }

    public void deleteSupplier(Long supplierId) {
        //supplierRepository.findById(supplierId);
        boolean exists = supplierRepository.existsById(supplierId);
        if (!exists)
        {
            throw new IllegalStateException("Supplier with ID " + supplierId + " does not exist.");
        }
        supplierRepository.deleteById(supplierId);
    }

    @Transactional
    public void updateSupplier(Long supplierId, String supplierName, String supplierBase) {
        Supplier supplier = supplierRepository.findBySupplierId(supplierId).orElseThrow(() -> new IllegalStateException("Supplier with id " +
                supplierId + " does not exist."));
        if(supplierName != null && supplierName.length() > 0 && !Objects.equals(supplier.getSupplierName(), supplierName))
        {
            supplier.setSupplierName(supplierName);
        }
        if(supplierBase != null && supplierBase.length() > 0 && !Objects.equals(supplier.getSupplierBase(), supplierBase))
        {
            supplier.setSupplierBase(supplierBase);
        }
    }
    //new method-----------------------------------------------------------------------------------------------------------------------------
    public void addContact(Long supplierId, Long contactId)
    {
        Contact contact = contactRepository.findById(contactId).orElseThrow(RuntimeException::new);
        Supplier supplier = supplierRepository.findBySupplierId(supplierId).orElseThrow(RuntimeException::new);
        List<Contact> con = new ArrayList<>();
        if (!supplier.getContacts().isEmpty()) {
            con.addAll(supplier.getContacts());
        }
        con.add(contact);
        supplier.setContacts(con);
        supplierRepository.save(supplier);
    }

    public boolean validateID(@PathVariable Long id){
        //checks if supplier id exists
        boolean idValid = supplierRepository.existsById(id);
        if(idValid){
            Optional<Supplier> supplier = supplierRepository.findById(id);
            System.out.println("Supplier Name: " + supplier.get().getSupplierName());// + "\n" + "CUSTOMER CONTACTS");
//            for(int i = 0; i < customer.get().getContacts().size(); i++){
//                System.out.println(customer.get().getContacts().get(i).getPhone());
//            }
            return true;
        } else {
            System.out.println("Customer " + id + " does not exist");
            return false;
        }

    }

//    public void lookUpSupplierByPart()
//    {
//        Supplier lookUpSupplierByPart = restTemplate.getForObject(
//                "http:localhost:8080/part/{partId}",
//                Supplier.class,
//
//
//
//                )
//    }

    //Look up Supplier by Part
//    public Supplier lookUpSupplierByPart(Long partId)
//    {
//        return partRepository.findById(partId).get().getSupplier();
//    }
}
//    @GetMapping("/findCustomerByOrder/{orderId}")
//    public Customer findCustomerByOrder(@PathVariable Long orderId){
//        return orderRepository.findById(orderId).get().getCustomer();
//    }
