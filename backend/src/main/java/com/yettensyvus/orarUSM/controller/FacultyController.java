package com.yettensyvus.orarUSM.controller;

import com.yettensyvus.orarUSM.dto.FacultyDto;
import com.yettensyvus.orarUSM.service.FacultyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("/faculties")
    public String getFaculties(Model model) {
        List<FacultyDto> faculties = facultyService.getAllFaculties();
        model.addAttribute("faculties", faculties);
        return "faculties"; // faculties.html
    }
}
