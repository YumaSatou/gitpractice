package dao;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.KadaiDTO;
import util.GenerateHashedPw;
import util.GenerateSalt;

public class KadaiDAO {

	private static Connection getConnection() throws URISyntaxException, SQLException {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	    URI dbUri = new URI(System.getenv("DATABASE_URL"));

	    String username = dbUri.getUserInfo().split(":")[0];
	    String password = dbUri.getUserInfo().split(":")[1];
	    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

	    return DriverManager.getConnection(dbUrl, username, password);
	}
	
	public static int registerAccount(KadaiDTO kadaiaccount) {
		String sql = "INSERT INTO kadaiaccount VALUES(default, ?, ?, ?, ?, ?, ?, ?, current_timestamp)";
		int result = 0;
		
		// ランダムなソルトの取得(今回は32桁で実装)
		String salt = GenerateSalt.getSalt(32);
		
		// 取得したソルトを使って平文PWをハッシュ
		String hashedPw = GenerateHashedPw.getSafetyPassword(kadaiaccount.getPassword(), salt);
		
		try (
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, kadaiaccount.getName());
			pstmt.setString(2, kadaiaccount.getAge());
			pstmt.setString(3, kadaiaccount.getGender());
			pstmt.setString(4, kadaiaccount.getNumber());
			pstmt.setString(5, kadaiaccount.getMail());
			pstmt.setString(6, salt);
			pstmt.setString(7, hashedPw);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} finally {
			System.out.println(result + "件更新しました。");
		}
		return result;
	}
	public static List<KadaiDTO> selectAllkadaiaccount() {
		
		// 返却用変数
		List<KadaiDTO> result = new ArrayList<>();

		String sql = "SELECT * FROM kadaiaccount";
		
		try (
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			try (ResultSet rs = pstmt.executeQuery()){
				while(rs.next()) {
					
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String age = rs.getString("age");
					String gender = rs.getString("gender");
					String number = rs.getString("number");
					String mail = rs.getString("mail");
					String salt = rs.getString("salt");
					String password = rs.getString("password");
					
					KadaiDTO Ac = new KadaiDTO(id, name, age, gender, number, mail, salt, password, null);
					
					result.add(Ac);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		// Listを返却する。0件の場合は空のListが返却される。
		return result;
	}
	public static int deletekadaiaccount(String mail) {
		
		
		String sql = "DELETE FROM kadaiaccount WHERE mail = ?";
		int result = 0;

		try (
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);		// 構文解析
				){
			
			pstmt.setString(1, mail);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} finally {
			System.out.println(result + "件削除しました。");
		}
		return result;
	}
	
	// メールアドレスを元にソルトを取得
		public static String getSalt(String mail) {
			String sql = "SELECT salt FROM kadaiaccount WHERE mail = ?";
			
			try (
					Connection con = getConnection();
					PreparedStatement pstmt = con.prepareStatement(sql);
					){
				pstmt.setString(1, mail);

				try (ResultSet rs = pstmt.executeQuery()){
					
					if(rs.next()) {
						String salt = rs.getString("salt");
						return salt;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		// ログイン処理
		public static KadaiDTO login(String mail, String hashedPw) {
			String sql = "SELECT * FROM kadaiaccount WHERE mail = ? AND password = ?";
			
			try (
					Connection con = getConnection();
					PreparedStatement pstmt = con.prepareStatement(sql);
					){
				pstmt.setString(1, mail);
				pstmt.setString(2, hashedPw);

				try (ResultSet rs = pstmt.executeQuery()){
					
					if(rs.next()) {
						int id = rs.getInt("id");
						String name = rs.getString("name");
						String age = rs.getString("age");
						String gender = rs.getString("gender");
						String number = rs.getString("number");
						String salt = rs.getString("salt");
						String createdAt = rs.getString("created_at");
						
						return new KadaiDTO(id, name, age, gender, number, mail, salt, null, null);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			return null;
		}
}


