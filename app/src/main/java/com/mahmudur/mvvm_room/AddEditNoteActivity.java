package com.mahmudur.mvvm_room;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddEditNoteActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "com.mahmudur.mvvm_room.EXTRA_ID";
    public static final String EXTRA_TITLE = "com.mahmudur.mvvm_room.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTIOM = "com.mahmudur.mvvm_room.EXTRA_DESCRIPTIOM";
    public static final String EXTRA_PRIORITY = "com.mahmudur.mvvm_room.EXTRA_PRIORITY";
    EditText editTextTitle, editTextDesc;
    NumberPicker numberPickerPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        editTextTitle = findViewById(R.id.EditTitle);
        editTextDesc = findViewById(R.id.EditDescription);
        numberPickerPriority = findViewById(R.id.numberpicker_set_Priority);

        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_close);
        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");
            editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            editTextDesc.setText(intent.getStringExtra(EXTRA_DESCRIPTIOM));
            numberPickerPriority.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));
        } else {
            setTitle("Add Note");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    private void saveNote() {
        String title = editTextTitle.getText().toString();
        String description = editTextDesc.getText().toString();
        int prio = numberPickerPriority.getValue();
        String priority = String.valueOf(prio);

        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Empty Values cannot be saved", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTIOM, description);
        data.putExtra(EXTRA_PRIORITY, priority);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1){
            data.putExtra(EXTRA_ID,id);
        }
        setResult(RESULT_OK, data);
        finish();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save_note) {
            saveNote();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}