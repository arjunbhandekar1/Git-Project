package com.skillcube.insurance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.skillcube.insurance.entity.Plan;
import com.skillcube.insurance.service.PlanService;

@RestController
public class PlanController {

	@Autowired
	PlanService planService;

	@PostMapping("/Plan")
	public void savePlan(@RequestBody Plan p) {
		planService.savePlan(p);
	}
}
