package demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


// we can test static methods starting with mockito 3.4
@ExtendWith(MockitoExtension.class)
class Test14StaticMethods {

    @InjectMocks
    private BookingService bookingService;
    @Mock
    private PaymentService paymentServiceMock;
    @Mock
    private RoomService roomServiceMock;
    @Spy
    private BookingDAO bookingDAOSpy;
    @Mock
    private MailSender mailSenderMock;
    @Captor
    private ArgumentCaptor<Double> doubleCaptor;

    @Test
    void shouldCalculateCorrectPrice() {

        try (MockedStatic<CurrencyConverter> mockedConverter = mockStatic(CurrencyConverter.class)) {

            // given
            BookingRequest bookingRequest =
                    new BookingRequest("1", LocalDate.of(2021, 11, 1),
                            LocalDate.of(2021, 11, 3), 2, false);

            double calculatedInDollar = bookingService.calculatePrice(bookingRequest);
            double calculatedInEuro = CurrencyConverter.toEuro(calculatedInDollar);

            mockedConverter.when(() -> CurrencyConverter.toEuro(calculatedInDollar)).thenReturn(calculatedInEuro);

            // when
            double actual = bookingService.calculatePriceEuro(bookingRequest);
            // then
            assertEquals(calculatedInEuro, actual);
        }
    }
}