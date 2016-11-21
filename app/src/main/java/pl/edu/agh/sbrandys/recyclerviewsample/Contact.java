package pl.edu.agh.sbrandys.recyclerviewsample;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class Contact implements Serializable {

    private String name;
    private String address;
    private List<String> phoneNumbers;
    private List<String> emailAddresses;

    public Contact() {
    }

    public Contact(String name, List<String> phoneNumbers, List<String> emailAddresses) {
        this.name = name;
        this.phoneNumbers = phoneNumbers;
        this.emailAddresses = emailAddresses;
    }

    public Contact(String name, String address, List<String> phoneNumbers, List<String> emailAddresses) {
        this.name = name;
        this.address = address;
        this.phoneNumbers = phoneNumbers;
        this.emailAddresses = emailAddresses;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public List<String> getEmailAddresses() {
        return emailAddresses;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public void setEmailAddresses(List<String> emailAddresses) {
        this.emailAddresses = emailAddresses;
    }

    @Override
    public String toString() {
        StringBuilder phonesBuilder = new StringBuilder();
        phonesBuilder.append("[");
        for (int i = 0; i < phoneNumbers.size(); i++) {
            if (i != 0) {
                phonesBuilder.append("; ");
            }
            phonesBuilder.append(phoneNumbers.get(i));
        }
        phonesBuilder.append("]");

        StringBuilder emailsBuilder = new StringBuilder();
        emailsBuilder.append("[");
        for (int i = 0; i < emailAddresses.size(); i++) {
            if (i != 0) {
                emailsBuilder.append("; ");
            }
            emailsBuilder.append(phoneNumbers.get(i));
        }
        emailsBuilder.append("]");

        return "Contact{" +
                "name='" + name + '\'' +
                ", address='" + address +'\'' +
                ", phoneNumbers=" + phonesBuilder +
                ", emailAddresses=" + emailsBuilder +
                '}';
    }
}
