package mina.app.wordleapp;

//Importing all needed packages
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;




public class MainActivity extends AppCompatActivity {

    //Creating all needed variables (MAINLY EDITTEXTS)
    GridLayout grid;

    TextView result;

    EditText firstRowFirst;
    EditText firstRowSecond;
    EditText firstRowThird;
    EditText firstRowFourth;
    EditText firstRowFifth;
    EditText secondRowFirst;
    EditText secondRowSecond;
    EditText secondRowThird;
    EditText secondRowFourth;
    EditText secondRowFifth;
    EditText thirdRowFirst;
    EditText thirdRowSecond;
    EditText thirdRowThird;
    EditText thirdRowFourth;
    EditText thirdRowFifth;
    EditText fourthRowFirst;
    EditText fourthRowSecond;
    EditText fourthRowThird;
    EditText fourthRowFourth;
    EditText fourthRowFifth;

    EditText fifthRowFirst;
    EditText fifthRowSecond;
    EditText fifthRowThird;
    EditText fifthRowFourth;
    EditText fifthRowFifth;

    EditText sixthRowFirst;
    EditText sixthRowSecond;
    EditText sixthRowThird;
    EditText sixthRowFourth;
    EditText sixthRowFifth;

    Button submitButton;
    Button clearButton;
    Button restartButton;
    Button addNewButton;

    int count = 0;

    ArrayList<String> words = new ArrayList<>();

    String wordyCorrectWord = "hello";


