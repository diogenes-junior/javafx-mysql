package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DBException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao{
	
	private Connection conn = null;
	
	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department obj) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("INSERT INTO department(Name) VALUES(?)", 
				Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getName());
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected>0) {
				ResultSet rs = null;
				rs = st.getGeneratedKeys();
				if(rs.next()) {
					obj.setId(rs.getInt(1));
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DBException("Unexpected error! No rows affected!");
			}
			
		}
		catch(SQLException e){
			throw new DBException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void update(Department obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE department SET name = ? "
					+ "WHERE Id = ?");
			st.setString(1, obj.getName());
			st.setInt(2, obj.getId());
			int rowsAffected = st.executeUpdate();
			if(rowsAffected>0) {
				System.out.println("Done! Rows Affected = " + rowsAffected);
			}
			else {
				System.out.println("Unexpected error!!");
			}
		}
		catch(SQLException e){
			throw new DBException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("DELETE FROM Department WHERE Id = ?");
			st.setInt(1, id);
			int rowsAffected = st.executeUpdate();
			if(rowsAffected == 0) {
				System.out.println("Not found!");
			}
		}
		catch(SQLException e) {
			throw new DBException(e.getMessage());			
		}
		finally {
			DB.closeStatement(st);
		}		
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT * FROM department WHERE Id=?");
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()) {
				Department department = new Department();
				department = instantiateDepartment(rs);
				return department;
			}
			
			return null;
			
		}
		catch(SQLException e){
			throw new DBException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Department> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT * FROM department");
			rs = st.executeQuery();
			List<Department> list = new ArrayList<>();
			while(rs.next()) {
				Department department = instantiateDepartment(rs);
				list.add(department);
			}
			return list;
		}
		catch(SQLException e) {
			throw new DBException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}
	
	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department department = new Department();
		department.setId(rs.getInt("Id"));
		department.setName(rs.getString("Name"));
		
		return department;
	}

}
