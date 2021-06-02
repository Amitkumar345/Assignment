package com.example.assignment;


import androidx.lifecycle.LiveData;
import androidx.room.*;

import java.util.List;

@Dao
public interface CrewDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Crew crew);

    @Delete
    void delete(Crew crew);

    @Query("SELECT * FROM Crew_members ORDER BY name ASC")
    LiveData<List<Crew>> getAllMembers();

}
