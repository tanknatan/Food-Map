package com.example.myproject.viewmodel;

import android.Manifest;
import android.app.Application;
import android.content.pm.PackageManager;
import android.location.Location;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.example.myproject.data.models.UserData;
import com.example.myproject.data.repositories.UserDataRepository;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;


import java.util.List;


public class UserViewModel extends AndroidViewModel {
    private UserDataRepository repo;
    private LiveData<UserData> data;
    private UserData localUser;
    private FusedLocationProviderClient fusedLocationProviderClient;

    public UserViewModel(@NonNull Application application) {
        super(application);
        this.repo = new UserDataRepository(application);

        this.data = repo.getUserDataBase();
        this.fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getApplication());
    }

    public LiveData<UserData> getData(){
        updateData();
        return data;
    }

    public void insertData(UserData userData){
        repo.updateData(userData);
    }

    public boolean updateData(){
        if (ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }

        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(getApplication().getMainExecutor(), task -> {
            if (task.getResult() != null && task.getResult().getLatitude() != 0 && data.getValue() != null) {
                Location location = task.getResult();

                UserData tempData = new UserData(data.getValue().getName(),
                        data.getValue().getUserId(),
                        data.getValue().getIdOfVisitedPlaces(),
                        data.getValue().getIdFriends(),
                        location.getLatitude(),
                        location.getLongitude());

                repo.updateData(tempData);
                this.data = repo.getUserDataBase();
            }
        });
        return true;
    }

    public void refusedDataBase(LifecycleOwner lifecycleOwner){
        data.observe(lifecycleOwner, user->{
            localUser = user;
        });
    }

    public void deleteUserData(UserData userdata){
        repo.deleteUser(userdata);
    }

    public void insertPlace(long idToAdd){
        insertPlaceInThread(idToAdd);
    }

    public void insertFriend(String idToAdd){
        insertFriendInThread(idToAdd);
    }

    public void deleteFriend(String idToDel){
        deleteFriendInThread(idToDel);
    }

    private void deleteFriendInThread(String idToDel){
        if (localUser != null) {
            new Thread(()-> {
                synchronized (localUser) {
                    for (String id : localUser.getIdFriends()) {
                        if (id.equals(idToDel)) {
                            localUser.deleteFriend(idToDel);
                            repo.updateData(localUser);
                            break;
                        }
                    }
                }
                updateData();
            }).start();
        };
    }

    private void insertFriendInThread(String idToAdd){
        if (localUser != null) {
            new Thread(()-> {
                synchronized (localUser) {
                    for (String id : localUser.getIdFriends()) {
                        if (id.equals(idToAdd)) {
                            return;
                        }
                    }
                    localUser.addFriend(idToAdd);
                    repo.updateData(localUser);
                }
                updateData();
            }).start();
            updateData();
        }
    }

    private void insertPlaceInThread(long idToAdd){
        if (localUser != null && localUser.getIdOfVisitedPlaces() != null) {
            new Thread(()-> {
                synchronized (localUser) {
                    for (long id : localUser.getIdOfVisitedPlaces()) {
                        if (id == idToAdd) {
                            return;
                        }
                    }
                    localUser.addVisitPlace(idToAdd);
                    repo.updateData(localUser);
                }
            }).start();
            updateData();
        };
    }
}
