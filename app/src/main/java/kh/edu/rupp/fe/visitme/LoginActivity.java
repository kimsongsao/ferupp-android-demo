package kh.edu.rupp.fe.visitme;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import kh.edu.rupp.fe.visitme.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    int attempt_counter = 5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.buttonLogin.setOnClickListener(v -> {
            navigateToMain();
        });
    }

    private void navigateToMain() {
        if (binding.editTextTextEmailAddress.getText().toString().equals("Kimsong") &&
                binding.editTextPassword.getText().toString().equals("123456")) {
            Toast.makeText(this, "User and Password is correct", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "User and Password is not correct", Toast.LENGTH_SHORT).show();
            attempt_counter--;
            if (attempt_counter == 0) {
                binding.buttonLogin.setEnabled(false);
            }
        }
    }
}
