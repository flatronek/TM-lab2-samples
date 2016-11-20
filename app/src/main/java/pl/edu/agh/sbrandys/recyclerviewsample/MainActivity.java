package pl.edu.agh.sbrandys.recyclerviewsample;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RecyclerView contactsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactsRecyclerView = (RecyclerView) findViewById(R.id.contacts_recycler_view);

        if (!Utils.hasContactsPermission(this)) {
            Utils.requestContactsPermission(this);
        } else {
            // TODO RETRIEVE CONTACTS
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case Utils.CONTACTS_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // TODO RETRIEVE CONTACTS
                } else {
                    Toast.makeText(this, "No permission for contacts.", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }
}
