package padma.soode.slipgajionline;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableLayout;
import com.github.aakira.expandablelayout.ExpandableLayoutListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static boolean isLogin;
    private Spinner spinner;
    private RelativeLayout gaji;
    private RelativeLayout potongan;
    private RelativeLayout lembur;
    private ExpandableLayout expandableGaji;
    private ExpandableLayout expandableLembur;
    private ExpandableLayout expandablePotongan;
    private ImageView potonganImg;
    private ImageView lemburImg;
    private ImageView gajiImg;
    private TextView gajiBulan;
    private Button downloadBtn;
    private Button sendEmailBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (isLogin != true) {
            finish();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }

        expandableGaji = (ExpandableLayout) findViewById(R.id.expandableSlipLayout);
        expandableLembur = (ExpandableLayout) findViewById(R.id.expandableLemburLayout);
        expandablePotongan = (ExpandableLayout) findViewById(R.id.expandablePotonganLayout);
        potonganImg = (ImageView) findViewById(R.id.potongan_img);
        lemburImg = (ImageView) findViewById(R.id.lembur_img);
        gajiImg = (ImageView) findViewById(R.id.gaji_img);
        spinner = (Spinner) findViewById(R.id.spinner);
        lembur = (RelativeLayout) findViewById(R.id.lembur);
        gaji = (RelativeLayout) findViewById(R.id.gaji);
        potongan = (RelativeLayout) findViewById(R.id.potongan);
        gajiBulan = (TextView) findViewById(R.id.gaji_bulan);
        downloadBtn = (Button) findViewById(R.id.downloadBtn);
        sendEmailBtn = (Button) findViewById(R.id.sendEmailBtn);

        getSupportActionBar().setTitle("Padma Soode");

        ArrayList<String> bulan = new ArrayList<>();
        bulan.add("2 Bulan Lalu");
        bulan.add("1 Bulan Lalu");
        bulan.add("Bulan ini");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, bulan);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text = "";
                switch (i) {
                    case 0:
                        text = "Juni, 2017";
                        break;
                    case 1:
                        text = "Juli, 2017";
                        break;
                    case 2:
                        text = "Agustus, 2017";
                        break;
                }
                gajiBulan.setText(text);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner.setSelection(2);

        gaji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandableGaji.toggle();
            }
        });

        potongan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandablePotongan.toggle();
            }
        });

        lembur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandableLembur.toggle();
            }
        });

        expandableGaji.setListener(new ExpandableLayoutListener() {
            @Override
            public void onAnimationStart() {

            }

            @Override
            public void onAnimationEnd() {

            }

            @Override
            public void onPreOpen() {
                gajiImg.setRotation(180f);
            }

            @Override
            public void onPreClose() {
                gajiImg.setRotation(0f);
            }

            @Override
            public void onOpened() {
            }

            @Override
            public void onClosed() {
            }
        });

        expandablePotongan.setListener(new ExpandableLayoutListener() {
            @Override
            public void onAnimationStart() {

            }

            @Override
            public void onAnimationEnd() {

            }

            @Override
            public void onPreOpen() {
                potonganImg.setRotation(180f);
            }

            @Override
            public void onPreClose() {
                potonganImg.setRotation(0f);
            }

            @Override
            public void onOpened() {
            }

            @Override
            public void onClosed() {
            }
        });

        expandableLembur.setListener(new ExpandableLayoutListener() {
            @Override
            public void onAnimationStart() {

            }

            @Override
            public void onAnimationEnd() {

            }

            @Override
            public void onPreOpen() {
                lemburImg.setRotation(180f);
            }

            @Override
            public void onPreClose() {
                lemburImg.setRotation(0f);
            }

            @Override
            public void onOpened() {

            }

            @Override
            public void onClosed() {

            }
        });

        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/open?id=0B6Ntai66P4rmSEVmVENvaGNuckU"));
                startActivity(intent);

//                DownloadManager downloadManager = new DownloadManager();
//                downloadManager.startDownLoad(
//                        getApplicationContext(),
//                        "https://drive.google.com/open?id=0B6Ntai66P4rmSEVmVENvaGNuckU",
//                        "/");
            }
        });

        sendEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String filename="contacts_sid.vcf";
//                File filelocation = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), filename);
//                Uri path = Uri.fromFile(filelocation);
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
// set the type to 'email'
                emailIntent.setType("vnd.android.cursor.dir/email");
                String to[] = {"test@gmail.com"};
                emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
// the attachment
//                emailIntent .putExtra(Intent.EXTRA_STREAM, path);
// the mail subject
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Slip gaji");
                startActivity(Intent.createChooser(emailIntent, "Mengirim email..."));

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            MainActivity.isLogin = false;
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
