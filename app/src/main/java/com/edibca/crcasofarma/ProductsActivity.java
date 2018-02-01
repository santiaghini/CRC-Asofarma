package com.edibca.crcasofarma;

import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.IOException;
import java.io.InputStream;

public class ProductsActivity extends AppCompatActivity {
    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        setTitle(getString(R.string.products));

        // my_child_toolbar is defined in the layout file
        Toolbar myChildToolbar =
                (Toolbar) findViewById(R.id.products_toolbar);
        setSupportActionBar(myChildToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        pdfView = (PDFView) findViewById(R.id.products_pdf);

        AssetManager assetMgr = this.getAssets();
        try {
            InputStream inputStream = assetMgr.open("products.pdf");
            pdfView.fromStream(inputStream).load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Uri uri = Uri.parse("file:///android_asset/products.pdf");

    }
}
