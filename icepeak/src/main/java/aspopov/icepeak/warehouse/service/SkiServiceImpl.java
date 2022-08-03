package aspopov.icepeak.warehouse.service;

import aspopov.icepeak.warehouse.domain.Ski;
import aspopov.icepeak.warehouse.dto.ModelShortDto;
import aspopov.icepeak.warehouse.dto.SkiDto;
import aspopov.icepeak.warehouse.dto.SkiSearchParams;
import aspopov.icepeak.warehouse.repository.SkiRepository;
import aspopov.icepeak.warehouse.repository.SkiSpecification;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkiServiceImpl implements SkiService {

    private final SkiRepository skiRepository;

    public SkiServiceImpl(SkiRepository skiRepository) {
        this.skiRepository = skiRepository;
    }

    @Override
    public Collection<ModelShortDto> search(SkiSearchParams skiSearchParams) {
        var spec = SkiSpecification.build(skiSearchParams);
        var skis = skiRepository.findAll(spec);
        var modelDtos = skis.stream().map(ModelShortDto::fromProduct).collect(Collectors.toCollection(HashSet::new));
        return modelDtos;
    }
}
