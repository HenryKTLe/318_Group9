package group09.InventoryService.Repositories;

import group09.InventoryService.Entities.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {
}
