package mina.app.wordleapp;

//importing all needed packages
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class AddActivity extends AppCompatActivity {


    //creating all needed variables
    FirebaseDatabase database;
    DatabaseReference myRef;
    EditText wordInput;
    Button submitButton;
    TextView wordView;
    private ArrayList<String> words = new ArrayList<>();
    Button returnButton;

    //onClickListener to return back to Wordle screen
    View.OnClickListener activityListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    };




    //onClickListener to submit button
    View.OnClickListener submitListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String word = wordInput.getText().toString().toLowerCase();

            // Check if the word is not empty or less than 5
            if (word.isEmpty() || word.length() < 5) {
                //set text to purple and display toast
                Toast.makeText(getApplicationContext(), "Please enter a valid word.", Toast.LENGTH_LONG).show();
                wordView.setTextColor(getResources().getColor(R.color.purple));

                return;
            }

            DatabaseReference wordsRef = database.getReference(word);
            String key = wordsRef.push().getKey();

            if (key != null) {
                // Check if the word already exists in the database
                wordsRef.orderByValue().equalTo(word).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            // Word already exists in the database
                            Toast.makeText(getApplicationContext(), "Word already exists in the database.", Toast.LENGTH_LONG).show();
                        } else {
                            // Word doesn't exist in the database, add it
                            wordView.setTextColor(getResources().getColor(R.color.black));
                            wordsRef.child(key).setValue(word)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            // Word added successfully
                                            Toast.makeText(getApplicationContext(), "The word has been added to the database!", Toast.LENGTH_LONG).show();
                                            wordInput.setText("");
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            // Handle any errors here
                                        }
                                    });
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle errors here
                    }
                });
            }
        }
    };

    //onCreate (self explanatory)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //setting all viewbyid to corresponding view
        wordInput = findViewById(R.id.word_input);
        returnButton = findViewById(R.id.cancel_button);
        submitButton = findViewById(R.id.data_submit_button);
        wordView = findViewById(R.id.word_view);

        //setting onclicklisteners for buttons
        returnButton.setOnClickListener(activityListener);
        submitButton.setOnClickListener(submitListener);

        //creating database reference
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

    }
}