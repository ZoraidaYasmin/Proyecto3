package com.project3.debitcard.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project3.debitcard.client.TransactionClient;
import com.project3.debitcard.entity.DebitCard;
import com.project3.debitcard.entity.Transaction;
import com.project3.debitcard.repository.DebitCardRepository;
import com.project3.debitcard.service.DebitCardService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DebitCardServiceImpl implements DebitCardService{
	
	@Autowired
    DebitCardRepository debitCardRepository;
	
	@Autowired
    TransactionClient transactionClient;

    @Override
    public Flux<DebitCard> accountDetail(String cardNumber) {
    	
		return findAll().filter(dc -> dc.getCardNumber().equalsIgnoreCase(cardNumber))
				.flatMap(debitCard -> transactionClient.findAllWithDetail()
				.filter(trans -> trans.getCardNumber().equalsIgnoreCase(cardNumber) && trans.getCardNumber().equalsIgnoreCase(debitCard.getCardNumber()))
				.collectList()
				.flatMapMany(trans -> {
					ValorAllValidator(debitCard, trans);
					return Flux.just(debitCard);
				}));

	}

	private void ValorAllValidator(DebitCard debitCard, List<Transaction> transaction) {
		transaction.sort((o1, o2) -> o1.getCreditCardAssociationDate().compareTo(o2.getCreditCardAssociationDate()));
    	debitCard.setTransaction(transaction);
    }

	@Override
	public Flux<DebitCard> findAll() {
		return debitCardRepository.findAll();
	}

	@Override
	public Mono<DebitCard> create(DebitCard dc) {
		return debitCardRepository.save(dc);
	}

	@Override
	public Mono<DebitCard> findById(String id) {
		return debitCardRepository.findById(id);
	}

	@Override
	public Mono<DebitCard> update(DebitCard dc, String id) {
        return debitCardRepository.findById(id)
                .map( x -> {
                    x.setCardNumber(dc.getCardNumber());
                    return x;
                }).flatMap(debitCardRepository::save);
	}

	@Override
	public Mono<Void> delete(String id) {
		return debitCardRepository.deleteById(id);
	}

	@Override
	public Mono<DebitCard> findByTransactionId(String id) {
		return debitCardRepository.findByTransactionId(id);
	}

	@Override
	public Mono<DebitCard> findByCardNumber(String cardNumber) {
		return debitCardRepository.findByCardNumber(cardNumber);
	}

}
