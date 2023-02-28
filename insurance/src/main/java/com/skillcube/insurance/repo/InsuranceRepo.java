package com.skillcube.insurance.repo;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.skillcube.insurance.entity.Insurance;
import com.skillcube.insurance.entity.Plan;
import com.skillcube.insurance.exception.InsuranceAlreadyExistException;
import com.skillcube.insurance.util.AppConstant;
import com.skillcube.insurance.util.DBConnection;

@Repository
public class InsuranceRepo {
	List<Insurance> insuList;

	public InsuranceRepo() {
		super();
		insuList = new ArrayList<>();

	}

	public List<Insurance> getAllInsurance() {

		insuList = new ArrayList<>();
		try {
			Connection con = DBConnection.getConnection();
			String sql = AppConstant.sqlAllInsurance;
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Insurance i = new Insurance(rs.getString(1), rs.getString(2),
						new Plan(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4)));
				insuList.add(i);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return insuList;

	}

	public Insurance findInsuranceById(String id) {
		Insurance i = null;
		try {
			Connection con = DBConnection.getConnection();
			String sql = AppConstant.sqlFindInsurance;
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				i = new Insurance(rs.getString(1), rs.getString(2),
						new Plan(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4)));
				insuList.add(i);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return i;
		}
	}

	public void saveInsurance(Insurance i) throws InsuranceAlreadyExistException {
		Connection con = null;
		int id = 0;
		try {
			con = DBConnection.getConnection();
			String sql = AppConstant.sqlSaveInsurance;

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, i.getInsuranceId());
			ps.setString(2, i.getInsuranceType());
			ps.setArray(3, (Array) i.getPlans());

			id = ps.executeUpdate();
			if (id == 0) {
				throw new InsuranceAlreadyExistException("insurance is already exist...");
			}

		} catch (SQLException ex) {

			ex.printStackTrace();

		} finally {

			System.out.println("Number of insurances created=" + id);
		}
	}

	public Insurance findInsuranceByPlanId(String pid) {
		Insurance i = null;
		try {
			Connection con = DBConnection.getConnection();
			String sql = AppConstant.sqlFindInsuranceByPlanId;
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, pid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				i = new Insurance(rs.getString(1), rs.getString(2),
						new Plan(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4)));
				insuList.add(i);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return i;
		}

	}

}
