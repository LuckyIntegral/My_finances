package my.finances.controller;

import lombok.AllArgsConstructor;
import my.finances.dto.AccountShortInfo;
import my.finances.dto.AccountWithTransactionsDTO;
import my.finances.facade.AccountFacade;
import my.finances.persistence.entity.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@AllArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

    private final AccountFacade accountFacade;

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@RequestBody Account entity, @PathVariable Long id) {
        accountFacade.update(entity, id);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        accountFacade.delete(id);
        return ResponseEntity.ok(true);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountWithTransactionsDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(accountFacade.findById(id));
    }

    @GetMapping
    public ResponseEntity<Collection<AccountShortInfo>> findAll() {
        return ResponseEntity.ok(accountFacade.findAll());
    }
}
