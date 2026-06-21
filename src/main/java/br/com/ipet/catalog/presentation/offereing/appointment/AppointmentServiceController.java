package br.com.ipet.catalog.presentation.offereing.appointment;

import br.com.ipet.catalog.application.offering.management.AppointmentInput;
import br.com.ipet.catalog.application.offering.management.AppointmentManagementApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/v1/companies/{companyId}/services")
@RequiredArgsConstructor
public class AppointmentServiceController {

    private final AppointmentManagementApplicationService appointmentManagementApplicationService;

    @PostMapping("/hygienes")
    public ResponseEntity<Void> createHygiene(@PathVariable UUID companyId,
                                              @RequestBody AppointmentInput input) {
        var serviceId = appointmentManagementApplicationService.createHygiene(companyId, input);
        return ResponseEntity.created(buildLocationUri(serviceId)).build();
    }

    @PostMapping("/healths")
    public ResponseEntity<Void> createHealth(@PathVariable UUID companyId,
                                             @RequestBody AppointmentInput input) {
        var serviceId = appointmentManagementApplicationService.createHealth(companyId, input);
        return ResponseEntity.created(buildLocationUri(serviceId)).build();
    }

    @PostMapping("/activities")
    public ResponseEntity<Void> createActivity(@PathVariable UUID companyId,
                                              @RequestBody AppointmentInput input) {
        var serviceId = appointmentManagementApplicationService.createrActivity(companyId, input);
        return ResponseEntity.created(buildLocationUri(serviceId)).build();
    }

    private URI buildLocationUri(UUID serviceId) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(serviceId)
                .toUri();
    }

}
