package com.edibca.crcasofarma;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuActivity extends AppCompatActivity {

    ListView listview;
    int layoutHeight, contador;
    ContenidoAdapter contenidoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        setTitle("Menú");

        Toolbar myToolbar = (Toolbar) findViewById(R.id.menu_toolbar);
        setSupportActionBar(myToolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_products:
                // User chose the "Settings" item, show the app settings UI...
                Intent intent = new Intent(MenuActivity.this, ProductsActivity.class);
                startActivity(intent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        final ConstraintLayout layout = (ConstraintLayout)findViewById(R.id.menu_list_layout);
        ViewTreeObserver vto = layout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                layout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int width  = layout.getMeasuredWidth();
                layoutHeight = layout.getMeasuredHeight();

            }
        });

        contador = 0;

        Log.d("screenheight", String.valueOf(layoutHeight));

        listview = (ListView) findViewById(R.id.menu_list);

        Map<String, String> framingham = new HashMap<String, String>();
        framingham.put("text", "Framingham (usando lípidos)");
        framingham.put("image", "framingham");

        Map<String, String> coronary = new HashMap<String, String>();
        coronary.put("text", "Riesgo enfermedad coronaria");
        coronary.put("image", "coronary");

        Map<String, String> sm = new HashMap<String, String>();
        sm.put("text", "Síndrome metabólico");
        sm.put("image", "sind_meta");

        Map<String, String> imc = new HashMap<String, String>();
        imc.put("text", "Índice de masa corporal");
        imc.put("image", "masa_corporal");

        Map<String, String> rcp = new HashMap<String, String>();
        rcp.put("text", "Riesgo cardiaco postoperatorio");
        rcp.put("image", "riesgo_cardiaco");

        List<Map<String, String>> menuItems = new ArrayList<Map<String, String>>();
        menuItems.add(framingham);
        menuItems.add(coronary);
        menuItems.add(sm);
        menuItems.add(imc);
        menuItems.add(rcp);

        Log.d("info", menuItems.get(0).get("image"));


        contenidoAdapter = new ContenidoAdapter(getApplicationContext(), R.layout.menu_listcell, menuItems);

        listview.setAdapter(contenidoAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                //final String item = (String) parent.getItemAtPosition(position);
                /*
                view.animate().setDuration(2000).alpha(0).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        labels.remove(item);
                        adapter.notifyDataSetChanged();
                        view.setAlpha(1);
                    }
                });
                */

                ImageView filter = (ImageView) view.findViewById(R.id.list_filter);
                filter.setVisibility(View.VISIBLE);

                SharedPreferences.Editor editor = getSharedPreferences("CRC", MODE_PRIVATE).edit();
                editor.putInt("position", position);
                editor.apply();

                Intent intent = new Intent(MenuActivity.this, FieldsActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        update();
    }

    private class ContenidoAdapter extends ArrayAdapter<Map<String, String>> {
        Context context;
        int resourceid;
        List<Map<String, String>> data;

        public ContenidoAdapter(Context context , int resourceid , List<Map<String, String>> data) {
            super(context , resourceid, data);
            this.context = context;
            this.resourceid = resourceid;
            this.data = data;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.menu_listcell, parent, false);
            }

            Log.d("height" , String.valueOf(layoutHeight));
            AbsListView.LayoutParams params = (AbsListView.LayoutParams) convertView.getLayoutParams();
            params.height = layoutHeight/5;
            convertView.setLayoutParams(params);

            ImageView imageView = (ImageView) convertView.findViewById(R.id.list_image);
            int id = getResources().getIdentifier(data.get(position).get("image"), "drawable", getPackageName());
            imageView.setImageResource(id);

            ImageView filter = (ImageView) convertView.findViewById(R.id.list_filter);
            filter.setVisibility(View.INVISIBLE);

            TextView textView = (TextView) convertView.findViewById(R.id.cell_text);
            //textView.setTextColor(getResources().getColor(R.color.white));

            Log.d("text", data.get(position).get("text"));
            textView.setText(data.get(position).get("text"));

            contador++;

            if (contador==4){
                update();
            }

            return convertView;
        }
    }

    private void update(){
        contenidoAdapter.notifyDataSetChanged();
    }
}
