package com.skillcube.insurance.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skillcube.insurance.entity.Insurance;

@Service
public interface InsuranceService {

	List<Insurance> getAllInsurance();

	public Insurance findInsuranceByPlanId(String id);

	public void saveInsurance(Insurance i);

	public Insurance findInsuranceById(String id);

//	public void updateInsurance(Insurance i);
//
//	public void deleteInsurance(Insurance i);
}
