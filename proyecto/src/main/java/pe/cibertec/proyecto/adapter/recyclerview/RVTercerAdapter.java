package pe.cibertec.proyecto.adapter.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import pe.cibertec.proyecto.R;
import pe.cibertec.proyecto.adapter.recyclerview.interfaces.IRVTercerAdapter;
import pe.cibertec.proyecto.entities.Pedido;

/**
 * Created by JOSE on 11/06/2016.
 */
public class RVTercerAdapter extends RecyclerView.Adapter<RVTercerAdapter.RVTercerAdapterViewHolder> {

    private ArrayList<Pedido> mLstPedido;
    private IRVTercerAdapter mIRVTercerAdapter;

    public RVTercerAdapter(IRVTercerAdapter mIRVTercerAdapter) {
        this.mIRVTercerAdapter = mIRVTercerAdapter;
        mLstPedido = new ArrayList<>();
    }

    public void add(Pedido pedido) {
        mLstPedido.add(pedido);
        notifyItemInserted(mLstPedido.size() - 1);
    }

    public void addAll(ArrayList<Pedido> lstPedido) {
        int position = mLstPedido.size();
        mLstPedido.addAll(lstPedido);
        notifyItemRangeInserted(position, mLstPedido.size());
    }

    public void clearAndAddAll(ArrayList<Pedido> lstPedido) {
        mLstPedido.clear();
        mLstPedido.addAll(lstPedido);
        notifyDataSetChanged();
    }

    @Override
    public RVTercerAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RVTercerAdapterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.principal_pedido_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RVTercerAdapterViewHolder holder, int position) {
        Pedido pedido = mLstPedido.get(position);
        holder.tvPPIPedido.setText(pedido.getIdPedido());
        holder.tvPPICliente.setText(pedido.getIdCliente());
        holder.tvPPIProducto.setText(pedido.getIdProducto());
        holder.tvPPIPrecioUnitario.setText(pedido.getPrecioUnitario().toString().trim());
        holder.tvPPICantidad.setText(pedido.getCantidad().toString().trim());
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(itemViewOnClickListener);
    }

    View.OnClickListener itemViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = (int) v.getTag();
            mIRVTercerAdapter.onSelectItem(mLstPedido.get(position));
        }
    };

    @Override
    public int getItemCount() {
        return mLstPedido.size();
    }

    class RVTercerAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView tvPPIPedido, tvPPICliente, tvPPIProducto, tvPPIPrecioUnitario, tvPPICantidad;

        public RVTercerAdapterViewHolder(View itemView) {
            super(itemView);
            /*
            tvPPIPedido = (TextView) itemView.findViewById(R.id.);
            tvPPICliente = (TextView) itemView.findViewById(R.id.);
            tvPPIProducto = (TextView) itemView.findViewById(R.id.);
            tvPPIPrecioUnitario = (TextView) itemView.findViewById(R.id.);
            tvPPICantidad = (TextView) itemView.findViewById(R.id.);
            */
        }
    }

}
