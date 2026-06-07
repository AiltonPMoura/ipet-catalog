package br.com.ipet.catalog.domain.model.service;

import br.com.ipet.catalog.domain.model.AggregateRoot;
import br.com.ipet.catalog.domain.model.FieldValidator;
import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;
import lombok.Builder;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

public class Accommodation extends Service
        implements Stay, AggregateRoot<ServiceId> {

    private CheckInOut checkInOut;
    private List<ServiceRate> rates;

    @Builder(builderClassName = "CreateAccommodationServiceBuilder", builderMethodName = "create")
    private static Accommodation create(CompanyId companyId, ServiceType type, Species species,
                                        CheckInOut checkInOut, List<ServiceRate> rates) {

        var checkIn = checkInOut.checkIn();
        var checkOut = checkInOut.checkOut();

        if (type.category() != ServiceCategory.ACCOMMODATION)
            throw new UnsupportedServiceCategoryException(type.name());

        if (checkIn.isBefore(LocalTime.of(14, 0, 0, 0)))
            throw new CheckinHoursCannotBeLessThanSixHoursException("");

        if (checkOut.isAfter(LocalTime.of(12, 0, 0, 0)))
            throw new CheckoutHoursCannotBeGreaterThanNineteenHoursException("");

        if (Duration.between(checkIn, checkOut).toHours() < 22)
            throw new DayCareCannotBeLessThanFourHoursException("");

        return new Accommodation(new ServiceId(), companyId, type, species, checkInOut, rates);
    }

    @Builder(builderClassName = "ExistingAccommodationServiceBuilder", builderMethodName = "existing")
    public Accommodation(ServiceId id, CompanyId companyId, ServiceType type, Species species,
                         CheckInOut checkInOut, List<ServiceRate> rates) {
        super(id, companyId, type, species);
        this.setCheckinOut(checkInOut);
        this.setRates(rates);
    }

    public CheckInOut checkInOut() {
        return checkInOut;
    }

    private void setCheckinOut(CheckInOut checkInOut) {
        FieldValidator.requiresNonNull("checkInOut", checkInOut);
        this.checkInOut = checkInOut;
    }

    @Override
    public List<ServiceRate> rates() {
        return Collections.unmodifiableList(rates);
    }

    private void setRates(List<ServiceRate> rates) {
        FieldValidator.requiresNonNull("rates", rates);
        this.rates = rates;
    }
}
