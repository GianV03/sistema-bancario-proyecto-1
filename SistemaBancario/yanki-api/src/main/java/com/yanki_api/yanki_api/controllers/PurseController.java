package com.yanki_api.yanki_api.controllers;

import com.yanki_api.yanki_api.entities.Purse;
import com.yanki_api.yanki_api.services.PurseService;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/purses")
public class PurseController {

    @Autowired
    private PurseService purseService;

    @GetMapping("/{id}")
    public Single<ResponseEntity<Purse>> getPurseById(@PathVariable String id) {
        return purseService.getPurseById(id)
                .map(ResponseEntity::ok)
                .onErrorReturnItem(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Single<ResponseEntity<Purse>> createPurse(@RequestBody Purse purse) {
        return purseService.createPurse(purse)
                .map(savedPurse -> ResponseEntity.status(HttpStatus.CREATED).body(savedPurse));
    }

    @DeleteMapping("/{id}")
    public Single<ResponseEntity<Void>> deletePurseById(@PathVariable String id) {
        return purseService.deletePurseById(id)
                .map(deleted -> ResponseEntity.noContent().<Void>build());
    }

    @PutMapping("/{id}/balance")
    public Single<ResponseEntity<Purse>> updateBalance(@PathVariable String id, @RequestParam double amount) {
        return purseService.updateBalance(id, amount)
                .map(updatedPurse -> ResponseEntity.ok(updatedPurse));
    }
}
