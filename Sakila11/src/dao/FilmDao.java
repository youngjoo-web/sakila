package dao;
import util.*;
import java.sql.*;
import java.util.*;
import vo.*;
public class FilmDao {
	public void insertFilm(Film f) throws Exception{
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		String sql = "INSERT INTO `sakila`.`film` (`title`, `description`, `release_year`, `language_id`, `original_language_id`, `rental_duration`, `rental_rate`, `length`, `replacement_cost`,`rating`, `special_features`) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?);";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, f.getTitle());
		stmt.setString(2, f.getDescription());
		stmt.setString(3, f.getReleaseYear());
		stmt.setInt(4, f.getLanguageId());	
		stmt.setInt(5, f.getOriginalLanguageId());	
		stmt.setInt(6, f.getRentalDuration());
		stmt.setDouble(7, f.getRentalRate());
		stmt.setInt(8, f.getLength());
		stmt.setDouble(9, f.getReplacementCost());
		stmt.setString(10, f.getRating());
		stmt.setString(11, f.getSpecialFeatures());
		stmt.executeUpdate();	
	}
	public ArrayList<FilmAndLanguage> selectSearchFilmAndLanguageListAll(int beginRow, int rowPerPage, String searchWord) throws Exception {
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		String sql="SELECT fi.*, la.* FROM film fi inner join language la on fi.language_id = la.language_id WHERE fi.title LIKE ? order By fi.film_id asc LIMIT ?, ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%"+searchWord+"%");
		stmt.setInt(2, beginRow);
		stmt.setInt(3, rowPerPage);
		ResultSet rs = stmt.executeQuery();
		ArrayList<FilmAndLanguage> list = new ArrayList<>();
		while(rs.next()) {
			FilmAndLanguage fal = new FilmAndLanguage();
			Film film = new Film();
			
			film.setFilmId(rs.getInt("film_id"));
			film.setTitle(rs.getString("title"));
			film.setDescription(rs.getString("description"));
			film.setReleaseYear(rs.getString("release_year"));
			film.setLanguageId(rs.getInt("language_id"));
			film.setOriginalLanguageId(rs.getInt("original_language_id"));
			film.setRentalDuration(rs.getInt("rental_duration"));
			film.setRentalRate(rs.getDouble("rental_rate"));
			film.setLength(rs.getInt("length"));
			film.setReplacementCost(rs.getDouble("replacement_cost"));
			film.setRating(rs.getString("rating"));
			film.setSpecialFeatures(rs.getString("special_features"));
			film.setLastUpdate(rs.getString("last_update"));
			Language la = new Language();
			la.setLanguageId(rs.getInt("language_id"));
			la.setName(rs.getString("name"));
			la.setLastUpdate(rs.getString("last_update"));
			fal.setFilm(film);
			fal.setLanguage(la);
			list.add(fal);			
		}
		return list;
	}
	public ArrayList<Film> selectFilmListAll(int rowPerPage, int beginRow) throws Exception {
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		String sql="SELECT * FROM film limit ?,?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, beginRow);
		stmt.setInt(2, rowPerPage);
		ResultSet rs = stmt.executeQuery();
		ArrayList<Film> list = new ArrayList<Film>();
		while(rs.next()) {
			Film film = new Film();
			film.setFilmId(rs.getInt("film_id"));
			film.setTitle(rs.getString("title"));
			film.setDescription(rs.getString("description"));
			film.setReleaseYear(rs.getString("release_year"));
			film.setLanguageId(rs.getInt("language_id"));
			film.setOriginalLanguageId(rs.getInt("original_language_id"));
			film.setRentalDuration(rs.getInt("rental_duration"));
			film.setRentalRate(rs.getDouble("rental_rate"));
			film.setLength(rs.getInt("length"));
			film.setReplacementCost(rs.getDouble("replacement_cost"));
			film.setRating(rs.getString("rating"));
			film.setSpecialFeatures(rs.getString("special_features"));
			film.setLastUpdate(rs.getString("last_update"));
			list.add(film);
		}
		return list;
	}
	public int selectTotalCount(int totalCount) throws Exception {
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		String sql="SELECT count(*) as count FROM film";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			totalCount = rs.getInt("count"); 
		}
		return totalCount;
	}
	public ArrayList<FilmAndLanguage> selectFilmAndLanguageListAll(int rowPerPage, int beginRow) throws Exception {
	      DBUtil dbUtil = new DBUtil();
	      Connection conn = dbUtil.getConnection();
	      String sql="SELECT fi.*, la.* FROM film fi ";
	      sql+="INNER JOIN language la ";
	      sql+="ON fi.language_id = la.language_id ";
	      sql+=" ORDER BY fi.film_id ";
	      sql+="LIMIT ?,?";
	      PreparedStatement stmt = conn.prepareStatement(sql);
	      stmt.setInt(1, beginRow);
		stmt.setInt(2, rowPerPage);
	      ResultSet rs = stmt.executeQuery();
	      ArrayList<FilmAndLanguage> list = new ArrayList<>();
	      while(rs.next()) {
	         FilmAndLanguage fal = new FilmAndLanguage();
	         Film film = new Film();
	         
	         film.setFilmId(rs.getInt("film_id"));
	         film.setTitle(rs.getString("title"));
	         film.setDescription(rs.getString("description"));
	         film.setReleaseYear(rs.getString("release_year"));
	         film.setLanguageId(rs.getInt("language_id"));
	         film.setOriginalLanguageId(rs.getInt("original_language_id"));
	         film.setRentalDuration(rs.getInt("rental_duration"));
	         film.setRentalRate(rs.getDouble("rental_rate"));
	         film.setLength(rs.getInt("length"));
	         film.setReplacementCost(rs.getDouble("replacement_cost"));
	         film.setRating(rs.getString("rating"));
	         film.setSpecialFeatures(rs.getString("special_features"));
	         film.setLastUpdate(rs.getString("last_update"));
	         Language la = new Language();
	         la.setLanguageId(rs.getInt("language_id"));
	         la.setName(rs.getString("name"));
	         la.setLastUpdate(rs.getString("last_update"));
	         fal.setFilm(film);
	         fal.setLanguage(la);
	         list.add(fal);         
	      }
	      return list;
	   }
}
