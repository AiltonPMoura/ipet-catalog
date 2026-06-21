package br.com.ipet.catalog.domain.model.offering.stay.accommodation;

import br.com.ipet.catalog.domain.model.AggregateRoot;
import br.com.ipet.catalog.domain.model.FieldValidator;
import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;
import br.com.ipet.catalog.domain.model.offering.DuplicatePetSizeException;
import br.com.ipet.catalog.domain.model.offering.PetSize;
import br.com.ipet.catalog.domain.model.offering.RateService;
import br.com.ipet.catalog.domain.model.offering.ServiceCategory;
import br.com.ipet.catalog.domain.model.offering.ServiceOffereingId;
import br.com.ipet.catalog.domain.model.offering.ServiceOffering;
import br.com.ipet.catalog.domain.model.offering.ServiceType;
import br.com.ipet.catalog.domain.model.offering.Species;
import br.com.ipet.catalog.domain.model.offering.UnsupportedServiceCategoryException;
import br.com.ipet.catalog.domain.model.offering.stay.MinimumStayDurationException;
import br.com.ipet.catalog.domain.model.offering.stay.Stay;
import lombok.Builder;

import java.time.Duration;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Accommodation extends ServiceOffering
        implements Stay, AggregateRoot<ServiceOffereingId> {

    private CheckInOutTime checkInOutTime;
    private Set<RateService> rates;

    @Builder(builderClassName = "CreateNewAccommodationServiceBuilder", builderMethodName = "createNew")
    static Accommodation create(CompanyId companyId, ServiceType type, Species species,
                                CheckInOutTime checkInOutTime, Set<RateService> rates) {

        validateCategory(type);
        validateCheckIn(checkInOutTime.checkInTime());
        validateCheckOut(checkInOutTime.checkOutTime());
        validateMinimumDuration(checkInOutTime.checkInTime(), checkInOutTime.checkOutTime());
        validateUniqueRates(rates);

        return new Accommodation(new ServiceOffereingId(), companyId, type, species, checkInOutTime, rates, OffsetDateTime.now());
    }

    private static void validateCategory(ServiceType type) {
        if (type.category() != ServiceCategory.ACCOMMODATION)
            throw new UnsupportedServiceCategoryException(type.name());
    }

    private static void validateCheckIn(LocalTime checkInTime) {
        if (checkInTime.isBefore(LocalTime.of(14, 0)) || checkInTime.isAfter(LocalTime.of(18, 0)))
            throw new InvalidCheckInTimeException("Check-in deve estar entre 14h e 18h");
    }

    private static void validateCheckOut(LocalTime checkOutTime) {
        if (checkOutTime.isBefore(LocalTime.of(12, 0)) || checkOutTime.isAfter(LocalTime.of(13, 0)))
            throw new InvalidCheckOutTimeException("Check-out deve estar entre 12h e 13h");
    }

    private static void validateMinimumDuration(LocalTime checkInTime, LocalTime checkOutTime) {
        long durationHours = Duration.between(checkInTime, checkOutTime).toHours() + 24;
        if (durationHours < 18)
            throw new MinimumStayDurationException("Duração mínima de 18 horas não atingida");
    }

    private static void validateUniqueRates(Set<RateService> rates) {
        var petSizes = new HashSet<PetSize>();

        for (var rate : rates)
            if (!petSizes.add(rate.size()))
                throw new DuplicatePetSizeException("Duplicate PetSize found in rates: " + rate.size());
    }

    @Builder(builderClassName = "ExistingAccommodationServiceBuilder", builderMethodName = "existing")
    private Accommodation(ServiceOffereingId id, CompanyId companyId, ServiceType type, Species species,
                          CheckInOutTime checkInOutTime, Set<RateService> rates, OffsetDateTime registerAt) {
        super(id, companyId, type, species, registerAt);
        this.setCheckinOutTime(checkInOutTime);
        this.setRates(rates);
    }

    public CheckInOutTime checkInOutTime() {
        return checkInOutTime;
    }

    private void setCheckinOutTime(CheckInOutTime checkInOutTime) {
        FieldValidator.requiresNonNull("checkInOutTime", checkInOutTime);
        this.checkInOutTime = checkInOutTime;
    }

    @Override
    public Set<RateService> rates() {
        return Collections.unmodifiableSet(rates);
    }

    private void setRates(Set<RateService> rates) {
        FieldValidator.requiresNonEmpty("rates", rates);
        this.rates = rates;
    }
}
