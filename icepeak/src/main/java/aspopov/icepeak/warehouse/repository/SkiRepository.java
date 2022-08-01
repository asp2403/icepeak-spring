package aspopov.icepeak.warehouse.repository;

import aspopov.icepeak.warehouse.domain.Ski;
import aspopov.icepeak.warehouse.projection.SkiModelGalleryProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SkiRepository extends JpaRepository<Ski, Long> {

    @Query("select new aspopov.icepeak.warehouse.projection.SkiModelGalleryProjection(m.id, m.name, v.name, m.price, m.imageSmall) " +
            "from Model m " +
            "left join m.vendor v " +
            "where m.productType = 1")
    List<SkiModelGalleryProjection> findAllSkiForGallery();
}
