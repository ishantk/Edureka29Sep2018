package co.edureka.edureka29sep2018;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.ViewHolder>{

    Context context;            // ref to the activity
    int resource;               // list_item
    ArrayList<User> objects;    // ArrayList of Users

    public UserRecyclerAdapter(Context context, int resource, ArrayList<User> objects) {
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }


    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        // view as a ref shall be pointing to an object of list_item which contains various views.
        // for us it contains, 1 ImageView and 2 TextViews
        View view = LayoutInflater.from(context).inflate(resource,parent,false);

        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {

        // Fetch the Object from ArrayList
        User user = objects.get(position);

        // Get the data and set on the Views
        holder.imageView.setBackgroundResource(user.image);
        holder.txtName.setText(user.name);
        holder.txtEmail.setText(user.email);

    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    // ViewHolder is a Nested Class is holding Views in the list_item
    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView txtName;
        TextView txtEmail;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            txtName = itemView.findViewById(R.id.textViewName);
            txtEmail = itemView.findViewById(R.id.textViewEmail);
        }
    }

}
