package group09.SalesService.Controllers;

import group09.SalesService.Entities.Sale;
import group09.SalesService.Services.SalesService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
@RequestMapping(path = "/sale")
public class SaleController {
    private final SalesService salesService;

    public SaleController(SalesService salesService) {
        this.salesService = salesService;
    }


    @GetMapping
    List<Sale> getSales(){
        return salesService.getSales();
    }

    @GetMapping(path = "{id}")
    List<Sale> getSale(@PathVariable("id") Long id)
    {
        return salesService.getSale(id);
    }

    @PostMapping
    Sale createSale(@RequestBody Sale sale)
    {
        return salesService.createSale(sale);
    }

    @PutMapping(path = "{id}")
    public Optional<Sale> updateSale(@RequestBody Sale newSale, @PathVariable("id") Long id)
    {
        return salesService.updateSale(newSale, id);
    }

    @DeleteMapping(path = "{id}")
    public void deleteSale(@PathVariable("id") Long id)
    {
        salesService.deleteSale(id);
    }
}
