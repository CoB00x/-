package com.example.pr9_zubarev;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String FILE_NAME_KEY = "file_name_key";
    private static final String FILE_CONTENT_KEY = "file_content_key";

    private EditText fileNameEditText;
    private EditText fileContentEditText;
    private TextView outputTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fileNameEditText = findViewById(R.id.filename_edittext);
        fileContentEditText = findViewById(R.id.content_edittext);
        Button createButton = findViewById(R.id.save_button);
        Button appendButton = findViewById(R.id.append_button);
        Button readButton = findViewById(R.id.read_button);
        Button deleteButton = findViewById(R.id.delete_button);
        outputTextView = findViewById(R.id.output_textview);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fileName = fileNameEditText.getText().toString();
                String fileContent = fileContentEditText.getText().toString();
                try {
                    FileOutputStream fos = openFileOutput(fileName, Context.MODE_PRIVATE);
                    fos.write(fileContent.getBytes());
                    fos.close();
                    Toast.makeText(MainActivity.this, "Файл создан", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        appendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fileName = fileNameEditText.getText().toString();
                String fileContent = fileContentEditText.getText().toString();
                try {
                    FileOutputStream fos = openFileOutput(fileName, Context.MODE_APPEND);
                    fos.write(fileContent.getBytes());
                    fos.close();
                    Toast.makeText(MainActivity.this, "Информация добавлена", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fileName = fileNameEditText.getText().toString();
                File directory = getFilesDir();
                File file = new File(directory, fileName);

                if (file.exists()) {
                    try {
                        FileInputStream fis = openFileInput(fileName);
                        byte[] bytes = new byte[fis.available()];
                        fis.read(bytes);
                        fis.close();
                        String fileContent = new String(bytes);
                        outputTextView.setText(fileContent);
                        Toast.makeText(MainActivity.this, "Файл прочитан", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Файл не существует", Toast.LENGTH_SHORT).show();
                    outputTextView.setText("");
                }
            }
        });


        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText fileNameEditText = findViewById(R.id.filename_edittext);
                String fileName = fileNameEditText.getText().toString();

                File directory = getFilesDir();
                File file = new File(directory, fileName);

                if (file.exists()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Удаление файла");
                    builder.setMessage("Вы действительно хотите удалить файл " + fileName + "?");
                    builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            boolean success = file.delete();
                            if (success) {
                                Toast.makeText(MainActivity.this,
                                        "Файл удален", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MainActivity.this,
                                        "Не удалось удалить файл", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else {
                    Toast.makeText(MainActivity.this, "Файл не существует", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}