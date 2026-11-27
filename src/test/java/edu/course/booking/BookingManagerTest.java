
package edu.course.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingManagerTest {

    @Mock
    private IPaymentGateway paymentGateway;

    @Mock
    private INotificationService notificationService;

    @Mock
    private IEventRepository eventRepository;

    private BookingManager bookingManager;

    @BeforeEach
    void setUp() {
        bookingManager = new BookingManager(
                paymentGateway,
                notificationService,
                eventRepository
        );
    }

    @Test
    void successfulBooking() {

   
        BookingRequest request = new BookingRequest(
                "C001",
                "E001",
                2,
                1500
        );

        when(eventRepository.isSoldOut("E001"))
                .thenReturn(false);

        when(paymentGateway.processPayment(3000))
                .thenReturn("TXN123");

      
        String transactionId = bookingManager.processBooking(request);

     
        assertEquals("TXN123", transactionId);

        verify(eventRepository, times(1))
                .isSoldOut("E001");

        verify(paymentGateway, times(1))
                .processPayment(3000);

        verify(eventRepository, times(1))
                .saveBooking(any(Booking.class));

        verify(notificationService, times(1))
                .sendConfirmation("C001", "TXN123");
    }
}
