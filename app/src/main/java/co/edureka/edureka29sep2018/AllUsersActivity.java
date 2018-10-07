package co.edureka.edureka29sep2018;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AllUsersActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    //ListView listView;
    //GridView listView;
    ArrayList<User> users;

    //UserAdapter userAdapter;

    RecyclerView recyclerView;
    UserRecyclerAdapter recyclerAdapter;

    void initViews(){

        //listView = findViewById(R.id.listView);
        recyclerView = findViewById(R.id.recyclerView);

        User user1 = new User(R.drawable.todo,"Mike","mike@example.com");
        User user2 = new User(R.drawable.category,"Leo","leo@example.com");
        User user3 = new User(R.drawable.music,"Sia","sia@example.com");
        User user4 = new User(R.drawable.pb,"Fionna","fionna@example.com");
        User user5 = new User(R.drawable.folder,"Jennie","jennie@example.com");

        users = new ArrayList<>();
        users.add(user1); //0
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5); //4
        users.add(user3);
        users.add(user2);
        users.add(user4);

        //userAdapter = new UserAdapter(this,R.layout.list_item,users);
        //listView.setAdapter(userAdapter);
        //listView.setOnItemClickListener(this);

        recyclerAdapter = new UserRecyclerAdapter(this,R.layout.list_item,users);

        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this); // ListView
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);

        // StaggeredGridLayoutManager -> Explore !!

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(recyclerAdapter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);
        initViews();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        User user = users.get(position);
        Toast.makeText(this,"You Selected: "+user.name,Toast.LENGTH_LONG).show();
    }
}
