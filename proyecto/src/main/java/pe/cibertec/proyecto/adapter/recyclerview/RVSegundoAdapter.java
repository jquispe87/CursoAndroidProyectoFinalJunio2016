package pe.cibertec.proyecto.adapter.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import pe.cibertec.proyecto.R;
import pe.cibertec.proyecto.adapter.recyclerview.interfaces.IRVPrimerAdapter;
import pe.cibertec.proyecto.adapter.recyclerview.interfaces.IRVSegundoAdapter;
import pe.cibertec.proyecto.entities.Cliente;
import pe.cibertec.proyecto.entities.Producto;

/**
 * Created by JOSE on 10/06/2016.
 */
public class RVSegundoAdapter extends RecyclerView.Adapter<RVSegundoAdapter.RVSegundoAdapterViewHolder> {

    private ArrayList<Producto> mLstProducto;
    private IRVSegundoAdapter mIRVSegundoAdapter;

    public RVSegundoAdapter(IRVSegundoAdapter mIRVSegundoAdapter) {
        this.mIRVSegundoAdapter = mIRVSegundoAdapter;
        mLstProducto = new ArrayList<>();
    }

    public void add(Producto producto) {
        mLstProducto.add(producto);
        notifyItemInserted(mLstProducto.size() - 1);
    }

    public void addAll(ArrayList<Producto> lstProducto) {
        int position = mLstProducto.size();
        mLstProducto.addAll(lstProducto);
        notifyItemRangeInserted(position, mLstProducto.size());
    }

    public void clearAndAddAll(ArrayList<Producto> lstProducto) {
        mLstProducto.clear();
        mLstProducto.addAll(lstProducto);
        notifyDataSetChanged();
    }

    @Override
    public RVSegundoAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RVSegundoAdapterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.principal_producto_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RVSegundoAdapterViewHolder holder, int position) {
        Producto producto = mLstProducto.get(position);
        holder.tvPPINombre.setText(producto.getNombre());
        holder.tvPPIDescripcion.setText(producto.getDescripcion());
        holder.tvPPIPrecioUnitario.setText(producto.getPrecioUnitario().toString().trim());
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(itemViewOnClickListener);
    }

    View.OnClickListener itemViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = (int) v.getTag();
            mIRVSegundoAdapter.onSelectItem(mLstProducto.get(position));
        }
    };

    @Override
    public int getItemCount() {
        return mLstProducto.size();
    }

    class RVSegundoAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView tvPPINombre, tvPPIDescripcion, tvPPIPrecioUnitario;

        public RVSegundoAdapterViewHolder(View itemView) {
            super(itemView);
            tvPPINombre = (TextView) itemView.findViewById(R.id.tvPPINombre);
            tvPPIDescripcion = (TextView) itemView.findViewById(R.id.tvPPIDescripcion);
            tvPPIPrecioUnitario = (TextView) itemView.findViewById(R.id.tvPPIPrecioUnitario);
        }
    }
}
