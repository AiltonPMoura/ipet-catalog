package br.com.ipet.catalog.presentation.offereing;

import br.com.ipet.catalog.application.commons.AppointmentInput;
import br.com.ipet.catalog.application.offering.management.ActivityManagementApplicationService;
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
@RequestMapping("/v1/companies/{companyId}/services/activities")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityManagementApplicationService activityManagementApplicationService;

    @PostMapping
    public ResponseEntity<Void> create(@PathVariable UUID companyId,
                                       @RequestBody AppointmentInput input) {
        var serviceId = activityManagementApplicationService.create(companyId, input);

        var uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/../{id}")
                .buildAndExpand(serviceId)
                .normalize()
                .toUri();

        return ResponseEntity.created(uri).build();
    }

}
