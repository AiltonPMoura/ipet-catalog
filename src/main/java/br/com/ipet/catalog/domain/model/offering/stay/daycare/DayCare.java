package br.com.ipet.catalog.domain.model.offering.stay.daycare;

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
import br.com.ipet.catalog.domain.model.offering.stay.accommodation.CheckInOutTime;
import br.com.ipet.catalog.domain.model.offering.stay.accommodation.InvalidCheckInTimeException;
import br.com.ipet.catalog.domain.model.offering.stay.accommodation.InvalidCheckOutTimeException;
import br.com.ipet.catalog.domain.model.offering.stay.MinimumStayDurationException;
import br.com.ipet.catalog.domain.model.offering.stay.Stay;
import lombok.Builder;

import java.time.Duration;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class DayCare extends ServiceOffering
        implements Stay, AggregateRoot<ServiceOffereingId> {

    private CheckInOutTime checkInOutTime;
    private Set<RateService> rates;

    @Builder(builderClassName = "CreateNewDayCareServiceBuilder", builderMethodName = "createNew")
    private static DayCare create(CompanyId companyId, ServiceType type, Species species,
                                  CheckInOutTime checkInOutTime, Set<RateService> rates) {

        validateCategory(type);
        validateCheckIn(checkInOutTime.checkInTime());
        validateCheckOut(checkInOutTime.checkOutTime());
        validateMinimumDuration(checkInOutTime.checkInTime(), checkInOutTime.checkOutTime());
        validateUniqueRates(rates);

        return new DayCare(new ServiceOffereingId(), companyId, type, species, checkInOutTime, rates, OffsetDateTime.now());
    }

    private static void validateCategory(ServiceType type) {
        if (type.category() != ServiceCategory.DAYCARE)
            throw new UnsupportedServiceCategoryException(type.name());
    }

    private static void validateCheckIn(LocalTime checkInTime) {
        if (checkInTime.isBefore(LocalTime.of(6, 0)) || checkInTime.isAfter(LocalTime.of(12, 0)))
            throw new InvalidCheckInTimeException("");
    }

    private static void validateCheckOut(LocalTime checkOutTime) {
        if (checkOutTime.isBefore(LocalTime.of(16, 0)) || checkOutTime.isAfter(LocalTime.of(19, 0)))
            throw new InvalidCheckOutTimeException("");
    }

    private static void validateMinimumDuration(LocalTime checkInTime, LocalTime checkOutTime) {
        if (Duration.between(checkInTime, checkOutTime).toHours() < 4)
            throw new MinimumStayDurationException("");
    }

    private static void validateUniqueRates(Set<RateService> rates) {
        var petSizes = new HashSet<PetSize>();

        for (var rate : rates)
            if (!petSizes.add(rate.size()))
                throw new DuplicatePetSizeException("Duplicate PetSize found in rates: " + rate.size());
    }

    @Builder(builderClassName = "ExistingDayCareServiceBuilder", builderMethodName = "existing")
    private DayCare(ServiceOffereingId id, CompanyId companyId, ServiceType type, Species species,
                    CheckInOutTime checkInOutTime, Set<RateService> rates, OffsetDateTime registerAt) {
        super(id, companyId, type, species, registerAt);
        this.setCheckinOutTime(checkInOutTime);
        this.setRates(rates);
    }

    public CheckInOutTime checkInOutTime() {
        return checkInOutTime;
    }

    private void setCheckinOutTime(CheckInOutTime checkInOutTime) {
        FieldValidator.requiresNonNull("checkInOut", checkInOutTime);
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
