package com.expensecalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    Spinner categorySpinner;
    TextInputEditText amountInput, descInput;
    Button saveButton;

    String[] categories = {"Food", "Transport", "Bills", "School", "Others"};
    int[] icons = {R.drawable.ic_food, R.drawable.ic_transport, R.drawable.ic_bills,
            R.drawable.ic_school, R.drawable.ic_others};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categorySpinner = findViewById(R.id.categorySpinner);
        amountInput = findViewById(R.id.amountInput);
        descInput = findViewById(R.id.descInput);
        saveButton = findViewById(R.id.saveButton);

        CategoryAdapter adapter = new CategoryAdapter();
        categorySpinner.setAdapter(adapter);

        saveButton.setOnClickListener(v ->
                Toast.makeText(this, "Expense saved!", Toast.LENGTH_SHORT).show()
        );
    }

    class CategoryAdapter extends BaseAdapter {
        @Override public int getCount() { return categories.length; }
        @Override public Object getItem(int i) { return categories[i]; }
        @Override public long getItemId(int i) { return i; }

        @Override
        public View getView(int i, View convertView, android.view.ViewGroup parent) {
            View view = getLayoutInflater().inflate(android.R.layout.simple_list_item_1, null);
            LinearLayout layout = new LinearLayout(MainActivity.this);
            layout.setOrientation(LinearLayout.HORIZONTAL);
            ImageView icon = new ImageView(MainActivity.this);
            icon.setImageResource(icons[i]);
            TextView text = new TextView(MainActivity.this);
            text.setText("  " + categories[i]);
            text.setTextColor(getColor(android.R.color.white));
            layout.addView(icon);
            layout.addView(text);
            return layout;
        }
    }
}
