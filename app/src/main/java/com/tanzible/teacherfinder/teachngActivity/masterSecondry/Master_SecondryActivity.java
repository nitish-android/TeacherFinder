package com.tanzible.teacherfinder.teachngActivity.masterSecondry;

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
import com.tanzible.teacherfinder.teachngActivity.masterPrimary.Master_PrimaryActivity;
import com.tanzible.teacherfinder.teachngActivity.masterPrimary.Master_PrimaryAdapter;
import com.tanzible.teacherfinder.teachngActivity.masterPrimary.Mater_PrimaryModel;

import java.util.ArrayList;
import java.util.List;

public class Master_SecondryActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private Master_SecondaryAdapter adapter;
    List<Master_SecondaryModal> master_secondaryModals;
    FirebaseFirestore firestore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master__secondry);

        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recyclerView);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Secondary Education");


        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        master_secondaryModals = new ArrayList<>();

        adapter = new Master_SecondaryAdapter(master_secondaryModals);
        recyclerView.setAdapter(adapter);

        firestore = FirebaseFirestore.getInstance();
        firestore.collection("PrimaryTeaching").orderBy("priority").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()){

                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()){
                                master_secondaryModals.add(new Master_SecondaryModal(documentSnapshot.get("student_image").toString(),documentSnapshot.get("name").toString(),documentSnapshot.get("class").toString()
                                        ,documentSnapshot.get("subject").toString(),documentSnapshot.get("priority").toString()));

                            }
                            adapter.notifyDataSetChanged();

                        }
                        else {
                            String error = task.getException().getMessage();
                            Toast.makeText(Master_SecondryActivity.this, error, Toast.LENGTH_SHORT).show();
                        }

                    }
                });





    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
