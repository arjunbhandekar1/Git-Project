package com.skillcube.insurance.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.skillcube.insurance.entity.Plan;
import com.skillcube.insurance.util.AppConstant;
import com.skillcube.insurance.util.DBConnection;

@Repository
public class PlanRepo {
	
	
	

	public PlanRepo() {
		super();

		
	}

	public void savePlan(Plan p) {
		Connection con = null;
		int id = 0;
		try {
			con = DBConnection.getConnection();
			String sql = AppConstant.sqlSavePlan;

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, p.getPlanId());
			ps.setString(2, p.getPlanName());
			ps.setInt(3, p.getPeriod());
			ps.setDouble(4, p.getAmount());

			id = ps.executeUpdate();

		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();

		} finally {

			System.out.println("Number of plans created=" + id);
		}
	}

}
