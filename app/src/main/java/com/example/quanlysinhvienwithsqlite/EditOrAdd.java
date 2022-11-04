package com.example.quanlysinhvienwithsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditOrAdd extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextID_335, editTextName_335, editTextNamSinh_335;
    private RadioButton rbtnNam, rbtnNu;
    private Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_or_add);

        editTextID_335 = findViewById(R.id.editTextNumberMSV2);
        editTextName_335 = findViewById(R.id.editTextTextPersonName4);
        editTextNamSinh_335 = findViewById(R.id.editTextNumberNamSinh);
        rbtnNam = findViewById(R.id.radioButtonNam);
        rbtnNu = findViewById(R.id.radioButtonNu);
        btnSave = findViewById(R.id.buttonSave);
        btnSave.setOnClickListener(this);
        findViewById(R.id.buttonBack).setOnClickListener(this);

        readSinhVien();
    }

    private void readSinhVien(){
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        if(bundle == null)
            return;

        int id = bundle.getInt("id");

        SinhVienDAO svDAO = new SinhVienDAO(this);
        SinhVien sinhVien = svDAO.getSVbyId(id);

        String gioiTinh = sinhVien.getGioiTinhSV();

        editTextID_335.setText(String.valueOf(sinhVien.getIdSV()));
        editTextName_335.setText(sinhVien.getNameSV());
        editTextNamSinh_335.setText(String.valueOf(sinhVien.getNamSinhSV()));
        if(gioiTinh != null && gioiTinh.equals("Nam"))
            rbtnNam.setChecked(true);
        else
            rbtnNu.setChecked(true);

        btnSave.setText("Lưu thay đổi");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonSave:
                SinhVienDAO svDAO = new SinhVienDAO(this);
                SinhVien sv = new SinhVien();

                sv.setIdSV(Integer.valueOf(editTextID_335.getText().toString()));
                sv.setNameSV(editTextName_335.getText().toString());
                if(rbtnNam.isChecked())
                    sv.setGioiTinhSV("Nam");
                else if(rbtnNu.isChecked())
                    sv.setGioiTinhSV("Nữ");
                sv.setNamSinhSV(Integer.valueOf(editTextNamSinh_335.getText().toString()));

                if(btnSave.getText().equals("Lưu"))
                {
                    svDAO.insert(sv);
                    editTextID_335.setText("");
                    editTextName_335.setText("");
                    editTextNamSinh_335.setText("");
                    rbtnNam.setChecked(false);
                    rbtnNu.setChecked(false);
                }
                else
                svDAO.update(sv);

                Toast.makeText(this,"Lưu thành công", Toast.LENGTH_SHORT).show();

                break;
            case R.id.buttonBack:
                Intent intentList = new Intent(this, MainActivity.class);
                startActivity(intentList);
        }
    }
}