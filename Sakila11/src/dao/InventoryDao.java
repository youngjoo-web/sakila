package dao;
import vo.*;
import java.sql.*;
import java.util.*;
import util.DBUtil;

public class InventoryDao {
	public ArrayList<InventoryAndFilmAndStore> selectSearchInventoryAndFilmAndStorelistAll(int beginRow, int rowPerPage, String searchWord)throws Exception{
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		String sql = "select iv.*, fi.*, st.* from inventory iv inner join film fi inner join store st on iv.film_id = fi.film_id and iv.store_id = st.store_id where fi.title like ? order by iv.inventory_id asc limit ?, ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%"+searchWord+"%");
		stmt.setInt(2, beginRow);
		stmt.setInt(3, rowPerPage);
		ResultSet rs = stmt.executeQuery();
		ArrayList<InventoryAndFilmAndStore> list = new ArrayList<>();
		while(rs.next()) {
			InventoryAndFilmAndStore iafas = new InventoryAndFilmAndStore();
			Inventory inventory = new Inventory();//여따넣을거임
			inventory.setInventoryId(rs.getInt("inventory_id"));//디비에서 뽑아온 인벤토리 아이디 값을 인벤토리 클래스 안에있는 셋인벤토리에 넣어주겠다.
			inventory.setFilmId(rs.getInt("film_id"));
			inventory.setStoreId(rs.getInt("store_id"));
			inventory.setLastUpdate(rs.getString("last_update"));
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
			Store s = new Store();
			s.setStoreId(rs.getInt("store_id"));
			s.setManagerStaffId(rs.getInt("manager_staff_id"));
			s.setAddressId(rs.getInt("address_id"));
			s.setLastUpdate(rs.getString("last_update"));
			iafas.setInventory(inventory);
			iafas.setFilm(film);
			iafas.setStore(s);
			list.add(iafas);
		}
		return list;
	}
	public ArrayList<Inventory> selectInventoryListAll(int beginRow, int rowPerPage)throws Exception{
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		String sql = "SELECT * FROM inventory ORDER BY inventory_id LIMIT ?,?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, beginRow);
		stmt.setInt(2, rowPerPage);
		ResultSet rs = stmt.executeQuery();
		ArrayList<Inventory>list = new ArrayList<Inventory>();
		while(rs.next()) {
			Inventory inventory = new Inventory();
			inventory.setInventoryId(rs.getInt("inventory_id"));
			inventory.setFilmId(rs.getInt("film_id"));
			inventory.setStoreId(rs.getInt("store_id"));
			inventory.setLastUpdate(rs.getString("last_update"));
			list.add(inventory);
		}
		return list;
	}
	public int selectTotalCount(int totalCount) throws Exception {
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		String sql="SELECT count(*) as count FROM inventory";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			totalCount = rs.getInt("count"); 
		}
		return totalCount;
	}
	public ArrayList<InventoryAndFilmAndStore> selectInventoryAndFilmAndStorelistAll(int beginRow,int rowPerPage)throws Exception{
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		String sql = "SELECT iv.*, fi.*, st.* ";
		sql+="FROM inventory iv ";
		sql+="INNER JOIN film fi ";
		sql+="INNER JOIN store st ";
		sql+="ON iv.film_id = fi.film_id ";
		sql+="AND iv.store_id = st.store_id "
				+ "order BY iv.inventory_id ";
		sql+="LIMIT ?,?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, beginRow);
		stmt.setInt(2, rowPerPage);
		ResultSet rs = stmt.executeQuery();
		ArrayList<InventoryAndFilmAndStore> list = new ArrayList<>();
		while(rs.next()) {
			InventoryAndFilmAndStore iafas = new InventoryAndFilmAndStore();
			Inventory inventory = new Inventory();//�뿬�뵲�꽔�쓣嫄곗엫
			inventory.setInventoryId(rs.getInt("inventory_id"));//�뵒鍮꾩뿉�꽌 戮묒븘�삩 �씤踰ㅽ넗由� �븘�씠�뵒 媛믪쓣 �씤踰ㅽ넗由� �겢�옒�뒪 �븞�뿉�엳�뒗 �뀑�씤踰ㅽ넗由ъ뿉 �꽔�뼱二쇨쿋�떎.
			inventory.setFilmId(rs.getInt("film_id"));
			inventory.setStoreId(rs.getInt("store_id"));
			inventory.setLastUpdate(rs.getString("last_update"));
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
			Store s = new Store();
			s.setStoreId(rs.getInt("store_id"));
			s.setManagerStaffId(rs.getInt("manager_staff_id"));
			s.setAddressId(rs.getInt("address_id"));
			s.setLastUpdate(rs.getString("last_update"));
			iafas.setInventory(inventory);
			iafas.setFilm(film);
			iafas.setStore(s);
			list.add(iafas);
		}
		return list;
	}
}
