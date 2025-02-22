package com.example.btl.login;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.btl.Home;
import com.example.btl.R;
import com.example.btl.model.UserDetail;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    //    private SessionManager session;
    private EditText loginEmail, loginPassword;
    private TextView signupRedirectText;
    private Button loginButton;

    TextView forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is already logged in, start MainActivity
            startActivity(new Intent(LoginActivity.this, Home.class));
            finish();
            return;
        }
        setContentView(R.layout.activity_login);
//        session = new SessionManager(getApplicationContext());
        Log.i("----------------------------Login:", "chay");
        // Check if the user is already logged in
//        if (session.isLoggedIn()) {
//            startActivity(new Intent(LoginActivity.this, Home.class));
//            finish();
//            return;
//        }

        auth = FirebaseAuth.getInstance();
        loginEmail = findViewById(R.id.login_email);
        loginPassword = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);
        signupRedirectText = findViewById(R.id.signupRedirectText);
        forgotPassword = findViewById(R.id.forgot_password);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkUser();
            }
        });
        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                View dialogView = getLayoutInflater().inflate(R.layout.dialog_forgot, null);
                EditText emailBox = dialogView.findViewById(R.id.emailBox);

                builder.setView(dialogView);
                AlertDialog dialog = builder.create();
                Log.i("login", emailBox.getText().toString());
                dialogView.findViewById(R.id.btnReset).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String userEmail = emailBox.getText().toString();
                        if (TextUtils.isEmpty(userEmail) && !Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
                            Toast.makeText(LoginActivity.this, "Nhập email đã đăng ký", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        auth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(LoginActivity.this, "Kiểm tra email", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                } else {
                                    Toast.makeText(LoginActivity.this, "Gửi mail thất bại", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
                dialogView.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                if (dialog.getWindow() != null) {
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                }
                dialog.show();
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    public void checkUser() {
        String userUsername = loginEmail.getText().toString().trim();
        String userPassword = loginPassword.getText().toString().trim();
        if (userUsername.isEmpty() || userPassword.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(userUsername).matches()) {
            Toast.makeText(getApplicationContext(), "Vui lòng nhập địa chỉ email hợp lệ", Toast.LENGTH_SHORT).show();
        } else {
            auth.signInWithEmailAndPassword(userUsername, userPassword)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("Login", "signInWithEmail:success");
                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("profile");
                                reference.orderByChild("email").startAt(userUsername)
                                        .endAt(userUsername + "\uf8ff")
                                        .addValueEventListener(new ValueEventListener() {
                                            UserDetail userDetails;

                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                Log.i("Snapshot", snapshot.toString());
                                                for (DataSnapshot data : snapshot.getChildren()) {
                                                    Log.i("data", data.getKey());
                                                    userDetails = data.getValue(UserDetail.class);
                                                    break;
                                                }
                                                Log.i("----------------------Login",userDetails.getName());
                                                String nameFromDB = userDetails.getName();
                                                String contactFromDB = userDetails.getContact();
                                                String emailFromDB = userDetails.getEmail();
                                                String BirthDateFromDB = userDetails.getBirthdate();
                                                Intent intent = new Intent(LoginActivity.this, Home.class);
                                                intent.putExtra("name", nameFromDB);
                                                intent.putExtra("email", emailFromDB);
                                                intent.putExtra("birthdate", BirthDateFromDB);
                                                intent.putExtra("contact", contactFromDB);
                                                Toast.makeText(LoginActivity.this, "Đăng nhập thành công",
                                                        Toast.LENGTH_SHORT).show();
                                                startActivity(intent);
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                                Toast.makeText(LoginActivity.this, "Không tìm thấy tài khoản", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("Login", "signInWithEmail:failure", task.getException());
                                Toast.makeText(LoginActivity.this, "Đăng nhập thất bại",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}
