package crane.com.cranegratitude;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SubmitData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_data);

        Intent intent = this.getIntent();
        double lat = intent.getDoubleExtra("latitude", 0.0);
        double lng = intent.getDoubleExtra("longitude", 0.0);

        EditText editText = (EditText) findViewById(R.id.inputLatitude);
        editText.setText(lat + "");

        editText = (EditText) findViewById(R.id.inputLongitude);
        editText.setText(lng + "");

        Button button = (Button) findViewById(R.id.btnCancel);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        button = (Button) findViewById(R.id.btnBack);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        button = (Button) findViewById(R.id.btnSubmit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = (TextView) findViewById(R.id.thanks);
                tv.setVisibility(View.VISIBLE);
                Button back = (Button) findViewById(R.id.btnBack);
                back.setVisibility(View.VISIBLE);
            }
        });
    }

}
