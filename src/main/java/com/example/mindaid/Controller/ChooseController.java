package com.example.mindaid.Controller;
import com.example.mindaid.Dto.ChooseDto;
import com.example.mindaid.Dto.ConcernDto;
import com.example.mindaid.Dto.DoctorsDto;
import com.example.mindaid.Dto.UserDto;
import com.example.mindaid.Model.*;
import com.example.mindaid.Repository.ConcernRepository;
import com.example.mindaid.Repository.DoctorConcernRepository;
import com.example.mindaid.Repository.DoctorsRepository;
import com.example.mindaid.Repository.UserRepository;
import com.example.mindaid.Request.Signup_request;
import com.example.mindaid.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
public class ChooseController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ConcernRepository concernRepository;
    @Autowired
    Signup_request signup_request;
    @Autowired
    EmailVerificationService emailVerificationService;
    @Autowired
    UserService userService;
    @Autowired
    DoctorListService doctorListService;
    @Autowired
    DoctorConcernRepository doctorConcernRepository;
    @Autowired
    DoctorsRepository doctorsRepository;
    @Autowired
    TemporaryConcernService temporaryConcernService;
    @Autowired
    TemporaryObjectHoldService temporaryObjectHoldService;
    @Autowired
    SchedulingService schedulingService;

    @GetMapping("/choose")
    public String getChoose(Model model){
        return "choose";
    }
    @PostMapping("/doctors-list")
    public String postChoose(Model model, ChooseDto chooseDto, UserDto userDto,ConcernDto concernDto){
        List<DoctorsDto> doctorsList=doctorListService.getDoctorList(temporaryConcernService.chooseList.get(0),chooseDto);
//        List<DoctorsDto> doctorsList=doctorListService.getDoctorList(concernDto,chooseDto);
        temporaryObjectHoldService.doctorsDtoList.clear();
        temporaryObjectHoldService.doctorsDtoList.add(doctorsList);
        System.out.println(doctorsList);
        System.out.println(doctorsList.get(0));
        System.out.println("choose: "+chooseDto.contactMedia);
        model.addAttribute("doctorsList",doctorsList);
        DoctorsDto doctorDto=new DoctorsDto();
        model.addAttribute(doctorDto);
        return "doctorsListNew";
    }
}
