package com.fantasy.dao.impls;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.sql.DataSource;
import javax.swing.*;

import com.fantasy.dao.interfaces.MP3Dao;
import com.fantasy.dao.objects.MP3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

@Component("sqliteDAO")
public class SQLiteDAO implements MP3Dao {

	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public void insert(MP3 mp3) {
		String sql = "insert into mp3 (name, author) VALUES (:name, :author)";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", mp3.getName());
		params.addValue("author", mp3.getAuthor());

		jdbcTemplate.update(sql,params);
	}

	public int insertAndGetId(MP3 mp3){
		String sql = "insert into mp3(name, author) values(:name, :author)";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", mp3.getName());
		params.addValue("author", mp3.getAuthor());

		jdbcTemplate.update(sql, params, keyHolder);
		return keyHolder.getKey().intValue();
	}

	public void insert(List<MP3> mp3List) {
		for(MP3 mp3: mp3List){
			insert(mp3);
		}
	}

	public void insertWithJDBC(MP3 mp3) {

		Connection conn = null;

		try {
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite:db/SpringDB.db";
			conn = DriverManager.getConnection(url, "", "");
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}

		String sql = "insert into mp3 (name, author) VALUES (?, ?)";

		try {
			assert conn != null;
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, mp3.getName());
			ps.setString(2, mp3.getAuthor());
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	public void delete(MP3 mp3) {
		delete(mp3.getId());
	}
	public void delete(int id) {
		String sql = "delete from mp3 where id == :id";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		jdbcTemplate.update(sql, params);
	}

	public MP3 getMP3ByID(int id) {
		String sql = "select * from mp3 where id == :id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		return jdbcTemplate.queryForObject(sql, params, new MP3RowMapper());
	}

	public List<MP3> getMP3ListByName(String name) {
		String sql = "select * from mp3 where upper(name) like :name";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", "%" + name.toUpperCase() + "%");

		return jdbcTemplate.query(sql, params, new MP3RowMapper());
	}

	public List<MP3> getMP3ListByAuthor(String author) {
		String sql = "select * from mp3 where upper(author) like :author";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("author", "%" + author.toUpperCase() + "%");

		return jdbcTemplate.query(sql, params, new MP3RowMapper());
	}

	public int getMP3Count(){
		String sql = "select count(*) from mp3";
		return jdbcTemplate.getJdbcOperations().queryForObject(sql, Integer.class);
	}

	public Map<String, Integer> getStat(){
		String sql = "select author, count(*) as count from mp3 group by author";

		return jdbcTemplate.query(sql, new ResultSetExtractor<Map<String, Integer>>() {
			@Override
			public Map<String, Integer> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
				Map<String, Integer> map = new TreeMap<>();
				while (resultSet.next()){
					String author = resultSet.getString("author");
					int count = resultSet.getInt("count");
					map.put(author, count);
				}
				return map;
			}
		});
	}

	private static final class MP3RowMapper implements RowMapper<MP3> {
		@Override
		public MP3 mapRow(ResultSet resultSet, int i) throws SQLException {
			int id = resultSet.getInt("id");
			String author = resultSet.getString("author");
			String name = resultSet.getString("name");
			return new MP3(id, author, name);
		}
	}
}
