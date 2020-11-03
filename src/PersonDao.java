import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//DB ���� �۾�(i,u,d,s)
public class PersonDao {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url ="jdbc:oracle:thin:@localhost:1521:orcl";
	String id = "jspid";
	String pw = "jsppw";
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	PersonDao(){
		System.out.println("PersonDao()");

		try {
			//1.����̹� �ε�
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	void getConnect(){
		try { 
			// 2.������ ����
			conn = DriverManager.getConnection(url,id,pw);
			//conn �������� jspid ������ ���ӵƴٴ� ������ ���´�.
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public ArrayList<PersonBean> getAllPerson(){
		
		// 2.������ ����
		getConnect();

		//3.sql�� �м�
		String sql = "select * from person order by num asc";
		
		ArrayList<PersonBean> lists = new ArrayList<PersonBean>();
		
		try {
			
			ps = conn.prepareStatement(sql);

			// 4.sql�� ����
			rs = ps.executeQuery();

			while(rs.next()) {
				int num = rs.getInt("num");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String gender = rs.getString("gender");
				Date birth = rs.getDate("birth");

				PersonBean bean = new PersonBean();
				bean.setNum(num);
				bean.setName(name);
				bean.setAge(age);
				bean.setGender(gender);
				bean.setBirth( String.valueOf(birth));
				
				lists.add(bean);
			}


		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)
					rs.close();
				if(ps!=null)
					ps.close();
				if(conn!=null)
					conn.close();
			}catch(SQLException e) {

			}
		} // finally

		System.out.println(lists.size());
		return lists;

	} // getAllPerson()

	
	public ArrayList<PersonBean> getPersonByGender(String findGender){
		//2.
		getConnect();
		
		// 3.
		String sql = "select * from person where gender=?";
		ArrayList<PersonBean> lists = new ArrayList<PersonBean>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1,findGender);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int num = rs.getInt("num");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String gender = rs.getString("gender");
				Date birth = rs.getDate("birth");
				
				PersonBean bean = new PersonBean();
				bean.setNum(num);
				bean.setName(name);
				bean.setAge(age);
				bean.setGender(gender);
				bean.setBirth(String.valueOf(birth));
				
				lists.add(bean);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				if(rs!=null)
					rs.close();
				
				if(ps!=null)
					ps.close();
				
				if(conn!=null)
					conn.close();
			}catch(SQLException e) {
				
			}
		}// finally 
		
		System.out.println("�˻��� ������:"+lists.size()+"�� �Դϴ�.");
		return lists;
		
	}// getPersonByGender

	public int insertData(String name, int age, String gender, String birth) {
		getConnect();
		int cnt = -1 ;
		String sql = "insert into person(num,name,age,gender,birth) " + 
					"values(perseq.nextval,?,?,?,?)";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1,name);
			ps.setInt(2, age);
			ps.setString(3, gender);
			ps.setString(4, birth);
			
			
			cnt = ps.executeUpdate();
			System.out.println("cnt:" + cnt);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				
				if(ps!=null)
					ps.close();
				
				if(conn!=null)
					conn.close();
			}catch(SQLException e) {
				
			}
		}
		
		return cnt;
	}//insertData
	
	public int updateData(PersonBean bean) {
		
		System.out.println(bean.getNum());
		System.out.println(bean.getName());
		
		//2.
		getConnect();
		
		String sql = "update person set name=?, age=?, gender=?, birth=? where num=?";
		int cnt = -1;
		
		try {
			// 3.
			ps = conn.prepareStatement(sql);
			
			//�м����Ŀ� ?�߰��ϸ� ?�ڸ��� �� �����ϰ� �����ؾ� �Ѵ�.
			ps.setString(1,bean.getName());
			ps.setInt(2, bean.getAge());
			ps.setString(3,  bean.getGender());
			ps.setString(4, bean.getBirth());
			ps.setInt(5, bean.getNum());

			// 4. ����:�����ϴ� ��ɾ�� ������ �߸� sql���� ������ ���� Ȯ���� ����.
			cnt = ps.executeUpdate();
//			���ǿ� �´� �����Ͱ� ���� : 0
//			���ǿ� �´� �����Ͱ� 1�� �ִ� : 1
//			���� ���� : -1
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			
			try {
				if(ps!=null)
					ps.close();
				if(conn != null)
					conn.close();
			}catch(SQLException e) {
				
			}
		}
		
		return cnt;
	} // updateData
	
	public int deleteData(int num) {
		
		getConnect();
		
		String sql = "delete from person where num=?";
		int cnt = -1;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
			
			cnt = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null)
					ps.close();
				if(conn != null)
					conn.close();
			}catch(SQLException e) {
				
			}
		}// finally
		
		return cnt;
	}
	
	
}











