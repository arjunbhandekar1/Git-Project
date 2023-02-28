package com.skillcube.insurance.service;

import org.springframework.stereotype.Service;

import com.skillcube.insurance.entity.Plan;

@Service
public interface PlanService {

	public void savePlan(Plan p);

}
