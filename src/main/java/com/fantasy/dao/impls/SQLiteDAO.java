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
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

@Component("sqliteDAO")
public class SQLiteDAO implements MP3Dao {

	private NamedParameterJdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert insertMP3;
	private SimpleJdbcCall jdbcCall;
	private DataSource dataSource;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		this.insertMP3 = new SimpleJdbcInsert(dataSource).withTableName("mp3").usingColumns("name","author");
		this.dataSource = dataSource;
	}

	public void insert(MP3 mp3) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", mp3.getName());
		params.addValue("author", mp3.getAuthor());

		insertMP3.execute(params);
	}

	public int insertAndGetCount(MP3 mp3) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", mp3.getName());
		params.addValue("author", mp3.getAuthor());

		return insertMP3.execute(params);
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

	public int insertList(List<MP3> mp3List) {
		String sql = "insert into mp3(author, name) values(:author, :name)";

		SqlParameterSource[] pars = new SqlParameterSource[mp3List.size()];

		int i=0;
		for (MP3 mp3: mp3List){
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("name", mp3.getName());
			params.addValue("author", mp3.getAuthor());

			pars[i++] = params;
		}

		return jdbcTemplate.batchUpdate(sql, pars).length;
	}

	public int updateList(List<MP3> mp3List) {
		String sql = "update mp3 set author=:author, name=:name where id=:id";

		SqlParameterSource[] pars = new SqlParameterSource[mp3List.size()];

		int i=0;
		for (MP3 mp3: mp3List){
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("id", mp3.getId());
			params.addValue("name", mp3.getName());
			params.addValue("author", mp3.getAuthor());

			pars[i++] = params;
		}

		return jdbcTemplate.batchUpdate(sql, pars).length;
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
