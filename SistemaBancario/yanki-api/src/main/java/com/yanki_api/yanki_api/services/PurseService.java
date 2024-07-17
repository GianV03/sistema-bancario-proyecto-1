package com.yanki_api.yanki_api.services;

import com.yanki_api.yanki_api.entities.Purse;
import com.yanki_api.yanki_api.repositories.PurseRepository;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurseService {

    @Autowired
    private PurseRepository purseRepository;

    public Single<Purse> getPurseById(String id) {
        return Single.fromPublisher(purseRepository.findById(id));
    }

    public Single<Purse> createPurse(Purse purse) {
        return Single.fromPublisher(purseRepository.save(purse));
    }

    public Single<Void> deletePurseById(String id) {
        return Single.fromPublisher(purseRepository.deleteById(id));
    }

    public Single<Purse> updateBalance(String id, double amount) {
        return getPurseById(id)
                .flatMap(purse -> {
                    purse.setBalance(purse.getBalance() + amount);
                    return createPurse(purse);
                });
    }
}
