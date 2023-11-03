package demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mockStatic;


// we can test static methods starting with mockito 3.4
@ExtendWith(MockitoExtension.class)
class Test15Answers {

    @InjectMocks
    private BookingService bookingService;

    @Test
    void shouldCalculateCorrectPrice() {

        try (MockedStatic<CurrencyConverter> mockedConverter = mockStatic(CurrencyConverter.class)) {

            // given
            BookingRequest bookingRequest =
                    new BookingRequest("1", LocalDate.of(2021, 11, 1),
                            LocalDate.of(2021, 11, 3), 2, false);

            double expected = 200 * 0.85;

            mockedConverter.when(() -> CurrencyConverter.toEuro(anyDouble()))
                    .thenAnswer( invocationOnMock -> (double) invocationOnMock.getArgument(0) * 0.85);

            // when
            double actual = bookingService.calculatePriceEuro(bookingRequest);
            // then
            assertEquals(expected, actual);
        }
    }
}