package cl.ipvg.trabajo2_2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    ExpandableListAdapter listAdapter;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    ExpandableListView expListView;
    Button btnCrearCliente;
    TextView tvNombreCliente;
    TextView tvRutCliente;
    TextView tvTelefonoCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        expListView =  findViewById(R.id.lvExp);
        btnCrearCliente =  findViewById(R.id.buttonCrearCliente);
        tvNombreCliente = findViewById(R.id.editTextNombre);
        tvRutCliente = findViewById(R.id.editTextRut);
        tvTelefonoCliente = findViewById(R.id.editTextTelefono);


        inicializarFireBase();
        getClientesYProductosDeFirebase();


        //crear cliente
        btnCrearCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cliente c = new Cliente();
                c.setIdCliente(UUID.randomUUID().toString());
                c.setRut(tvRutCliente.getText().toString());
                c.setNombre(tvNombreCliente.getText().toString());
                c.setNumeroTelefono(tvTelefonoCliente.getText().toString());

                databaseReference.child("Cliente").child(c.getIdCliente()).setValue(c);
            }
        });

        //setup list
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);



}
    private void getClientesYProductosDeFirebase() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Clientes");
        listDataHeader.add("Productos");
        List<String> clientesNombres = new ArrayList<>();
        List<String> productosNombres = new ArrayList<>();

        databaseReference.child("Cliente").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                List<Cliente> Clientes = new ArrayList<>();
                List<Producto> Productos = new ArrayList<>();

                for (DataSnapshot objs : snapshot.getChildren()){
                    Cliente c =objs.getValue(Cliente.class);
                    Producto p =objs.getValue(Producto.class);

                    Productos.add(p);
                    Clientes.add(c);

                    clientesNombres.add(""+c.getNombre()+"  -  "+c.getRut());
                    productosNombres.add(""+p.getNombre()+"  -  $"+p.getPrecio());
                    //Todo esta es la lista de los clientes, cacha como hacer que sea la lista 1
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        listDataChild.put(listDataHeader.get(0), clientesNombres); // Header, Child data
        listDataChild.put(listDataHeader.get(1), productosNombres);

    }

    private void inicializarFireBase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference =firebaseDatabase.getReference();
    }

}


