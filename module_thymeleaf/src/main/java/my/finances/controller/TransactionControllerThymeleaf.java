package my.finances.controller;

import lombok.AllArgsConstructor;
import my.finances.api.TransactionApiService;
import my.finances.model.TransactionPostModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/transactions")
public class TransactionControllerThymeleaf {
    private final TransactionApiService transactionApiService;

    @GetMapping("/export/{id}")
    public String exportByAccId(@PathVariable Long id) {
        transactionApiService.exportByAccId(id);
        return "export_done";
    }

    @GetMapping("/export")
    public String exportAll() {
        transactionApiService.exportAll();
        return "export_done";
    }

    @GetMapping("/new")
    public String createTransactionMenu(Model model) {
        model.addAttribute("transaction", new TransactionPostModel());
        return "nep/transaction_new";
    }

    @PostMapping("/new")
    public String createUser(@ModelAttribute TransactionPostModel transaction) {
        if (!transactionApiService.create(transaction)) {
            return "400";
        }
        return "redirect:/transactions";
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("transactions", transactionApiService.findAll());
        return "elp/transactions";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        if (transactionApiService.findById(id).isPresent()) {
            model.addAttribute("transaction", transactionApiService.findById(id).get());
            return "edp/transaction_details";
        }
        return "404";
    }
}
