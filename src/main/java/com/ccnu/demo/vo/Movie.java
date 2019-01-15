package com.ccnu.demo.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Movie {
    @Id
    private Integer ranking;

    private String link;

    private String name;

    private Float score;

    private Integer assessNum;

    private String director;

    private String type;

    private String date;

    private String actors;

    public Movie() {
    }

    public Movie(Integer ranking, String link, String name, Float score, Integer assessNum, String director, String type, String date, String actors) {
        this.ranking = ranking;
        this.link = link;
        this.name = name;
        this.score = score;
        this.assessNum = assessNum;
        this.director = director;
        this.type = type;
        this.date = date;
        this.actors = actors;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Integer getAssessNum() {
        return assessNum;
    }

    public void setAssessNum(Integer assessNum) {
        this.assessNum = assessNum;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }
}
