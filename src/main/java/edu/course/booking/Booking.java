package edu.course.booking;

public class Booking {
    private final BookingRequest request;
    private final String transactionId;

    public Booking(BookingRequest request, String transactionId) {
        this.request = request;
        this.transactionId = transactionId;
    }

    public BookingRequest getRequest() { 
        return request; 
        }

    public String getTransactionId() { 
        return transactionId; 
        }
}