package com.edibca.crcasofarma;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.edibca.crcasofarma.Clases.Framingham;
import com.edibca.crcasofarma.Clases.IMC;
import com.edibca.crcasofarma.Clases.RiesgoEC;
import com.edibca.crcasofarma.Clases.RiesgoPostOperatorio;
import com.edibca.crcasofarma.Clases.SindromeMetabolico;

public class FieldsActivity extends AppCompatActivity {
    public static final int FRAMINGHAM_POS = 0;
    public static final int REC_POS = 1;
    public static final int METABOLIC_POS = 2;
    public static final int IMC_POS = 3;
    public static final int RCP_POS = 4;

    JSONObject data;
    ListView listView;
    Map<Integer,Double> valuesMap;
    ArrayList<JSONObject> listdata;
    ArrayList<JSONObject> listDataMinus;

    Integer seekBarProgress = 0;

    double[] framingham;
    Double riesgoEC;
    boolean sindromeM;
    Double imc;
    double[] rpo;

    Boolean hideItems = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fields);

        Intent intent = getIntent();
        final Integer position = intent.getIntExtra("position", 0);

        data =  getData(position);

        String title = null;
        try {
            title = data.getString("name");
            setTitle(title);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // my_child_toolbar is defined in the layout file
        Toolbar myChildToolbar =
                (Toolbar) findViewById(R.id.fields_toolbar);
        setSupportActionBar(myChildToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        try {
            initFields(data.getJSONArray("fields"), position);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        TextView calcText = (TextView) findViewById(R.id.fields_calc);
        calcText.setTextSize(20);
        calcText.setTextColor(getResources().getColor(R.color.white));
        calcText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                for (int i = 0; i < valuesMap.keySet().size(); i++){
                    boolean noData = false;
                    if (valuesMap.get(i) == null){
                        noData = true;
                        Toast.makeText(getApplicationContext(), "Por favor rellena todos los campos", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }

                Intent intent = new Intent(FieldsActivity.this, ResultsActivity.class);
                intent.putExtra("position", position);
                try {
                    intent.putExtra("references", data.getString("references"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                switch (position) {
                    case FRAMINGHAM_POS:
                        //FRAMINGHAM
                        Double genero = valuesMap.get(0);
                        Double edad = valuesMap.get(1);
                        Double presionSistolica = valuesMap.get(2);
                        Double tratamiento = valuesMap.get(3);
                        Double fumador = valuesMap.get(4);
                        Double diabetico = valuesMap.get(5);
                        Double colesterolHDL = valuesMap.get(6);
                        Double colesterolTot = valuesMap.get(7);

                        Framingham framinghamObj = new Framingham(genero, edad, presionSistolica, tratamiento, fumador, diabetico, colesterolHDL, colesterolTot);
                        framingham = framinghamObj.calculaFramingham();
                        for (int i = 0; i < framingham.length; i++){
                            Log.d("FRAMINGHAM " + i, String.valueOf(framingham[i]));
                        }
                        intent.putExtra("result",framingham);
                        startActivity(intent);
                        break;
                    case REC_POS:
                        //REC
                        edad = valuesMap.get(0);
                        genero = valuesMap.get(1);
                        colesterolTot = valuesMap.get(2);
                        colesterolHDL = valuesMap.get(3);
                        fumador = valuesMap.get(4);
                        Double presion = valuesMap.get(5);
                        tratamiento = valuesMap.get(6);
                        RiesgoEC riesgoECObj = new RiesgoEC(genero, edad, presion, tratamiento, fumador, colesterolHDL, colesterolTot);
                        riesgoEC = riesgoECObj.calculaRiesgo();
                        Log.d("RIESGO EC: ", riesgoEC.toString());
                        intent.putExtra("result", riesgoEC);
                        startActivity(intent);
                        break;
                    case METABOLIC_POS:
                        //SINDROME METABOLICO
                        genero = valuesMap.get(0);
                        Double circunferencia = valuesMap.get(1);
                        if (circunferencia >= 90){
                            Double trigliceridos = valuesMap.get(2);
                            colesterolHDL = valuesMap.get(3);
                            tratamiento = valuesMap.get(4);
                            presionSistolica = valuesMap.get(5);
                            Double presionDiastolica = valuesMap.get(6);
                            Double glucosa = valuesMap.get(7);
                            SindromeMetabolico sindromeMetabolicoObj = new SindromeMetabolico(genero, circunferencia, trigliceridos, colesterolHDL, presionSistolica, presionDiastolica, tratamiento, glucosa);
                            sindromeM = sindromeMetabolicoObj.calcular();
                        } else {
                            sindromeM = false;
                        }

                        Log.d("SINDROME: ", String.valueOf(sindromeM));
                        intent.putExtra("result", sindromeM);
                        startActivity(intent);
                        break;
                    case IMC_POS:
                        // IMC
                        IMC imcObj = new IMC(valuesMap.get(0), valuesMap.get(1));
                        imc = imcObj.calcularIMC()*10000;
                        Log.d("IMC: ", imc.toString());
                        intent.putExtra("result", imc);
                        startActivity(intent);
                        break;
                    case RCP_POS:
                        //Postoperatory
                        Double ruido = valuesMap.get(0);
                        Double infarto = valuesMap.get(1);
                        Double ritmoNo = valuesMap.get(2);
                        Double contracciones = valuesMap.get(3);
                        Double edadMayor = valuesMap.get(4);
                        Double operaciones = valuesMap.get(5);
                        Double cirugia = valuesMap.get(6);
                        Double estenosis = valuesMap.get(7);
                        Double malaCondicion = valuesMap.get(8);
                        HashMap<Double, Boolean> map = new HashMap<Double, Boolean>();
                        map.put(0.0, false);
                        map.put(1.0, true);
                        RiesgoPostOperatorio rpoObj = new RiesgoPostOperatorio(map.get(edadMayor), map.get(infarto), map.get(ruido), map.get(estenosis), map.get(ritmoNo), map.get(contracciones), map.get(malaCondicion), map.get(cirugia), map.get(operaciones));
                        rpo = rpoObj.calcularRiesgo();
                        for (int i = 0; i < rpo.length; i++){
                            Log.d("RPO " + i, String.valueOf(rpo[i]));
                        }
                        intent.putExtra("result", rpo);
                        startActivity(intent);
                        break;

                }
            }
        });

    }

    public JSONObject getData(int position){

        String massStr = "mass";
        String framinghamStr = "framingham";
        String coronaryStr = "coronary";
        String metabolicStr = "metabolic";
        String postoperatory = "postoperatory";

        List<String> filenames = new ArrayList<String>();
        filenames.add(framinghamStr);
        filenames.add(coronaryStr);
        filenames.add(metabolicStr);
        filenames.add(massStr);
        filenames.add(postoperatory);

        List<JSONObject> dataArray = new ArrayList<JSONObject>();

        try {
            for (int i = 0; i < filenames.size();i++){
                JSONObject obj = new JSONObject(loadJSONFromAsset(filenames.get(i)));
                dataArray.add(obj);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return dataArray.get(position);
    }

    public String loadJSONFromAsset(String filename) {
        String json = null;
        try {
            InputStream is = getAssets().open(filename + ".json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void initFields(JSONArray fieldsData, int position) throws JSONException {
        // Cast JSONArray to ArrayList
        listdata = new ArrayList<JSONObject>();
        JSONArray jArray = (JSONArray)fieldsData;
        if (jArray != null) {
            for (int i = 0; i < jArray.length(); i++){
                listdata.add(jArray.getJSONObject(i));
            }
        }

        Map<String, Integer> mapping = new HashMap<String, Integer>();
        mapping.put("textField", 0);
        mapping.put("segmentedControl", 1);
        mapping.put("slider", 2);

        // Array For Metabolic Slider
        listDataMinus = new ArrayList<JSONObject>();
        listDataMinus.addAll(listdata);
        int size = listDataMinus.size();
        for (int i = 2; i < size; i++){
            Log.d("object",listDataMinus.get(2).toString());
            listDataMinus.remove(2);
            Log.d("REMOVED", "LIST ITEM");
        }

        // ListViewItems initialize
        ArrayList<ListViewItem> items = new ArrayList<ListViewItem>();
        for (int i = 0; i < listdata.size(); i++){
            JSONObject object = listdata.get(i);
            String name = listdata.get(i).getString("name");;
            Integer type = mapping.get(listdata.get(i).getString("type"));

            ListViewItem item = new ListViewItem(name, type);
            items.add(item);
        }

        ContenidoAdapter contenidoAdapter;

        if (position == METABOLIC_POS){
            contenidoAdapter = new ContenidoAdapter(getApplicationContext(), R.layout.fields_numbercell, listDataMinus, items);
        } else{
            contenidoAdapter = new ContenidoAdapter(getApplicationContext(), R.layout.fields_numbercell, listdata, items);
        }

        listView = (ListView) findViewById(R.id.fields_list);
        listView.setAdapter(contenidoAdapter);

        valuesMap = new HashMap<Integer, Double>();

    }

    private class ContenidoAdapter extends ArrayAdapter<JSONObject> {
        Context context;
        int resourceid;
        ArrayList<JSONObject> data;
        private ArrayList<ListViewItem> items;

        public static final int TYPE_INPUT = 0;
        public static final int TYPE_CHOICE = 1;
        public static final int TYPE_SLIDER = 2;

        @Override
        public int getViewTypeCount() {
            return 3;
        }

        @Override
        public int getItemViewType(int position) {
            return items.get(position).getType();
        }

        public ContenidoAdapter(Context context , int resourceid , ArrayList<JSONObject> data, ArrayList<ListViewItem> items) {
            super(context , resourceid, data);
            this.context = context;
            this.resourceid = resourceid;
            this.data = data;
            this.items = items;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            ListViewItem listViewItem = items.get(position);
            int listViewItemType = getItemViewType(position);
            TextView titleView;
            boolean viewWasNull = false;

            if (convertView == null){
                holder = new ViewHolder();
                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                if (listViewItemType == TYPE_INPUT) {
                    convertView = inflater.inflate(R.layout.fields_numbercell, parent, false);
                    holder.title = (TextView) convertView.findViewById(R.id.fields_listcell_title);
                    holder.editText = (EditText) convertView.findViewById(R.id.fields_numbercell_input);
                } else if (listViewItemType == TYPE_CHOICE){
                    convertView = inflater.inflate(R.layout.fields_choicecell, parent, false);
                    holder.title = (TextView) convertView.findViewById(R.id.fields_listcell_title);
                    holder.buttonContainer = (LinearLayout) convertView.findViewById(R.id.fields_choicecell_buttoncontainer);
                    holder.button0 = (Button) convertView.findViewById(R.id.fields_choicecell_button0);
                    holder.button1 = (Button) convertView.findViewById(R.id.fields_choicecell_button1);
                } else if (listViewItemType == TYPE_SLIDER){
                    convertView = inflater.inflate(R.layout.fields_slidercell, parent, false);
                    holder.title = (TextView) convertView.findViewById(R.id.fields_listcell_title);
                    holder.slider = (SeekBar) convertView.findViewById(R.id.fields_slidercell_seekbar);
                    holder.label = (TextView) convertView.findViewById(R.id.fields_slidercell_label);
                }
                convertView.setTag(holder);
                viewWasNull = true;
            } else{
                holder = (ViewHolder)convertView.getTag();
            }

            if (hideItems && position < 1){
                convertView.setVisibility(View.GONE);
            }

            JSONObject field = data.get(position);
            titleView = holder.title;
            final String title;
            try {
                title = field.getString("name");
                titleView.setText(title);

                if (listViewItemType == TYPE_INPUT) {
                    EditText input = holder.editText;

                    if (valuesMap.get(position) == null){
                        input.getText().clear();
                        Log.d("Value in map", "null");
                        String hint = field.getString("placeholder");
                        input.setHint(hint);
                    } else{
                        input.setText(valuesMap.get(position).toString());
                    }

                    input.setTag(position);


                    if (viewWasNull){}
                        final ViewHolder finalHolder = holder;
                        input.addTextChangedListener(new TextWatcher() {

                            @Override
                            public void afterTextChanged(Editable s) {}

                            @Override
                            public void beforeTextChanged(CharSequence s, int start,
                                                          int count, int after) {}

                            @Override
                            public void onTextChanged(CharSequence s, int start,
                                                      int before, int count) {
                                if (s != null && s != ""){
                                    int pos = (Integer) finalHolder.editText.getTag();
                                    Log.d(title + " position : " + pos, s.toString());

                                    String textString = s.toString();

                                    if (!textString.isEmpty() && textString != null) {
                                        valuesMap.put(pos, Double.valueOf(textString));
                                    }
                                }
                            }
                        });

                } else if (listViewItemType == TYPE_CHOICE){
                    final Button button0 = (Button) holder.button0;
                    final Button button1 = (Button) holder.button1;

                    button0.setTag(Double.valueOf(0));
                    button1.setTag(Double.valueOf(1));

                    if (valuesMap.get(position) == null){
                        valuesMap.put(position, Double.valueOf(button0.getTag().toString()));
                        switchButtons(button0, button1);
                    } else {
                        if (valuesMap.get(position) == 0.0){
                            switchButtons(button0, button1);
                        } else{
                            switchButtons(button1, button0);
                        }
                    }

                    Log.d("BUTTON 0 TAG", valuesMap.get(position).toString());

                    button0.setText(field.getJSONArray("options").getString(0));
                    button1.setText(field.getJSONArray("options").getString(1));

                    button0.setTextSize(14);
                    button1.setTextSize(14);

                    button0.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            switchButtons(button0, button1);
                            valuesMap.put(position, Double.valueOf(button0.getTag().toString()));
                        }
                    });

                    button1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            switchButtons(button1, button0);
                            valuesMap.put(position, Double.valueOf(button1.getTag().toString()));
                        }
                    });

                } else if (listViewItemType == TYPE_SLIDER){

                    SeekBar sk = (SeekBar) holder.slider;
                    final TextView label = (TextView) holder.label;

                    sk.setMax(180);
                    sk.setProgress(seekBarProgress);
                    label.setTextColor(getResources().getColor(R.color.white));
                    label.setText(seekBarProgress + " cm");

                    sk.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {
                            Log.d("stop", "onStopTrackingTouch: ");
                            int progress = seekBar.getProgress();
                            valuesMap.put(position, (double) progress);
                            seekBarProgress = progress;

                            if (progress >= 90){
                                ContenidoAdapter contenidoAdapter = new ContenidoAdapter(getApplicationContext(), R.layout.fields_numbercell, listdata, items);
                                listView.setAdapter(contenidoAdapter);
                            } else {
                                ContenidoAdapter contenidoAdapter = new ContenidoAdapter(getApplicationContext(), R.layout.fields_numbercell, listDataMinus, items);
                                listView.setAdapter(contenidoAdapter);
                            }
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {
                        }

                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                            label.setText(String.valueOf(progress) + " cm");
                        }
                    });

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return convertView;
        }
    }

    public void switchButtons(Button clicked, Button other){
        clicked.setTextColor(getResources().getColor(R.color.white));
        other.setTextColor(getResources().getColor(R.color.asofarmaRed));

        clicked.setBackgroundResource(R.drawable.round_button_selected);
        other.setBackgroundResource(R.drawable.round_button);
    }

    public void goToResult(){
        Intent intent = new Intent(FieldsActivity.this, ResultsActivity.class);
        startActivity(intent);
    }

    // CLASSES FOR LIST
    private class ListViewItem {
        private String name;
        private int type;

        public ListViewItem(String name, int type) {
            this.name = name;
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String text) {
            this.name = text;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

    }

    public class ViewHolder {
        TextView title;
        EditText editText;
        LinearLayout buttonContainer;
        Button button0;
        Button button1;
        SeekBar slider;
        TextView label;
    }

}

