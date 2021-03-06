package com.fantasy.dao.impls;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.sql.DataSource;

import com.fantasy.dao.interfaces.MP3Dao;
import com.fantasy.dao.objects.Author;
import com.fantasy.dao.objects.MP3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Component("sqliteDAO")
public class SQLiteDAO implements MP3Dao {

	private static final String mp3Table = "mp3";
	private static final String mp3View = "mp3_view";

	private NamedParameterJdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert insertMP3;
	private SimpleJdbcCall jdbcCall;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		this.insertMP3 = new SimpleJdbcInsert(dataSource).withTableName("mp3").usingColumns("name","author");
	}

	public void insertMP3(MP3 mp3) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", mp3.getName());
		params.addValue("author", mp3.getAuthor());

		insertMP3.execute(params);
	}

	public int insertMP3AndGetCount(MP3 mp3) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", mp3.getName());
		params.addValue("author", mp3.getAuthor());

		return insertMP3.execute(params);
	}

	public int insertMP3AndGetId(MP3 mp3) {

		int author_id = insertAuthor(mp3.getAuthor());

		String sqlMP3 = "insert into mp3(name, author_id) values(:mp3_name, :author_id)";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("mp3_name", mp3.getName());
		params.addValue("author_id", author_id);
		jdbcTemplate.update(sqlMP3, params);
		return author_id;
	}

	public int insertAuthor(Author author) {

		String sqlAuhtor = "insert into author(name) values(:author_name)";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("author_name", author.getName());

		jdbcTemplate.update(sqlAuhtor, params, keyHolder);
		return keyHolder.getKey().intValue();
	}

	public void insertMP3(List<MP3> mp3List) {
		for(MP3 mp3: mp3List){
			insertMP3(mp3);
		}
	}

	public int insertList(List<MP3> mp3List) {
		String sqlAuthor = "insert into author(name) VALUES (:author_name)";
		String sqlMP3 = "insert into mp3(author_id, name) VALUES (:author_id, :mp3_name)";

		SqlParameterSource[] params = new SqlParameterSource[mp3List.size()];

		int i = 0;
		for (MP3 mp3 : mp3List) {
			MapSqlParameterSource p = new MapSqlParameterSource();
			p.addValue("mp3_name", mp3.getName());

			MapSqlParameterSource param = new MapSqlParameterSource();
			param.addValue("author_name", mp3.getAuthor().getName());
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(sqlAuthor, param, keyHolder);

			int author_id = keyHolder.getKey().intValue();
			p.addValue("author_id", author_id);

			params[i] = p;
			i++;
		}

		// SqlParameterSource[] batch =
		// SqlParameterSourceUtils.createBatch(listMP3.toArray());

		return jdbcTemplate.batchUpdate(sqlMP3, params).length;
	}

	public int updateList(List<MP3> mp3List) {
		String sqlMP3 = "update mp3 set name=:mp3_name where id=:mp3_id";
		String sqlAuthor = "update author set name=:author_name where id=:author_id";

		SqlParameterSource[] pars = new SqlParameterSource[mp3List.size()];

		int i=0;
		for (MP3 mp3: mp3List){
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("mp3_id", mp3.getId());
			params.addValue("mp3_name", mp3.getName());
			params.addValue("author_name", mp3.getAuthor().getName());
			params.addValue("author_id", mp3.getAuthor().getId());

			pars[i++] = params;
		}
		jdbcTemplate.batchUpdate(sqlMP3, pars);
		return jdbcTemplate.batchUpdate(sqlAuthor, pars).length;
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
		String sql = "select * from " + mp3View + " where mp3_id == :id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		return jdbcTemplate.queryForObject(sql, params, new MP3RowMapper());
	}

	public int getIdAuthor(String name){
		String sql = "select * from author where name = :name";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", name);

		return jdbcTemplate.queryForObject(sql, params, new MP3RowMapper()).getId();
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
		String sql = "select count(*) from " + mp3Table;
		return jdbcTemplate.getJdbcOperations().queryForObject(sql, Integer.class);
	}

	public Map<String, Integer> getStat(){
		String sql = "select author, count(*) as count from " + mp3View + " group by author";

		return jdbcTemplate.query(sql, new ResultSetExtractor<Map<String, Integer>>() {
			@Override
			public Map<String, Integer> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
				Map<String, Integer> map = new TreeMap<>();
				while (resultSet.next()){
					String author = resultSet.getString("author_name");
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
			int mp3_id = resultSet.getInt("mp3_id");
			String mp3_name = resultSet.getString("mp3_name");

			String author_name = resultSet.getString("author_name");
			int author_id = resultSet.getInt("author_id");

			return new MP3(mp3_id, mp3_name, new Author(author_id, author_name));
		}
	}
}
