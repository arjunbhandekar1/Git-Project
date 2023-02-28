package com.skillcube.insurance.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillcube.insurance.entity.Plan;
import com.skillcube.insurance.repo.PlanRepo;
import com.skillcube.insurance.service.PlanService;

@Service
public class PlanServImpl implements PlanService{

	@Autowired
	PlanRepo planRepo;
	
	@Override
	public void savePlan(Plan p) {
	
		planRepo.savePlan(p);
	}

}
