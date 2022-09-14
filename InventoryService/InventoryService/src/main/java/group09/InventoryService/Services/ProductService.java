package group09.InventoryService.Services;

import group09.InventoryService.Entities.Part;
import group09.InventoryService.Entities.Product;
import group09.InventoryService.Repositories.PartRepository;
import group09.InventoryService.Repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final PartRepository partRepository;

    public ProductService(ProductRepository productRepository, PartRepository partRepository) {
        this.productRepository = productRepository;
        this.partRepository = partRepository;
    }

    public List<Product> getProducts(){return productRepository.findAll();}

    public List<Product> getProduct(Long id)
    {
        return productRepository.findAllById(Collections.singleton(id));
    }

    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    public Optional<Product> updateProduct(Product newProduct, Long id)
    {
        return productRepository.findById(id)
                .map(product -> {
                    product.setProductName(newProduct.getProductName());
                    product.setComment(newProduct.getComment());
                    product.setPrice(newProduct.getPrice());
                    return productRepository.save(product);
                });
    }

    public void deleteProduct(Long id)
    {
        productRepository.deleteById(id);
    }

    public void addPart(Long productId, Long partId)
    {
        Part part = partRepository.findById(partId).orElseThrow(RuntimeException::new);
        Product product = productRepository.findById(productId).orElseThrow(RuntimeException::new);
        List<Part> parts = new ArrayList<>();
        if (!product.getParts().isEmpty()) {
            parts.addAll(product.getParts());
        }
        part.setProduct(product);
        parts.add(part);
        product.setParts(parts);
        productRepository.save(product);
    }

    //Look up all parts by product
    public List<Part> getParts(Long id) {
        boolean exists = productRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Product with ID " + id + " does not exist");
        }
        Product product = productRepository.findById(id).orElseThrow(RuntimeException::new);
        return product.getParts();
    }
}
