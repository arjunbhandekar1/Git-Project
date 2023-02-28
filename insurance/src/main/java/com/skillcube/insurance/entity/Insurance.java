package com.skillcube.insurance.entity;

import org.springframework.stereotype.Component;

@Component
public class Insurance {

	private String insuranceId;
	private String insuranceType;
	private Plan plans;

	public Insurance(String insuranceId, String insuranceType, Plan plans) {
		super();
		this.insuranceId = insuranceId;
		this.insuranceType = insuranceType;
		this.plans = plans;
	}

	public String getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}

	public String getInsuranceId() {
		return insuranceId;
	}

	public void setInsuranceId(String insuranceId) {
		this.insuranceId = insuranceId;
	}

	public Plan getPlans() {
		return plans;
	}

	public void setPlans(Plan plans) {
		this.plans = plans;
	}

	public Insurance(String insuranceId, Plan plans) {
		super();
		this.insuranceId = insuranceId;
		this.plans = plans;
	}

	public Insurance() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Insurance [insuranceId=" + insuranceId + ", insuranceType=" + insuranceType + ", plans=" + plans + "]";
	}

}
