package com.nttdata.bootcamp.microservicio3.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * [Description]. <br/>
 * <b>Class</b>: {@link Credit}<br/>
 * <b>Copyright</b>: &Copy; 2022 NTT DATA SAC. <br/>
 * <b>Company</b>: NTT DATA SAC. <br/>
 *
 * @author Yennyffer Lizana <br/>
 * <u>Developed by</u>: <br/>
 * <ul>
 * <li>{USERNAME}. (acronym) From (YEN)</li>
 * </ul>
 * <u>Changes</u>:<br/>
 * <ul>
 * <li>ene. 08, 2022 (acronym) Creation class.</li>
 * </ul>
 * @version 1.0
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Document(collection = "credit")

public class Credit {
	@Id
    private String id = UUID.randomUUID().toString();
    private String cardNumber;
    private String cvv;
    private int month;
    private int year;
    private double creditLimit;
    private LocalDate paymentDate;
    private String status;
    private double amountAvailable;
    private double amountConsumed;
    private Customer customer;
    private CreditType creditType;
}
