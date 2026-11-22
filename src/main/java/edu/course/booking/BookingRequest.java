package edu.course.booking;

public class BookingRequest {
    private final String customerId;
    private final String eventId;
    private final int ticketQuantity;
    private final int pricePerTicketInCents;

    public BookingRequest(String customerId, String eventId,
                          int ticketQuantity, int pricePerTicketInCents) {
        this.customerId = customerId;
        this.eventId = eventId;
        this.ticketQuantity = ticketQuantity;
        this.pricePerTicketInCents = pricePerTicketInCents;
    }

    public String getCustomerId() { return customerId; }
    public String getEventId() { return eventId; }
    public int getTicketQuantity() { return ticketQuantity; }
    public int getPricePerTicketInCents() { return pricePerTicketInCents; }

    public boolean isValid() {
        return isNotBlank(customerId)
            && isNotBlank(eventId)
            && ticketQuantity >= 1 && ticketQuantity <= 5
            && pricePerTicketInCents > 0;
    }

    private static boolean isNotBlank(String value) {
        return value != null && !value.isBlank();
    }
}