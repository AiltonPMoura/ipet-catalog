package br.com.ipet.catalog.domain.model.offering;

import br.com.ipet.catalog.domain.model.commons.valueobject.CompanyId;
import br.com.ipet.catalog.domain.model.commons.exception.FieldCannotBeEmptyException;
import br.com.ipet.catalog.domain.model.offering.stay.accommodation.Accommodation;
import br.com.ipet.catalog.domain.model.offering.stay.accommodation.CheckInOut;
import br.com.ipet.catalog.domain.model.offering.stay.accommodation.InvalidCheckInTimeException;
import br.com.ipet.catalog.domain.model.offering.stay.accommodation.InvalidCheckOutTimeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;
import java.math.BigDecimal;
import br.com.ipet.catalog.domain.model.commons.valueobject.Money;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class AccommodationTest {

    private CompanyId companyId;
    private ServiceType validType;
    private Species validSpecies;
    private CheckInOut validCheckInOut;
    private List<ServiceDetails> validServiceDetails;

    @BeforeEach
    void setUp() {
        companyId = new CompanyId();
        validType = ServiceType.STANDARD_HOTEL;
        validSpecies = Species.GOG;
        validCheckInOut = new CheckInOut(LocalTime.of(14, 0), LocalTime.of(12, 0));
        validServiceDetails = List.of();
    }

    @Test
    void givenValidCheckInOut_Time_whenCreate_thenReturnAccommodation() {
        var accommodation = Accommodation.create(companyId, validType, validSpecies, validCheckInOut, validServiceDetails);

        assertThat(accommodation).isNotNull();
        assertThat(accommodation.checkInOut().checkin()).isEqualTo(LocalTime.of(14, 0));
        assertThat(accommodation.checkInOut().checkout()).isEqualTo(LocalTime.of(12, 0));
    }

    @Test
    void givenInvalidCheckIn_whenCreate_thenThrowInvalidCheckInTimeException() {
        var invalidCheckIn = new CheckInOut(LocalTime.of(13, 59), LocalTime.of(12, 0));

        assertThatThrownBy(() -> Accommodation.create(companyId, validType, validSpecies, invalidCheckIn, validServiceDetails))
                .isInstanceOf(InvalidCheckInTimeException.class);
    }

    @Test
    void givenInvalidCheckOut_whenCreate_thenThrowInvalidCheckOutTimeException() {
        var invalidCheckOut = new CheckInOut(LocalTime.of(14, 0), LocalTime.of(11, 59));

        assertThatThrownBy(() -> Accommodation.create(companyId, validType, validSpecies, invalidCheckOut, validServiceDetails))
                .isInstanceOf(InvalidCheckOutTimeException.class);
    }

    @Test
    void givenUnsupportedServiceType_whenCreate_thenThrowUnsupportedServiceCategoryException() {
        var unsupportedType = ServiceType.BATH;

        assertThatThrownBy(() -> Accommodation.create(companyId, unsupportedType, validSpecies, validCheckInOut, validServiceDetails))
                .isInstanceOf(UnsupportedServiceCategoryException.class);
    }

    @Test
    void givenNullDetails_whenCreate_thenThrowFieldCannotBeEmptyException() {
        assertThatThrownBy(() -> Accommodation.create(companyId, validType, validSpecies, validCheckInOut, null))
                .isInstanceOf(FieldCannotBeEmptyException.class);
    }

    @Test
    void givenIncompatibleSpecies_whenCreate_thenThrowServiceTypeDoesNotSupportSpeciesException() {
        var incompatibleSpecies = Species.BIRD;

        assertThatThrownBy(() -> Accommodation.create(companyId, validType, incompatibleSpecies, validCheckInOut, validServiceDetails))
                .isInstanceOf(ServiceTypeDoesNotSupportSpeciesException.class);
    }

    @Test
    void givenDuplicatePetSizeInDetails_whenCreate_thenThrowDuplicatePetSizeException() {
        var ratesWithDuplicate = new ArrayList<ServiceDetails>();
        ratesWithDuplicate.add(new ServiceDetails(PetSize.SMALL, new Money(new BigDecimal("50.00"))));
        ratesWithDuplicate.add(new ServiceDetails(PetSize.SMALL, new Money(new BigDecimal("60.00"))));

        assertThatThrownBy(() -> Accommodation.create(companyId, validType, validSpecies, validCheckInOut, ratesWithDuplicate))
                .isInstanceOf(DuplicatePetSizeException.class);
    }
}







