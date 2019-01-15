package com.ccnu.demo.spider;

import com.ccnu.demo.index.Index;
import com.ccnu.demo.repo.MovieRepository;
import com.ccnu.demo.vo.Movie;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Spider {
    @Autowired
    private MovieRepository movieRepository;
    private static final String BASE="http://movie.douban.com/top250";

    public void movieSpider(){
        String subUrl="";
        for (int i=0;i<1;i++){
            String url=BASE+"?start="+i*25;
            //String subUrl="";
            try{
                Document document = Jsoup.connect(url).timeout(5000).get();
                Elements urls = document.getElementsByClass("hd");
                for (Element e : urls
                        ) {
                    subUrl = e.getElementsByTag("a").get(0).attr("href");
                    Document movieDoc = Jsoup.connect(subUrl).timeout(5000).get();
                    String ranking = movieDoc.getElementsByClass("top250-no").html();
                    String name = movieDoc.getElementById("content").getElementsByTag("h1").text();
                    String score = movieDoc.getElementsByClass("ll rating_num").html();
                    String assessNum = movieDoc.getElementsByClass("rating_people").select("span").html();
                    String director = movieDoc.getElementById("info").child(0).getElementsByTag("a").html();
                    Elements types = movieDoc.getElementById("info").getElementsByAttributeValue("property", "v:genre");
                    String type = "";
                    for (Element element : types
                            ) {
                        type += element.html() + "/";
                    }
                    String date = movieDoc.getElementById("info").getElementsByAttributeValue("property", "v:initialReleaseDate").text();
                    Elements actorElements = movieDoc.getElementsByClass("actor").get(0).getElementsByClass("attrs");
                    String actors = "";
                    for (Element element : actorElements
                            ) {
                        actors += element.text() + "/";
                    }
                    System.out.println(date);
                    Movie movie = new Movie(str2int(ranking), subUrl, name, Float.parseFloat(score), Integer.parseInt(assessNum), director, type, date, actors);
                    movieRepository.saveAndFlush(movie);
                    System.out.println(subUrl + "已加载");
                }
            }catch (IOException e) {
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
                System.err.println(subUrl);
                return;
            }
        }
        save2Index();

    }

    @Async
    public void spider(int i){

    }

    public void save2Index(){
        Index index=new Index();
        index.createIndex(movieRepository.findAll());
    }

    private int str2int(String s){
        String regEx="[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(s);
        return Integer.parseInt(m.replaceAll("").trim());
    }
}
