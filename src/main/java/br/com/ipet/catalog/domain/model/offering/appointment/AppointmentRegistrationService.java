package br.com.ipet.catalog.domain.model.offering.appointment;


import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;
import br.com.ipet.catalog.domain.model.offering.Rate;
import br.com.ipet.catalog.domain.model.offering.ServiceType;
import br.com.ipet.catalog.domain.model.offering.Species;
import br.com.ipet.catalog.domain.model.offering.appointment.activity.Activities;
import br.com.ipet.catalog.domain.model.offering.appointment.activity.Activity;
import br.com.ipet.catalog.domain.model.offering.appointment.health.Health;
import br.com.ipet.catalog.domain.model.offering.appointment.health.Healths;
import br.com.ipet.catalog.domain.model.offering.appointment.hygiene.Hygiene;
import br.com.ipet.catalog.domain.model.offering.appointment.hygiene.Hygienes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentRegistrationService {

    private final Healths healths;
    private final Hygienes higienes;
    private final Activities activities;

    public Health registerHealth(CompanyId companyId, ServiceType serviceType, Species species,
                                 DurationTime duration, Rate rate) {

        if (higienes.existsOfCompany(companyId) || activities.existsOfCompany(companyId)) {
            throw new AppointmentAlreadyException("The company already has a service registered");
        }

        var hasPetSize = healths.ofCompany(companyId).stream().anyMatch(health -> health.rate().size().equals(rate.size()));
        if (hasPetSize) {
            throw new AppointmentAlreadyException("The company already has service registered");
        }

        return Health.createNew()
                .companyId(companyId)
                .type(serviceType)
                .species(species)
                .duration(duration)
                .rate(rate)
                .build();
    }

    public Hygiene registerHygiene(CompanyId companyId, ServiceType serviceType, Species species,
                                   DurationTime duration, Rate rate) {

        if (healths.existsOfCompany(companyId) || activities.existsOfCompany(companyId)) {
            throw new AppointmentAlreadyException("The company already has a service registered");
        }

        var hasPetSize = higienes.ofCompany(companyId).stream().anyMatch(hygiene -> hygiene.rate().size().equals(rate.size()));
        if (hasPetSize) {
            throw new AppointmentAlreadyException("The company already has service registered");
        }

        return Hygiene.createNew()
                .companyId(companyId)
                .type(serviceType)
                .species(species)
                .duration(duration)
                .rate(rate)
                .build();
    }

    public Activity registerActivity(CompanyId companyId, ServiceType serviceType, Species species,
                                     DurationTime duration, Rate rate) {

        if (healths.existsOfCompany(companyId) || higienes.existsOfCompany(companyId)) {
            throw new AppointmentAlreadyException("The company already has a service registered");
        }

        var hasPetSize = activities.ofCompany(companyId).stream().anyMatch(activity -> activity.rate().size().equals(rate.size()));
        if (hasPetSize) {
            throw new AppointmentAlreadyException("The company already has service registered");
        }

        return Activity.createNew()
                .companyId(companyId)
                .type(serviceType)
                .species(species)
                .duration(duration)
                .rate(rate)
                .build();
    }

    /*private void verifyIfBelongsToTheCompany(CompanyId companyId, ServiceOffering service) {
        var doesNottBelongsToTheCustomer = dayCares.ofCompany(companyId)
                .stream()
                .noneMatch(service::equals);

        if (doesNottBelongsToTheCustomer)
            throw new ServiceDoesNotBelongToTheCompany();
    }*/

}
