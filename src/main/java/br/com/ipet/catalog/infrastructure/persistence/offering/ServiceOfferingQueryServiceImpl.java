package br.com.ipet.catalog.infrastructure.persistence.offering;

import br.com.ipet.catalog.application.offering.query.AppointmentDetailOutput;
import br.com.ipet.catalog.application.offering.query.ServiceOfferingOutput;
import br.com.ipet.catalog.application.offering.query.ServiceOfferingQueryService;
import br.com.ipet.catalog.application.offering.query.StayDetailOutput;
import br.com.ipet.catalog.application.util.Mapper;
import br.com.ipet.catalog.infrastructure.persistence.offering.accommodation.AccommodationDocument;
import br.com.ipet.catalog.infrastructure.persistence.offering.activity.ActivityDocument;
import br.com.ipet.catalog.infrastructure.persistence.offering.daycare.DayCareDocument;
import br.com.ipet.catalog.infrastructure.persistence.offering.health.HealthDocument;
import br.com.ipet.catalog.infrastructure.persistence.offering.hygiene.HygieneDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ServiceOfferingQueryServiceImpl implements ServiceOfferingQueryService {

    private final ServiceOfferingPersistenceRepository serviceOfferingRepository;
    private final Mapper mapper;

    @Override
    public ServiceOfferingOutput findById(UUID companyId, UUID id) {
        return serviceOfferingRepository.findByIdAndCompanyId(id, companyId)
                .map(this::toOutput)
                .orElseThrow(() -> new ServiceOfferingNotFoundException(""));
    }

    @Override
    public List<ServiceOfferingOutput> findAll(UUID companyId) {
        var serviceOfferings = serviceOfferingRepository.findAllByCompanyId(companyId);
        return serviceOfferings.stream().map(this::toOutput).toList();
    }

    private ServiceOfferingOutput toOutput(AbstractServiceOfferingDocument serviceOffering) {
        return switch (serviceOffering) {
            case DayCareDocument dayCare -> mapper.convert(dayCare, StayDetailOutput.class);
            case AccommodationDocument accommodation -> mapper.convert(accommodation, StayDetailOutput.class);
            case HygieneDocument hygiene -> mapper.convert(hygiene, AppointmentDetailOutput.class);
            case HealthDocument health -> mapper.convert(health, StayDetailOutput.class);
            case ActivityDocument activity -> mapper.convert(activity, StayDetailOutput.class);
            default -> throw new UnsupportedServiceOfferingException("");
        };
    }

}
