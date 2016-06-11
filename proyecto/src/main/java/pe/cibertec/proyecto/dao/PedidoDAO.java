package pe.cibertec.proyecto.dao;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;

import pe.cibertec.proyecto.entities.Pedido;

/**
 * Created by JOSE on 10/06/2016.
 */
public class PedidoDAO {

    public ArrayList<Pedido> listPedido() {
        Cursor cursor = DataBaseSingleton.getInstance().query("pedidos", null, null, null, null, null, null);

        ArrayList<Pedido> lstPedido = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                lstPedido.add(transformCursorToPedido(cursor));
            } while (cursor.moveToNext());
        }

        if (cursor != null && !cursor.isClosed())
            cursor.close();

        return lstPedido;
    }

    public Pedido getPedido(Pedido pedido) {
        Cursor cursor = null;
        try {
            cursor = DataBaseSingleton.getInstance().query("pedidos", null, "idPedido = ?", new String[]{String.valueOf(pedido.getIdPedido())}, null, null, null, "1");

            if (cursor.moveToFirst())
                pedido = transformCursorToPedido(cursor);
            else
                pedido = null;

        } catch (Exception ex) {
            ex.printStackTrace();
            pedido = null;
        } finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();
            return pedido;
        }
    }

    public boolean insertPedido(Pedido pedido) {
        ContentValues cv = new ContentValues();
        //cv.put("idCliente", cliente.getIdCliente());
        cv.put("idPedido", pedido.getIdPedido());
        cv.put("idCliente", pedido.getIdCliente());
        cv.put("idProducto", pedido.getIdProducto());
        cv.put("precioUnitario", pedido.getPrecioUnitario());
        cv.put("cantidad", pedido.getCantidad());

        long inserto = DataBaseSingleton.getInstance().insert("pedidos", null, cv);
        return inserto != -1;
    }

    public boolean updatePedido(Pedido pedido) {
        ContentValues cv = new ContentValues();
        cv.put("idProducto", pedido.getIdPedido());
        cv.put("idCliente", pedido.getIdCliente());
        cv.put("idProducto", pedido.getIdProducto());
        cv.put("precioUnitario", pedido.getPrecioUnitario());
        cv.put("cantidad", pedido.getCantidad());

        int update = DataBaseSingleton.getInstance().update("pedidos", cv, "idPedido = ?", new String[]{String.valueOf(pedido.getIdPedido())});
        return update > 0;
    }

    private Pedido transformCursorToPedido(Cursor cursor) {
        Pedido pedido = new Pedido();
        pedido.setIdProducto(cursor.getInt(cursor.getColumnIndex("idProducto")));
        pedido.setIdCliente(cursor.getInt(cursor.getColumnIndex("idCliente")));
        pedido.setIdProducto(cursor.getInt(cursor.getColumnIndex("idProducto")));
        pedido.setPrecioUnitario(cursor.getDouble(cursor.getColumnIndex("precioUnitario")));
        pedido.setCantidad(cursor.getDouble(cursor.getColumnIndex("cantidad")));

        return pedido;
    }
}
