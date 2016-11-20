package pl.edu.agh.sbrandys.recyclerviewsample;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


public class Utils {

    private static final String TAG = "Utils";

    public static final int CONTACTS_REQUEST_CODE = 123;

    public static List<Contact> retrieveContactsList(Context context) {
        ArrayList<Contact> result = new ArrayList<>();

        ContentResolver cr = context.getContentResolver();
        Cursor contactCursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        if (contactCursor != null) {
            while (contactCursor.moveToNext()) {
                Contact retrievedContact = new Contact();

                String id = contactCursor.getString(contactCursor.getColumnIndex(ContactsContract.Contacts._ID));
                String contactName = contactCursor.getString(contactCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                retrievedContact.setName(contactName);

                List<String> phoneNumbers = new ArrayList<>();
                if (Integer.parseInt(contactCursor.getString(contactCursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    Cursor phonesCursor = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null);
                    while (phonesCursor.moveToNext()) {
                        String contactNumber = phonesCursor.getString(phonesCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        phoneNumbers.add(contactNumber);
                    }
                    phonesCursor.close();
                }
                retrievedContact.setPhoneNumbers(phoneNumbers);

                List<String> emailAddresses = new ArrayList<>();
                Cursor emailsCursor = cr.query(
                        ContactsContract.CommonDataKinds.Email.CONTENT_URI, null,
                        ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
                        new String[]{id}, null);
                while (emailsCursor.moveToNext()) {
                    String email = emailsCursor.getString(emailsCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                    if (email != null) {
                        emailAddresses.add(email);
                    }
                }
                emailsCursor.close();
                retrievedContact.setEmailAddresses(emailAddresses);

                Cursor addressCursor = cr.query(
                        ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_URI, null,
                        ContactsContract.CommonDataKinds.StructuredPostal.CONTACT_ID + " = ? AND " + ContactsContract.CommonDataKinds.StructuredPostal.MIMETYPE + " = ?",
                        new String[]{id, ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE},
                        null);

                if (addressCursor.moveToFirst()) {
                    String address = addressCursor.getString(addressCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.FORMATTED_ADDRESS));
                    retrievedContact.setAddress(address);
                }
                addressCursor.close();

                result.add(retrievedContact);
                Log.d(TAG, "retrieveContactsList: " + retrievedContact);
            }

            contactCursor.close();
        }

        return result;
    }

    public static boolean hasContactsPermission(Context context) {
        return !(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED);
    }

    public static void requestContactsPermission(Activity activity) {
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_CONTACTS}, CONTACTS_REQUEST_CODE);
    }
}
