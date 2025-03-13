package com.example.imageUploader.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.imageUploader.model.Image;

@Repository
public class ImageDao {

	private final JdbcTemplate jdbcTemplate;
    public ImageDao(JdbcTemplate jdbcTemplate) {
    	this.jdbcTemplate=jdbcTemplate;
    }
    
    public void saveImage(String fileName) {
        String sql = "INSERT INTO images (file_name, upload_time) VALUES (?, CURRENT_TIMESTAMP)";
        jdbcTemplate.update(sql, fileName);
    }

	
    public List<Image> getAllImages() {
        String sql = "SELECT * FROM images";
        RowMapper<Image> rowMapper = (rs, rowNum) -> {
            Image image = new Image();
            image.setId(rs.getInt("id"));
            image.setFileName(rs.getString("file_name"));
            image.setUploadTime(rs.getTimestamp("upload_time"));
            return image;
        };
        return jdbcTemplate.query(sql, rowMapper);
    }
	
}
