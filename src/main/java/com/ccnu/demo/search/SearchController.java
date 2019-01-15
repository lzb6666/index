package com.ccnu.demo.search;

import com.ccnu.demo.vo.Movie;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {

    @RequestMapping("/search")
    public List<Movie> search(String content,String search){
        Query query = new WildcardQuery(new Term(content,search));
        return query(query);
    }

    public List<Movie> query(Query query){
        List<Movie> movies=new ArrayList<>();
        try{
            Directory directory = FSDirectory.open(new File("E:\\test"));
            IndexSearcher indexSearcher = new IndexSearcher(DirectoryReader.open(directory));
            TopDocs topDocs=indexSearcher.search(query,10);
            for (ScoreDoc doc:topDocs.scoreDocs
                 ) {
                Document document = indexSearcher.doc(doc.doc);
                Movie movie=new Movie();
                movie.setRanking(Integer.parseInt(document.get("ranking")));
                movie.setName(document.get("name"));
                movie.setDirector(document.get("director"));
                movie.setScore(Float.parseFloat(document.get("score")));
                movie.setActors(document.get("actors"));
                movie.setDate(document.get("date"));
                movie.setAssessNum(Integer.parseInt(document.get("assessNum")));
                movie.setLink(document.get("link"));
                movie.setType("type");
                movies.add(movie);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return movies;
    }
}
