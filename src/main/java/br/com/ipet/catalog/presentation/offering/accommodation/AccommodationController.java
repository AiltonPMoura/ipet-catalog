package br.com.ipet.catalog.presentation.offering.accommodation;

import br.com.ipet.catalog.application.commons.StayInput;
import br.com.ipet.catalog.application.offering.management.AccommodationManagementApplicationService;
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
@RequestMapping("/v1/companies/{companyId}/services/accommodations")
@RequiredArgsConstructor
public class AccommodationController {

    private final AccommodationManagementApplicationService accommodationManagementApplicationService;

    @PostMapping
    public ResponseEntity<Void> registerAccommodation(@PathVariable UUID companyId,
                                                      @RequestBody StayInput command) {
        var serviceId = accommodationManagementApplicationService.create(companyId, command);

        var uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/../{id}")
                .buildAndExpand(serviceId)
                .normalize()
                .toUri();

        return ResponseEntity.created(uri).build();
    }

}
