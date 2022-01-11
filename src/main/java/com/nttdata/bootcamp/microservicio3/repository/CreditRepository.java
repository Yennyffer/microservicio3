package com.nttdata.bootcamp.microservicio3.repository;

import com.nttdata.bootcamp.microservicio3.model.Credit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * [Description]. <br/>
 * <b>Class</b>: {@link CreditRepository}<br/>
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

@Repository
public interface CreditRepository 
    extends ReactiveMongoRepository<Credit,String> {

    Flux<Credit> findAllByCardNumber(String cardNumber);
    Mono<Credit> findFirstByCustomer(Mono<String> customer);
    
}
