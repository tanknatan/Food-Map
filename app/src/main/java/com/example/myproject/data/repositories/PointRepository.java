//package com.example.myproject.data.repositories;
//
//import android.app.Application;
//
//import androidx.lifecycle.LiveData;
//
//import com.example.myproject.data.data_sources.PointDataSource;
//import com.example.myproject.data.models.Point;
//
//import java.util.List;
//
//import ru.student.detected.educator.data.data_sources.TheoryDataSource;
//import ru.student.detected.educator.data.models.Theory;
//
//public class PointRepository {
//    private final PointRepository pointDataSource;
//    public PointRepository() {
//        pointDataSource = new PointDataSource();
//    }
//
//    public LiveData<List<Point>> getTheoryData(Application application) {
//        return pointDataSource.points(application);
//    }
//}
