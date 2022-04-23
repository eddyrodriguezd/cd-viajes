package com.codingdojo.viajes.controllers;

import com.codingdojo.viajes.models.Expense;
import com.codingdojo.viajes.services.ExpenseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/expenses";
    }

    @GetMapping("/expenses")
    public String listExpenses(Model model, @ModelAttribute("expense") Expense expense) {
        List<Expense> expenses = expenseService.getAllExpenses();
        System.out.println("Expenses retrieved: " + expenses.size());
        model.addAttribute("expenses", expenses);
        return "expenses";
    }

    @PostMapping("/expenses")
    public String create(@Valid @ModelAttribute("expense") Expense expense, BindingResult result) {
        System.out.println("Expense <" + expense + "> will be created");
        if (result.hasErrors()) {
            return "index";
        } else {
            expenseService.createExpense(expense);
            return "redirect:/expenses";
        }
    }

    @GetMapping("/expenses/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        Expense expense = expenseService.findExpense(id);
        model.addAttribute("expense", expense);
        return "/expenses/edit.jsp";
    }

    @PutMapping("/expenses/{id}")
    public String update(@Valid @ModelAttribute("expense") Expense expense, BindingResult result) {
        if (result.hasErrors()) {
            return "/expenses/edit.jsp";
        } else {
            expenseService.updateExpense(expense);
            return "redirect:/expense";
        }
    }

    @DeleteMapping("/expenses/{id}")
    public String delete(@PathVariable("id") Long id) {
        expenseService.deleteExpense(id);
        return "redirect:/expenses";
    }
}
