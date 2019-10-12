package com.vastika.userinfosystem.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vastika.userinfosystem.model.User;
import com.vastika.userinfosystem.util.DbUtil;

public class UserDaoImpl implements UserDao{
	
	private static final String INSERT_SQL = "insert into user_tbl(username, password, email, gender, hobbies, nationality, dob)values(?,?,?,?,?,?,?)";
	private static final String UPDATE_SQL = "update user_tbl set username=?, password=?, email=?, gender=?, hobbies=?, nationality=?,dob=? where id=?";
	private static final String DELETE_SQL = "delete from user_tbl where id=?";
	private static final String GET_BY_ID_SQL = "select * from user_tbl where id=?";
	private static final String GET_ALL_SQL = "select * from user_tbl";
	
	
	@Override
	public void saveUser(User user) {
		try(Connection con = DbUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(INSERT_SQL);){
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getGender());
			ps.setString(5,user.getHobbies());
			ps.setString(6, user.getNationality());
			ps.setDate(7, new Date(user.getDob().getTime()));
			ps.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateUser(User user) {
		try(Connection con = DbUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(UPDATE_SQL);){
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getGender());
			ps.setString(5,user.getHobbies());
			ps.setString(6, user.getNationality());
			ps.setDate(7, new Date(user.getDob().getTime()));
			ps.setInt(8, user.getId());
			ps.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteUser(int id) {
		try(Connection con = DbUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(DELETE_SQL);){
			ps.setInt(1, id);			
			ps.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public User getUserById(int id) {
		User user = new User();
		
		try(Connection con = DbUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(GET_BY_ID_SQL);){
			ps.setInt(1, id);			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setGender(rs.getString("gender"));
				user.setHobbies(rs.getString("hobbies"));
				user.setNationality(rs.getString("nationality"));
				user.setDob(rs.getDate("date"));
							}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public List<User> getAllUserInfo() {
		User user = new User();
		List<User> userList = new ArrayList<>();
		try(Connection con = DbUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(GET_ALL_SQL);){
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setGender(rs.getString("gender"));
				user.setHobbies(rs.getString("hobbies"));
				user.setNationality(rs.getString("nationality"));
				user.setDob(rs.getDate("dob"));
				userList.add(user);
				
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;
	}

}
