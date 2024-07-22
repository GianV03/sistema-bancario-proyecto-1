package com.yanki_api.yanki_api.services;

import com.yanki_api.yanki_api.entities.Purse;
import com.yanki_api.yanki_api.repositories.PurseRepository;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PurseService {

    @Autowired
    private PurseRepository purseRepository;

    public Single<Purse> getPurseById(String id) {
        Mono<Purse> monoPurse = purseRepository.findById(id);
        return Single.fromPublisher(monoPurse);
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

    public Single<Purse> transferMoneyByPhoneNumber(String fromPhoneNumber, String toPhoneNumber, double amount) {

        // Buscar las carteras por número de teléfono
        Single<Purse> fromPurseSingle = purseRepository.findByPhoneNumber(fromPhoneNumber)
                .doOnError(e -> Single.error(new IllegalArgumentException("Cuenta de origen no encontrada")));

        Single<Purse> toPurseSingle = purseRepository.findByPhoneNumber(toPhoneNumber)
                .doOnError(e -> Single.error(new IllegalArgumentException("Cuenta de destino no encontrada")));

        // Combinar los resultados de las búsquedas
        return Single.zip(fromPurseSingle, toPurseSingle, (fromPurse, toPurse) -> {
                    // Validar si la transferencia es posible
                    if (amount <= 0) {
                        throw new IllegalArgumentException("El monto de transferencia debe ser mayor a 0.");
                    }
                    if (fromPurse.getBalance() < amount) {
                        throw new IllegalArgumentException("Fondos insuficientes");
                    }

                    // Realizar la transferencia
                    fromPurse.setBalance(fromPurse.getBalance() - amount);
                    toPurse.setBalance(toPurse.getBalance() + amount);


                    return Single.zip(
                            Single.fromPublisher(purseRepository.save(fromPurse)),
                            Single.fromPublisher(purseRepository.save(toPurse)),
                            (savedFromPurse, savedToPurse) -> savedToPurse // Devolver la cartera 'to' actualizada
                    );
                }).flatMap(single -> single);
    }


}
