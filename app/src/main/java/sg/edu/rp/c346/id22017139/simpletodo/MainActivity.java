package sg.edu.rp.c346.id22017139.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Spinner spinnerTask;
    EditText editTextTask;
    Button buttonAdd, buttonDelete, buttonClear;
    ListView listViewTasks;

    ArrayList<String> tasks;
    ArrayAdapter<String> tasksAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerTask = findViewById(R.id.spinnerTask);
        editTextTask = findViewById(R.id.editTextTask);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonClear = findViewById(R.id.buttonClear);
        listViewTasks = findViewById(R.id.listViewTasks);

        tasks = new ArrayList<>();
        tasksAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tasks);
        listViewTasks.setAdapter(tasksAdapter);

        spinnerTask.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();

                if (selectedItem.equals("Add a new task")) {
                    editTextTask.setHint(R.string.hint_task_input);
                    buttonAdd.setEnabled(true);
                    buttonDelete.setEnabled(false);
                } else if (selectedItem.equals("Remove a task")) {
                    editTextTask.setHint(R.string.hint_task_index);
                    buttonAdd.setEnabled(false);
                    buttonDelete.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTask = editTextTask.getText().toString();

                if (!newTask.isEmpty()) {
                    tasks.add(newTask);
                    tasksAdapter.notifyDataSetChanged();
                    editTextTask.getText().clear();
                }
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String indexStr = editTextTask.getText().toString();

                if (!indexStr.isEmpty()) {
                    int index = Integer.parseInt(indexStr);

                    if (index >= 0 && index < tasks.size()) {
                        tasks.remove(index);
                        tasksAdapter.notifyDataSetChanged();
                        editTextTask.getText().clear();
                    } else {
                        Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tasks.clear();
                tasksAdapter.notifyDataSetChanged();
            }
        });
    }
}
