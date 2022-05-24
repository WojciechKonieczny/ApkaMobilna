package pl.wojciech.konieczny.apkamobilna30166.MyCars;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import pl.wojciech.konieczny.apkamobilna30166.MyDatabase;

public class MyCarsRepository {

    private MyCarsDao dao;
    private LiveData<List<MyCarsModal>> allCars;

    public MyCarsRepository(Application application) {
        MyDatabase database = MyDatabase.getInstance(application);
        dao = (MyCarsDao) database.Dao();
        allCars = dao.getAllCars();
    }

    public void insert(MyCarsModal model) {
        new InsertCarAsyncTask(dao).execute(model);
    }

    public void update(MyCarsModal model) {
        new UpdateCarAsyncTask(dao).execute(model);
    }

    public void delete(MyCarsModal model) {
        new DeleteCarAsyncTask(dao).execute(model);
    }

    public void deleteAllCars() {
        new DeleteAllCarsAsyncTask(dao).execute();
    }

    public LiveData<List<MyCarsModal>> getAllCars() {
        return allCars;
    }

    // we are creating a async task method to insert new course.
    private static class InsertCarAsyncTask extends AsyncTask<MyCarsModal, Void, Void> {
        private MyCarsDao dao;

        private InsertCarAsyncTask(MyCarsDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(MyCarsModal... model) {
            // below line is use to insert our modal in dao.
            dao.insert(model[0]);
            return null;
        }
    }


    // we are creating a async task method to update our course.
    private static class UpdateCarAsyncTask extends AsyncTask<MyCarsModal, Void, Void> {
        private MyCarsDao dao;

        private UpdateCarAsyncTask(MyCarsDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(MyCarsModal... models) {
            // below line is use to update our modal in dao.
            dao.update(models[0]);
            return null;
        }
    }

    // we are creating a async task method to delete course.
    private static class DeleteCarAsyncTask extends AsyncTask<MyCarsModal, Void, Void> {
        private MyCarsDao dao;

        private DeleteCarAsyncTask(MyCarsDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(MyCarsModal... models) {
            dao.delete(models[0]);
            return null;
        }
    }

    // we are creating a async task method to delete all courses.
    private static class DeleteAllCarsAsyncTask extends AsyncTask<Void, Void, Void> {
        private MyCarsDao dao;
        private DeleteAllCarsAsyncTask(MyCarsDao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAllCars();
            return null;
        }
    }

}
