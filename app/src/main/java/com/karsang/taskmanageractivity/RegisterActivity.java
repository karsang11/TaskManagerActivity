package com.karsang.taskmanageractivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.karsang.taskmanageractivity.API.UsersAPI;
import com.karsang.taskmanageractivity.Model.User;
import com.karsang.taskmanageractivity.ServerResponse.SignUpResponse;
import com.karsang.taskmanageractivity.Url.Url;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private ImageView imageView;
    private EditText etfname,etlname,etuname,etpwsd,etcpwsd;
    private Button btnsignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        imageView=findViewById(R.id.imageView);
        etfname=findViewById(R.id.etfname);
        etlname=findViewById(R.id.etlname);
        etuname=findViewById(R.id.etuname);
        etpwsd=findViewById(R.id.etpwsd);
        etcpwsd=findViewById(R.id.etcpwsd);
        btnsignup=findViewById(R.id.btnsignup);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseImage();
            }
        });


        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etcpwsd.getText().toString().equals(etcpwsd.getText().toString())){
                    register();

                }
                else {
                    Toast.makeText(RegisterActivity.this, "Password does not match", Toast.LENGTH_SHORT).show();
                    etpwsd.requestFocus();
                    return;
                }
            }


        });

    }

    private void BrowseImage(){
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            if (data==null){
                Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show();
            }
        }
        Uri uri=data.getData();

    }

 /*   private String getRealPathFromUri(Uri uri){
        String[] projection={MediaStore.Images.Media.DATA};
        CursorLoader loader=new CursorLoader(getApplicationContext(),uri,projection,null,null,null);
        Cursor cursor=loader.loadInBackground();
        int colIndex=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result=cursor.getString(colIndex);
        cursor.close();
        return result;
    }*/

    private void register(){
        String fname=etfname.getText().toString();
        String lname=etlname.getText().toString();
        String uname=etuname.getText().toString();
        String pwsd=etpwsd.getText().toString();

        User user=new User(fname,lname,uname,pwsd);

        UsersAPI usersAPI= Url.getInstance().create(UsersAPI.class);
        Call<SignUpResponse> signUpResponseCall=usersAPI.register(user);
        signUpResponseCall.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "Code", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(RegisterActivity.this, "Registered", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
