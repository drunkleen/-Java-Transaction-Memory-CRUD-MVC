package com.drunkleen.crudmvc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.drunkleen.crudmvc.domain.Transaction;
import com.drunkleen.crudmvc.services.TransactionService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    private TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("")
    public String listTransactions(Model model, HttpServletRequest request) {

        List<Transaction> transactions = transactionService.findAllSortByIdAsc();
        model.addAttribute("transactions", transactions);
        model.addAttribute("request", request);

        return "transactions-list";
    }

    @GetMapping("/add-transaction")
    public String addTransactionForm(Model model, HttpServletRequest request) {
        Transaction transaction = new Transaction();

        model.addAttribute("transaction", transaction);
        model.addAttribute("request", request);

        return "transaction-add-form";
    }

    @PostMapping("/add-transaction")
    public String addTransaction(Model model, @Valid @ModelAttribute("transaction") Transaction transaction,
            BindingResult bindingResult, HttpServletRequest request) {

        model.addAttribute("request", request);

        if (bindingResult.hasErrors()) {
            model.addAttribute("transaction", transaction);
            return "transaction-add-form";
        }

        transactionService.save(transaction);
        model.addAttribute("request", request);

        return "redirect:/transactions";
    }

    @GetMapping("/update-transaction")
    public String updateTransaction(Model model, @RequestParam("id") int id) {

        Transaction transaction = transactionService.findById(id);

        model.addAttribute("transaction", transaction);

        return "transaction-add-form";
    }

    @GetMapping("/delete-transaction")
    public String deleteTransaction(Model model, @RequestParam("id") int id) {
        Transaction transaction = transactionService.findById(id);

        transactionService.deleteById(transaction.getId());

        return "redirect:/transactions";
    }
}
