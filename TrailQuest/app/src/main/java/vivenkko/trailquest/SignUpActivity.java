package vivenkko.trailquest;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vivenkko.trailquest.models.User;
import vivenkko.trailquest.retrofit.TrailQuestController;
import vivenkko.trailquest.retrofit.TrailQuestServiceGenerator;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;


public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    EditText displayName, email, password, token;
    RequestBody username, useremail, userpassword, usertoken;
    ImageView avatar, image;
    Button btn;
    Uri selectedImage = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        avatar = findViewById(R.id.signUpAvatar);
        displayName = findViewById(R.id.editTextSingUpName);
        email = findViewById(R.id.editTextSignUpEmail);
        password = findViewById(R.id.editTextSignUpPassword);
        token = findViewById(R.id._id);
        btn = findViewById(R.id.buttonRegisterSingUp);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(displayName.getText().toString().equals("")){
                    displayName.setFocusable(true);
                    displayName.requestFocus();
                    displayName.setError(getString(R.string.error_register_empty));

                } else if (email.getText().toString().equals("")){
                    email.setFocusable(true);
                    email.requestFocus();
                    email.setError(getString(R.string.error_register_empty));

                } else if (password.getText().toString().equals("")){
                    password.setFocusable(true);
                    password.requestFocus();
                    password.setError(getString(R.string.error_register_empty));

                } else {
                    TrailQuestController api = TrailQuestServiceGenerator.createService(TrailQuestController.class);
                    String strFile = FilesUtils.getFilePath(SignUpActivity.this, selectedImage);
                    File file = new File(strFile);

                    final RequestBody requestFile = RequestBody.create(MediaType.parse(getContentResolver().getType(selectedImage)), file);

                    MultipartBody.Part body = MultipartBody.Part.createFormData("photo", file.getName(), requestFile);
                    RequestBody username = RequestBody.create(MultipartBody.FORM, displayName.getText().toString());
                    RequestBody useremail = RequestBody.create(MultipartBody.FORM, email.getText().toString());
                    RequestBody userpassword = RequestBody.create(MultipartBody.FORM, password.getText().toString());
                    RequestBody token = RequestBody.create(MultipartBody.FORM, token.getText().toString());

                    Call<ResponseBody> peticion = api.signUp("Bearer " + token, body, displayName, email, password);

                    Call<User> signUp = api.signUp(createUser);

                    final ProgressDialog progressDialog;
                    progressDialog = new ProgressDialog(SignUpActivity.this);
                    progressDialog.setMax(100);
                    progressDialog.setMessage("Registrándose...");
                    progressDialog.setTitle("Sign Up");
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

                    progressDialog.show();

                    signUp.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            if (response.isSuccessful()) {
                                progressDialog.dismiss();
                                Toast.makeText(SignUpActivity.this, "Usuario registrado", Toast.LENGTH_SHORT).show();

                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                i.putExtra("avatar", response.body().getEmail());
                                i.putExtra("displayName", response.body().getDisplayName());
                                i.putExtra("email", response.body().getEmail());
                                i.putExtra("password", response.body().getPassword());
                                i.putExtra("token", response.body().getToken());

                                startActivity(i);
                                finish();
                            } else if(response.code() == 400) {
                                progressDialog.dismiss();
                                Toast.makeText(SignUpActivity.this, "Email ya registrado", Toast.LENGTH_SHORT).show();
                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(SignUpActivity.this, "No se ha registrado aún", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Log.e("Error", t.toString());
                        }
                    });
                }
            }
        });

    }

    public void pickFromGal(){
        Intent getIntent = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        getIntent.setType("image/*");
        startActivityForResult(getIntent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(requestCode==PICK_IMAGE){
                onSelectFromGalleryResult(data);
            }
        }
    }

    private void onSelectFromGalleryResult(Intent data) {

        if (data != null) {
            try {
                Uri chosenImageUri = data.getData();
                selectedImage = chosenImageUri;
                Picasso
                        .with(SignUpActivity.this)
                        .load(chosenImageUri)
                        .into(image);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean mayRequestStoragePermission(View view) {

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return true;

        if((checkSelfPermission(WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) &&
                (checkSelfPermission(CAMERA) == PackageManager.PERMISSION_GRANTED))
            return true;

        if((shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE)) || (shouldShowRequestPermissionRationale(CAMERA))){
            Snackbar.make(view, "Los permisos son necesarios para poder usar la aplicación",
                    Snackbar.LENGTH_INDEFINITE).setAction(android.R.string.ok, new View.OnClickListener() {

                @TargetApi(Build.VERSION_CODES.M)
                @Override
                public void onClick(View v) {
                    requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, CAMERA}, 100);
                }
            });
        }else{
            requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, CAMERA}, 100);
        }

        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 100){
            if(grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permisos aceptados", Toast.LENGTH_SHORT).show();
                //mOptionButton.setEnabled(true);
            }
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Permisos denegados");
            builder.setMessage("Para usar las funciones de la app necesitas aceptar los permisos");
            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                    intent.setData(uri);
                    startActivity(intent);
                }
            });
            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    finish();
                }
            });

            builder.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
