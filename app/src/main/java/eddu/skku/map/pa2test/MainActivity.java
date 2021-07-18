package eddu.skku.map.pa2test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Button button;
    Button button2;
    EditText editText;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    TextView textView;
    TextView textView2;
    TextView textView10, textView11, textView12, textView13, textView14, textView15, textView16, textView17, textView18, textView19;
    TextView textView20, textView21, textView22, textView23, textView24, textView25, textView26, textView27, textView28, textView29;
    TextView textView30, textView31, textView32, textView33, textView34, textView35, textView36, textView37, textView38, textView39;
    TextView textView40, textView41, textView42, textView43, textView44, textView45, textView46, textView47, textView48, textView49;
    String a;
    String result;
    Bitmap bitmap;
    Bitmap bitmap2;
    int rows, cols;
    int chunkHeight, chunkWidth;
    GridView gridView;

    TextView[] textViewArray = new TextView[20];
    TextView[] textViewArray2 = new TextView[20];
    int[][] color = new int[20][20];
    int[][] play = new int[20][20];
    int[][] array1 = new int[10][20];
    int[][] array2 = new int[20][10];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);
        button = (Button) findViewById(R.id.GALLERY);
        button2 = (Button) findViewById(R.id.SEARCH);
        editText = (EditText) findViewById(R.id.query);
        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        gridView = (GridView) findViewById(R.id.gridView);
        gridView.setBackgroundColor(Color.GRAY);

        for(int i=0;i<20;i++)
        {
            for(int j=0;j<20;j++)
            {
                color[i][j] = 0;
            }
        }

        for(int i=0;i<20;i++)
        {
            for(int j=0;j<20;j++)
            {
                play[i][j] = 0;
            }
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(gallery, PICK_IMAGE);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a = editText.getText().toString();
                Intent intent = new Intent(MainActivity.this, Subactivity.class);
                intent.putExtra("keyword",a);
                startActivityForResult(intent, 1234);
            }
        });
    }

    @Override
    protected void onActivityResult(int reqCode, int resCode, Intent data){
        super.onActivityResult(reqCode, resCode, data);
        if (reqCode == PICK_IMAGE && resCode == RESULT_OK){
            imageUri = data.getData();
            try {
                for(int i=0;i<20;i++)
                {
                    for(int j=0;j<20;j++)
                    {
                        color[i][j] = 0;
                    }
                }

                for(int i=0;i<20;i++)
                {
                    for(int j=0;j<20;j++)
                    {
                        play[i][j] = 0;
                    }
                }

                textView10 = (TextView) findViewById(R.id.textView10);
                textView11 = (TextView) findViewById(R.id.textView11);
                textView12 = (TextView) findViewById(R.id.textView12);
                textView13 = (TextView) findViewById(R.id.textView13);
                textView14 = (TextView) findViewById(R.id.textView14);
                textView15 = (TextView) findViewById(R.id.textView15);
                textView16 = (TextView) findViewById(R.id.textView16);
                textView17 = (TextView) findViewById(R.id.textView17);
                textView18 = (TextView) findViewById(R.id.textView18);
                textView19 = (TextView) findViewById(R.id.textView19);

                textView20 = (TextView) findViewById(R.id.textView20);
                textView21 = (TextView) findViewById(R.id.textView21);
                textView22 = (TextView) findViewById(R.id.textView22);
                textView23 = (TextView) findViewById(R.id.textView23);
                textView24 = (TextView) findViewById(R.id.textView24);
                textView25 = (TextView) findViewById(R.id.textView25);
                textView26 = (TextView) findViewById(R.id.textView26);
                textView27 = (TextView) findViewById(R.id.textView27);
                textView28 = (TextView) findViewById(R.id.textView28);
                textView29 = (TextView) findViewById(R.id.textView29);

                textView30 = (TextView) findViewById(R.id.textView30);
                textView31 = (TextView) findViewById(R.id.textView31);
                textView32 = (TextView) findViewById(R.id.textView32);
                textView33 = (TextView) findViewById(R.id.textView33);
                textView34 = (TextView) findViewById(R.id.textView34);
                textView35 = (TextView) findViewById(R.id.textView35);
                textView36 = (TextView) findViewById(R.id.textView36);
                textView37 = (TextView) findViewById(R.id.textView37);
                textView38 = (TextView) findViewById(R.id.textView38);
                textView39 = (TextView) findViewById(R.id.textView39);

                textView40 = (TextView) findViewById(R.id.textView40);
                textView41 = (TextView) findViewById(R.id.textView41);
                textView42 = (TextView) findViewById(R.id.textView42);
                textView43 = (TextView) findViewById(R.id.textView43);
                textView44 = (TextView) findViewById(R.id.textView44);
                textView45 = (TextView) findViewById(R.id.textView45);
                textView46 = (TextView) findViewById(R.id.textView46);
                textView47 = (TextView) findViewById(R.id.textView47);
                textView48 = (TextView) findViewById(R.id.textView48);
                textView49 = (TextView) findViewById(R.id.textView49);

                textViewArray[0] = textView10;
                textViewArray[1] = textView11;
                textViewArray[2] = textView12;
                textViewArray[3] = textView13;
                textViewArray[4] = textView14;
                textViewArray[5] = textView15;
                textViewArray[6] = textView16;
                textViewArray[7] = textView17;
                textViewArray[8] = textView18;
                textViewArray[9] = textView19;
                textViewArray[10] = textView20;
                textViewArray[11] = textView21;
                textViewArray[12] = textView22;
                textViewArray[13] = textView23;
                textViewArray[14] = textView24;
                textViewArray[15] = textView25;
                textViewArray[16] = textView26;
                textViewArray[17] = textView27;
                textViewArray[18] = textView28;
                textViewArray[19] = textView29;

                textViewArray2[0] = textView30;
                textViewArray2[1] = textView31;
                textViewArray2[2] = textView32;
                textViewArray2[3] = textView33;
                textViewArray2[4] = textView34;
                textViewArray2[5] = textView35;
                textViewArray2[6] = textView36;
                textViewArray2[7] = textView37;
                textViewArray2[8] = textView38;
                textViewArray2[9] = textView39;
                textViewArray2[10] = textView40;
                textViewArray2[11] = textView41;
                textViewArray2[12] = textView42;
                textViewArray2[13] = textView43;
                textViewArray2[14] = textView44;
                textViewArray2[15] = textView45;
                textViewArray2[16] = textView46;
                textViewArray2[17] = textView47;
                textViewArray2[18] = textView48;
                textViewArray2[19] = textView49;

                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                int w2 = bitmap.getWidth();
                int h2 = bitmap.getHeight();
                int size2 = w2;

                if(w2>=h2)
                {
                    size2 = h2;
                }
                else
                {
                    size2 = w2;
                }
                Bitmap scaledBitmap2 = Bitmap.createScaledBitmap(bitmap, size2, size2, true);
                imageView.setImageBitmap(scaledBitmap2);

                bitmap2 = grayScale(bitmap);
                int w = bitmap2.getWidth();
                int h = bitmap2.getHeight();
                int size = w;

                if(w>=h)
                {
                    size = h;
                }
                else
                {
                    size = w;
                }

                Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap2, size, size, true);

                rows = cols = 20;
                chunkHeight = size/rows;
                chunkWidth = size/cols;
                ArrayList<Bitmap> chunkedImages = new ArrayList<Bitmap>(400);

                int yCoord = 0;
                for (int x = 0; x < rows; x++) {
                    int xCoord = 0;
                    for (int y = 0; y < cols; y++) {
                        chunkedImages.add(Bitmap.createBitmap(scaledBitmap, xCoord, yCoord, chunkWidth, chunkHeight));
                        xCoord += chunkWidth;
                    }
                    yCoord += chunkHeight;
                }

                for(int i=0; i<400; i++)
                {
                    Bitmap orgBitmap = chunkedImages.get(i);
                    int width, height;
                    width = orgBitmap.getWidth();
                    height = orgBitmap.getHeight();

                    int A, R, G, B;
                    int pixel;
                    int gray_value = 0;
                    int total = width*height;

                    for (int x = 0; x < width; ++x) {
                        for (int y = 0; y < height; ++y) {
                            pixel = orgBitmap.getPixel(x, y);
                            A = Color.alpha(pixel);
                            R = Color.red(pixel);
                            G = Color.green(pixel);
                            B = Color.blue(pixel);
                            int gray = (int) (0.2989 * R + 0.5870 * G + 0.1140 * B);
                            gray_value += gray;
                        }
                    }

                    for (int x = 0; x < width; ++x) {
                        for (int y = 0; y < height; ++y) {
                            pixel = orgBitmap.getPixel(x, y);
                            A = Color.alpha(pixel);
                            int gray = 255;
                            orgBitmap.setPixel(x, y, Color.argb(A, gray, gray, gray));
                        }
                    }

                    gray_value = gray_value / total;
                    if(gray_value > 128)
                    {
                    }
                    else
                    {
                        int row = 0, column = 0;
                        if(i == 0)
                        {
                            row = column = 0;
                        }
                        else
                        {
                            row = i/20;
                            column = i%20;
                        }
                        color[row][column] = 1;
                    }
                }

                String input;
                for(int i=0; i<20; i++)
                {
                    textViewArray[i].setText("");
                    input = "";
                    int cnt = 0;
                    int check = 0;
                    for(int j=0; j<20; j++)
                    {
                        if(color[j][i] == 1)
                        {
                            cnt ++;
                        }
                        if(color[j][i] == 0)
                        {
                            if(cnt != 0)
                            {
                                String add = Integer.toString(cnt);
                                input += "\n";
                                input += add;
                                cnt = 0;
                                check ++;
                            }
                        }
                    }
                    if(cnt != 0)
                    {
                        String add = Integer.toString(cnt);
                        input += "\n";
                        input += add;
                    }
                    textViewArray[i].setText(input);
                }

                String input2;
                for(int i=0; i<20; i++)
                {
                    textViewArray2[i].setText("");
                    input2 = "";
                    int cnt = 0;
                    int check = 0;
                    for(int j=0; j<20; j++)
                    {
                        if(color[i][j] == 1)
                        {
                            cnt ++;
                        }
                        if(color[i][j] == 0)
                        {
                            if(cnt != 0)
                            {
                                String add = Integer.toString(cnt);
                                input2 += add;
                                input2 += " ";
                                cnt = 0;
                                check ++;
                            }
                        }
                    }
                    if(cnt != 0)
                    {
                        String add = Integer.toString(cnt);
                        input2 += add;
                        input2 += " ";
                    }
                    textViewArray2[i].setText(input2);
                }

                GridAdapter adapter = new GridAdapter();

                for(int i=0; i<400; i++)
                {
                    adapter.addItem(chunkedImages.get(i));
                }

                gridView.setAdapter(adapter);

                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                        int row = 0, column = 0;
                        if(position == 0)
                        {
                            row = column = 0;
                        }
                        else
                        {
                            row = position/20;
                            column = position%20;
                        }

                        if(color[row][column] == 1)
                        {
                            Bitmap orgBitmap = chunkedImages.get(position);
                            int width, height;
                            width = orgBitmap.getWidth();
                            height = orgBitmap.getHeight();

                            int A;
                            int pixel;

                            for (int x = 0; x < width; ++x) {
                                for (int y = 0; y < height; ++y) {
                                    pixel = orgBitmap.getPixel(x, y);
                                    A = Color.alpha(pixel);
                                    int gray = 0;
                                    orgBitmap.setPixel(x, y, Color.argb(A, gray, gray, gray));
                                }
                            }
                            play[row][column] = 1;
                            int finish = 0;
                            for(int x=0; x<20; x++)
                            {
                                for(int y=0; y<20; y++)
                                {
                                    if(color[x][y] == play[x][y])
                                    {
                                        finish++;
                                    }
                                }
                            }
                            if(finish == 400)
                            {
                                Toast.makeText(getApplicationContext(), "FINISH!", Toast.LENGTH_LONG).show();
                            }
                        }
                        else
                        {
                            int finish = 0;
                            for(int x=0; x<20; x++)
                            {
                                for(int y=0; y<20; y++)
                                {
                                    if(color[x][y] == play[x][y])
                                    {
                                        finish++;
                                    }
                                }
                            }
                            if(finish == 400)
                            {
                                Toast.makeText(getApplicationContext(), "FINISH!", Toast.LENGTH_LONG).show();
                            }

                            else
                            {
                                for(int k=0; k<400; k++)
                                {
                                    Bitmap orgBitmap = chunkedImages.get(k);
                                    int width, height;
                                    width = orgBitmap.getWidth();
                                    height = orgBitmap.getHeight();

                                    int A;
                                    int pixel;

                                    for (int x = 0; x < width; ++x) {
                                        for (int y = 0; y < height; ++y) {
                                            pixel = orgBitmap.getPixel(x, y);
                                            A = Color.alpha(pixel);
                                            int gray = 255;
                                            orgBitmap.setPixel(x, y, Color.argb(A, gray, gray, gray));
                                        }
                                    }
                                }

                                for(int i=0;i<20;i++)
                                {
                                    for(int j=0;j<20;j++)
                                    {
                                        play[i][j] = 0;
                                    }
                                }

                                Toast.makeText(getApplicationContext(), "WRONG!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        else if(reqCode == 1234 && resCode == RESULT_OK)
        {
            result = data.getStringExtra("result");

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        bitmap = getBitmapFromURL(result);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                for(int i=0;i<20;i++)
                                {
                                    for(int j=0;j<20;j++)
                                    {
                                        color[i][j] = 0;
                                    }
                                }

                                for(int i=0;i<20;i++)
                                {
                                    for(int j=0;j<20;j++)
                                    {
                                        play[i][j] = 0;
                                    }
                                }

                                textView10 = (TextView) findViewById(R.id.textView10);
                                textView11 = (TextView) findViewById(R.id.textView11);
                                textView12 = (TextView) findViewById(R.id.textView12);
                                textView13 = (TextView) findViewById(R.id.textView13);
                                textView14 = (TextView) findViewById(R.id.textView14);
                                textView15 = (TextView) findViewById(R.id.textView15);
                                textView16 = (TextView) findViewById(R.id.textView16);
                                textView17 = (TextView) findViewById(R.id.textView17);
                                textView18 = (TextView) findViewById(R.id.textView18);
                                textView19 = (TextView) findViewById(R.id.textView19);

                                textView20 = (TextView) findViewById(R.id.textView20);
                                textView21 = (TextView) findViewById(R.id.textView21);
                                textView22 = (TextView) findViewById(R.id.textView22);
                                textView23 = (TextView) findViewById(R.id.textView23);
                                textView24 = (TextView) findViewById(R.id.textView24);
                                textView25 = (TextView) findViewById(R.id.textView25);
                                textView26 = (TextView) findViewById(R.id.textView26);
                                textView27 = (TextView) findViewById(R.id.textView27);
                                textView28 = (TextView) findViewById(R.id.textView28);
                                textView29 = (TextView) findViewById(R.id.textView29);

                                textView30 = (TextView) findViewById(R.id.textView30);
                                textView31 = (TextView) findViewById(R.id.textView31);
                                textView32 = (TextView) findViewById(R.id.textView32);
                                textView33 = (TextView) findViewById(R.id.textView33);
                                textView34 = (TextView) findViewById(R.id.textView34);
                                textView35 = (TextView) findViewById(R.id.textView35);
                                textView36 = (TextView) findViewById(R.id.textView36);
                                textView37 = (TextView) findViewById(R.id.textView37);
                                textView38 = (TextView) findViewById(R.id.textView38);
                                textView39 = (TextView) findViewById(R.id.textView39);

                                textView40 = (TextView) findViewById(R.id.textView40);
                                textView41 = (TextView) findViewById(R.id.textView41);
                                textView42 = (TextView) findViewById(R.id.textView42);
                                textView43 = (TextView) findViewById(R.id.textView43);
                                textView44 = (TextView) findViewById(R.id.textView44);
                                textView45 = (TextView) findViewById(R.id.textView45);
                                textView46 = (TextView) findViewById(R.id.textView46);
                                textView47 = (TextView) findViewById(R.id.textView47);
                                textView48 = (TextView) findViewById(R.id.textView48);
                                textView49 = (TextView) findViewById(R.id.textView49);

                                textViewArray[0] = textView10;
                                textViewArray[1] = textView11;
                                textViewArray[2] = textView12;
                                textViewArray[3] = textView13;
                                textViewArray[4] = textView14;
                                textViewArray[5] = textView15;
                                textViewArray[6] = textView16;
                                textViewArray[7] = textView17;
                                textViewArray[8] = textView18;
                                textViewArray[9] = textView19;
                                textViewArray[10] = textView20;
                                textViewArray[11] = textView21;
                                textViewArray[12] = textView22;
                                textViewArray[13] = textView23;
                                textViewArray[14] = textView24;
                                textViewArray[15] = textView25;
                                textViewArray[16] = textView26;
                                textViewArray[17] = textView27;
                                textViewArray[18] = textView28;
                                textViewArray[19] = textView29;

                                textViewArray2[0] = textView30;
                                textViewArray2[1] = textView31;
                                textViewArray2[2] = textView32;
                                textViewArray2[3] = textView33;
                                textViewArray2[4] = textView34;
                                textViewArray2[5] = textView35;
                                textViewArray2[6] = textView36;
                                textViewArray2[7] = textView37;
                                textViewArray2[8] = textView38;
                                textViewArray2[9] = textView39;
                                textViewArray2[10] = textView40;
                                textViewArray2[11] = textView41;
                                textViewArray2[12] = textView42;
                                textViewArray2[13] = textView43;
                                textViewArray2[14] = textView44;
                                textViewArray2[15] = textView45;
                                textViewArray2[16] = textView46;
                                textViewArray2[17] = textView47;
                                textViewArray2[18] = textView48;
                                textViewArray2[19] = textView49;

                                int w2 = bitmap.getWidth();
                                int h2 = bitmap.getHeight();
                                int size2 = w2;

                                if(w2>=h2)
                                {
                                    size2 = h2;
                                }
                                else
                                {
                                    size2 = w2;
                                }
                                Bitmap scaledBitmap2 = Bitmap.createScaledBitmap(bitmap, size2, size2, true);
                                imageView.setImageBitmap(scaledBitmap2);

                                bitmap2 = grayScale(bitmap);
                                int w = bitmap2.getWidth();
                                int h = bitmap2.getHeight();
                                int size = w;

                                if(w>=h)
                                {
                                    size = h;
                                }
                                else
                                {
                                    size = w;
                                }

                                Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap2, size, size, true);

                                rows = cols = 20;
                                chunkHeight = size/rows;
                                chunkWidth = size/cols;
                                ArrayList<Bitmap> chunkedImages = new ArrayList<Bitmap>(400);

                                int yCoord = 0;
                                for (int x = 0; x < rows; x++) {
                                    int xCoord = 0;
                                    for (int y = 0; y < cols; y++) {
                                        chunkedImages.add(Bitmap.createBitmap(scaledBitmap, xCoord, yCoord, chunkWidth, chunkHeight));
                                        xCoord += chunkWidth;
                                    }
                                    yCoord += chunkHeight;
                                }

                                for(int i=0; i<400; i++)
                                {
                                    Bitmap orgBitmap = chunkedImages.get(i);
                                    int width, height;
                                    width = orgBitmap.getWidth();
                                    height = orgBitmap.getHeight();

                                    int A, R, G, B;
                                    int pixel;
                                    int gray_value = 0;
                                    int total = width*height;

                                    for (int x = 0; x < width; ++x) {
                                        for (int y = 0; y < height; ++y) {
                                            pixel = orgBitmap.getPixel(x, y);
                                            A = Color.alpha(pixel);
                                            R = Color.red(pixel);
                                            G = Color.green(pixel);
                                            B = Color.blue(pixel);
                                            int gray = (int) (0.2989 * R + 0.5870 * G + 0.1140 * B);
                                            gray_value += gray;
                                        }
                                    }

                                    for (int x = 0; x < width; ++x) {
                                        for (int y = 0; y < height; ++y) {
                                            pixel = orgBitmap.getPixel(x, y);
                                            A = Color.alpha(pixel);
                                            int gray = 255;
                                            orgBitmap.setPixel(x, y, Color.argb(A, gray, gray, gray));
                                        }
                                    }

                                    gray_value = gray_value / total;
                                    if(gray_value > 128)
                                    {
                                    }
                                    else
                                    {
                                        int row = 0, column = 0;
                                        if(i == 0)
                                        {
                                            row = column = 0;
                                        }
                                        else
                                        {
                                            row = i/20;
                                            column = i%20;
                                        }
                                        color[row][column] = 1;
                                    }
                                }

                                String input;
                                for(int i=0; i<20; i++)
                                {
                                    textViewArray[i].setText("");
                                    input = "";
                                    int cnt = 0;
                                    int check = 0;
                                    for(int j=0; j<20; j++)
                                    {
                                        if(color[j][i] == 1)
                                        {
                                            cnt ++;
                                        }
                                        if(color[j][i] == 0)
                                        {
                                            if(cnt != 0)
                                            {
                                                String add = Integer.toString(cnt);
                                                input += "\n";
                                                input += add;
                                                cnt = 0;
                                                check ++;
                                            }
                                        }
                                    }
                                    if(cnt != 0)
                                    {
                                        String add = Integer.toString(cnt);
                                        input += "\n";
                                        input += add;
                                    }
                                    textViewArray[i].setText(input);
                                }

                                String input2;
                                for(int i=0; i<20; i++)
                                {
                                    textViewArray2[i].setText("");
                                    input2 = "";
                                    int cnt = 0;
                                    int check = 0;
                                    for(int j=0; j<20; j++)
                                    {
                                        if(color[i][j] == 1)
                                        {
                                            cnt ++;
                                        }
                                        if(color[i][j] == 0)
                                        {
                                            if(cnt != 0)
                                            {
                                                String add = Integer.toString(cnt);
                                                input2 += add;
                                                input2 += " ";
                                                cnt = 0;
                                                check ++;
                                            }
                                        }
                                    }
                                    if(cnt != 0)
                                    {
                                        String add = Integer.toString(cnt);
                                        input2 += add;
                                        input2 += " ";
                                    }
                                    textViewArray2[i].setText(input2);
                                }

                                GridAdapter adapter = new GridAdapter();

                                for(int i=0; i<400; i++)
                                {
                                    adapter.addItem(chunkedImages.get(i));
                                }

                                gridView.setAdapter(adapter);

                                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                                        int row = 0, column = 0;
                                        if(position == 0)
                                        {
                                            row = column = 0;
                                        }
                                        else
                                        {
                                            row = position/20;
                                            column = position%20;
                                        }

                                        if(color[row][column] == 1)
                                        {
                                            Bitmap orgBitmap = chunkedImages.get(position);
                                            int width, height;
                                            width = orgBitmap.getWidth();
                                            height = orgBitmap.getHeight();

                                            int A;
                                            int pixel;

                                            for (int x = 0; x < width; ++x) {
                                                for (int y = 0; y < height; ++y) {
                                                    pixel = orgBitmap.getPixel(x, y);
                                                    A = Color.alpha(pixel);
                                                    int gray = 0;
                                                    orgBitmap.setPixel(x, y, Color.argb(A, gray, gray, gray));
                                                }
                                            }
                                            play[row][column] = 1;
                                            int finish = 0;
                                            for(int x=0; x<20; x++)
                                            {
                                                for(int y=0; y<20; y++)
                                                {
                                                    if(color[x][y] == play[x][y])
                                                    {
                                                        finish++;
                                                    }
                                                }
                                            }
                                            if(finish == 400)
                                            {
                                                Toast.makeText(getApplicationContext(), "FINISH!", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                        else
                                        {
                                            int finish = 0;
                                            for(int x=0; x<20; x++)
                                            {
                                                for(int y=0; y<20; y++)
                                                {
                                                    if(color[x][y] == play[x][y])
                                                    {
                                                        finish++;
                                                    }
                                                }
                                            }
                                            if(finish == 400)
                                            {
                                                Toast.makeText(getApplicationContext(), "FINISH!", Toast.LENGTH_LONG).show();
                                            }

                                            else
                                            {
                                                for(int k=0; k<400; k++)
                                                {
                                                    Bitmap orgBitmap = chunkedImages.get(k);
                                                    int width, height;
                                                    width = orgBitmap.getWidth();
                                                    height = orgBitmap.getHeight();

                                                    int A;
                                                    int pixel;

                                                    for (int x = 0; x < width; ++x) {
                                                        for (int y = 0; y < height; ++y) {
                                                            pixel = orgBitmap.getPixel(x, y);
                                                            A = Color.alpha(pixel);
                                                            int gray = 255;
                                                            orgBitmap.setPixel(x, y, Color.argb(A, gray, gray, gray));
                                                        }
                                                    }
                                                }

                                                for(int i=0;i<20;i++)
                                                {
                                                    for(int j=0;j<20;j++)
                                                    {
                                                        play[i][j] = 0;
                                                    }
                                                }

                                                Toast.makeText(getApplicationContext(), "WRONG!", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                                });
                            }
                        });

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });thread.start();
        }
    }

    public Bitmap getBitmapFromURL(String src) {
        URL url = null;
        HttpURLConnection connection = null;
        InputStream input = null;
        Bitmap myBitmap = null;
        try {
            url = new URL(src);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            input = connection.getInputStream();
            myBitmap = BitmapFactory.decodeStream(input);
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            if(connection!=null) {
                connection.disconnect();
            }
        }
        return myBitmap;
    }

    private Bitmap grayScale(Bitmap orgBitmap){
        Log.i("gray", "in");
        int width, height;
        width = orgBitmap.getWidth();
        height = orgBitmap.getHeight();

        Bitmap bmpGrayScale = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_4444);

        int A, R, G, B;
        int pixel;

        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                pixel = orgBitmap.getPixel(x, y);
                A = Color.alpha(pixel);
                R = Color.red(pixel);
                G = Color.green(pixel);
                B = Color.blue(pixel);
                int gray = (int) (0.2989 * R + 0.5870 * G + 0.1140 * B);

                if (gray > 128)
                    gray = 255;
                else
                    gray = 0;
                bmpGrayScale.setPixel(x, y, Color.argb(A, gray, gray, gray));
            }
        }
        return bmpGrayScale;
    }

    public class GridAdapter extends BaseAdapter{
        ArrayList<Bitmap> items = new ArrayList<Bitmap>();
        Context context;

        public void addItem(Bitmap bitmap)
        {
            items.add(bitmap);
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int i) {
            return items.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            context =  parent.getContext();
            Bitmap listItem = items.get(position);

            if(convertView == null)
            {
                LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.list_item, parent, false);
            }

            ImageView view = convertView.findViewById(R.id.imageView3);
            view.setImageBitmap(listItem);
            return convertView;
        }
    }
}



