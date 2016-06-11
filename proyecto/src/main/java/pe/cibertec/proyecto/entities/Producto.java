package pe.cibertec.proyecto.entities;

import android.os.Parcel;
import android.os.Parcelable;
import android.renderscript.Double2;

/**
 * Created by JOSE on 03/06/2016.
 */
public class Producto implements Parcelable {
    private int idProducto;
    private String nombre;
    private String descripcion;
    private Double precioUnitario;

    public Producto() {
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.idProducto);
        dest.writeString(this.nombre);
        dest.writeString(this.descripcion);
        dest.writeDouble(this.precioUnitario);
    }

    protected Producto(Parcel in) {
        this.idProducto = in.readInt();
        this.nombre = in.readString();
        this.descripcion = in.readString();
        this.precioUnitario = in.readDouble();
    }

    public static final Parcelable.Creator<Producto> CREATOR = new Parcelable.Creator<Producto>() {
        @Override
        public Producto createFromParcel(Parcel source) {
            return new Producto(source);
        }

        @Override
        public Producto[] newArray(int size) { return new Producto[size]; }
    };
}
