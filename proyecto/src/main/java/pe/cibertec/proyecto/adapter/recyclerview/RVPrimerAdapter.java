package pe.cibertec.proyecto.adapter.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import pe.cibertec.proyecto.R;
import pe.cibertec.proyecto.adapter.recyclerview.interfaces.IRVPrimerAdapter;
import pe.cibertec.proyecto.entities.Cliente;

/**
 * Created by JOSE on 08/06/2016.
 */
public class RVPrimerAdapter extends RecyclerView.Adapter<RVPrimerAdapter.RVPrimerAdapterViewHolder> {

    private ArrayList<Cliente> mLstCliente;
    private IRVPrimerAdapter mIRVPrimerAdapter;

    public RVPrimerAdapter(IRVPrimerAdapter mIRVPrimerAdapter) {
        this.mIRVPrimerAdapter = mIRVPrimerAdapter;
        mLstCliente = new ArrayList<>();
    }

    public void add(Cliente cliente) {
        mLstCliente.add(cliente);
        notifyItemInserted(mLstCliente.size() - 1);
    }

    public void addAll(ArrayList<Cliente> lstCliente) {
        int position = mLstCliente.size();
        mLstCliente.addAll(lstCliente);
        notifyItemRangeInserted(position, lstCliente.size());
    }

    public void clearAndAddAll(ArrayList<Cliente> lstCliente) {
        mLstCliente.clear();
        mLstCliente.addAll(lstCliente);
        notifyDataSetChanged();
    }

    @Override
    public RVPrimerAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RVPrimerAdapterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.principal_cliente_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RVPrimerAdapterViewHolder holder, int position) {
        Cliente cliente = mLstCliente.get(position);
        holder.tvPCIEmpresa.setText(cliente.getEmpresa());
        holder.tvPCIDireccion.setText(cliente.getDireccion());
        holder.tvPCITelefono.setText(cliente.getTelefono());
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(itemViewOnClickListener);
    }

    View.OnClickListener itemViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = (int) v.getTag();
            mIRVPrimerAdapter.onSelectItem(mLstCliente.get(position));
        }
    };

    @Override
    public int getItemCount() {
        return mLstCliente.size();
    }

    class RVPrimerAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView tvPCIEmpresa, tvPCIDireccion, tvPCITelefono;

        public RVPrimerAdapterViewHolder(View itemView) {
            super(itemView);
            tvPCIEmpresa = (TextView) itemView.findViewById(R.id.tvPCIEmpresa);
            tvPCIDireccion = (TextView) itemView.findViewById(R.id.tvPCIDireccion);
            tvPCITelefono = (TextView) itemView.findViewById(R.id.tvPCITelefono);
        }
    }

}
