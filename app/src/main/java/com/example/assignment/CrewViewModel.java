package com.example.assignment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CrewViewModel extends AndroidViewModel {
    private CrewRepository repo;
    private LiveData<List<Crew>> allMembers;
    public CrewViewModel(@NonNull Application application) {
        super(application);
        repo = new CrewRepository(application);
        allMembers = repo.getAllMembers();
    }

    public void insert(Crew crew){
        repo.insert(crew);
    }
    public void delete(Crew crew){
        repo.delete(crew);
    }
    public LiveData<List<Crew>> getAllMembers(){
        return allMembers;
    }
}
