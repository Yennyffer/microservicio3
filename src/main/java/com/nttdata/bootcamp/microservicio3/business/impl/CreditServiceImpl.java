package com.nttdata.bootcamp.microservicio3.business.impl;

import com.nttdata.bootcamp.microservicio3.business.CreditService;
import com.nttdata.bootcamp.microservicio3.model.Credit;
import com.nttdata.bootcamp.microservicio3.model.Customer;
import com.nttdata.bootcamp.microservicio3.model.dto.CreditDto;
import com.nttdata.bootcamp.microservicio3.repository.CreditRepository;
import com.nttdata.bootcamp.microservicio3.utils.UserNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.NoSuchElementException;
import java.util.Random;

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
@RequiredArgsConstructor
@Slf4j
public class CreditServiceImpl implements CreditService{

  @Autowired
  private CreditRepository creditRepository;
  @Autowired
  private WebClient webClientUser;
  private Credit credit;

  
  @Override
  public Mono<Credit> create(CreditDto creditDTO) {

      if (creditDTO.getCustomerId() == null){
          Mono<Credit> createdCredit = findByIdCustomerService(creditDTO.getCustomerId())
                  .flatMap(retrievedCustomer -> {
                      log.info("Validacion del credito");
                      return creditToCreateValidation(creditDTO, retrievedCustomer);
                  })
                  .flatMap(validatedCustomer -> {
                      Credit creditToCreate = credit;
                      Customer customer = validatedCustomer;
                      Random cardNumberRandom = new Random();

                      creditToCreate.setCustomer(customer);
                      creditToCreate.setStatus("true");
                      creditToCreate.setCardNumber(Long.toString(cardNumberRandom.nextLong()));

                      log.info("Creando un nuevo credito", creditToCreate.toString());
                      return creditRepository.insert(creditToCreate);
                  })
                  .switchIfEmpty(Mono.error(new NoSuchElementException("Customer does not exist")));

          log.info("Se culminó la creación del credito");
          return createdCredit;
      } else {
			log.info("No se pudo crear un credito, el id del cliente no existe");
          return Mono.error(new IllegalArgumentException("No se pudo crear la cuenta, el id del cliente no existe"));
      }
  }

  private Mono<Customer> creditToCreateValidation(CreditDto creditForCreate, Customer customerExtracted) {
      log.info("Existe un cliente");
      
      //CustomerType:   personal = '1' y empresarial = '2'
      if (customerExtracted.getCustomerType().getId().equals("1")){
          return findByCustomerId(customerExtracted.getId())
                  .filter(retrievedAccount -> retrievedAccount.getStatus().equals("true"))
                  .hasElements()
                  .flatMap(haveAnAccount -> {
                      if (haveAnAccount) {
                          log.warn("Can not create more than one credit for a personal customer");
                          log.warn("Proceeding to abort create credit");
                          return Mono.error(new UserNotFoundException("Customer already have one credit"));
                      }
                      else {
                          log.info("Credit successfully validated");
                          return Mono.just(customerExtracted);
                      }
                  });
      } else {
          log.info("Credit successfully validated");
          return Mono.just(customerExtracted);
      }
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
  
  @Override
  public Mono<Customer> findByIdCustomerService(String id) {
      Mono<Customer> retrievedCustomer = webClientUser.get()
              .uri(uriBuilder -> uriBuilder
                      .path("v1/customers/" + id)
                      .build())
              .retrieve()
              .bodyToMono(Customer.class);
      
      log.info("Cliente recuperado: ", id);
      return retrievedCustomer;
  }

@Override
public Flux<Credit> findByCustomerId(String id) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Mono<Credit> change(Credit credit) {
	// TODO Auto-generated method stub
	return null;
}

}

