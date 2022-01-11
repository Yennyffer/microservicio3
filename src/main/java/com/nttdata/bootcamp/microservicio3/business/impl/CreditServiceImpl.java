package com.nttdata.bootcamp.microservicio3.business.impl;

import com.nttdata.bootcamp.microservicio3.business.CreditService;
import com.nttdata.bootcamp.microservicio3.model.Credit;
import com.nttdata.bootcamp.microservicio3.model.Customer;
import com.nttdata.bootcamp.microservicio3.repository.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * [Description]. <br/>
 * <b>Class</b>: {@link CreditServiceImpl}<br/>
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

@Service
public class CreditServiceImpl implements CreditService{

  @Autowired
  private CreditRepository creditRepository;
  @Autowired
  private WebClient webClientUser;

  @Override
  public Mono<Credit> create(Credit credit) {

    if(!credit.getId().isBlank()){
    	
    }

    return creditRepository.save(credit);
  }

  @Override
  public Mono<Credit> findById(String creditId) {
    return creditRepository.findById(creditId);
  }

  @Override
  public Flux<Credit> findAll() {
    return creditRepository.findAll();
  }

  @Override
  public Mono<Credit> update(Credit credit) {
	  return creditRepository.save(credit);
  }

  @Override
  public Mono<Credit> change(Credit credit) {
    return creditRepository.findById(credit.getId())
        .flatMap(creditDB -> {
          return create(credit);
        })
        .switchIfEmpty(Mono.empty());
  }

  @Override
  public Mono<Credit> remove(String creditId) {
    return creditRepository
        .findById(creditId)
        .flatMap(p -> creditRepository.deleteById(p.getId()).thenReturn(p));

  }
  
  @Override
  public Flux<Customer> findCustomerAll() {
    return webClientUser.get()
        .uri(uriBuilder -> uriBuilder
            .path("customers-all/" )
            .build())
        .retrieve()
        .bodyToFlux(Customer.class);
  }

}

