package com.yanki_api.yanki_api.repositories;

import com.yanki_api.yanki_api.entities.Purse;
import io.reactivex.rxjava3.core.Single;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PurseRepository extends ReactiveMongoRepository<Purse, String> {

    Single<Purse> findByDocumentNumber(String documentNumber);
    Single<Purse> findByPhoneNumber(String phoneNumber);
    Single<Purse> findByEmail(String email);
}