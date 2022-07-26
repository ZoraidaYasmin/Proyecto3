package com.project3.debitcard.service;

import com.project3.debitcard.entity.DebitCard;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DebitCardService {
	
	Flux<DebitCard> findAll();

    Mono<DebitCard> create(DebitCard dc);

    Mono<DebitCard> findById(String id);
    
    Mono<DebitCard> findByTransactionId(String id);

    Mono<DebitCard> update(DebitCard dc, String id);

    Mono<Void> delete(String id);
	
	Mono<DebitCard> findByCardNumber(String cardNumber);
	
	Flux<DebitCard> accountDetail(String cardNumber);
	
}
