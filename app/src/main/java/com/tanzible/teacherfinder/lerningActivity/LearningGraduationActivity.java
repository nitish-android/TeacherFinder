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
import com.tanzible.teacherfinder.lerningActivity.teacherGraduation.TeacherGraduationAdapter;
import com.tanzible.teacherfinder.lerningActivity.teacherGraduation.TeacherGraduationModal;
import com.tanzible.teacherfinder.lerningActivity.teacherPrimary.TeacherPrimaryAdapter;
import com.tanzible.teacherfinder.lerningActivity.teacherPrimary.TeacherPrimaryModel;

public class LearningGraduationActivity extends AppCompatActivity {

    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private CollectionReference reference = firestore.collection("GraduationLearning");
    private TeacherGraduationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_graduation);
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {

        Query query = reference.orderBy("priority",Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<TeacherGraduationModal> options = new FirestoreRecyclerOptions.Builder<TeacherGraduationModal>()
                .setQuery(query,TeacherGraduationModal.class).build();

        adapter = new TeacherGraduationAdapter(options);
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
