package group09.InventoryService.Controllers;

import group09.InventoryService.Entities.Part;
import group09.InventoryService.Services.PartService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@ResponseBody
@RequestMapping(path = "/part")
public class PartController {

    private final PartService partService;

    public PartController(PartService partService) {
        this.partService = partService;
    }


    @GetMapping
    List<Part> getParts(){
        return partService.getParts();
    }

    @GetMapping(path = "{id}")
    List<Part> getPart(@PathVariable("id") Long id)
    {
        return partService.getPart(id);
    }

    @PostMapping
    Part createPart(@RequestBody Part part)
    {
        return partService.createPart(part);
    }

    @PutMapping(path = "{id}")
    public Optional<Part> updatePart(@RequestBody Part newPart, @PathVariable("id") Long id)
    {
        return partService.updatePart(newPart, id);
    }

    @DeleteMapping(path = "{id}")
    public void deletePart(@PathVariable("id") Long id)
    {
        partService.deletePart(id);
    }

    @PutMapping("/addSupplierToPart/{supplierId}/{partId}")
    public void addSupplierToPart(@PathVariable("supplierId") Long supplierId, @PathVariable("partId") Long partId)//(@RequestParam(value="partId") Long partId, @RequestParam(value="supplierId") Long supplierId)
    {
        partService.addSupplierToPart(partId, supplierId);
    }
    @GetMapping(path = "lookUpSupplierByPart/{id}")
    public void lookUpSupplierByPart(@PathVariable("id") Long partId)
    {
        partService.lookUpSupplierByPart(partId);
    }

}