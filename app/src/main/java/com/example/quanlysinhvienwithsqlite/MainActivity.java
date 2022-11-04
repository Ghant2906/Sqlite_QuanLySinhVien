package com.example.quanlysinhvienwithsqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private SVAdapter adapter_335;
    private ListView listViewSV_335;
    private int idSVien = -1;
    private SinhVienDAO dao;
    private List<SinhVien> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.buttonAdd).setOnClickListener(this);
        findViewById(R.id.buttonEdit).setOnClickListener(this);
        listViewSV_335 = findViewById(R.id.listViewSV);
        dao = new SinhVienDAO(this);

        list = dao.getAll();

        adapter_335 = new SVAdapter(this, list);
        listViewSV_335.setAdapter(adapter_335);

        listViewSV_335.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SinhVien svien = list.get(position);
                idSVien = svien.getIdSV();
            }
        });

        listViewSV_335.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Xóa Item?");
                alertDialog.setMessage("Bạn chắc chắn muốn xóa?");
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dao.delete(list.get(position).getIdSV());
                        idSVien = -1;
                        onResume();
                        Toast.makeText(MainActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                alertDialog.show();
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, EditOrAdd.class);
        switch (v.getId()){
            case R.id.buttonAdd:
                startActivity(intent);
                break;
            case R.id.buttonEdit:
                if(idSVien == -1)
                    Toast.makeText(this, "Vui lòng chọn sinh viên muốn sửa", Toast.LENGTH_SHORT).show();
                else {
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", idSVien);
                    intent.putExtra("data", bundle);
                    startActivity(intent);
                }
                break;
        }
    }

    protected void onResume(){
        super.onResume();

        SinhVienDAO dao = new SinhVienDAO(this);
        List<SinhVien> listUpdate = dao.getAll();

        list.clear();
        listUpdate.forEach(item->list.add(item));

        adapter_335.notifyDataSetChanged();
     }
}