| Test Case          | isSoldOut | processPayment | saveBooking | sendConfirmation | Expected Result                            |
| ------------------ | --------- | -------------- | ----------- | ---------------- | ------------------------------------------ |
| Successful Booking | 1         | 1              | 1           | 1                | Booking successful                         |
| Invalid Request    | 0         | 0              | 0           | 0                | Request rejected                           |
| Sold Out           | 1         | 0              | 0           | 0                | Booking rejected because event is sold out |
| Failed Payment     | 1         | 1              | 0           | 0                | Booking rejected because payment failed    |

## Test Case Inputs and Expected Results

### TC-01: Successful Booking

- customerId: C001
- eventId: E001
- ticketQuantity: 2
- pricePerTicketInCents: 1500
- Expected Result: Booking successful.
- Expected Transaction ID: A valid transaction ID.

### TC-02: Invalid Request

- customerId: C001
- eventId: E001
- ticketQuantity: 0
- pricePerTicketInCents: 1500
- Expected Result: Request rejected because the ticket quantity is invalid.
- Expected External Calls: No dependency methods should be called.

### TC-03: Sold Out

- customerId: C001
- eventId: E001
- ticketQuantity: 2
- pricePerTicketInCents: 1500
- Event Status: Sold Out
- Expected Result: Booking rejected because the event is sold out.
- Expected External Calls:
  - isSoldOut(): 1
  - processPayment(): 0
  - saveBooking(): 0
  - sendConfirmation(): 0

### TC-04: Failed Payment

- customerId: C001

- eventId: E001

- ticketQuantity: 2

- pricePerTicketInCents: 1500

- Event Status: Not Sold Out

- Payment Status: Failed

- Expected Result: Booking rejected because payment failed.

- Expected External Calls:

  - isSoldOut(): 1

  - processPayment(): 1

  - saveBooking(): 0

  - sendConfirmation(): 0