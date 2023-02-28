package com.skillcube.insurance.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillcube.insurance.entity.Insurance;
import com.skillcube.insurance.exception.InsuranceAlreadyExistException;
import com.skillcube.insurance.repo.InsuranceRepo;
import com.skillcube.insurance.service.InsuranceService;

@Service
public class InsuranceSerImpl implements InsuranceService {

	@Autowired
	InsuranceRepo insuranceRepo;

	@Override
	public List<Insurance> getAllInsurance() {

		return insuranceRepo.getAllInsurance();
	}

	@Override
	public Insurance findInsuranceById(String id) {

		return insuranceRepo.findInsuranceById(id);

	}

	@Override
	public void saveInsurance(Insurance i) {

		try {
			insuranceRepo.saveInsurance(i);
		} catch (InsuranceAlreadyExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Insurance findInsuranceByPlanId(String id) {

		return insuranceRepo.findInsuranceByPlanId(id);
	}

}
