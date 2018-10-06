package co.edureka.edureka29sep2018;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MyFragmentsActivity extends AppCompatActivity {

    // Declare references to Fragments
    UpperFragment upperFragment;
    LowerFragment lowerFragment;

    // FragmentManager : which manages Fragments in Activity i.e. adding, removing or replacing Fragments is the job of FM
    FragmentManager fragmentManager;

    void initViews(){

        // Create Objects of your Fragments.
        upperFragment = new UpperFragment();
        lowerFragment = new LowerFragment();

        fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().add(R.id.upperFrame,upperFragment).commit();
        fragmentManager.beginTransaction().add(R.id.lowerFrame,lowerFragment).commit();

        //fragmentManager.beginTransaction().replace(R.id.lowerFrame,lowerFragment).commit();
        //fragmentManager.beginTransaction().remove(lowerFragment).commit();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fragments);
        initViews();
    }
}
