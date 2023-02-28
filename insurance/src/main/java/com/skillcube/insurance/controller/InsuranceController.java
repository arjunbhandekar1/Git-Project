package com.skillcube.insurance.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.skillcube.insurance.entity.Insurance;
import com.skillcube.insurance.entity.Plan;
import com.skillcube.insurance.service.InsuranceService;

@RestController
public class InsuranceController {

	@Autowired
	InsuranceService insuranceService;

	@GetMapping("/insu")
	public List<Insurance> getAllInsurances() {
		List<Insurance> insurances = new ArrayList<>();
		insurances.add(new Insurance("IN012",new Plan("PL12", "Monthly",10, 2250.50)));
		insurances.add(new Insurance("IN013",new Plan("PL13", "Monthly",20, 3250.50)));
		
		
		return insurances;

	}

	@PostMapping("/insurance")
	public void saveInsurance(@RequestBody Insurance i) {
		insuranceService.saveInsurance(i);
	}

	@GetMapping("/insurance/{insuranceId}")
	public Insurance findByInsuranceId(@PathVariable("insuranceId") String id) {
		return insuranceService.findInsuranceById(id);

	}

	@GetMapping("/insurance1/{planid}")
	public Insurance findByInsuranceByPlanId(@PathVariable("planid") String pid) {
		return insuranceService.findInsuranceById(pid);

	}

}
