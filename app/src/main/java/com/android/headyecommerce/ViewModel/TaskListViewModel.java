package com.android.headyecommerce.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.android.headyecommerce.DB.ProductTable;
import com.android.headyecommerce.DB.TableDao;
import com.android.headyecommerce.DB.TaskDatabase;

import java.util.List;

public class TaskListViewModel  extends AndroidViewModel {

    private LiveData<List<ProductTable>> mTasks;

    private TaskDatabase appDatabase;
    public TaskListViewModel(Application application) {
        super(application);

        appDatabase = TaskDatabase.getDatabaseInstance(this.getApplication());

        mTasks = appDatabase.tableDao().getAllProduct();
    }

    public LiveData<List<ProductTable>> getItem() {
        return mTasks;
    }

    public void deleteItem(int id) {
        new deleteAsyncTask(appDatabase).execute(id);
    }

    public void insertItem(ProductTable productTable) {
        new insertAsyncTask(appDatabase).execute(productTable);
    }

    private static class deleteAsyncTask extends AsyncTask<Integer, Void, Void> {

        private TaskDatabase db;

        deleteAsyncTask(TaskDatabase appDatabase) {
            db = appDatabase;
        }


        @Override
        protected Void doInBackground(Integer... integers) {
            db.tableDao().deleteproduct(integers[0]);
            return null;
        }
    }

    private static class insertAsyncTask extends AsyncTask<ProductTable, Void, Void> {

        private TaskDatabase db;

        insertAsyncTask(TaskDatabase appDatabase) {
            db = appDatabase;
        }


        @Override
        protected Void doInBackground(ProductTable... productTables) {

            db.tableDao().insert(productTables[0]);

            return null;
        }
    }


}
