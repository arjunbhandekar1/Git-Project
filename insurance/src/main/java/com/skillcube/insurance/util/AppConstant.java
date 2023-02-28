package com.skillcube.insurance.util;

public class AppConstant {

	static String dbURL = "jdbc:mysql://localhost:3306/insurancedb";
	static String username = "root";
	static String password = "root";

	public static String sqlAllInsurance = "select * from insurance";
	public static String sqlFindInsurance = "select * from insurance where insuranceid=?";
	public static String sqlFindInsuranceByPlanId = "select * from insurance where planid=?";
	
	public static String sqlSavePlan = "insert into plan (planid,planName,period,amount) values (?,?,?,?)";
	
	public static String sqlSaveInsurance = "insert into insurance(insuranceid,type,planid) values (?,?,?)";
	
	
	
//	public static String sqlUpdateInsurance = "update insurance set name =? where id=?";
//	public static String sqlDeleteInsurance = "delete from insurance  where id=?";

}
