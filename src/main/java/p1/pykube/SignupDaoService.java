package p1.pykube;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SignupDaoService {

	public static void update(SignupDTO signupDTO) {
		try {
			//
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/batch100_db", "root",
					"mysql@1234");
			String sql = "update psignup_tbl set username=?,password=?,email=?,mobile=?,address=? where pid = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, signupDTO.getUsername());
			pstmt.setString(2, signupDTO.getPassword());
			pstmt.setString(3, signupDTO.getEmail());
			pstmt.setLong(4, Long.parseLong(signupDTO.getMobile()));
			pstmt.setString(5, signupDTO.getAddress());
			pstmt.setInt(6, signupDTO.getPid());
			// FIRE THE QUERY
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SignupDTO findByPid(int pid) {

		SignupDTO signupDTO = new SignupDTO();
		try {
			//
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/batch100_db", "root",
					"mysql@1234");
			String sql = "select pid,username,password,email,mobile,address,doe from psignup_tbl where pid = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pid);
			// FIRE THE QUERY
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				// Creating object for each row
				signupDTO.setPid(rs.getInt(1));
				signupDTO.setUsername(rs.getString(2));
				signupDTO.setPassword(rs.getString(3));
				signupDTO.setEmail(rs.getString(4));
				// valueOf convets Long into String object
				signupDTO.setMobile(String.valueOf(rs.getLong(5)));
				signupDTO.setAddress(rs.getString(6));
				signupDTO.setDoe(rs.getTimestamp(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return signupDTO;
	}

	public static void deleteByPid(int pid) {
		try {
			//
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/batch100_db", "root",
					"mysql@1234");
			String sql = "delete from psignup_tbl where pid = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pid);
			// FIRE THE QUERY
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void save(SignupDTO signupDTO) {
		try {
			//
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/batch100_db", "root",
					"mysql@1234");
			String sql = "insert into psignup_tbl(username,password,email,mobile,address,doe) values(?,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, signupDTO.getUsername());
			pstmt.setString(2, signupDTO.getPassword());
			pstmt.setString(3, signupDTO.getEmail());
			pstmt.setLong(4, Long.parseLong(signupDTO.getMobile()));
			pstmt.setString(5, signupDTO.getAddress());
			Timestamp doe = new Timestamp(new Date().getTime());
			pstmt.setTimestamp(6, doe);
			// FIRE THE QUERY
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static List<SignupDTO> findAll() {
		List<SignupDTO> signupDTOs = new ArrayList<SignupDTO>();
		try {
			//
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/batch100_db", "root",
					"mysql@1234");
			String sql = "select pid,username,password,email,mobile,address,doe from psignup_tbl";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// FIRE THE QUERY
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// Creating object for each row
				SignupDTO signupDTO = new SignupDTO();
				signupDTO.setPid(rs.getInt(1));
				signupDTO.setUsername(rs.getString(2));
				signupDTO.setPassword(rs.getString(3));
				signupDTO.setEmail(rs.getString(4));
				// valueOf convets Long into String object
				signupDTO.setMobile(String.valueOf(rs.getLong(5)));
				signupDTO.setAddress(rs.getString(6));
				signupDTO.setDoe(rs.getTimestamp(7));
				// Every object we are adding inside list
				signupDTOs.add(signupDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return signupDTOs;
	}

}
