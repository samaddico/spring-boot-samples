package com.samaddico.jacoco.employeeapp.controller;

import com.samaddico.jacoco.employeeapp.form.EmployeeForm;
import com.samaddico.jacoco.employeeapp.form.JsonResponse;
import com.samaddico.jacoco.employeeapp.model.Employee;
import com.samaddico.jacoco.employeeapp.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController()
@ControllerAdvice
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);


    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public JsonResponse createEmployee(@RequestBody @Valid EmployeeForm form){

        //set employee object here
        Employee newEmployee = new Employee();
        newEmployee.setEmail(form.getEmail());
        newEmployee.setDepartment(form.getDepartment());
        newEmployee.setRole(form.getRole());
        newEmployee.setFirstName(form.getFirstName());
        newEmployee.setLastName(form.getLastName());
        newEmployee.setAppointmentDate(new Date());

        JsonResponse response = new JsonResponse();
        response.setStatus(true);
        response.setMessage("SUCCESS");
        response.setData(employeeService.addNew(newEmployee));//save it here and return the object

        logger.info("[className: EmployeeController][method: createEmployee][message : Successfully saved employee data][fname: {} , lname: {} , email: {} ,role: {}, department:{} ]"
                , new Object[]{form.getFirstName(),form.getLastName(),form.getEmail(),form.getRole(),form.getDepartment(),});

        return response;
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public JsonResponse listEmployee(){

        List<Employee> list = employeeService.listAll();
        JsonResponse response = new JsonResponse();
        response.setStatus(true);
        response.setMessage("SUCCESS");
        response.setData(list);

        logger.info("[className: EmployeeController][method: listEmployee][message : Retrieve all employee data][count: {}]"
                , new Object[]{list.size()});

        return response;
    }



    @RequestMapping(value = "/uat")
    public Object uat(){
        Map<String,Object> response = new HashMap<>();
        response.put("status",true);
        response.put("message","success");
        response.put("result","I was committed today by Spiriiituuuuuaaaaal on uat");
        logger.info("[className: EmployeeController][method: uat][api response][environmenet: {} status: {}]", new Object[]{"UAT","FAILED"});

        return response;
    }


    @RequestMapping(value = "/prod")
    public Object prod(){
        Map<String,Object> response = new HashMap<>();
        response.put("status",true);
        response.put("message","success");
        response.put("result","I was committed today by Philip on prod area");
        return response;
    }

    @RequestMapping(value = "/test")
    public Object test(){
        Map<String,Object> response = new HashMap<>();
        response.put("status",true);
        response.put("message","success");
        response.put("result","I was committed today by Justice on test bed");
        return response;
    }

    @ResponseBody
    @ExceptionHandler(IllegalStateException.class)
    public JsonResponse missingParamter(IllegalStateException ise){
        JsonResponse response = new JsonResponse();
        response.setStatus(false);
        response.setMessage("missing parameter");
        return response;
    }

}

