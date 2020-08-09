package dao;
import java.sql.*;
import java.util.ArrayList;
import util.DBUtil;
import vo.*;

public class FilmActorDao {
	public ArrayList<ActorAndFilm> selectSearchActorAndFilmListAll(int beginRow, int rowPerPage, String searchWord) throws Exception{
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		String sql="SELECT fa.*,fi.*, ac.* FROM film_actor fa inner join film fi inner join actor ac on fa.actor_id = ac.actor_id and fa.film_id = fi.film_id where (ac.first_name like ?)or(ac.last_name like ?)order by actor_id asc order By fi.film_id limit ?,?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%"+searchWord+"%");
		stmt.setString(2, "%"+searchWord+"%");
		stmt.setInt(3, beginRow);
		stmt.setInt(4, rowPerPage);
		ResultSet rs = stmt.executeQuery();
		ArrayList<ActorAndFilm> list = new ArrayList<>();
		while(rs.next()) {
			ActorAndFilm aaf = new ActorAndFilm();
			Film film = new Film();
			FilmActor filmActor = new FilmActor();
	    	filmActor.setActorId(rs.getInt("actor_id"));
	    	filmActor.setFilmId(rs.getInt("film_id"));
	    	filmActor.setLastUpdate(rs.getString("last_update"));
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
			Actor actor = new Actor();
			actor.setActorId( rs.getInt("actor_id"));
			actor.setFirstName(rs.getString("first_name"));
			actor.setLastName(rs.getString("last_name"));
			actor.setLastUpdate(rs.getString("last_update"));
			aaf.setActor(actor);
			aaf.setFilm(film);
			aaf.setFilmActor(filmActor);
			list.add(aaf);		
		}
		return list;
	}

	public ArrayList<FilmActor> selectFilActorList(int rowPerPage,int beginRow) throws Exception{
		DBUtil dbUtil = new DBUtil();
		Connection conn= dbUtil.getConnection();
		String sql= "select * from film_actor limit ?,?";
		PreparedStatement stmt=conn.prepareStatement(sql);
		stmt.setInt(1, beginRow);
		stmt.setInt(2, rowPerPage);
		ResultSet rs = stmt.executeQuery();
	    ArrayList<FilmActor> list = new ArrayList<FilmActor>();
	    while(rs.next()) {
	    	FilmActor filmActor = new FilmActor();
	    	filmActor.setActorId(rs.getInt("actor_id"));
	    	filmActor.setFilmId(rs.getInt("film_id"));
	    	filmActor.setLastUpdate(rs.getString("last_update"));
	    	list.add(filmActor);
	    }
	    return list;
	}
	public int selectTotalCount(int totalCount) throws Exception{
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		String sql="SELECT count(*) as count FROM film_actor";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			totalCount = rs.getInt("count"); 
		}
		return totalCount;
	}
	public ArrayList<ActorAndFilm> selectActorAndFilmListAll(int rowPerPage, int beginRow) throws Exception{
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		String sql="SELECT fa.*,fi.*, ac.* ";
		sql+="FROM film_actor fa ";
		sql+="INNER JOIN film fi ";
		sql+="INNER JOIN actor ac ";
		sql+="ON fa.actor_id = ac.actor_id ";
		sql+="AND fa.film_id = fi.film_id ";
		sql+="ORDER By fi.film_id ";
		sql+="LIMIT ?,?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, beginRow);
		stmt.setInt(2, rowPerPage);
		ResultSet rs = stmt.executeQuery();
		ArrayList<ActorAndFilm> list = new ArrayList<>();
		while(rs.next()) {
			ActorAndFilm aaf = new ActorAndFilm();
			Film film = new Film();
			FilmActor filmActor = new FilmActor();
			filmActor.setActorId(rs.getInt("actor_id"));
			filmActor.setFilmId(rs.getInt("film_id"));
			filmActor.setLastUpdate(rs.getString("last_update"));
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
			aaf.setFilm(film);
			
			Actor actor = new Actor();
			actor.setActorId( rs.getInt("actor_id"));
			actor.setFirstName(rs.getString("first_name"));
			actor.setLastName(rs.getString("last_name"));
			actor.setLastUpdate(rs.getString("last_update"));
			aaf.setActor(actor);
			aaf.setFilmActor(filmActor);
			list.add(aaf);      
		}
		return list;
	}

}