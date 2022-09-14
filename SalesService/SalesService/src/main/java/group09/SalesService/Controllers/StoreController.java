package group09.SalesService.Controllers;

import group09.SalesService.Entities.Store;
import group09.SalesService.Services.StoreService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
@RequestMapping(path = "/sale")
public class StoreController {
    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }


    @GetMapping
    List<Store> getStores(){
        return storeService.getStores();
    }

    @PostMapping
    Store createStore(@RequestBody Store store) { return storeService.createStore(store); }

    @PutMapping(path = "{id}")
    public Optional<Store> updateStore(@RequestBody Store newStore, @PathVariable("id") Long id)
    {
        return storeService.updateStore(newStore, id);
    }

    @DeleteMapping(path = "{id}")
    public void deleteStore(@PathVariable("id") Long id)
    {
        storeService.deleteStore(id);
    }
}
