package com.sistema_bancario.bootcoin_monedero_api.repositories;

import com.sistema_bancario.bootcoin_monedero_api.entities.Purse;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PurseRepository extends ReactiveCrudRepository<Purse, String> {
}
