package com.ccnu.demo.repo;

import com.ccnu.demo.vo.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Integer> {

}
