package br.com.ipet.catalog.presentation.offering;

import br.com.ipet.catalog.application.offering.query.ServiceOfferingOutput;
import br.com.ipet.catalog.application.offering.query.ServiceOfferingQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/companies/{companyId}/services")
@RequiredArgsConstructor
public class ServiceOfferingQueryController {

    private final ServiceOfferingQueryService serviceOfferingQueryService;

    @GetMapping("/{id}")
    public ServiceOfferingOutput findById(@PathVariable UUID companyId,
                                          @PathVariable UUID id) {
        return serviceOfferingQueryService.findById(companyId, id);
    }

    @GetMapping
    public List<ServiceOfferingOutput> findAll(@PathVariable UUID companyId) {
        return serviceOfferingQueryService.findAll(companyId);
    }

}
