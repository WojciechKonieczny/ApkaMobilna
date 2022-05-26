package pl.wojciech.konieczny.apkamobilna30166.MyTanks;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;
import pl.wojciech.konieczny.apkamobilna30166.MyDatabase;

public class MyTanksRepository {

    private MyTanksDao dao;
    private LiveData<List<MyTanksModal>> allTanks;

    public MyTanksRepository(Application application) {
        MyDatabase database = MyDatabase.getInstance(application);
        dao = (MyTanksDao) database.Dao2();
        allTanks = dao.getAllTanks();
    }

    public void insert(MyTanksModal model) {
        new MyTanksRepository.InsertTankAsyncTask(dao).execute(model);
    }

    public void update(MyTanksModal model) {
        new MyTanksRepository.UpdateTankAsyncTask(dao).execute(model);
    }

    public void delete(MyTanksModal model) {
        new MyTanksRepository.DeleteTankAsyncTask(dao).execute(model);
    }

    public void deleteAllTanks() {
        new MyTanksRepository.DeleteAllTanksAsyncTask(dao).execute();
    }

    public LiveData<List<MyTanksModal>> getAllTanks() {
        return allTanks;
    }

    // we are creating a async task method to insert new course.
    private static class InsertTankAsyncTask extends AsyncTask<MyTanksModal, Void, Void> {
        private MyTanksDao dao;

        private InsertTankAsyncTask(MyTanksDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(MyTanksModal... model) {
            // below line is use to insert our modal in dao.
            dao.insert(model[0]);
            return null;
        }
    }


    // we are creating a async task method to update our course.
    private static class UpdateTankAsyncTask extends AsyncTask<MyTanksModal, Void, Void> {
        private MyTanksDao dao;

        private UpdateTankAsyncTask(MyTanksDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(MyTanksModal... models) {
            // below line is use to update our modal in dao.
            dao.update(models[0]);
            return null;
        }
    }

    // we are creating a async task method to delete course.
    private static class DeleteTankAsyncTask extends AsyncTask<MyTanksModal, Void, Void> {
        private MyTanksDao dao;

        private DeleteTankAsyncTask(MyTanksDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(MyTanksModal... models) {
            dao.delete(models[0]);
            return null;
        }
    }

    // we are creating a async task method to delete all courses.
    private static class DeleteAllTanksAsyncTask extends AsyncTask<Void, Void, Void> {
        private MyTanksDao dao;
        private DeleteAllTanksAsyncTask(MyTanksDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAllTanks();
            return null;
        }
    }
}
