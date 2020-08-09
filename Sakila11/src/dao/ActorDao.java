package dao;
import java.sql.*;
import java.util.*;



import util.DBUtil;
import vo.*;
public class ActorDao {
	public ArrayList<Actor> searchActorByPage(int beginRow, int rowPerPage, String searchWord) throws Exception{
		System.out.println("beginRow..." + beginRow);
		System.out.println("rowPerPage..." + rowPerPage);
		System.out.println("Searchword..." + searchWord);
		
		String sql = "select * from actor where (first_name like ?)or(last_name like ?)order by actor_id asc limit ?,?";
		DBUtil dbutil = new DBUtil();
		Connection conn = dbutil.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%"+searchWord+"%");
		stmt.setString(2, "%"+searchWord+"%");
		stmt.setInt(3, beginRow);
		stmt.setInt(4, rowPerPage);
		ResultSet rs = stmt.executeQuery();
		ArrayList<Actor> list = new ArrayList<>();
		while (rs.next()) {
			Actor actor = new Actor();
			actor.setActorId( rs.getInt("actor_id"));
			actor.setFirstName(rs.getString("first_name"));
			actor.setLastName(rs.getString("last_name"));
			actor.setLastUpdate(rs.getString("last_update"));
			list.add(actor);
		}
		return list;
	}
	public ArrayList<Actor> selectActorListAll(int beginRow, int rowPerPage) throws Exception{
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		String sql = "SELECT * FROM actor LIMIT ?,?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, beginRow);
		stmt.setInt(2, rowPerPage);
		ResultSet rs = stmt.executeQuery();
		ArrayList<Actor> list = new ArrayList<Actor>();
		while(rs.next()) {
			Actor actor = new Actor();
			actor.setActorId(rs.getInt("actor_id"));
			actor.setFirstName(rs.getString("first_name"));
			actor.setLastName(rs.getString("last_name"));
			actor.setLastUpdate(rs.getString("last_update"));
			list.add(actor);
		}
		return list;
	}
	
	public int selectTotalCount(int totalCount) throws Exception {
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		String sql="SELECT count(*) as count FROM actor";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			totalCount = rs.getInt("count"); 
		}
		return totalCount;
	}
	
	public void insertActor(Actor actor) throws Exception {
		Class.forName("org.mariadb.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/sakila","root","java1234");
		//System.out.println(conn+" <-- conn");
		PreparedStatement stmt = conn.prepareStatement("INSERT INTO actor(actor_id,first_name,last_name,last_update) VALUE(?,?,?,now())");
		stmt.setInt(1, actor.getActorId());
		stmt.setString(2, actor.getFirstName());
		stmt.setString(3, actor.getLastName());
		stmt.executeQuery();
		
	}
	
	public void deleteActor(int actorId) throws Exception {
		System.out.println("deleteActor------------");
		System.out.println(actorId+" <-- �궘�젣 �빐�빞�븷 �뻾");
		String driver = "org.mariadb.jdbc.Driver";
		String dbAddr = "jdbc:mariadb://localhost:3306/sakila";
		String dbId = "root";
		String dbPw = "java1234";
		String sql = "DELETE FROM actor where actor_id=?";
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(dbAddr,dbId,dbPw);
		PreparedStatement stmt = conn.prepareStatement(sql); 
		stmt.setInt(1,actorId);
		stmt.executeQuery();
	}
	public Actor selectActorOne(int actorId) throws Exception {
		System.out.println("selectActorOne------------");
		System.out.println(actorId+" <-- actorId");
		String driver = "org.mariadb.jdbc.Driver";
		String dbAddr = "jdbc:mariadb://localhost:3306/sakila";
		String dbId = "root";
		String dbPw = "java1234";
		String sql = "SELECT first_name as fname, last_name as lname FROM actor where actor_id=?";
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(dbAddr,dbId,dbPw);
		PreparedStatement stmt = conn.prepareStatement(sql); 
		stmt.setInt(1,actorId);
		ResultSet rs = stmt.executeQuery();
		Actor actor = null;
		if(rs.next()) {
			actor = new Actor();
			actor.setFirstName(rs.getString("fname"));
			actor.setLastName(rs.getString("lname"));
		}
		return actor;
	}
	
	public void updateActor(Actor actor) throws Exception {
		System.out.println("updateActor-----------");
		System.out.println(actor.getActorId()+" <-- actorId");
		System.out.println(actor.getFirstName()+" <-- firstName");
		System.out.println(actor.getLastName()+" <-- lastName");
		
		String driver = "org.mariadb.jdbc.Driver";
		String dbAddr = "jdbc:mariadb://localhost:3306/sakila";
		String dbId = "root";
		String dbPw = "java1234";
		String sql = "UPDATE actor set first_name=?, last_name=?, last_update=now() where actor_id=?";
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(dbAddr,dbId,dbPw);
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, actor.getFirstName());
		stmt.setString(2, actor.getLastName());
		stmt.setInt(3,actor.getActorId());
		stmt.executeQuery();
	}
}
