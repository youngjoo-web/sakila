package dao;
import vo.*;
import java.sql.*;
import java.util.*;
import util.DBUtil;

public class FilmTextDao {

	public ArrayList<FilmText> selectSearchFilmTextAll(int beginRow, int rowPerPage, String searchWord) throws Exception {
			DBUtil dbutil = new DBUtil();
			Connection conn = dbutil.getConnection();
			PreparedStatement stmt = conn.prepareStatement("select * from film_text where title like ? order by film_id asc limit ?,?");
			ResultSet rs = stmt.executeQuery();
			stmt.setString(1, "%"+searchWord+"%");
			stmt.setInt(2, beginRow);
			stmt.setInt(3, rowPerPage);
			ArrayList<FilmText> list = new ArrayList<>();
			while (rs.next()) {
				FilmText ft = new FilmText();
				ft.setFilmId(rs.getInt("film_id"));
				ft.setTitle(rs.getString("title"));
				ft.setDescription(rs.getString("description"));
				list.add(ft);
			}
			return list;
		}
	public ArrayList<FilmText> selectFilmTextAll(int beginRow, int rowPerPage) throws Exception {
		DBUtil dbutil = new DBUtil();
		Connection conn = dbutil.getConnection();
		PreparedStatement stmt = conn.prepareStatement("select * from film_text LIMIT ?,?");
		stmt.setInt(1, beginRow);
		stmt.setInt(2, rowPerPage);
		ResultSet rs = stmt.executeQuery();
		ArrayList<FilmText> list = new ArrayList<>();
		while (rs.next()) {
			FilmText ft = new FilmText();
			ft.setFilmId(rs.getInt("film_id"));
			ft.setTitle(rs.getString("title"));
			ft.setDescription(rs.getString("description"));
			list.add(ft);
		}
		return list;
	}
	public int selectTotalCount(int totalCount) throws Exception {
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		String sql="SELECT count(*) as count FROM film_text";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			totalCount = rs.getInt("count"); 
		}
		return totalCount;
	}
}
