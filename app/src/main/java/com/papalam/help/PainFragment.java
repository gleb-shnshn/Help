package com.papalam.help;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.papalam.help.model.PainPoint;
import com.papalam.help.responses.DefaultResponse;

import java.io.ByteArrayOutputStream;
import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PainFragment extends Fragment implements View.OnClickListener {
    public static final int INPUT_FILE_REQUEST_CODE = 1;
    ImageView image;
    EditText descriptionEditText;
    float x, y;
    String uri = "";
    String date = "";

    public void setXY(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pain, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        view.findViewById(R.id.applyPicture).setOnClickListener(this);
        view.findViewById(R.id.approve).setOnClickListener(this);
        image = view.findViewById(R.id.image);
        descriptionEditText = view.findViewById(R.id.descriptionEditText);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void applyImage() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Intent chooserIntent = new Intent(Intent.ACTION_CHOOSER);
        chooserIntent.putExtra(Intent.EXTRA_INTENT, takePictureIntent);
        chooserIntent.putExtra(Intent.EXTRA_TITLE, "Прикрепить изображение");
        startActivityForResult(chooserIntent, INPUT_FILE_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 100, bytes);
            String path = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), photo, "image", null);
            uri = path;
            image.setImageURI(Uri.parse(uri));
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.applyPicture) {
            Permissioner permissioner = new Permissioner(getActivity());
            if (!permissioner.checkPermissionForCamera())
                permissioner.requestPermissionForCamera();
            else {
                applyImage();
            }
        } else {
            Log.d("okhttp", uri);
            File file = new File(getPath(Uri.parse(uri)));
            // Create a request body with file and image media type
            RequestBody fileReqBody = RequestBody.create(MediaType.parse("image/*"), file);
            // Create MultipartBody.Part using file request-body,file name and part name
            MultipartBody.Part part = MultipartBody.Part.createFormData("image", file.getName(), fileReqBody);
            //Create request body with text description and text media type
            App.getInstance().getRetrofit().addPainArea(part, new PainPoint(x, y, descriptionEditText.getText().toString())).enqueue(new Callback<DefaultResponse>() {
                @Override
                public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                    MainActivity activity = ((MainActivity) getActivity());
                    activity.setFragment(App.getInstance().getDataHandler().getData("date"), new AllPainFragment());
                }

                @Override
                public void onFailure(Call<DefaultResponse> call, Throwable t) {
                    App.getInstance().getUtils().showError(t.toString());
                }
            });
        }
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().managedQuery(uri, projection, null, null, null);
        getActivity().startManagingCursor(cursor);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
}
