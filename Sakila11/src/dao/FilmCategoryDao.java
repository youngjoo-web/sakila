package dao;
import java.sql.*;
import java.util.ArrayList;
import util.DBUtil;
import vo.*;
public class FilmCategoryDao {
	public ArrayList<FilmCategory> selectFilmCategoryList1(int rowPerPage, int beginRow) throws Exception{ // 議곗씤�븯湲곗쟾 FilmCategorty List
		DBUtil dbUtil = new DBUtil();
		Connection conn= dbUtil.getConnection();
		String sql= "SELECT * FROM film_category LIMIT ?,?";
		PreparedStatement stmt=conn.prepareStatement(sql);
		stmt.setInt(1, beginRow);
		stmt.setInt(2, rowPerPage);
		ResultSet rs = stmt.executeQuery();
		ArrayList<FilmCategory> list = new ArrayList<FilmCategory>();
		while(rs.next()) {
			FilmCategory filmCategory = new FilmCategory();
			filmCategory.setFilmId(rs.getInt("film_id"));
			filmCategory.setCategoryId(rs.getInt("category_id"));
			filmCategory.setLastUpdate(rs.getString("last_update"));
			list.add(filmCategory);
		}
		return list;
	}
	public int selectTotalCount(int totalCount) throws Exception {
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		String sql="SELECT count(*) as count FROM film_category";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			totalCount = rs.getInt("count"); 
		}
		return totalCount;
	}
	public ArrayList<FilmCategoryAndFilmAndCategory> selectFilmCategoryList(int beginRow, int rowPerPage, String searchWord )throws Exception{
		DBUtil dbutil = new DBUtil();
	    Connection conn = dbutil.getConnection();
	    String sql="SELECT fic.*,fi.*,Ca.* FROM film_category fic INNER JOIN film fi INNER JOIN category ca ON  fi.film_id=fic.film_id and fic.category_id =ca.category_id where fi.title like? limit ?,?";
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setString(1, "%"+searchWord+"%");
	    stmt.setInt(2, beginRow);
	    stmt.setInt(3, rowPerPage);
	    ResultSet rs = stmt.executeQuery();
	    ArrayList<FilmCategoryAndFilmAndCategory> list =new ArrayList<FilmCategoryAndFilmAndCategory>();
	    while(rs.next()) {
	    	FilmCategoryAndFilmAndCategory fcafaca = new FilmCategoryAndFilmAndCategory();
	    	Film film = new Film();
			film.setFilmId(rs.getInt("film_id"));
			film.setTitle(rs.getString("title"));
			film.setDescription(rs.getString("description"));
			film.setReleaseYear(rs.getString("release_year"));
			film.setLanguageId(rs.getInt("language_id"));
			film.setOriginalLanguageId(rs.getInt("original_language_id"));
			film.setRentalDuration(rs.getInt("rental_duration"));
			film.setRentalRate(rs.getInt("rental_rate"));
			film.setLength(rs.getInt("length"));
			film.setReplacementCost(rs.getInt("replacement_cost"));
			film.setRating(rs.getString("rating"));
			film.setSpecialFeatures(rs.getString("special_features"));
			film.setLastUpdate(rs.getString("last_update"));
	    	fcafaca.setFilm(film);
	    	FilmCategory filmCategory = new FilmCategory();
			filmCategory.setFilmId(rs.getInt("film_id"));
			filmCategory.setCategoryId(rs.getInt("category_id"));
			filmCategory.setLastUpdate(rs.getString("last_update"));
			fcafaca.setFilmcategory(filmCategory);
	    	Category category = new Category();
	    	category.setCategoryId(rs.getInt("category_id"));
	    	category.setName(rs.getString("name"));
	    	category.setLastUpdate(rs.getString("last_update"));
	    	fcafaca.setCategory(category);
	    	list.add(fcafaca);
	    }
	    return list;
	}
	public ArrayList<FilmCategoryAndFilmAndCategory> selectFilmCategoryList(int rowPerPage, int beginRow) throws Exception{
		DBUtil dbutil = new DBUtil();
	    Connection conn = dbutil.getConnection();
	    String sql="SELECT fi.film_id, fi.title, Ca.name ";
	    sql += "FROM film_category fic ";
	    sql += "INNER JOIN film fi ";
	    sql += "INNER JOIN category ca ";
	    sql += "ON fi.film_id = fic.film_id ";
	    sql += "AND fic.category_id = ca.category_id "
	    	+ "ORDER BY fi.film_id ";
	    sql += "LIMIT ?,?";
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setInt(1, beginRow);
		stmt.setInt(2, rowPerPage);
	    ResultSet rs = stmt.executeQuery();
	    ArrayList<FilmCategoryAndFilmAndCategory> list =new ArrayList<FilmCategoryAndFilmAndCategory>();
	    while(rs.next()) {
	    	FilmCategoryAndFilmAndCategory fcafaca = new FilmCategoryAndFilmAndCategory();
	    	Film film = new Film();
	    	film.setFilmId(rs.getInt("film_id"));
	    	film.setTitle(rs.getString("title"));
	    	fcafaca.setFilm(film);
	    	Category category = new Category();
	    	category.setName(rs.getString("name"));
	    	fcafaca.setCategory(category);
	    	list.add(fcafaca);
	    }
	    return list;
	}
}
