package pl.edu.agh.sbrandys.recyclerviewsample;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ContactAdapter.ContactClickListener {

    private static final String TAG = "MainActivity";

    private RecyclerView contactsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactsRecyclerView = (RecyclerView) findViewById(R.id.contacts_recycler_view);

        if (!Utils.hasContactsPermission(this)) {
            Utils.requestContactsPermission(this);
        } else {
            initContactsRecycler();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case Utils.CONTACTS_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initContactsRecycler();
                } else {
                    Toast.makeText(this, "No permission for contacts.", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    private void initContactsRecycler() {
        ContactAdapter adapter = new ContactAdapter(Utils.retrieveContactsList(this), this);
        contactsRecyclerView.setAdapter(adapter);
        contactsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        contactsRecyclerView.setHasFixedSize(true);
    }

    @Override
    public void onContactItemClicked(Contact contact) {
        Log.d(TAG, "onContactItemClicked: " + contact);

        Bundle bundle = new Bundle();
        bundle.putSerializable(ContactDetailsActivity.CONTACT_EXTRA, contact);

        Intent intent = new Intent(this, ContactDetailsActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
