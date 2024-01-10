package com.example.menu2interfazes.ui.gallery;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.view.ActionMode.Callback;

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
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        adapter = new GalleryAdapter(galleryItems);

        // Establecer el listener para el ActionMode
        // En GalleryFragment
        adapter.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public void onItemLongClicked() {
                if (getActivity() != null) {
                    ((AppCompatActivity) getActivity()).startSupportActionMode(actionModeCallbacks);
                }
            }
        });


        recyclerView.setAdapter(adapter);

        return root;
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_edit) {
            // Maneja la opción "Editar"
            return true;
        } else if (id == R.id.action_delete) {
            // Maneja la opción "Eliminar"
            return true;
        } else if (id == R.id.action_share) {
            // Maneja la opción "Compartir"
            return true;
        } else {
            return super.onContextItemSelected(item);
        }
    }



    private ActionMode.Callback actionModeCallbacks = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // Infla el menú para ActionMode
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.action_mode_menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false; // Devuelve false si nada es hecho
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            int id = item.getItemId();

            if (id == R.id.action_delete) {
                // Implementa la lógica para "Eliminar"
            } else if (id == R.id.action_edit) {
                // Implementa la lógica para "Editar"
            } else if (id == R.id.action_share) {
                // Implementa la lógica para "Compartir"
            }

            mode.finish(); // Cierra el ActionMode
            return true;
        }



        @Override
        public void onDestroyActionMode(ActionMode mode) {
            // Realiza limpieza si es necesario
        }
    };

    // Método para iniciar ActionMode
    public void startActionMode() {
        ((AppCompatActivity) getActivity()).startSupportActionMode(actionModeCallbacks);
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

        private OnItemLongClickListener longClickListener;



        public void setOnItemLongClickListener(OnItemLongClickListener listener) {
            this.longClickListener = listener;
        }

        // Constructor
        public GalleryAdapter(List<GalleryItem> galleryItems) {
            this.galleryItems = galleryItems;
        }

        // Método para crear nuevos Views (invocado por el layout manager)
        @Override
        public GalleryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gallery, parent, false);
            GalleryViewHolder viewHolder = new GalleryViewHolder(view);
            return viewHolder;
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
        public class GalleryViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
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

                itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        if (longClickListener != null) {
                            longClickListener.onItemLongClicked();
                            v.setSelected(true);
                        }
                        return true;
                    }
                });

                itemView.setOnCreateContextMenuListener(this);
            }

            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                // Asegúrate de que este método esté correctamente implementado
                MenuInflater inflater = new MenuInflater(v.getContext());
                inflater.inflate(R.menu.context_menu, menu);
            }
        }


    }
}
