package group09.InventoryService.Controllers;

import group09.InventoryService.Entities.Part;
import group09.InventoryService.Entities.Product;
import group09.InventoryService.Services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    List<Product> getProducts(){
        return productService.getProducts();
    }

    @GetMapping(path = "{id}")
    List<Product> getProduct(@PathVariable("id") Long id)
    {
        return productService.getProduct(id);
    }

    @PostMapping
    Product createProduct(@RequestBody Product product)
    {
        return productService.createProduct(product);
    }

    @PutMapping(path = "{id}")
    public Optional<Product> updateProduct(@RequestBody Product newProduct, @PathVariable("id") Long id)
    {
        return productService.updateProduct(newProduct, id);
    }

    @DeleteMapping(path = "{id}")
    public void deleteProduct(@PathVariable("id") Long id)
    {
        productService.deleteProduct(id);
    }

    @PutMapping(path = "{productId}/part/{partId}")
    public void AddPartToProduct(@PathVariable("productId") Long productId,
                                 @PathVariable("partId") Long partId)
    {
        productService.addPart(productId, partId);
    }

    //Look up all parts by product
    @GetMapping(path = "{id}/part")
    List<Part> getParts(@PathVariable("id") Long id)
    {
        return productService.getParts(id);
    }

}
