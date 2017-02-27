package app42Sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.vrs.spinnerdemo.Home;
import com.example.vrs.spinnerdemo.R;

public class MainhomeActivity extends AppCompatActivity {
    public final static String KEY5 = "com.sliet.jeevansingh.projectjeevankeyone5";
    private EditText Name;
    private EditText Email;
    public String a ,c,d;
    public String b ;
    private Button button;
    private Button button1;


    /// private SharedPrefManager sharedPrefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_);
        Name = (((EditText) findViewById(R.id.editTextName)));
        Email = (((EditText) findViewById(R.id.editTextEmail)));
        button = (Button) findViewById(R.id.button);
        button1=(Button) findViewById(R.id.button2);



    }

    public void user_registration(View v) {
        Name = (((EditText) findViewById(R.id.editTextName)));
        Email = (((EditText) findViewById(R.id.editTextEmail)));
        button = (Button) findViewById(R.id.button);
        button1=(Button) findViewById(R.id.button2);
        c=Name.getText().toString();
        d=Email.getText().toString();
        a = (Name.getText().toString()+"__"+Email.getText().toString());
        String CurrentString = a;
     //   Toast.makeText(this,a,Toast.LENGTH_SHORT).show();
       // if((c!=null)&&(d!=null)) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(KEY5, CurrentString);
            startActivity(intent);
       /* }else{
            Toast.makeText(this,"Not valid",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
        }*/
    }
    public void Skip(View v){

        Intent intent = new Intent(this,Home.class);
        startActivity(intent);
    }

}