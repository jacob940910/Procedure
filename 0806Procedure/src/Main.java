import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

public class Main {

	public static void main(String[] args) {
		Connection con = null;
		CallableStatement call = null;
		try {
			// 데이터베이스 드라이버 클래스 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 데이터베이스 연결
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
			// 실행할 statement 객체 만들기
			//call = con.prepareCall("{call insertDeptProc(?,?,?)}");
			call = con.prepareCall("{call deleteDeptProc(?)}");
			//물음표에 실제 값 바인딩
			//call.setInt(1, 11);
			//call.setString(2, "회계");
			//call.setString(3, "부산");
			call.setInt(1, 11);
			//삽입 구문 실행
			int r = call.executeUpdate();
			if(r > 0) {
				System.out.println("작업 성공");
			}else {
				System.out.println("작업 실패");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					call.close();
					con.close();
				} catch (Exception e) {
				}
			}
		}
	}

}