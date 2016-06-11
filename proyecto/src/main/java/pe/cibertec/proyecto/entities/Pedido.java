package pe.cibertec.proyecto.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by JOSE on 03/06/2016.
 */
public class Pedido implements Parcelable {
    private int idPedido;
    private int idCliente;
    private int idProducto;
    private Double precioUnitario;
    private Double cantidad;

    public Pedido() {
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.idPedido);
        dest.writeInt(this.idCliente);
        dest.writeInt(this.idProducto);
        dest.writeDouble(this.precioUnitario);
        dest.writeDouble(this.cantidad);
    }

    protected Pedido(Parcel in) {
        this.idPedido = in.readInt();
        this.idCliente = in.readInt();
        this.idProducto = in.readInt();
        this.precioUnitario = in.readDouble();
        this.cantidad = in.readDouble();
    }

    public static final Parcelable.Creator<Pedido> CREATOR = new Parcelable.Creator<Pedido>() {
        @Override
        public Pedido createFromParcel(Parcel source) {
            return new Pedido(source);
        }

        @Override
        public Pedido[] newArray(int size) { return new Pedido[size]; }
    };
}