    //Creating listener to start go to the Add Activity when the "Add New Word" Button is pressed
    View.OnClickListener activityListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), AddActivity.class);
            startActivity(intent);
        }
    };

    //Listener to check if each word exists
    //In this listener, the count variable controls which row we are on and the next row to set visible
    //This listener also calls checkWord(), which checks to see if the the line matches the wordle
    View.OnClickListener submitListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (count == 0) {
                checkWord(wordyCorrectWord, firstRowFirst, firstRowSecond, firstRowThird, firstRowFourth, firstRowFifth);
                if (result.getText().toString().isEmpty()) {
                    secondRowFirst.setVisibility(View.VISIBLE);
                    secondRowSecond.setVisibility(View.VISIBLE);
                    secondRowThird.setVisibility(View.VISIBLE);
                    secondRowFourth.setVisibility(View.VISIBLE);
                    secondRowFifth.setVisibility(View.VISIBLE);
                }
            }
            if (count == 1) {
                checkWord(wordyCorrectWord, secondRowFirst, secondRowSecond, secondRowThird, secondRowFourth, secondRowFifth);
                if (result.getText().toString().isEmpty()) {
                    thirdRowFirst.setVisibility(View.VISIBLE);
                    thirdRowSecond.setVisibility(View.VISIBLE);
                    thirdRowThird.setVisibility(View.VISIBLE);
                    thirdRowFourth.setVisibility(View.VISIBLE);
                    thirdRowFifth.setVisibility(View.VISIBLE);
                }
            }
            if (count == 2) {
                checkWord(wordyCorrectWord, thirdRowFirst, thirdRowSecond, thirdRowThird, thirdRowFourth, thirdRowFifth);
                if (result.getText().toString().isEmpty()) {
                    fourthRowFirst.setVisibility(View.VISIBLE);
                    fourthRowSecond.setVisibility(View.VISIBLE);
                    fourthRowThird.setVisibility(View.VISIBLE);
                    fourthRowFourth.setVisibility(View.VISIBLE);
                    fourthRowFifth.setVisibility(View.VISIBLE);
                }
            }
            if (count == 3) {
                checkWord(wordyCorrectWord, fourthRowFirst, fourthRowSecond, fourthRowThird, fourthRowFourth, fourthRowFifth);
                if (result.getText().toString().isEmpty()) {
                    fifthRowFirst.setVisibility(View.VISIBLE);
                    fifthRowSecond.setVisibility(View.VISIBLE);
                    fifthRowThird.setVisibility(View.VISIBLE);
                    fifthRowFourth.setVisibility(View.VISIBLE);
                    fifthRowFifth.setVisibility(View.VISIBLE);
                }
            }
            if (count == 4) {
                checkWord(wordyCorrectWord, fifthRowFirst, fifthRowSecond, fifthRowThird, fifthRowFourth, fifthRowFifth);
                if (result.getText().toString().isEmpty()) {
                    sixthRowFirst.setVisibility(View.VISIBLE);
                    sixthRowSecond.setVisibility(View.VISIBLE);
                    sixthRowThird.setVisibility(View.VISIBLE);
                    sixthRowFourth.setVisibility(View.VISIBLE);
                    sixthRowFifth.setVisibility(View.VISIBLE);
                }
            }
            if (count == 5) {
                checkWord(wordyCorrectWord, sixthRowFirst, sixthRowSecond, sixthRowThird, sixthRowFourth, sixthRowFifth);
                count = 0;
            }
            count++;
        }
    };

    //This function is very important for the program.
    //It takes 6 parameters: the wordle and the five EditTexts of our current line
    //It first sets the correct gray and yellow if the wordle contains the letters
    //Then, if they are in the correct place, then the letters are set to green
    public void checkWord(String word, EditText first, EditText second, EditText third, EditText fourth, EditText fifth) {
        int count = 0;

        if (word.contains(first.getText().toString()) && !first.getText().toString().isEmpty()) {
            first.setBackgroundColor(getResources().getColor(R.color.yellow));
        } else {
            first.setBackgroundColor(getResources().getColor(R.color.gray));
        }
        if (word.contains(second.getText().toString()) && !second.getText().toString().isEmpty()) {
            second.setBackgroundColor(getResources().getColor(R.color.yellow));
        } else {
            second.setBackgroundColor(getResources().getColor(R.color.gray));
        }
        if (word.contains(third.getText().toString()) && !third.getText().toString().isEmpty()) {
            third.setBackgroundColor(getResources().getColor(R.color.yellow));
        } else {
            third.setBackgroundColor(getResources().getColor(R.color.gray));
        }
        if (word.contains(fourth.getText().toString()) && !fourth.getText().toString().isEmpty()) {
            fourth.setBackgroundColor(getResources().getColor(R.color.yellow));
        } else {
            fourth.setBackgroundColor(getResources().getColor(R.color.gray));
        }
        if (word.contains(fifth.getText().toString()) && !fifth.getText().toString().isEmpty()) {
            fifth.setBackgroundColor(getResources().getColor(R.color.yellow));
        } else {
            fifth.setBackgroundColor(getResources().getColor(R.color.gray));
        }

        if (word.substring(0, 1).equals(first.getText().toString())) {
            first.setBackgroundColor(getResources().getColor(R.color.green));
            count++;
        }
        if (word.substring(1, 2).equals(second.getText().toString())) {
            second.setBackgroundColor(getResources().getColor(R.color.green));
            count++;
        }
        if (word.substring(2, 3).equals(third.getText().toString())) {
            third.setBackgroundColor(getResources().getColor(R.color.green));
            count++;
        }
        if (word.substring(3, 4).equals(fourth.getText().toString())) {
            fourth.setBackgroundColor(getResources().getColor(R.color.green));
            count++;
        }
        if (word.substring(4).equals(fifth.getText().toString())) {
            fifth.setBackgroundColor(getResources().getColor(R.color.green));
            count++;
        }

        //If all letters are green, then display winning textview
        if (count == 5) {
            result.setText("CONGRATS: YOU WIN! Press Restart to play again");
            result.setTextColor(getResources().getColor(R.color.green));
        } else {
            //if we are on the sixthRow and we did not get all the correct letters, then display losing textview
            if (first == sixthRowFirst && count != 5) {
                result.setText("YOU LOSEEEEEEEEE! Press Restart to play again");
                result.setTextColor(getResources().getColor(R.color.red));
            }
        }
    }

    //Listener to reset contentview of app and make sure to not change the wordle
    //we do this by basically just setting the activity to its original state
    View.OnClickListener clearListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setContentView(R.layout.activity_main);
            count = 0;
            firstRowFirst = findViewById(R.id.first_row_first);
            firstRowSecond = findViewById(R.id.first_row_second);
            firstRowThird = findViewById(R.id.first_row_third);
            firstRowFourth = findViewById(R.id.first_row_fourth);
            firstRowFifth = findViewById(R.id.first_row_fifth);
            secondRowFirst = findViewById(R.id.second_row_first);
            secondRowSecond = findViewById(R.id.second_row_second);
            secondRowThird = findViewById(R.id.second_row_third);
            secondRowFourth = findViewById(R.id.second_row_fourth);
            secondRowFifth = findViewById(R.id.second_row_fifth);
            thirdRowFirst = findViewById(R.id.third_row_first);
            thirdRowSecond = findViewById(R.id.third_row_second);
            thirdRowThird = findViewById(R.id.third_row_third);
            thirdRowFourth = findViewById(R.id.third_row_fourth);
            thirdRowFifth = findViewById(R.id.third_row_fifth);
            fourthRowFirst = findViewById(R.id.fourth_row_first);
            fourthRowSecond = findViewById(R.id.fourth_row_second);
            fourthRowThird = findViewById(R.id.fourth_row_third);
            fourthRowFourth = findViewById(R.id.fourth_row_fourth);
            fourthRowFifth = findViewById(R.id.fourth_row_fifth);
            fifthRowFirst = findViewById(R.id.fifth_row_first);
            fifthRowSecond = findViewById(R.id.fifth_row_second);
            fifthRowThird = findViewById(R.id.fifth_row_third);
            fifthRowFourth = findViewById(R.id.fifth_row_fourth);
            fifthRowFifth = findViewById(R.id.fifth_row_fifth);
            sixthRowFirst = findViewById(R.id.sixth_row_fifth);
            sixthRowSecond = findViewById(R.id.sixth_row_fourth);
            sixthRowThird = findViewById(R.id.sixth_row_third);
            sixthRowFourth = findViewById(R.id.sixth_row_second);
            sixthRowFifth = findViewById(R.id.sixth_row_first);
            result = findViewById(R.id.resultView);

            secondRowFirst.setVisibility(View.INVISIBLE);
            secondRowSecond.setVisibility(View.INVISIBLE);
            secondRowThird.setVisibility(View.INVISIBLE);
            secondRowFourth.setVisibility(View.INVISIBLE);
            secondRowFifth.setVisibility(View.INVISIBLE);
            thirdRowFirst.setVisibility(View.INVISIBLE);
            thirdRowSecond.setVisibility(View.INVISIBLE);
            thirdRowThird.setVisibility(View.INVISIBLE);
            thirdRowFourth.setVisibility(View.INVISIBLE);
            thirdRowFifth.setVisibility(View.INVISIBLE);
            fourthRowFirst.setVisibility(View.INVISIBLE);
            fourthRowSecond.setVisibility(View.INVISIBLE);
            fourthRowThird.setVisibility(View.INVISIBLE);
            fourthRowFourth.setVisibility(View.INVISIBLE);
            fourthRowFifth.setVisibility(View.INVISIBLE);
            fifthRowFirst.setVisibility(View.INVISIBLE);
            fifthRowSecond.setVisibility(View.INVISIBLE);
            fifthRowThird.setVisibility(View.INVISIBLE);
            fifthRowFourth.setVisibility(View.INVISIBLE);
            fifthRowFifth.setVisibility(View.INVISIBLE);
            sixthRowFirst.setVisibility(View.INVISIBLE);
            sixthRowSecond.setVisibility(View.INVISIBLE);
            sixthRowThird.setVisibility(View.INVISIBLE);
            sixthRowFourth.setVisibility(View.INVISIBLE);
            sixthRowFifth.setVisibility(View.INVISIBLE);


            addNewButton = findViewById(R.id.add_activity_button);
            submitButton = findViewById(R.id.submit_button);
            clearButton = findViewById(R.id.clear_button);
            restartButton = findViewById(R.id.restart_button);

            addNewButton.setOnClickListener(activityListener);
            submitButton.setOnClickListener(submitListener);
            clearButton.setOnClickListener(clearListener);
            restartButton.setOnClickListener(restartListener);


            // Initialize Firebase
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

            // Reference to the "Words" node in the Firebase database
            DatabaseReference myRef = firebaseDatabase.getReference();

            Log.d("wordy", wordyCorrectWord);
        }
    };

    //Listener to restart game WITH a new wordle
    //in this instance, we just basically start a new activity with a new wordle
    //a new wordle is automatically created within the OnCreate
    View.OnClickListener restartListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
    };

    //method to reference data/pull random word in Database
    private void getdata() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference();

        // calling add value event listener method
        // for getting the values from database.
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // this method is call to get the realtime
                // updates in the data.
                // this method is called when the data is
                // changed in our Firebase console.
                // below line is for getting the data from
                // snapshot of our database.
                Map<String, Object> map = (Map<String, Object>) snapshot.getValue();
                ArrayList<String> keyList = new ArrayList<>(map.keySet());

                int mapSize = map.size();
                Random random = new Random();
                int randomIndex = random.nextInt(mapSize);


                wordyCorrectWord = keyList.get(randomIndex);
                Log.d("updated", wordyCorrectWord);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
                Toast.makeText(MainActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }



    //onCreate method, self explanatoy
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setting all findViewById
        setContentView(R.layout.activity_main);
        grid = findViewById(R.id.gridLayout);
        firstRowFirst = findViewById(R.id.first_row_first);
        firstRowSecond = findViewById(R.id.first_row_second);
        firstRowThird = findViewById(R.id.first_row_third);
        firstRowFourth = findViewById(R.id.first_row_fourth);
        firstRowFifth = findViewById(R.id.first_row_fifth);
        secondRowFirst = findViewById(R.id.second_row_first);
        secondRowSecond = findViewById(R.id.second_row_second);
        secondRowThird = findViewById(R.id.second_row_third);
        secondRowFourth = findViewById(R.id.second_row_fourth);
        secondRowFifth = findViewById(R.id.second_row_fifth);
        thirdRowFirst = findViewById(R.id.third_row_first);
        thirdRowSecond = findViewById(R.id.third_row_second);
        thirdRowThird = findViewById(R.id.third_row_third);
        thirdRowFourth = findViewById(R.id.third_row_fourth);
        thirdRowFifth = findViewById(R.id.third_row_fifth);
        fourthRowFirst = findViewById(R.id.fourth_row_first);
        fourthRowSecond = findViewById(R.id.fourth_row_second);
        fourthRowThird = findViewById(R.id.fourth_row_third);
        fourthRowFourth = findViewById(R.id.fourth_row_fourth);
        fourthRowFifth = findViewById(R.id.fourth_row_fifth);
        fifthRowFirst = findViewById(R.id.fifth_row_first);
        fifthRowSecond = findViewById(R.id.fifth_row_second);
        fifthRowThird = findViewById(R.id.fifth_row_third);
        fifthRowFourth = findViewById(R.id.fifth_row_fourth);
        fifthRowFifth = findViewById(R.id.fifth_row_fifth);
        sixthRowFirst = findViewById(R.id.sixth_row_fifth);
        sixthRowSecond = findViewById(R.id.sixth_row_fourth);
        sixthRowThird = findViewById(R.id.sixth_row_third);
        sixthRowFourth = findViewById(R.id.sixth_row_second);
        sixthRowFifth = findViewById(R.id.sixth_row_first);
        result = findViewById(R.id.resultView);

        //Setting rows 2-6 to invisible to ensure user goes in sequential order from first line
        secondRowFirst.setVisibility(View.INVISIBLE);
        secondRowSecond.setVisibility(View.INVISIBLE);
        secondRowThird.setVisibility(View.INVISIBLE);
        secondRowFourth.setVisibility(View.INVISIBLE);
        secondRowFifth.setVisibility(View.INVISIBLE);
        thirdRowFirst.setVisibility(View.INVISIBLE);
        thirdRowSecond.setVisibility(View.INVISIBLE);
        thirdRowThird.setVisibility(View.INVISIBLE);
        thirdRowFourth.setVisibility(View.INVISIBLE);
        thirdRowFifth.setVisibility(View.INVISIBLE);
        fourthRowFirst.setVisibility(View.INVISIBLE);
        fourthRowSecond.setVisibility(View.INVISIBLE);
        fourthRowThird.setVisibility(View.INVISIBLE);
        fourthRowFourth.setVisibility(View.INVISIBLE);
        fourthRowFifth.setVisibility(View.INVISIBLE);
        fifthRowFirst.setVisibility(View.INVISIBLE);
        fifthRowSecond.setVisibility(View.INVISIBLE);
        fifthRowThird.setVisibility(View.INVISIBLE);
        fifthRowFourth.setVisibility(View.INVISIBLE);
        fifthRowFifth.setVisibility(View.INVISIBLE);
        sixthRowFirst.setVisibility(View.INVISIBLE);
        sixthRowSecond.setVisibility(View.INVISIBLE);
        sixthRowThird.setVisibility(View.INVISIBLE);
        sixthRowFourth.setVisibility(View.INVISIBLE);
        sixthRowFifth.setVisibility(View.INVISIBLE);


        //setting button viewbyid
        addNewButton = findViewById(R.id.add_activity_button);
        submitButton = findViewById(R.id.submit_button);
        clearButton = findViewById(R.id.clear_button);
        restartButton = findViewById(R.id.restart_button);

        //setting onclicklisteners
        addNewButton.setOnClickListener(activityListener);
        submitButton.setOnClickListener(submitListener);
        clearButton.setOnClickListener(clearListener);
        restartButton.setOnClickListener(restartListener);


        // Initialize Firebase
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        // Reference to the "Words" node in the Firebase database
        DatabaseReference myRef = firebaseDatabase.getReference();

        //call to get new Wordle (assigns it to wordyCorrectWord)
        getdata();
        Log.d("wordy", wordyCorrectWord);

    }
}