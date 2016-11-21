package pl.edu.agh.sbrandys.recyclerviewsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ContactDetailsActivity extends AppCompatActivity {

    public static final String CONTACT_EXTRA = "contact";

    private static final String TAG = "ContactDetailsActivity";

    private Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        if (getIntent().getExtras() != null && getIntent().getExtras().getSerializable(CONTACT_EXTRA) != null) {
            this.contact = (Contact) getIntent().getExtras().getSerializable(CONTACT_EXTRA);
            Log.d(TAG, "onCreate: " + this.contact);
        }
    }
}
