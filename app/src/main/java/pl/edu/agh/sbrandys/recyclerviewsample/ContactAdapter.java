package pl.edu.agh.sbrandys.recyclerviewsample;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Sebo on 2016-11-21.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private static final String TAG = "ContactAdapter";

    private List<Contact> dataset;
    private ContactClickListener clickListener;

    public ContactAdapter(List<Contact> dataset, ContactClickListener clickListener) {
        this.dataset = dataset;
        this.clickListener = clickListener;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new ContactViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        final Contact contact = dataset.get(position);

        holder.contactName.setText(contact.getName());

        if (contact.getPhoneNumbers().size() > 0) {
            holder.contactPhone.setText(contact.getPhoneNumbers().get(0));
        } else {
            holder.phoneLayout.setVisibility(View.GONE);
        }

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onContactItemClicked(contact);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    static class ContactViewHolder extends RecyclerView.ViewHolder {

        TextView contactName;
        TextView contactPhone;
        ViewGroup phoneLayout;
        ImageView emailButton;
        View view;

        public ContactViewHolder(View itemView) {
            super(itemView);

            view = itemView;
            contactName = (TextView) itemView.findViewById(R.id.contact_name);
            contactPhone = (TextView) itemView.findViewById(R.id.contact_phone);
            phoneLayout = (ViewGroup) itemView.findViewById(R.id.phone_layout);
        }
    }

    public interface ContactClickListener {
        void onContactItemClicked(Contact contact);
    }
}
