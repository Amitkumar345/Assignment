package com.example.assignment;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Crew_members")
public class Crew {

    @PrimaryKey(autoGenerate = true)
    private int id=0;

    @ColumnInfo
    private String name,agency,wikiLink,status,imgLink;

    public void setId(int id) {
        this.id = id;
    }
    public Crew(String name, String agency, String wikiLink, String status, String imgLink) {
        this.name = name;
        this.agency = agency;
        this.wikiLink = wikiLink;
        this.status = status;
        this.imgLink = imgLink;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public String getAgency() {
        return agency;
    }

    public String getWikiLink() {
        return wikiLink;
    }

    public String getStatus() {
        return status;
    }

    public String getImgLink() {
        return imgLink;
    }
}
