package com.tanzible.teacherfinder.lerningActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.tanzible.teacherfinder.R;
import com.tanzible.teacherfinder.lerningActivity.teacherHigher.TeacherHigherAdapter;
import com.tanzible.teacherfinder.lerningActivity.teacherHigher.TeacherHigherModal;
import com.tanzible.teacherfinder.lerningActivity.teacherPrimary.TeacherPrimaryAdapter;
import com.tanzible.teacherfinder.lerningActivity.teacherPrimary.TeacherPrimaryModel;

public class LearningHigherActivity extends AppCompatActivity {
    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private CollectionReference reference = firestore.collection("HigherLearning");
    private TeacherHigherAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_higher);

        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        Query query = reference.orderBy("priority",Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<TeacherHigherModal> options = new FirestoreRecyclerOptions.Builder<TeacherHigherModal>()
                .setQuery(query,TeacherHigherModal.class).build();

        adapter = new TeacherHigherAdapter(options);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
