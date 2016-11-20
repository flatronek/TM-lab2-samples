package pl.edu.agh.sbrandys.recyclerviewsample;

import java.util.List;

public class Contact {

    private String name;
    private List<String> phoneNumbers;
    private List<String> emailAddresses;

    public Contact() {
    }

    public Contact(String name, List<String> phoneNumbers, List<String> emailAddresses) {
        this.name = name;
        this.phoneNumbers = phoneNumbers;
        this.emailAddresses = emailAddresses;
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
                ", phoneNumbers=" + phonesBuilder +
                ", emailAddresses=" + emailsBuilder +
                '}';
    }
}
