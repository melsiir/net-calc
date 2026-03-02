package com.credit.calc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

  EditText etAmount, etDiscount;
  TextView tvResult;
  Button btnCalculate;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    EdgeToEdge.enable(this);

    setContentView(R.layout.activity_main);

    View mainView = findViewById(R.id.root);

    ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, windowInsets) -> {
      Insets systemBars = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());

      int extraPadding = (int) (24 * v.getContext().getResources().getDisplayMetrics().density);

      // Apply: System Inset + Your Margin
      v.setPadding(
          systemBars.left + extraPadding,
          systemBars.top + extraPadding,
          systemBars.right + extraPadding,
          systemBars.bottom);

      return windowInsets;
    });

    etAmount = findViewById(R.id.etAmount);
    etDiscount = findViewById(R.id.etDiscount);
    btnCalculate = findViewById(R.id.btnCalculate);
    tvResult = findViewById(R.id.tvResult);

    btnCalculate.setOnClickListener(view -> calculateNetAmount());
  }

  private void calculateNetAmount() {
    String amountStr = etAmount.getText().toString().trim();
    String discountStr = etDiscount.getText().toString().trim();

    if (amountStr.isEmpty() || discountStr.isEmpty()) {
      Toast.makeText(this, "Please enter both values", Toast.LENGTH_SHORT).show();
      return;
    }

    try {
      double amount = Double.parseDouble(amountStr);
      double discountPercent = Double.parseDouble(discountStr);

      double discountValue = amount * (discountPercent / 100);
      double netAmount = amount - discountValue;

      tvResult.setText("Net Amount: " + String.format("%.2f", netAmount));
    } catch (NumberFormatException e) {
      Toast.makeText(this, "Invalid number format", Toast.LENGTH_SHORT).show();
    }
  }
}
