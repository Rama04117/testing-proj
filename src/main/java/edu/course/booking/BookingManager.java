package edu.course.booking;

public class BookingManager {
    private final IPaymentGateway paymentGateway;
    private final INotificationService notificationService;
    private final IEventRepository eventRepository;

    public BookingManager(IPaymentGateway paymentGateway,
                          INotificationService notificationService,
                          IEventRepository eventRepository) {
        this.paymentGateway = paymentGateway;
        this.notificationService = notificationService;
        this.eventRepository = eventRepository;
    }

    public String processBooking(BookingRequest request) {
        if (!request.isValid()) {
            throw new IllegalArgumentException("Invalid booking request");
        }

        boolean soldOut = eventRepository.isSoldOut(request.getEventId());
        if (soldOut) {
            throw new IllegalStateException("Event is sold out");
        }

        int totalInCents = request.getTicketQuantity() * request.getPricePerTicketInCents();
        String transactionId = paymentGateway.processPayment(totalInCents);

        eventRepository.saveBooking(new Booking(request, transactionId));
        notificationService.sendConfirmation(request.getCustomerId(), transactionId);

        return transactionId;
    }
}