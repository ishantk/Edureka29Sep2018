package co.edureka.edureka29sep2018;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Explicit Way - Menu Creation
       /* menu.add(1,101,0,"All Songs");
        menu.add(1,201,0,"Favourites");
        menu.add(2,301,0,"Artists");
        menu.add(1,401,0,"Recently Played");
        menu.add(2,501,0,"Albums");*/

        // Implicit Way - Menu Creation
        // Read XML File and put data into menu
        getMenuInflater().inflate(R.menu.mymenu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        int gid = item.getGroupId();

        switch (id){
            case 101:

                break;

            case 201:

                break;

            case 301:

                break;

            case 401:

                break;

            case 501:

                break;

            case R.id.home:
                Intent intent = new Intent(MenuActivity.this,AllUsersActivity.class);
                startActivity(intent);
                break;

            case R.id.browse:

                break;

            //..
        }

        return super.onOptionsItemSelected(item);
    }
}
