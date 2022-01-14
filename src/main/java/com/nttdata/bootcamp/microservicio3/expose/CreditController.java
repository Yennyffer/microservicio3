package com.nttdata.bootcamp.microservicio3.expose;

import com.nttdata.bootcamp.microservicio3.model.Credit;
import com.nttdata.bootcamp.microservicio3.model.Customer;
import com.nttdata.bootcamp.microservicio3.model.dto.CreditDto;
import com.nttdata.bootcamp.microservicio3.business.CreditService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * [Description]. <br/>
 * <b>Class</b>: {@link CreditController}<br/>
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


@RestController
@Slf4j
public class CreditController {

  @Autowired
  private CreditService creditService;

  @GetMapping("/api/v1/customer/{id}")
  public Flux<Customer> getCustomer() {
    log.info("byCustomerAll>>>>>");
    return creditService.findCustomerAll();
  }
   
  
  @GetMapping("/api/v1/credit/{id}")
  public Mono<Credit> byId(@PathVariable("id") String id) {
    log.info("byId>>>>>");
    return creditService.findById(id);
  }
  
  @GetMapping("/api/v1/credits-all")
  public Flux<Credit> findAll() {
    log.info("findAll>>>>>");

    return creditService.findAll();
  }

  @PostMapping("/api/v1/credit/")
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Credit> create(@RequestBody CreditDto creditDTO) {
    log.info("create>>>>>");
    return creditService.create(creditDTO);
  }

  @PutMapping("/api/v1/credit/")
  public Mono<ResponseEntity<Credit>> update(@RequestBody Credit credit) {
    log.info("update>>>>>");
    return creditService.update(credit)
        .flatMap(creditUpdate -> Mono.just(ResponseEntity.ok(creditUpdate)))
        .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
  }

  @PatchMapping("/api/v1/credits")
  public Mono<ResponseEntity<Credit>> change(@RequestBody Credit credit) {
    log.info("change>>>>>");
    return creditService.change(credit)
        .flatMap(creditUpdate -> Mono.just(ResponseEntity.ok(creditUpdate)))
        .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
  }

  @DeleteMapping("/api/v1/credits/{id}")
  public Mono<ResponseEntity<Credit>> delete(@PathVariable("id") String id) {
    log.info("delete>>>>>");
    return creditService.remove(id)
        .flatMap(credit -> Mono.just(ResponseEntity.ok(credit)))
        .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
  }
  
}
