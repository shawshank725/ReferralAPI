package com.referral.ReferralAPI.controller;

import com.referral.ReferralAPI.entity.ReferralTable;
import com.referral.ReferralAPI.service.ReferService;
import com.referral.ReferralAPI.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import com.referral.ReferralAPI.entity.User;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

@Controller
public class ReferralController {

    private UserService userService;
    private ReferService referService;

    public ReferralController(UserService theUserService,ReferService theReferService){
        userService = theUserService;
        referService = theReferService;
    }

    @GetMapping("/")
    public String index(Model theModel){
        User user = new User();
        theModel.addAttribute("user", user);
        return "index";
    }

    @PostMapping("/processForm")
    public String processForm(Model theModel,
                              @Valid @ModelAttribute("user") User user,
                              BindingResult theBindingResult) {
        if (theBindingResult.hasErrors()) {
            return "index";
        }

        if (user.getName() != null && !user.getName().isEmpty()) {
            user.setProfile_completed("True");
        } else {
            user.setProfile_completed("False");
        }

        if (user.getReferredCode() == null || user.getReferredCode().isEmpty()) {
            user.setReferredCode(""); // Avoid null issues
        }

        // for adding data in referralTable
        // Ensure the user gets a generated referral code BEFORE referral logic
        user.setGeneratedCode(getSaltString());

        // Log to debug
        System.out.println("User Generated Code: " + user.getGeneratedCode());
        System.out.println("User Entered Referral Code: " + user.getReferredCode());

        // Fetch referrer from the database
        User referrer = userService.findUserByGeneratedCode(user.getReferredCode());
        System.out.println("Referrer found - " + referrer);

        // Ensure referrer exists and both profiles are completed
        if (referrer != null) {
            if ("True".equals(user.getProfile_completed()) && "True".equals(referrer.getProfile_completed())) {
                // Save the referral relationship
                ReferralTable referral = new ReferralTable(referrer.getGeneratedCode(), user.getGeneratedCode(), "True");
                referService.save(referral);

                System.out.println("Referral saved: Referrer - " + referrer.getGeneratedCode() +
                        ", Referred - " + user.getGeneratedCode());
            }
        }

        // Save the user after setting everything
        User savedUser = userService.save(user);
        System.out.println("User Registered: " + savedUser);

        return "registered";
    }

    @GetMapping("/showUsers")
    public String showUsers(Model theModel){
        List<User> allUsers = userService.findAll();
        theModel.addAttribute("users", allUsers);
        return "showUsers";
    }

    @GetMapping("/showReferrals")
    public String showReferrals(Model theModel){
        List<ReferralTable> allReferrals = referService.findAll();
        theModel.addAttribute("referrals", allReferrals);
        return "showReferrals";
    }

    @GetMapping("/downloadUsersCSV")
    public void downloadUsersCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=users.csv");

        PrintWriter writer = response.getWriter();
        writer.println("ID,Name,Email,Generated Code,Referred Code,Profile Completed");

        List<User> users = userService.findAll();
        for (User user : users) {
            writer.println(user.getId() + "," +
                    user.getName() + "," +
                    user.getEmail() + "," +
                    user.getGeneratedCode() + "," +
                    user.getReferredCode() + "," +
                    user.getProfileCompleted());
        }

        writer.flush();
        writer.close();
    }

    @GetMapping("/downloadReferralsCSV")
    public void downloadReferralsCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=referrals.csv");

        PrintWriter writer = response.getWriter();
        writer.println("ID,Referrer Code,Referred Code,Successful");

        List<ReferralTable> referralTables = referService.findAll();
        for (ReferralTable temp : referralTables) {
            writer.println(temp.getId() + "," +
                    temp.getReferrerCode() + "," +
                    temp.getReferredCode() + "," +
                    temp.getSuccessful());
        }

        writer.flush();
        writer.close();
    }
    protected static String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 6) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
}
