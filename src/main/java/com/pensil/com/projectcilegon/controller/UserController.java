package com.pensil.com.projectcilegon.controller;

import com.pensil.com.projectcilegon.model.TestSchedule;
import com.pensil.com.projectcilegon.model.User;
import com.pensil.com.projectcilegon.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) { this.userService = userService; }

    @GetMapping("/signup")
    public String showNewUserFormAndUserList(Model model) {
        model.addAttribute("message", "Silahkan untuk dibuat akun testnya dibawah.");

        List<User> userList = userService.getUser();
        model.addAttribute("userList", userList);

        return "newUserFormAndList";
    }



    @PostMapping("/signup")
    public String addUser(User user, Model model) {

        try {
            userService.addUser(user);
            model.addAttribute("successMessage", String.format("User baru %s berhasil ditambahkan", user.getEmail()));

            Collection<User> userCollection = userService.getUser();
            model.addAttribute("userList", userCollection);

            return "newUserFormAndList";
        } catch (Exception ex) {
            model.addAttribute("errorMessage", String.format("Tidak bisa isi %s karena email sudah terdaftar", user.getEmail()));
            model.addAttribute("userList", userService.getUser());
            return "newUserFormAndList";
        }
    }
    // http://localhost:8080/jadwaltest/
    @GetMapping("/jadwaltest")
    public String showTestScheduleDetail(Model model) {
        String nextPage = showNewUserFormAndUserList(model);
        return nextPage;
    }

    // http://localhost:8080/jadwaltest/1001
    @GetMapping("/jadwaltest/{userId}")
    public String showTestScheduleDetail(@PathVariable int userId, Model model) {
        try {
            User foundUserInDatabase = userService.getUser(userId);
            model.addAttribute("user", foundUserInDatabase);
            List<TestSchedule> futureList = new ArrayList<>();
            List<TestSchedule> pastList = new ArrayList<>();
            userService.listTestScheduleForUserId(userId, pastList, futureList);

            model.addAttribute("pastScheduleList", pastList);
            model.addAttribute("futureScheduleList", futureList);

        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());
        }

        return "BuatJadwalTest";
    }

    @PostMapping("/jadwaltest")
    public String addTestScheduleDetail(TestSchedule testSchedule, Model model) {
        try {
            userService.addTestSchedule(testSchedule);
            long userId = testSchedule.getUserId();
            List<TestSchedule> futureList = new ArrayList<>();
            List<TestSchedule> pastList = new ArrayList<>();

            userService.listTestScheduleForUserId(userId, pastList, futureList);

            model.addAttribute("pastScheduleList", pastList);
            model.addAttribute("futureScheduleList", futureList);

            model.addAttribute("successMessage", String.format("Jadwal dan waktu berhasil ditentukan %n", futureList));

            User foundUserInDatabase = userService.getUser(userId);
            model.addAttribute("user", foundUserInDatabase);

        } catch (Exception ex) {
            model.addAttribute("errorMessage", ("Jadwal dan waktu gagal untuk di isi"));
        }
        return "BuatJadwalTest";
    }

    @GetMapping("/jadwaltest/{userId}/{testScheduleId}")
    public String editAndRemoveTest(Model model){
        return "BuatJadwalTest";
    }
}
