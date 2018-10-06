package co.edureka.edureka29sep2018;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class LowerFragment extends Fragment implements AdapterView.OnItemClickListener{

    ListView listView;
    ArrayAdapter<String> adapter;

    public LowerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lower, container, false);

        listView = view.findViewById(R.id.listView);
        //adapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1);
        adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1);

        adapter.add("CNN");
        adapter.add("BBC");
        adapter.add("NDTV");
        adapter.add("ZEE");
        adapter.add("India Today");

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String news = adapter.getItem(position);
        //Toast.makeText(getContext(),"You Clicked: "+news,Toast.LENGTH_LONG).show();
        Toast.makeText(getActivity(),"You Clicked: "+news,Toast.LENGTH_LONG).show();
    }
}
