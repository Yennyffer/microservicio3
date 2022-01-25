package com.nttdata.bootcamp.microservicio3.expose;

import com.nttdata.bootcamp.microservicio3.business.CreditService;
import com.nttdata.bootcamp.microservicio3.model.Credit;
import com.nttdata.bootcamp.microservicio3.model.CreditType;
import com.nttdata.bootcamp.microservicio3.model.Customer;
import com.nttdata.bootcamp.microservicio3.model.dto.CreditDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient(timeout = "20000")
class CreditControllerTest {

    @MockBean
    private CreditService creditService;
    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private CreditController creditController;

    private static final Customer customer = new Customer();
    private static final Credit mockCredit = new Credit();
    private static final CreditDto mockCreditDto = new CreditDto();
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
    void getCustomer() {
    }

    @Test
    void byId() {
        log.info("--Metodo GET: Obtener un credito por ID--");
        Mockito.when(creditService.findById(id)).thenReturn(Mono.just(mockCredit));

        webTestClient.get().uri("/api/v1/credit/" + id)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void findAll() {
        log.info("--Metodo GET: Obtener todos los creditos registrados--");
        Mockito.when(creditService.findAll()).thenReturn(Flux.fromIterable(creditListMock));

        webTestClient.get().uri("/api/v1/credit/all")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void create() {
        log.info("--Metodo POST: Agregar un nuevo credito--");
        Mockito.when(creditService.create(mockCreditDto)).thenReturn(Mono.just(mockCredit));
    }

    @Test
    void update() {
        log.info("--Metodo UPDATE: Actualizar un credito--");
        Mockito.when(creditService.update(mockCredit)).thenReturn(Mono.just(mockCredit));
    }

    @Test
    void delete() {
        log.info("--Metodo DELETE: Eliminar un credito por ID--");
        Mockito.when(creditService.remove(id)).thenReturn(Mono.just(mockCredit));

        webTestClient.delete().uri("/api/v1/credit/" + id)
                .exchange()
                .expectStatus().isOk();
    }
}