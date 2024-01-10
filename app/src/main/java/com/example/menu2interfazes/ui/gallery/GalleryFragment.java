package com.example.menu2interfazes.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.menu2interfazes.R;
import com.example.menu2interfazes.databinding.FragmentGalleryBinding;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private RecyclerView recyclerView;
    private GalleryAdapter adapter;
    private List<GalleryItem> galleryItems; // Lista de ítems

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Inicializar la lista de ítems
        galleryItems = new ArrayList<>();
        galleryItems.add(new GalleryItem(R.drawable.image1, "Card 1"));
        galleryItems.add(new GalleryItem(R.drawable.image2, "Card 2"));
        galleryItems.add(new GalleryItem(R.drawable.image3, "Card 3"));
        galleryItems.add(new GalleryItem(R.drawable.image4, "Card 4"));
        galleryItems.add(new GalleryItem(R.drawable.image5, "Card 5"));
        galleryItems.add(new GalleryItem(R.drawable.image6, "Card 6"));
        galleryItems.add(new GalleryItem(R.drawable.image7, "Card 7"));
        galleryItems.add(new GalleryItem(R.drawable.image8, "Card 8"));
        galleryItems.add(new GalleryItem(R.drawable.image9, "Card 9"));



        // Configurar RecyclerView
        recyclerView = binding.recyclerView;
        // Usar GridLayoutManager con 2 columnas
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        adapter = new GalleryAdapter(galleryItems);
        recyclerView.setAdapter(adapter);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    // Clase del adaptador (deberás crearla según tus necesidades)
    public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {
        // Supongamos que tienes una lista de elementos que quieres mostrar en el RecyclerView.
        // Reemplaza 'GalleryItem' con la clase de tus datos.
        private List<GalleryItem> galleryItems;

        // Constructor
        public GalleryAdapter(List<GalleryItem> galleryItems) {
            this.galleryItems = galleryItems;
        }

        // Método para crear nuevos Views (invocado por el layout manager)
        @Override
        public GalleryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // Crea una nueva vista, inflando el layout del ítem
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gallery, parent, false);
            return new GalleryViewHolder(view);
        }


        @Override
        public void onBindViewHolder(GalleryViewHolder holder, int position) {
            GalleryItem item = galleryItems.get(position);
            holder.textView.setText(item.getText());
            holder.imageView.setImageResource(item.getImageResId()); // Configurar imagen
        }

        // Retorna el tamaño de tu dataset (invocado por el layout manager)
        @Override
        public int getItemCount() {
            return galleryItems.size();
        }

        // Clase ViewHolder que proporciona una referencia a las vistas para cada ítem de datos
        public class GalleryViewHolder extends RecyclerView.ViewHolder {
            // Cada ítem de datos es solo un String en este ejemplo
            // Puedes agregar más vistas aquí si necesitas
            public TextView textView;
            public ImageView imageView;
            public Button button1;
            public Button button2;

            public GalleryViewHolder(View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.textView);
                imageView = itemView.findViewById(R.id.imageView);
                button1 = itemView.findViewById(R.id.button1);
                button2 = itemView.findViewById(R.id.button2);
            }
        }

    }
}
