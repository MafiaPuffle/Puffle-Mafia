package com.example.pufflemafia;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class Test extends AppCompatActivity {
    private EditText nameEditText;
    private Button addNameButton;
    private GridView namesGridView;
    private ArrayList<String> namesList;
    private NamesAdapter namesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        nameEditText = findViewById(R.id.nameEditText);
        addNameButton = findViewById(R.id.addNameButton);
        namesGridView = findViewById(R.id.namesGridView);

        namesList = new ArrayList<>();
        namesAdapter = new NamesAdapter();
        namesGridView.setAdapter(namesAdapter);

        addNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addName();
            }
        });
    }

    private void addName() {
        String name = nameEditText.getText().toString().trim();
        if (!name.isEmpty()) {
            namesList.add(name);
            nameEditText.setText(""); // Clear the input field
            namesAdapter.notifyDataSetChanged();
        }
    }

    private class NamesAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return namesList.size();
        }

        @Override
        public Object getItem(int position) {
            return namesList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            Button button;
            if (convertView == null) {
                button = new Button(Test.this);
                button.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                button.setPadding(8, 8, 8, 8);
            } else {
                button = (Button) convertView;
            }

            final String name = namesList.get(position);
            button.setText(name);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    namesList.remove(position);
                    notifyDataSetChanged();
                }
            });

            return button;
        }
    }
}
