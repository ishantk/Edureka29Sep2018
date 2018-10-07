package co.edureka.edureka29sep2018;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends ArrayAdapter<User> {

    Context context;            // ref to the activity
    int resource;               // list_item
    ArrayList<User> objects;    // ArrayList of Users

    public UserAdapter(Context context, int resource, ArrayList<User> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    // getView method executes position number of times from 0 to position-1
    // where position will be based on the size of ArrayList
    // if size of ArrayList is 5, position will range from 0 to 4 and getView will run 5 times automatically
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // view as a ref shall be pointing to an object of list_item which contains various views.
        // for us it contains, 1 ImageView and 2 TextViews
        View view = LayoutInflater.from(context).inflate(resource,parent,false);

        ImageView imageView = view.findViewById(R.id.imageView);
        TextView txtName = view.findViewById(R.id.textViewName);
        TextView txtEmail = view.findViewById(R.id.textViewEmail);

        // Fetch the Object from ArrayList
        User user = objects.get(position);

        // Get the data and set on the Views
        imageView.setBackgroundResource(user.image);
        txtName.setText(user.name);
        txtEmail.setText(user.email);

        return view; // 5 views for us
    }
}
