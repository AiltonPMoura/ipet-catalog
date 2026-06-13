package br.com.ipet.catalog.domain.model.service;

import br.com.ipet.catalog.domain.model.AggregateRoot;
import br.com.ipet.catalog.domain.model.FieldValidator;
import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;
import lombok.Builder;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class DayCare extends Service
        implements Stay, AggregateRoot<ServiceId> {

    private CheckInOutTime checkInOutTime;
    private List<ServiceRate> rates;

    static DayCare create(CompanyId companyId, ServiceType type, Species species,
                          CheckInOutTime checkInOutTime, List<ServiceRate> rates) {

        validateCategory(type);
        validateCheckIn(checkInOutTime.checkInTime());
        validateCheckOut(checkInOutTime.checkOutTime());
        validateMinimumDuration(checkInOutTime.checkInTime(), checkInOutTime.checkOutTime());
        validateUniqueRates(rates);

        return new DayCare(new ServiceId(), companyId, type, species, checkInOutTime, rates);
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

    private static void validateUniqueRates(List<ServiceRate> rates) {
        var petSizes = new HashSet<PetSize>();

        for (var rate : rates)
            if (!petSizes.add(rate.size()))
                throw new DuplicatePetSizeException("Duplicate PetSize found in rates: " + rate.size());
    }

    @Builder(builderClassName = "ExistingDayCareServiceBuilder", builderMethodName = "existing")
    private DayCare(ServiceId id, CompanyId companyId, ServiceType type, Species species,
                   CheckInOutTime checkInOutTime, List<ServiceRate> rates) {
        super(id, companyId, type, species);
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
    public List<ServiceRate> rates() {
        return Collections.unmodifiableList(rates);
    }

    private void setRates(List<ServiceRate> rates) {
        FieldValidator.requiresNonEmpty("rates", rates);
        this.rates = rates;
    }
}
