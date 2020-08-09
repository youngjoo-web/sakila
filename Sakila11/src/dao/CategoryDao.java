package dao;
import vo.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import util.DBUtil;


public class CategoryDao { // 鍮꾩쫰�땲�뒪 濡쒖쭅 (�꽌鍮꾩뒪濡쒖쭅) - model1
	public ArrayList<Category> selectSearchCategoryList(int beginRow, int rowPerPage, String searchWord) throws Exception{
		DBUtil dbutil = new DBUtil();
		Connection conn = dbutil.getConnection();
		String sql = "select * from category where name like ? order by category_id asc limit ?, ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%"+searchWord+"%");
		stmt.setInt(2, beginRow);
		stmt.setInt(3, rowPerPage);
		ResultSet rs = stmt.executeQuery();
		ArrayList<Category> list = new ArrayList<>();
		while(rs.next()) {
			Category ctgr = new Category();
			ctgr.setCategoryId(rs.getInt("category_id"));
			ctgr.setName(rs.getString("name"));
			ctgr.setLastUpdate(rs.getString("last_update"));
			list.add(ctgr);
		}
		
		return list;
	}

	
	public void insertCategory(Category category) throws Exception {
		System.out.println("-------insertCategory-------");
		System.out.println(category.getCategoryId()+" <-- categoryId");
		System.out.println(category.getName()+" <-- name");
		Class.forName("org.mariadb.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/sakila","root","java1234");
		System.out.println(conn+" <-- conn");
		PreparedStatement stmt = conn.prepareStatement("INSERT INTO category(category_id, name, last_update) VALUE(?,?,now())");
		stmt.setInt(1,category.getCategoryId());
		stmt.setString(2,category.getName());
		stmt.executeQuery();
	}
	
	public void deleteCategory(int categoryId) throws Exception {
		System.out.println("--------insertCategory--------");
		System.out.println(categoryId+" <-- categoryId");
		String driver = "org.mariadb.jdbc.Driver";
		String dbAddr ="jdbc:mariadb://localhost:3306/sakila";
		String dbId ="root";
		String dbPw = "java1234";
		String sql ="DELETE FROM category WHERE category_id=?";
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(dbAddr,dbId,dbPw);
		//System.out.println(conn+" <-- conn");
		PreparedStatement stmt = conn.prepareStatement(sql);
		//System.out.println(stmt+" <-- stmt");
		stmt.setInt(1, categoryId);
		int row = stmt.executeUpdate();
		if(row==1) {
			System.out.println(categoryId+"踰� �궘�젣�꽦怨�");
		}else {
			System.out.println(categoryId+"踰� �궘�젣�떎�뙣");
		}
	}
	
	// UpdateCategoryAction.jsp (category_id �닔�젙 遺덇�)
	public void updateCategory(Category category) throws Exception {
		System.out.println("--------updateCategory--------");
		System.out.println(category.getCategoryId()+" <--  �닔�젙�븷 category_Id");
		System.out.println(category.getName()+" <--  �닔�젙 �빐�빞 �븷 �궡�슜 name");
		
		String driver = "org.mariadb.jdbc.Driver";
		String dbAddr ="jdbc:mariadb://localhost:3306/sakila";
		String dbId ="root";
		String dbPw = "java1234";
		String sql = "update category SET name=?, last_update=now() WHERE category_id=?";
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(dbAddr,dbId,dbPw);
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1,category.getName());
		stmt.setInt(2,category.getCategoryId());
		stmt.executeQuery();
	}
	
	//UpdateForm
	// Select * from category where category_id=?
	public Category selectCategoryOne(int categoryId) throws Exception{
		System.out.println("--------selectCategoryOne--------");
		System.out.println(categoryId+" <-- categoryId");
		Class.forName("org.mariadb.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/sakila","root","java1234");
		//System.out.println(conn+" <-- conn");
		PreparedStatement stmt = conn.prepareStatement("Select * from category where category_id=?");
		stmt.setInt(1,categoryId);
		//System.out.println(stmt+" <-- stmt");
		ResultSet rs = stmt.executeQuery();
		Category category = null;
		while(rs.next()){
			category = new Category();
			//category.categoryId = rs.getInt("category_id");
			category.setCategoryId(rs.getInt("category_id"));
			category.setName(rs.getString("name"));
			category.setLastUpdate(rs.getString("last_update"));
		}
			return category;
	}
	
	
	
	//select * from category
	public ArrayList<Category> selectCategoryAll(int rowPerPage, int beginRow) throws Exception{
		System.out.println("------selectCategoryAll------");
		System.out.println(rowPerPage+" <-- rowPerPage");
		System.out.println(beginRow+" <-- beginRow");
		Class.forName("org.mariadb.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/sakila","root","java1234");
		//System.out.println(conn+" <-- conn");
		PreparedStatement stmt = conn.prepareStatement("SELECT * from category LIMIT ?,?");
		stmt.setInt(1, beginRow);
		stmt.setInt(2, rowPerPage);
		//System.out.println(stmt+" <-- stmt");
		ResultSet rs = stmt.executeQuery();
		// Result -> ArrrayList
		ArrayList<Category> list
			= new ArrayList<Category>();
			while(rs.next()) {
		//	HashMap<String, Object> map = new HashMap<String, Object>();
			Category c = new Category();
			c.setCategoryId(rs.getInt("category_id"));
			c.setName(rs.getString("name"));
			c.setLastUpdate(rs.getString("last_update"));
			list.add(c);
		}
		return list;
	}
	public int selectTotalCount() throws Exception{
		System.out.println("------selectCategoryByLastPage------");	
		Class.forName("org.mariadb.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/sakila","root","java1234");
		//System.out.println(conn+" <-- conn");
		PreparedStatement stmt = conn.prepareStatement("SELECT count(*) as cnt from category");
		ResultSet rs = stmt.executeQuery();
		int totalCount = 0;
		if(rs.next()) {
			totalCount = rs.getInt("cnt");
		}
		System.out.println(totalCount+" <-- totalCount");
		return totalCount;
	}
}
