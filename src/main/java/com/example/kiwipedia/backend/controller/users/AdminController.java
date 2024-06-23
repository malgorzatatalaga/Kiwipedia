package com.example.kiwipedia.backend.controller.users;

import com.example.kiwipedia.backend.model.EditHistory;
import com.example.kiwipedia.backend.model.UserEntity;
import com.example.kiwipedia.backend.repository.UserRepository;
import com.example.kiwipedia.backend.service.EditHistoryService;
import com.example.kiwipedia.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EditHistoryService editHistoryService;

    @GetMapping("/dashboard")
    public String admin() {
        return "admin";
    }

    @GetMapping("/manage-users")
    public String manageUsers(Model model) {
        List<UserEntity> users = (List<UserEntity>) userRepository.findAll();
        model.addAttribute("users", users);
        return "zarzadzanie-uzytkownikami";
    }

    @Autowired
    private UserService userService;

    @PostMapping("/manage-users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        userService.deleteUserById(id);
        redirectAttributes.addFlashAttribute("successMessage", "User deleted successfully!");
        return "redirect:/admin/manage-users";
    }

    @GetMapping("/edit-user/{id}")
    public String showEditUserForm(@PathVariable("id") int id, Model model) {
        UserEntity user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", List.of("ADMIN", "USER"));
        return "edycja-uzytkownikow";
    }

    @PostMapping("/update-user/{id}")
    public String updateUser(@PathVariable("id") int id, @RequestParam("username") String username, @RequestParam("role") String role, Model model) {
        userService.updateUsernameAndRole(id, username, role);
        return "redirect:/admin/manage-users";
    }

    @GetMapping("/edit-history/kiwi-brunatny")
    public String showEditHistory(Model model) {
        List<EditHistory> editHistoryList = editHistoryService.getEditHistoryByPageName("kiwi-brunatny");
        model.addAttribute("editHistoryList", editHistoryList);
        return "historia-edycji";
    }

    @GetMapping("/stats")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getAdminStats() {
        int currentYear = java.time.Year.now().getValue();
        List<Object[]> monthlyRegistrationsRaw = userService.getMonthlyRegistrations(currentYear);

        Integer[] monthlyRegistrations = new Integer[12];
        for (int i = 0; i < 12; i++) {
            monthlyRegistrations[i] = 0;
        }
        for (Object[] entry : monthlyRegistrationsRaw) {
            int month = (int) entry[0] - 1;
            int count = ((Number) entry[1]).intValue();
            monthlyRegistrations[month] = count;
        }

        Map<String, Object> stats = new HashMap<>();
        stats.put("monthlyRegistrations", monthlyRegistrations);
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/statistics")
    public String showStatistics(Model model) {
        return "statystyki-uzytkownicy";
    }

    @GetMapping("/pages")
    public ResponseEntity<Map<String, Integer>> getPageEditStats() {
        Map<String, Integer> pageEditCounts = editHistoryService.getPageEditCounts();
        System.out.println("Page Edit Counts: " + pageEditCounts);
        return ResponseEntity.ok(pageEditCounts);
    }

    @GetMapping("/pages-statistics")
    public String showEditStatistics(Model model) {
        return "statystyki-strony";
    }
}
