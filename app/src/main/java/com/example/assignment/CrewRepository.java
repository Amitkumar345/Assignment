package com.example.assignment;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CrewRepository {
    private CrewDao crewDao;
    private static CrewDatabase crewDatabase;
    LiveData<List<Crew>> allCrewMembers;
    public CrewRepository(Application application){
        crewDatabase = CrewDatabase.getInstance(application);
        crewDao = crewDatabase.crewDao();
        allCrewMembers = crewDao.getAllMembers();
    }
    public void insert(Crew crew){
        crewDao.insert(crew);
    }
    public void delete(Crew crew){
        crewDao.delete(crew);
    }
    public LiveData<List<Crew>> getAllMembers(){
        return allCrewMembers;
    }
    public void deleteAll(){
        crewDatabase.clearAllTables();
    }
}
