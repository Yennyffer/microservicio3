package com.nttdata.bootcamp.microservicio3.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

import com.nttdata.bootcamp.microservicio3.model.CreditType;
import com.nttdata.bootcamp.microservicio3.model.Customer;

/**
 * [Description]. <br/>
 * <b>Class</b>: {@link CreditDto}<br/>
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
@NoArgsConstructor
@AllArgsConstructor
public class CreditDto {
	private String customerId;
    private Double creditLimit;
    private Date paymentDate;
    private String cardNumber;
    private String cvv;
    private int month;
    private int year;
    private String status;
    private double amountAvailable;
    private double amountConsumed;
}
