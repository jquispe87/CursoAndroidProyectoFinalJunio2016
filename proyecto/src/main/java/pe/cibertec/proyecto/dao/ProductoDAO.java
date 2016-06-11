package pe.cibertec.proyecto.dao;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;

import pe.cibertec.proyecto.entities.Producto;

/**
 * Created by JOSE on 10/06/2016.
 */
public class ProductoDAO {

    public ArrayList<Producto> listProducto() {
        Cursor cursor = DataBaseSingleton.getInstance().query("productos", null, null, null, null, null, null);

        ArrayList<Producto> lstProducto = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                lstProducto.add(transformCursorToProducto(cursor));
            } while (cursor.moveToNext());
        }

        if (cursor != null && !cursor.isClosed())
            cursor.close();

        return lstProducto;
    }

    public Producto getProducto(Producto producto) {
        Cursor cursor = null;
        try {
            cursor = DataBaseSingleton.getInstance().query("productos", null, "idProducto = ?", new String[]{String.valueOf(producto.getIdProducto())}, null, null, null, "1");

            if (cursor.moveToFirst())
                producto = transformCursorToProducto(cursor);
            else
                producto = null;

        } catch (Exception ex) {
            ex.printStackTrace();
            producto = null;
        } finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();
            return producto;
        }
    }

    public boolean insertProducto(Producto producto) {
        ContentValues cv = new ContentValues();
        //cv.put("idCliente", cliente.getIdCliente());
        cv.put("idProducto", producto.getIdProducto());
        cv.put("nombre", producto.getNombre());
        cv.put("descripcion", producto.getDescripcion());
        cv.put("precioUnitario", producto.getPrecioUnitario());

        long inserto = DataBaseSingleton.getInstance().insert("productos", null, cv);
        return inserto != -1;
    }

    public boolean updateProducto(Producto producto) {
        ContentValues cv = new ContentValues();
        cv.put("idProducto", producto.getIdProducto());
        cv.put("nombre", producto.getNombre());
        cv.put("descripcion", producto.getDescripcion());
        cv.put("precioUnitario", producto.getPrecioUnitario());

        int update = DataBaseSingleton.getInstance().update("productos", cv, "idProducto = ?", new String[]{String.valueOf(producto.getIdProducto())});
        return update > 0;
    }

    private Producto transformCursorToProducto(Cursor cursor) {
        Producto producto = new Producto();
        producto.setIdProducto(cursor.getInt(cursor.getColumnIndex("idProducto")));
        producto.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
        producto.setDescripcion(cursor.getString(cursor.getColumnIndex("descripcion")));
        producto.setPrecioUnitario(cursor.getDouble(cursor.getColumnIndex("precioUnitario")));

        return producto;
    }
}
