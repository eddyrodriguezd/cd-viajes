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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        model.addAttribute("expenses", expenses);
        return "expenses";
    }

    @PostMapping("/expenses")
    public String create(@ModelAttribute("expense") @Valid Expense expense, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            System.out.println("Expense <" + expense + "> has errors so it won't be created");
            redirectAttributes.addFlashAttribute("error", true);
            redirectAttributes.addFlashAttribute("errorMessage", "New expense doesn't follow the parameters criteria");
        }
        else {
            System.out.println("Expense <" + expense + "> will be created");
            redirectAttributes.addFlashAttribute("error", false);
            expenseService.createExpense(expense);
        }
        return "redirect:/expenses";
    }

    @GetMapping("/expenses/{id}")
    public String listExpenseDetails(@PathVariable("id") Long id, Model model) {
        Expense expense = expenseService.findExpense(id);
        System.out.println("Expense found: " + expense);
        model.addAttribute("expense", expense);
        return "details";
    }

    @GetMapping("/expenses/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        Expense expense = expenseService.findExpense(id);
        model.addAttribute("expense", expense);
        return "edit";
    }

    @PutMapping("/expenses/edit/{id}")
    public String update(@ModelAttribute("expense") @Valid Expense expense, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            System.out.println("Expense <" + expense + "> has errors so it won't be updated");
            redirectAttributes.addFlashAttribute("error", true);
            redirectAttributes.addFlashAttribute("errorMessage", "New expense doesn't follow the parameters criteria");
            return "edit";
        } else {
            redirectAttributes.addFlashAttribute("error", false);
            expenseService.updateExpense(expense);
            System.out.println("Expense updated: <" + expense + ">");
            return "redirect:/expenses";
        }
    }

    @DeleteMapping("/expenses/{id}")
    public String delete(@PathVariable("id") Long id) {
        expenseService.deleteExpense(id);
        return "redirect:/expenses";
    }
}
