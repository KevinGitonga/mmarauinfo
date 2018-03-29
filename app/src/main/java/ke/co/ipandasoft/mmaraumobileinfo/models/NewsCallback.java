package ke.co.ipandasoft.mmaraumobileinfo.models;

import java.util.ArrayList;

/**
 * Created by Kevin Gitonga on 2/18/2018.
 */

public class NewsCallback {

    private String status;
    private int totalResults;
    private ArrayList<NewsStructure> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public ArrayList<NewsStructure> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<NewsStructure> articles) {
        this.articles = articles;
    }


    }
