package edu.course.booking;

public interface IEventRepository {
    boolean isSoldOut(String eventId);
    void saveBooking(String customerId, String eventId, int ticketQuantity, String transactionId);
}