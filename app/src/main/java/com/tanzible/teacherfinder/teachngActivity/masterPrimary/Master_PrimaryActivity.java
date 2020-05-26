package com.tanzible.teacherfinder.teachngActivity.masterPrimary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.tanzible.teacherfinder.R;

import java.util.ArrayList;
import java.util.List;

public class Master_PrimaryActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private Master_PrimaryAdapter adapter;
    List<Mater_PrimaryModel> mater_primaryModels;
    FirebaseFirestore firestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master__primary);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Primary Education");


        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);


        mater_primaryModels = new ArrayList<Mater_PrimaryModel>();

        adapter = new Master_PrimaryAdapter(mater_primaryModels);
        recyclerView.setAdapter(adapter);


        firestore = FirebaseFirestore.getInstance();
        firestore.collection("PrimaryTeaching").orderBy("priority").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()){

                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()){
                                mater_primaryModels.add(new Mater_PrimaryModel(documentSnapshot.get("student_image").toString(),documentSnapshot.get("name").toString(),documentSnapshot.get("class").toString()
                                        ,documentSnapshot.get("subject").toString(),documentSnapshot.get("priority").toString()));

                            }
                            adapter.notifyDataSetChanged();

                        }
                        else {
                            String error = task.getException().getMessage();
                            Toast.makeText(Master_PrimaryActivity.this, error, Toast.LENGTH_SHORT).show();
                        }

                    }
                });





    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }




}
