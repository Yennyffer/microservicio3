package com.nttdata.bootcamp.microservicio3.business.impl;

import com.nttdata.bootcamp.microservicio3.business.CreditService;
import com.nttdata.bootcamp.microservicio3.model.Credit;
import com.nttdata.bootcamp.microservicio3.model.CreditType;
import com.nttdata.bootcamp.microservicio3.model.Customer;
import com.nttdata.bootcamp.microservicio3.repository.CreditRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
class CreditServiceImplTest {
    @Autowired
    private CreditService creditService;
    @MockBean
    private CreditRepository creditRepository;

    private static final Customer customer = new Customer();
    private static final Credit mockCredit = new Credit();
    private static final Credit mockCreditRemove = new Credit();
    private static final CreditType creditType = new CreditType();
    private static final List<Credit> creditListMock = new ArrayList<>();

    private static final String id = "byi2k4ksls";
    private static final String cardNumber = "4213 2563 1002 5896";
    private static final String cvv = "898";
    private static final int month = 11;
    private static final int year = 28;
    private static final double creditLimit = 1400;
    private static final LocalDate paymentDate = LocalDate.parse("2022-01-31");;
    private static final String status = "ACTIVO";
    private static final double amountAvailable = 800;
    private static final double amountConsumed = 600;
    private static final String creditTypeCodigo = "001";
    private static final String creditTypeDescription = "Personal";
    private static final int creditTypeAmountCredits = 1400;
    private static final String creditTypeStatus = "ACTIVO";
    private final static String customerId = "61d874f0dsf";
    private final static String customerFirstName = "Jose Luis";
    private final static String customerLastName = "Peralta";
    private final static String customerEmail = "joseluis@gmail.com";
    private final static String customerNumberDocumentIdentity = "72159854";

    @BeforeEach
    void setUp() {
        mockCredit.setId(id);
        mockCredit.setCardNumber(cardNumber);
        mockCredit.setCvv(cvv);
        mockCredit.setMonth(month);
        mockCredit.setYear(year);
        mockCredit.setCreditLimit(creditLimit);
        mockCredit.setPaymentDate(paymentDate);
        mockCredit.setStatus(status);
        mockCredit.setAmountAvailable(amountAvailable);
        mockCredit.setAmountConsumed(amountConsumed);
        creditType.setCodigo(creditTypeCodigo);
        creditType.setDescription(creditTypeDescription);
        creditType.setAmountCredits(creditTypeAmountCredits);
        creditType.setStatus(creditTypeStatus);
        mockCredit.setCreditType(creditType);
        customer.setId(customerId);
        customer.setFirstname(customerFirstName);
        customer.setLastname(customerLastName);
        customer.setEmail(customerEmail);
        customer.setNumberDocumentIdentity(customerNumberDocumentIdentity);
        mockCredit.setCustomer(customer);
        creditListMock.add(mockCredit);
    }

    @Test
    void create() {
        Mockito.when(creditRepository.save(mockCredit)).thenReturn(Mono.just(mockCredit));
    }

    @Test
    void findById() {
        Mockito.when(creditRepository.findById(id)).thenReturn(Mono.just(mockCredit));
    }

    @Test
    void findAll() {
        Mockito.when(creditRepository.findAll()).thenReturn(Flux.fromIterable(creditListMock));
        Flux<Credit> credit = creditService.findAll();
    }

    @Test
    void update() {
        Mockito.when(creditRepository.findById(id)).thenReturn(Mono.just(mockCredit));
        Mockito.when(creditRepository.save(mockCredit)).thenReturn(Mono.just(mockCredit));
    }

    @Test
    void remove() {
        Mockito.when(creditRepository.findById(id)).thenReturn(Mono.just(mockCreditRemove));
        Mockito.when(creditRepository.save(mockCreditRemove)).thenReturn(Mono.just(mockCreditRemove));
        Mono<Credit> credit = creditService.remove(id);
    }

    @Test
    void findByCustomerId() {
    }
}