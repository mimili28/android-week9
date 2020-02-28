package com.mariailieva.firebasehello.model;


public class Note {

    private String headline;
    private String body;
    String id;

    public Note (String id, String headline, String body){
        this.id=id;
        this.headline=headline;
        this.body=body;
    }
    public Note(){}

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
