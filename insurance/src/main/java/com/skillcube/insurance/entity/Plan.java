package com.skillcube.insurance.entity;

import org.springframework.stereotype.Component;

@Component
public class Plan {

	private String planId;
	
	private String planName;
	
	private int period;
	
	private double amount;

	
	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Plan(String planId, String planName, int period, double amount) {
		super();
		this.planId = planId;
		this.planName = planName;
		this.period = period;
		this.amount = amount;
	}

	public Plan() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Plan [planId=" + planId + ", planName=" + planName + ", period=" + period + ", amount=" + amount + "]";
	}
	
	
}
