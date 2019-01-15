package com.ccnu.demo.index;

import com.ccnu.demo.vo.Movie;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Index {
    public void createIndex(List<Movie> movieList){
        IndexWriter indexWriter;
        try{
            Directory directory = FSDirectory.open(new File("E:\\test"));
            IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_4_10_4, new StandardAnalyzer());
            indexWriter=new IndexWriter(directory,config);
            for (Movie m :movieList
                    ) {
                Document document=new Document();
                Field nameFiled=new TextField("name",m.getName(), Field.Store.YES);
                Field directorFiled=new TextField("director",m.getDirector(), Field.Store.YES);
                Field typeFiled=new TextField("type",m.getType(), Field.Store.YES);
                Field actorsFiled=new TextField("actors",m.getActors(), Field.Store.YES);
                Field dateFiled=new TextField("date",m.getDate(), Field.Store.YES);

                Field linkField = new StoredField("link", m.getLink());

                Field scoreField = new FloatField("score", m.getScore(), Field.Store.YES);

                Field assessNumField = new IntField("assessNum", m.getAssessNum(), Field.Store.YES);
                Field rankingField = new IntField("ranking", m.getRanking(), Field.Store.YES);

                document.add(nameFiled);
                document.add(directorFiled);
                document.add(typeFiled);
                document.add(actorsFiled);
                document.add(dateFiled);
                document.add(linkField);
                document.add(scoreField);
                document.add(assessNumField);
                document.add(rankingField);

                indexWriter.addDocument(document);
            }
            indexWriter.close();
        }catch (IOException e){
            e.printStackTrace();
            return;
        }
    }
}
