package com.nttdata.bootcamp.microservicio3.business;

import com.nttdata.bootcamp.microservicio3.model.Credit;
import com.nttdata.bootcamp.microservicio3.model.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * [Description]. <br/>
 * <b>Class</b>: {@link CreditService}<br/>
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
 * <li>ene. 11, 2022 (acronym) Creation class.</li>
 * </ul>
 * @version 1.0
 */

public interface CreditService {

    Mono<Credit> create(Credit credit);
  
    Mono<Credit> findById(String creditId);
  
    Flux<Credit> findAll();
  
    Mono<Credit> update(Credit credit);
  
    Mono<Credit> change(Credit credit);
  
    Mono<Credit> remove(String creditId);
    
    Flux<Customer> findCustomerAll();
  
  }
  
