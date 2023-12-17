package com.example.sonidosanimales;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    SoundPool reproductorSonidos;
    int sonidoPerro, sonidoLeon, sonidoLoro;
    int sonidoActual;
    private ImageView img1, img2, img3, img4, img5, img6, imgFondo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.toolbar));

        reproductorSonidos = new SoundPool.Builder().setMaxStreams(6).build();
        sonidoPerro = reproductorSonidos.load(this, R.raw.perro, 1);
        sonidoLeon = reproductorSonidos.load(this, R.raw.leon, 1);
        sonidoLoro = reproductorSonidos.load(this, R.raw.loro, 1);
        sonidoActual = sonidoPerro;

        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        img4 = findViewById(R.id.img4);
        img5 = findViewById(R.id.img5);
        img6 = findViewById(R.id.img6);
        imgFondo = findViewById(R.id.imgFondo);
        cambiarDomesticos();

        img1.setOnClickListener(v -> reproducirSonido());
        img2.setOnClickListener(v -> reproducirSonido());
        img3.setOnClickListener(v -> reproducirSonido());
        img4.setOnClickListener(v -> reproducirSonido());
        img5.setOnClickListener(v -> reproducirSonido());
        img6.setOnClickListener(v -> reproducirSonido());
    }

    private void reproducirSonido() {
        reproductorSonidos.play(sonidoActual, 1, 1, 0, 0, 1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        boolean res = super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.menuItemDomesticos) {
            cambiarDomesticos();
            res = true;
        } else if (item.getItemId() == R.id.menuItemSalvajes) {
            cambiarSalvajes();
            res = true;
        } else if (item.getItemId() == R.id.menuItemAves) {
            cambiarAves();
            res = true;
        } else if (item.getItemId() == R.id.menuItemSalir) {
            salir();
            res = true;
        }

        return res;
    }

    private void cambiarAves() {
        redondearImagenes(R.drawable.loro);
        imgFondo.setImageResource(R.drawable.cielo);
        sonidoActual = sonidoLoro;
    }

    private void cambiarSalvajes() {
        redondearImagenes(R.drawable.leon);
        imgFondo.setImageResource(R.drawable.sabana);
        sonidoActual = sonidoLeon;
    }

    private void cambiarDomesticos() {
        redondearImagenes(R.drawable.perro);
        imgFondo.setImageResource(R.drawable.casa);
        sonidoActual = sonidoPerro;
    }

    private void salir() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.adSalirTitulo)
                .setMessage(R.string.adSalirMensaje)
                .setCancelable(false)
                .setNegativeButton(R.string.adSalirNo, null)
                .setPositiveButton(R.string.adSalirSi, (dialog, which) -> {
                    finish();
                })
                .create()
                .show();
    }

    private void redondearImagenes(int img) {
        Glide.with(this)
                .load(img)
                .circleCrop()
                .into(img1);
        Glide.with(this)
                .load(img)
                .circleCrop()
                .into(img2);
        Glide.with(this)
                .load(img)
                .circleCrop()
                .into(img3);
        Glide.with(this)
                .load(img)
                .circleCrop()
                .into(img4);
        Glide.with(this)
                .load(img)
                .circleCrop()
                .into(img5);
        Glide.with(this)
                .load(img)
                .circleCrop()
                .into(img6);
    }
}