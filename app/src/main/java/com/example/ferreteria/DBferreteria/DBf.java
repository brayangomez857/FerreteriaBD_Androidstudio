package com.example.ferreteria.DBferreteria;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBf extends SQLiteOpenHelper {
  public DBf(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
    super(context, name, factory, version);
  }

  @Override
  public void onCreate(SQLiteDatabase BaseDatos) {
    BaseDatos.execSQL("create table clientes(cedula int primary key, nombre text, direccion text, telefono text)");
    BaseDatos.execSQL("create table pedido(codigo int primary key, descripcion text, fecha text, cantidad int)");
    BaseDatos.execSQL("create table producto(codigo int primary key, descripcion text, valor int)");
    BaseDatos.execSQL("create table factura(numero int primary key, fecha text,total int)");

  }

  @Override
  public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

  }
}
