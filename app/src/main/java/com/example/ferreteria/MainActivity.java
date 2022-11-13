package com.example.ferreteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ferreteria.DBferreteria.DBf;

public class MainActivity extends AppCompatActivity {

  Button insertar, buscar, borrar;
  private EditText ncedula, nombre, direccion, telefono, codigo_pedido, descripcion_pedido,
  fecha_pedido,cantidad_pedido,codigo_producto,descripcion_producto,valor_producto,numero_factura,fecha_factura,
  total_factura;




  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ncedula=(EditText) findViewById(R.id.etcedula);
    nombre=(EditText) findViewById(R.id.etnombre);
    direccion=(EditText) findViewById(R.id.etdireccion);
    telefono=(EditText) findViewById(R.id.ettelefono);
    codigo_pedido=(EditText) findViewById(R.id.etcodigopedido);
    descripcion_pedido=(EditText) findViewById(R.id.etdescripcionpedido);
    fecha_pedido=(EditText) findViewById(R.id.etfechapedido);
    cantidad_pedido=(EditText) findViewById(R.id.etcantidadpedido);
    codigo_producto=(EditText) findViewById(R.id.etcodigoproducto);
    descripcion_producto=(EditText) findViewById(R.id.etdescripcionproducto);
    valor_producto=(EditText) findViewById(R.id.etvalorproducto);
    numero_factura=(EditText) findViewById(R.id.etnumerofactura);
    fecha_factura=(EditText) findViewById(R.id.etfechafactura);
    total_factura=(EditText) findViewById(R.id.ettotal_factura);
  }
  public void Insertar(View view){
    DBf dbf=new DBf(this,"BaseDatos",null,1);
    SQLiteDatabase BaseDatos=dbf.getWritableDatabase();
    String cedula=ncedula.getText().toString();
    String nombreuser=nombre.getText().toString();
    String direccionuser=direccion.getText().toString();
    String telefonouser=telefono.getText().toString();
    String codigopedido=codigo_pedido.getText().toString();
    String descripcionpedido=descripcion_pedido.getText().toString();
    String fechapedido=fecha_pedido.getText().toString();
    String cantidadpedido=cantidad_pedido.getText().toString();
    String codigoproducto=codigo_producto.getText().toString();
    String descripcionproducto=descripcion_producto.getText().toString();
    String valorproducto=valor_producto.getText().toString();
    String numerofactura=numero_factura.getText().toString();
    String fechafactura=fecha_factura.getText().toString();
    String totalfactura=total_factura.getText().toString();

    if(!cedula.isEmpty() && !nombreuser.isEmpty() && !direccionuser.isEmpty() && !telefonouser.isEmpty() &&
    !codigopedido.isEmpty() && !descripcionpedido.isEmpty() && !fechapedido.isEmpty() && !cantidadpedido.isEmpty() &&
    !codigoproducto.isEmpty() && !descripcionproducto.isEmpty() && !valorproducto.isEmpty() && !numerofactura.isEmpty()
        && !fechafactura.isEmpty() && !totalfactura.isEmpty())
    {
      ContentValues Insertar=new ContentValues();
      Insertar.put("cedula", cedula);
      Insertar.put("nombre",nombreuser);
      Insertar.put("direccion",direccionuser);
      Insertar.put("telefono",telefonouser);
      BaseDatos.insert("clientes",null,Insertar);
      ContentValues Insertar2=new ContentValues();
      Insertar2.put("codigo",codigopedido);
      Insertar2.put("descripcion",descripcionpedido);
      Insertar2.put("fecha", fechapedido);
      Insertar2.put("cantidad", cantidadpedido);
      BaseDatos.insert("pedido",null,Insertar2);
      ContentValues Insertar3=new ContentValues();
      Insertar3.put("codigo",codigoproducto);
      Insertar3.put("descripcion", descripcionproducto);
      Insertar3.put("valor",valorproducto);
      BaseDatos.insert("producto",null,Insertar3);
      ContentValues Insertar4=new ContentValues();
      Insertar4.put("numero", numerofactura);
      Insertar4.put("fecha", fechafactura);
      Insertar4.put("total", totalfactura);
      BaseDatos.insert("factura", null,Insertar4);
      BaseDatos.close();
      ncedula.setText("");
      nombre.setText("");
      direccion.setText("");
      telefono.setText("");
      codigo_pedido.setText("");
      descripcion_pedido.setText("");
      fecha_pedido.setText("");
      cantidad_pedido.setText("");
      codigo_producto.setText("");
      descripcion_producto.setText("");
      valor_producto.setText("");
      numero_factura.setText("");
      fecha_factura.setText("");
      total_factura.setText("");

      Toast.makeText(this,"Se registro con exito",Toast.LENGTH_LONG).show();
    }
    else {
      Toast.makeText(this,"ingresé correctamente los datos",Toast.LENGTH_LONG).show();
    }

  }

  public void Buscar (View view) {
    DBf dbf = new DBf(this, "BaseDatos", null, 1);
    SQLiteDatabase BasedeDatos = dbf.getWritableDatabase();
    String cedula = ncedula.getText().toString();
    String codigopedido = codigo_pedido.getText().toString();
    String codigoproducto = codigo_producto.getText().toString();
    String numerofactura = numero_factura.getText().toString();
    if (!cedula.isEmpty()) {
      Cursor fila = BasedeDatos.rawQuery("select nombre, direccion, telefono from clientes where cedula=" + cedula, null);
      if (fila.moveToFirst()) {
        nombre.setText(fila.getString(0));
        direccion.setText(fila.getString(1));
        telefono.setText(fila.getString(2));
        BasedeDatos.close();
      }
      else {
        Toast.makeText(this,"No se encontro el dato de cedula cliente",Toast.LENGTH_LONG).show();
      }

    }
    if (!codigopedido.isEmpty()) {
      Cursor fila = BasedeDatos.rawQuery("select descripcion, fecha, cantidad from pedido where codigo=" + codigopedido, null);
      if (fila.moveToFirst()) {
        descripcion_pedido.setText(fila.getString(0));
        fecha_pedido.setText(fila.getString(1));
        cantidad_pedido.setText(fila.getString(2));
        BasedeDatos.close();
      }
      else {
        Toast.makeText(this,"No se encontro el dato de codigo pedido",Toast.LENGTH_LONG).show();
      }
    }
    if (!codigoproducto.isEmpty()) {
      Cursor fila = BasedeDatos.rawQuery("select descripcion, valor from producto where codigo=" + codigoproducto, null);
      if (fila.moveToFirst()) {
        descripcion_producto.setText(fila.getString(0));
        valor_producto.setText(fila.getString(1));
        BasedeDatos.close();
      }
      else {
        Toast.makeText(this,"No se encontro el dato de codigo producto",Toast.LENGTH_LONG).show();
      }
    }
    if (!numerofactura.isEmpty()) {
      Cursor fila = BasedeDatos.rawQuery("select fecha, total from factura where numero=" + numerofactura, null);
      if (fila.moveToFirst()) {
        fecha_factura.setText(fila.getString(0));
        total_factura.setText(fila.getString(1));
        BasedeDatos.close();
      }
      else {
        Toast.makeText(this,"No se encontro el dato de numero de factura",Toast.LENGTH_LONG).show();
      }
    }

  }
  public void Borrar (View view){
    DBf dbf = new DBf(this, "BaseDatos", null, 1);
    SQLiteDatabase BasedeDatos = dbf.getWritableDatabase();
    String cedula = ncedula.getText().toString();
    String codigopedido = codigo_pedido.getText().toString();
    String codigoproducto = codigo_producto.getText().toString();
    String numerofactura = numero_factura.getText().toString();
    if(!cedula.isEmpty()) {
      int cantidad = BasedeDatos.delete("clientes", "cedula=" + cedula, null);
      BasedeDatos.close();
      ncedula.setText("");

      if (cantidad == 1) {
        Toast.makeText(this, "Datos de la cedula Elminados Exitosamente", Toast.LENGTH_LONG).show();
      } else {
        Toast.makeText(this, "La cedula no existe", Toast.LENGTH_LONG).show();
      }
    }
  if (!codigopedido.isEmpty()) {
    int cantidad = BasedeDatos.delete("pedido", "codigo=" + codigopedido, null);
    BasedeDatos.close();
    codigo_pedido.setText("");

    if (cantidad == 1) {
      Toast.makeText(this, "Datos del pedido elminados exitosamente", Toast.LENGTH_LONG).show();
    } else {
      Toast.makeText(this, "el codigo de pedido no existe", Toast.LENGTH_LONG).show();
    }
  }
  if (!codigoproducto.isEmpty()) {
    int cantidad = BasedeDatos.delete("producto", "codigo=" + codigoproducto, null);
    BasedeDatos.close();
    codigo_producto.setText("");

    if (cantidad == 1) {
      Toast.makeText(this, "Datos del producto elminados exitosamente", Toast.LENGTH_LONG).show();
    } else {
      Toast.makeText(this, "el codigo de producto no existe", Toast.LENGTH_LONG).show();
    }
  }
  if (!numerofactura.isEmpty()) {
    int cantidad = BasedeDatos.delete("factura", "numero=" + numerofactura, null);
    BasedeDatos.close();
    numero_factura.setText("");

    if (cantidad == 1) {
      Toast.makeText(this, "Datos del numero de factura elminados exitosamente", Toast.LENGTH_LONG).show();
    } else {
      Toast.makeText(this, "el numero de factura no existe", Toast.LENGTH_LONG).show();
    }
  }
  }
  public void Modificar (View view){
    DBf dbf = new DBf(this, "BaseDatos", null, 1);
    SQLiteDatabase BasedeDatos = dbf.getWritableDatabase();
    String cedula=ncedula.getText().toString();
    String nombreuser=nombre.getText().toString();
    String direccionuser=direccion.getText().toString();
    String telefonouser=telefono.getText().toString();
    String codigopedido=codigo_pedido.getText().toString();
    String descripcionpedido=descripcion_pedido.getText().toString();
    String fechapedido=fecha_pedido.getText().toString();
    String cantidadpedido=cantidad_pedido.getText().toString();
    String codigoproducto=codigo_producto.getText().toString();
    String descripcionproducto=descripcion_producto.getText().toString();
    String valorproducto=valor_producto.getText().toString();
    String numerofactura=numero_factura.getText().toString();
    String fechafactura=fecha_factura.getText().toString();
    String totalfactura=total_factura.getText().toString();

    if (!cedula.isEmpty() && !nombreuser.isEmpty() && !direccionuser.isEmpty() && telefonouser.isEmpty()){
      ContentValues registro=new ContentValues();
      registro.put("cedula",cedula);
      registro.put("nombre",nombreuser);
      registro.put("direccion",direccionuser);
      registro.put("telefono",telefonouser);
      int cantidad=BasedeDatos.update("clientes", registro, "cedula="+cedula, null);
      BasedeDatos.close();
      if (cantidad==1){
        Toast.makeText(this, "Cliente Modificado", Toast.LENGTH_LONG).show();

      }else{
        Toast.makeText(this, "el cliente no existe", Toast.LENGTH_LONG).show();
      }

    }
    if (!codigopedido.isEmpty() && !descripcionpedido.isEmpty() && !fechapedido.isEmpty() && !cantidadpedido.isEmpty()) {
      ContentValues registro=new ContentValues();
      registro.put("codigo",codigopedido);
      registro.put("descripcion",descripcionpedido);
      registro.put("fecha",fechapedido);
      registro.put("cantidad",cantidadpedido);
      int cantidad=BasedeDatos.update("pedido", registro, "codigo="+codigopedido, null);
      BasedeDatos.close();
      if (cantidad==1){
        Toast.makeText(this, "Pedido Modificado", Toast.LENGTH_LONG).show();

      }else{
        Toast.makeText(this, "El pedido no existe", Toast.LENGTH_LONG).show();
      }
    }

    if (!codigoproducto.isEmpty() && !descripcionproducto.isEmpty() && !valorproducto.isEmpty()){
      ContentValues registro=new ContentValues();
      registro.put("codigo",codigoproducto);
      registro.put("descripcion",descripcionproducto);
      registro.put("valor", valorproducto);
      int cantidad=BasedeDatos.update("producto", registro, "codigo="+codigoproducto, null);
      BasedeDatos.close();
      if (cantidad==1){
        Toast.makeText(this, "Producto Modificado", Toast.LENGTH_LONG).show();

      }else{
        Toast.makeText(this, "El producto no existe", Toast.LENGTH_LONG).show();
      }
    }
    if (!numerofactura.isEmpty() && !fechafactura.isEmpty() && !totalfactura.isEmpty()){
      ContentValues registro=new ContentValues();
      registro.put("numero",numerofactura);
      registro.put("fecha",fechafactura);
      registro.put("total", totalfactura);
      int cantidad=BasedeDatos.update("factura", registro, "numero="+numerofactura, null);
      BasedeDatos.close();
      if (cantidad==1){
        Toast.makeText(this, "Factura Modificada", Toast.LENGTH_LONG).show();

      }else{
        Toast.makeText(this, "La factura no existe", Toast.LENGTH_LONG).show();
      }
    }
  }

}