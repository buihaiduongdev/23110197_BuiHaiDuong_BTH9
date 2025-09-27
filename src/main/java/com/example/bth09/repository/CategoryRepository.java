package com.example.bth09.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bth09.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
