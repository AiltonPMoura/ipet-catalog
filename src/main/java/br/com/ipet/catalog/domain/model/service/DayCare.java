package br.com.ipet.catalog.domain.model.service;

import br.com.ipet.catalog.domain.model.AggregateRoot;
import br.com.ipet.catalog.domain.model.FieldValidator;
import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;
import lombok.Builder;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

import static br.com.ipet.catalog.domain.model.service.ServiceType.STANDARD_DAYCARE;
import static br.com.ipet.catalog.domain.model.service.ServiceType.VIP_DAYCARE;

public class DayCare extends Service
        implements Stay, AggregateRoot<ServiceId> {

    private CheckInOut checkInOut;
    private List<ServiceRate> rates;

    @Builder(builderClassName = "CreateDayCareServiceBuilder", builderMethodName = "create")
    private static DayCare create(CompanyId companyId, ServiceType type, Species species,
                                  CheckInOut checkInOut, List<ServiceRate> rates) {

        var checkIn = checkInOut.checkIn();
        var checkOut = checkInOut.checkOut();

        if (type.category() != ServiceCategory.DAYCARE)
            throw new UnsupportedServiceCategoryException(type.name());

        if (checkIn.isBefore(LocalTime.of(6, 0, 0, 0)))
            throw new CheckinHoursCannotBeLessThanSixHoursException("");

        if (checkOut.isAfter(LocalTime.of(19, 0, 0, 0)))
            throw new CheckoutHoursCannotBeGreaterThanNineteenHoursException("");

        if (Duration.between(checkIn, checkOut).toHours() < 4)
            throw new DayCareCannotBeLessThanFourHoursException("");

        return new DayCare(new ServiceId(), companyId, type, species, checkInOut, rates);
    }

    @Builder(builderClassName = "ExistingDayCareServiceBuilder", builderMethodName = "existing")
    public DayCare(ServiceId id, CompanyId companyId, ServiceType type, Species species,
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
