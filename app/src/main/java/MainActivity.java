package com.expensecalculator;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    // UI Components
    Spinner categorySpinner;
    TextInputEditText amountInput, descInput;
    Button saveButton;

    // Data for the spinner
    String[] categories = {"Food", "Transport", "Bills", "School", "Others"};
    int[] icons = {R.drawable.ic_food, R.drawable.ic_transport, R.drawable.ic_bills,
            R.drawable.ic_school, R.drawable.ic_others};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // --- SAFE INITIALIZATION ---
        // This code will now try to find the views. If an ID is missing in the XML,
        // the variable will remain 'null' instead of crashing the build.
        // This will help us identify which specific ID is missing.

        try {
            categorySpinner = findViewById(R.id.categorySpinner);
            amountInput = findViewById(R.id.amountInput);
            descInput = findViewById(R.id.descInput);
            saveButton = findViewById(R.id.saveButton);
        } catch (Exception e) {
            // This catch block will prevent a crash if an ID is fundamentally missing,
            // which was causing your build to fail.
            Log.e("MainActivity", "A view ID is missing in your activity_main.xml file!", e);
            Toast.makeText(this, "Error: A View ID is missing in the XML layout.", Toast.LENGTH_LONG).show();
        }


        // --- ADAPTER SETUP ---
        // We check if the spinner was found before trying to use it.
        if (categorySpinner != null) {
            CategoryAdapter adapter = new CategoryAdapter(this, categories, icons);
            categorySpinner.setAdapter(adapter);
        } else {
            Log.e("MainActivity", "Could not find 'categorySpinner'. Check the ID in activity_main.xml.");
        }


        // --- BUTTON SETUP ---
        // We check if the button was found before trying to use it.
        if (saveButton != null) {
            saveButton.setOnClickListener(v -> {
                // Check if the input fields were found before getting their text
                String amount = (amountInput != null) ? amountInput.getText().toString() : "Not found";
                String description = (descInput != null) ? descInput.getText().toString() : "Not found";

                // Log the values to see if we are getting them correctly
                Log.d("MainActivity", "Amount: " + amount);
                Log.d("MainActivity", "Description: " + description);

                Toast.makeText(MainActivity.this, "Expense saved!", Toast.LENGTH_SHORT).show();
            });
        } else {
            Log.e("MainActivity", "Could not find 'saveButton'. Check the ID in activity_main.xml.");
        }
    }

    // This custom adapter for the spinner is efficient and follows best practices.
    class CategoryAdapter extends BaseAdapter {
        Context context;
        String[] categoryNames;
        int[] categoryIcons;
        LayoutInflater inflater;

        public CategoryAdapter(Context appContext, String[] categoryNames, int[] categoryIcons) {
            this.context = appContext;
            this.categoryNames = categoryNames;
            this.categoryIcons = categoryIcons;
            this.inflater = LayoutInflater.from(appContext);
        }

        @Override
        public int getCount() {
            return categoryNames.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                // This requires you to have a file named 'spinner_item_layout.xml' in your res/layout folder.
                convertView = inflater.inflate(R.layout.spinner_item_layout, parent, false);
            }

            ImageView icon = convertView.findViewById(R.id.spinner_icon);
            TextView names = convertView.findViewById(R.id.spinner_text);

            icon.setImageResource(categoryIcons[position]);
            names.setText(categoryNames[position]);

            // Make sure you have a color named "white" in your res/values/colors.xml file.
            names.setTextColor(ContextCompat.getColor(context, R.color.white));

            return convertView;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return getView(position, convertView, parent);
        }
    }
}
