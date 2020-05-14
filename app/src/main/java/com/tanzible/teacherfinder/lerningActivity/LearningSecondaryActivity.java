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
import com.tanzible.teacherfinder.lerningActivity.teacherSecondary.TeacherSecondaryAdapter;
import com.tanzible.teacherfinder.lerningActivity.teacherSecondary.TeacherSecondaryModal;

public class LearningSecondaryActivity extends AppCompatActivity {

    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private CollectionReference reference = firestore.collection("SecondaryLearning");
    private TeacherSecondaryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_secondary);


        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        Query query = reference.orderBy("priority",Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<TeacherSecondaryModal> options = new FirestoreRecyclerOptions.Builder<TeacherSecondaryModal>()
                .setQuery(query,TeacherSecondaryModal.class).build();

        adapter = new TeacherSecondaryAdapter(options);
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
