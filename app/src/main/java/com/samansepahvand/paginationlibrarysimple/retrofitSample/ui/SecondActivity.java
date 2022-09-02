package com.samansepahvand.paginationlibrarysimple.retrofitSample.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.samansepahvand.paginationlibrarysimple.R;
import com.samansepahvand.paginationlibrarysimple.retrofitSample.adapter.UserRVAdapter;
import com.samansepahvand.paginationlibrarysimple.retrofitSample.api.ApiClient;
import com.samansepahvand.paginationlibrarysimple.retrofitSample.model.UserModal;
import com.samansepahvand.paginationlibrarysimple.retrofitSample.model.UserModalResult;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "SecondActivity";
    // creating a variable for our array list, adapter class,
    // recycler view, progressbar, nested scroll view
    private ArrayList<UserModal> userModalArrayList;
    private UserRVAdapter userRVAdapter;
    private RecyclerView userRV;
    private ProgressBar loadingPB;
    private NestedScrollView nestedSV;

    // creating a variable for our page and limit as 2
    // as our api is having highest limit as 2 so
    // we are setting a limit = 2
    int page = 0, limit = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // creating a new array list.
        userModalArrayList = new ArrayList<>();

        // initializing our views.
        userRV = findViewById(R.id.idRVUsers);
        loadingPB = findViewById(R.id.idPBLoading);
        nestedSV = findViewById(R.id.idNestedSV);

        // calling a method to load our api.
        getDataList(page,limit);

        // adding on scroll change listener method for our nested scroll view.
        nestedSV.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                // on scroll change we are checking when users scroll as bottom.
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                    // in this method we are incrementing page number,
                    // making progress bar visible and calling get data method.
                    page++;
                    loadingPB.setVisibility(View.VISIBLE);
                    getDataList(page,limit);

                }
            }
        });
    }

    private void getDataList(int page,int limit) {

        if (page > limit) {
            // checking if the page number is greater than limit.
            // displaying toast message in this case when page>limit.
            Toast.makeText(this, "That's all the data..", Toast.LENGTH_SHORT).show();

            // hiding our progress bar.
            loadingPB.setVisibility(View.GONE);
            return;
        }


        Call<UserModalResult> call = ApiClient.getInstance().getMyApi().GetDataList(page);
     call.enqueue(new Callback<UserModalResult>() {
         @Override
         public void onResponse(Call<UserModalResult> call, retrofit2.Response<UserModalResult> response) {

             UserModalResult result=response.body();

             Log.e(TAG, "onResponse result.size(): "+result.data.size() );




             userModalArrayList.addAll(result.data);

             // passing array list to our adapter class.
             userRVAdapter = new UserRVAdapter(userModalArrayList, SecondActivity.this);

             // setting layout manager to our recycler view.
             userRV.setLayoutManager(new LinearLayoutManager(SecondActivity.this));

             // setting adapter to our recycler view.
             userRV.setAdapter(userRVAdapter);


         }

         @Override
         public void onFailure(Call<UserModalResult> call, Throwable t) {
             Log.e(TAG, "onFailure:  result.size(): "+t.getMessage() );
         }
     });


    }



}