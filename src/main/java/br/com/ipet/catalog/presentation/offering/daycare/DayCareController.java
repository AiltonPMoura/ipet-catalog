package br.com.ipet.catalog.presentation.offering.daycare;

import br.com.ipet.catalog.application.commons.StayInput;
import br.com.ipet.catalog.application.offering.management.DayCareManagementApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/v1/companies/{companyId}/services/daycares")
@RequiredArgsConstructor
public class DayCareController {

    private final DayCareManagementApplicationService dayCareManagementApplicationService;

    @PostMapping
    public ResponseEntity<Void> registerDayCare(@PathVariable UUID companyId,
                                                @RequestBody StayInput input) {
        var serviceId = dayCareManagementApplicationService.create(companyId, input);

        var uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/../{id}")
                .buildAndExpand(serviceId)
                .normalize()
                .toUri();

        return ResponseEntity.created(uri).build();
    }

}
