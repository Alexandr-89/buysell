package com.example.busell.dao;

import com.example.busell.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageDAO extends JpaRepository<Image, Long> {
}
