package com.example.desiredeal.adapter;

public class categoryhomeadapter { //extends RecyclerView.Adapter<categoryhomeadapter.categoryhomeViewHolder>{
/*
    private List<categoryhomemodel> categoryhomemodels;

    public categoryhomeadapter(List<categoryhomemodel> categoryhomemodels) {
        this.categoryhomemodels = categoryhomemodels;
    }

    @NonNull
    @Override
    public categoryhomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categories_home, parent, false);
        return new categoryhomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull categoryhomeViewHolder holder, int position) {

        categoryhomemodel cathomemodel = categoryhomemodels.get(position);
        holder.bind(cathomemodel);
    }

    @Override
    public int getItemCount() {
        return categoryhomemodels.size();
    }

    public class categoryhomeViewHolder extends RecyclerView.ViewHolder {
        
        private ImageView cathomeimage;
        private TextView cattexthome,cattexthome1;
        public categoryhomeViewHolder(@NonNull View itemView) {

            super(itemView);
            
            cathomeimage = itemView.findViewById(R.id.categoryhomeimage);
            cattexthome = itemView.findViewById(R.id.categorynamehome);
            cattexthome1 = itemView.findViewById(R.id.categorynamehome1);
            
            
        }

        public void bind(categoryhomemodel cathomemodel) {
            if (cathomeimage != null) {
                cathomeimage.setImageResource(cathomemodel.getImageResource());
            }
            cattexthome.setText(cathomemodel.getName());
            cattexthome1.setText(cathomemodel.getName());
        }
    }*/
}

