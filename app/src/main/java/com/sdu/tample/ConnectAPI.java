package com.sdu.tample;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sdu.tample.model.ModelTemple;
import com.sdu.tample.model.ModelTempleDetail;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ConnectAPI {
        String URL = "http://temple.ddnsking.com/temple/public";
//    String URL = "http://192.168.1.52/temple/public";

    public void getTempleAll(final Context mContext, final int ID) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url(URL + "/api/province/temple/" + ID)
                        .get()
                        .addHeader("cache-control", "no-cache")
                        .addHeader("postman-token", "a8bf0dca-f75d-ba16-329e-54be1d474ee0")
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        return response.body().string();
                    } else {
                        return "Not Success - code : " + response.code();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Error - " + e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String string) {
                super.onPostExecute(string);
                Log.d("ConnectAPI : ", "getTemple " + string);
                String[] temp = string.split(" ");
                if (temp[0].equals("Error") || temp[0].equals("Not")) {
                    dialogErrorNoIntent(mContext, string);
                } else if (string.equals("[]")) {
                    dialogNotfound(mContext);
                } else {
                    ((KaowatActivity) mContext).setAdap(string, URL);
                }
            }
        }.execute();
    }

    public void getProvinceAll(final Context mContext) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url(URL + "/api/province")
                        .get()
                        .addHeader("cache-control", "no-cache")
                        .addHeader("postman-token", "a8bf0dca-f75d-ba16-329e-54be1d474ee0")
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        return response.body().string();
                    } else {
                        return "Not Success - code : " + response.code();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Error - " + e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String string) {
                super.onPostExecute(string);
                Log.d("ConnectAPI : ", "getProvinceAll" + string);
                String[] temp = string.split(" ");
                if (temp[0].equals("Error") || temp[0].equals("Not")) {
                    dialogErrorNoIntent(mContext, string);
                } else if (string.equals("[]")) {
                    dialogNotfound(mContext);
                } else {
                    ((ProvinceActivity) mContext).setAdap(string, URL);
                }
            }
        }.execute();
    }

    public void getTempleMap(final Context mContext, final GoogleMap mMap, final int ID) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url(URL + "/api/province/temple/map/" + ID)
                        .get()
                        .addHeader("cache-control", "no-cache")
                        .addHeader("postman-token", "a8bf0dca-f75d-ba16-329e-54be1d474ee0")
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        return response.body().string();
                    } else {
                        return "Not Success - code : " + response.code();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Error - " + e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String string) {
                super.onPostExecute(string);
                Log.d("ConnectAPI : ", "getTempleMap " + string);
                String[] temp = string.split(" ");
                if (temp[0].equals("Error") || temp[0].equals("Not")) {
                    dialogErrorNoIntent(mContext, string);
                } else if (string.equals("[]")) {
                    dialogNotfound(mContext);
                } else {
                    ((MapWatActivity) mContext).addMarker(mMap, string, URL);
                    ((MapWatActivity) mContext).setAdap(string, URL);
                }
            }
        }.execute();
    }

    public void getVehicleCat(final Context mContext) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url(URL + "/api/vehicle/cat")
                        .get()
                        .addHeader("cache-control", "no-cache")
                        .addHeader("postman-token", "a8bf0dca-f75d-ba16-329e-54be1d474ee0")
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        return response.body().string();
                    } else {
                        return "Not Success - code : " + response.code();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Error - " + e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String string) {
                super.onPostExecute(string);
                Log.d("ConnectAPI : ", "getTempleMap " + string);
                String[] temp = string.split(" ");
                if (temp[0].equals("Error") || temp[0].equals("Not")) {
                    dialogErrorNoIntent(mContext, string);
                } else {
                    ((VehicleCatActivity) mContext).setAdap(string, URL);
                }
            }
        }.execute();
    }

    public void getVehicleCatId(final Context mContext, final int id, final int idPro) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url(URL + "/api/province/vehicle/" + id + "/" + idPro)
                        .get()
                        .addHeader("cache-control", "no-cache")
                        .addHeader("postman-token", "a8bf0dca-f75d-ba16-329e-54be1d474ee0")
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        return response.body().string();
                    } else {
                        return "Not Success - code : " + response.code();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Error - " + e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String string) {
                super.onPostExecute(string);
                Log.d("ConnectAPI : ", "getTempleMap " + string);
                String[] temp = string.split(" ");
                if (temp[0].equals("Error") || temp[0].equals("Not")) {
                    dialogErrorNoIntent(mContext, string);
                } else if (string.equals("[]")) {
                    dialogNotfound(mContext);
                } else {
                    ((VehicleActivity) mContext).setAdap(string, URL);
                }
            }
        }.execute();
    }

    public void getVehicleAll(final Context mContext) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url(URL + "/api/vehicle")
                        .get()
                        .addHeader("cache-control", "no-cache")
                        .addHeader("postman-token", "a8bf0dca-f75d-ba16-329e-54be1d474ee0")
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        return response.body().string();
                    } else {
                        return "Not Success - code : " + response.code();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Error - " + e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String string) {
                super.onPostExecute(string);
                Log.d("ConnectAPI : ", "getTemple " + string);
                String[] temp = string.split(" ");
                if (temp[0].equals("Error") || temp[0].equals("Not")) {
                    dialogErrorNoIntent(mContext, string);
                } else {
                    ((VehicleActivity) mContext).setAdap(string, URL);
                }
            }
        }.execute();
    }

    public void getActivitiesSearch(final Context mContext, final String id, final int idPro) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                OkHttpClient client = new OkHttpClient();

                MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
                RequestBody body = RequestBody.create(mediaType, "data=" + id + "&province=" + idPro);
                Request request = new Request.Builder()
                        .url(URL + "/api/province/activities/search")
                        .post(body)
                        .addHeader("content-type", "application/x-www-form-urlencoded")
                        .addHeader("cache-control", "no-cache")
                        .addHeader("postman-token", "b4a0b8aa-a9c7-b1ee-5f0f-6949a5d34b1c")
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        return response.body().string();
                    } else {
                        return "Not Success - code : " + response.code();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Error - " + e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String string) {
                super.onPostExecute(string);
                Log.d("ConnectAPI : ", "getActivitiesSearch " + string);
                String[] temp = string.split(" ");
                if (temp[0].equals("Error") || temp[0].equals("Not")) {
                    dialogErrorNoIntent(mContext, string);
                } else if (string.equals("[]")) {
                    dialogNotfound(mContext);
                } else {
                    ((KaowatActivity2) mContext).setAdap(string, URL);
                }
            }
        }.execute();
    }

    public void getNewsAll(final Context mContext, final int ID) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url(URL + "/api/province/news/" + ID)
                        .get()
                        .addHeader("cache-control", "no-cache")
                        .addHeader("postman-token", "a8bf0dca-f75d-ba16-329e-54be1d474ee0")
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        return response.body().string();
                    } else {
                        return "Not Success - code : " + response.code();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Error - " + e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String string) {
                super.onPostExecute(string);
                Log.d("ConnectAPI : ", "getTemple " + string);
                String[] temp = string.split(" ");
                if (temp[0].equals("Error") || temp[0].equals("Not")) {
                    dialogErrorNoIntent(mContext, string);
                } else if (string.equals("[]")) {
                    dialogNotfound(mContext);
                } else {
                    ((NewsActivity) mContext).setAdap(string, URL);
                }
            }
        }.execute();
    }

    public void getVehicleId(final Context mContext, final int id) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url(URL + "/api/vehicle/" + id)
                        .get()
                        .addHeader("cache-control", "no-cache")
                        .addHeader("postman-token", "a8bf0dca-f75d-ba16-329e-54be1d474ee0")
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        return response.body().string();
                    } else {
                        return "Not Success - code : " + response.code();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Error - " + e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String string) {
                super.onPostExecute(string);
                Log.d("ConnectAPI : ", "getTemple " + string);
                String[] temp = string.split(" ");
                if (temp[0].equals("Error") || temp[0].equals("Not")) {
                    dialogErrorNoIntent(mContext, string);
                } else {
                    ((DesVehicleActivity) mContext).setView(string, URL);
                }
            }
        }.execute();
    }

    public void getLitaniesId(final Context mContext, final int id) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url(URL + "/api/litanies/" + id)
                        .get()
                        .addHeader("cache-control", "no-cache")
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        return response.body().string();
                    } else {
                        return "Not Success - code : " + response.code();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Error - " + e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String string) {
                super.onPostExecute(string);
                Log.d("ConnectAPI : ", "getLitaniesId " + string);
                String[] temp = string.split(" ");
                if (temp[0].equals("Error") || temp[0].equals("Not")) {
                    dialogErrorNoIntent(mContext, string);
                } else {
                    ((TempleActivity) mContext).dialog_(string);
                }
            }
        }.execute();
    }

    public void getNewsId(final Context mContext, final int id) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url(URL + "/api/news/" + id)
                        .get()
                        .addHeader("cache-control", "no-cache")
                        .addHeader("postman-token", "a8bf0dca-f75d-ba16-329e-54be1d474ee0")
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        return response.body().string();
                    } else {
                        return "Not Success - code : " + response.code();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Error - " + e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String string) {
                super.onPostExecute(string);
                Log.d("ConnectAPI : ", "getNewsId " + string);
                String[] temp = string.split(" ");
                if (temp[0].equals("Error") || temp[0].equals("Not")) {
                    dialogErrorNoIntent(mContext, string);
                } else {
                    ((DetailNewsActivity) mContext).setView(string, URL);
                }
            }
        }.execute();
    }

    public void getQuizAll(final Context mContext) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url(URL + "/api/quiz")
                        .get()
                        .addHeader("cache-control", "no-cache")
                        .addHeader("postman-token", "a8bf0dca-f75d-ba16-329e-54be1d474ee0")
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        return response.body().string();
                    } else {
                        return "Not Success - code : " + response.code();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Error - " + e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String string) {
                super.onPostExecute(string);
                Log.d("ConnectAPI : ", "getQuizAll " + string);
                String[] temp = string.split(" ");
                if (temp[0].equals("Error") || temp[0].equals("Not")) {
                    dialogErrorNoIntent(mContext, string);
                } else {
                    ((QuizActivity) mContext).setQuiz(string);
                }
            }
        }.execute();
    }

    public void getHotAll(final Context mContext, final GoogleMap mMap, final int ID) {

        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url(URL + "/api/province/hot/" + ID)
                        .get()
                        .addHeader("cache-control", "no-cache")
                        .addHeader("postman-token", "a8bf0dca-f75d-ba16-329e-54be1d474ee0")
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        return response.body().string();
                    } else {
                        return "Not Success - code : " + response.code();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Error - " + e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String string) {
                super.onPostExecute(string);
                Log.d("ConnectAPI : ", "getQuizAll " + string);
                String[] temp = string.split(" ");
                if (temp[0].equals("Error") || temp[0].equals("Not")) {
                    dialogErrorNoIntent(mContext, string);
                } else if (string.equals("[]")) {
                    dialogNotfound(mContext);
                } else {
                    ((MapHotActivity) mContext).addMarker(mMap, string, URL);
                }
            }
        }.execute();

    }

    public void getActivitiesAll(final Context mContext) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url(URL + "/api/activities")
                        .get()
                        .addHeader("cache-control", "no-cache")
                        .addHeader("postman-token", "a8bf0dca-f75d-ba16-329e-54be1d474ee0")
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        return response.body().string();
                    } else {
                        return "Not Success - code : " + response.code();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Error - " + e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String string) {
                super.onPostExecute(string);
                Log.d("ConnectAPI : ", "getActivitiesAll " + string);
                String[] temp = string.split(" ");
                if (temp[0].equals("Error") || temp[0].equals("Not")) {
                    dialogErrorNoIntent(mContext, string);
                } else {
                    ((ActivitiesActivity) mContext).setAdap(string);
                }
            }
        }.execute();
    }

    public void getImgTempleAll(final Context mContext, final int ID) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url(URL + "/api/province/temple/image/" + ID)
                        .get()
                        .addHeader("cache-control", "no-cache")
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        return response.body().string();
                    } else {
                        return "Not Success - code : " + response.code();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Error - " + e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String string) {
                super.onPostExecute(string);
                Log.d("ConnectAPI : ", "getImgTempleAll " + string);
                String[] temp = string.split(" ");
                if (temp[0].equals("Error") || temp[0].equals("Not")) {
                    dialogErrorNoIntent(mContext, string);
                } else if (string.equals("[]")) {
                    dialogNotfound2(mContext);
                } else {
                    ((MainActivity) mContext).setHeader(string, URL);
                }
            }
        }.execute();
    }

    public void getTempleId(final Context mContext, final int id) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url(URL + "/api/temple/" + id)
                        .get()
                        .addHeader("cache-control", "no-cache")
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        return response.body().string();
                    } else {
                        return "Not Success - code : " + response.code();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Error - " + e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String string) {
                super.onPostExecute(string);
                Log.d("ConnectAPI : ", "getTempleId " + string);
                String[] temp = string.split(" ");
                if (temp[0].equals("Error") || temp[0].equals("Not")) {
                    dialogErrorNoIntent(mContext, string);
                } else {
                    ((TempleActivity) mContext).setView(string, URL);
//                    new GsonBuilder().create().toJson(new Gson().fromJson(string, ModelTempleDetail.class))
                }
            }
        }.execute();
    }

    public void getMapTempleAll(final Context mContext) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url(URL + "/api/temple")
                        .get()
                        .addHeader("cache-control", "no-cache")
                        .addHeader("postman-token", "a8bf0dca-f75d-ba16-329e-54be1d474ee0")
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        return response.body().string();
                    } else {
                        return "Not Success - code : " + response.code();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Error - " + e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String string) {
                super.onPostExecute(string);
                Log.d("ConnectAPI : ", "getTemple " + string);
                String[] temp = string.split(" ");
                if (temp[0].equals("Error") || temp[0].equals("Not")) {
                    dialogErrorNoIntent(mContext, string);
                } else {
                    ((MapActivity) mContext).setAdap(string, URL);
                }
            }
        }.execute();
    }

    private static void dialogError(final Context mContext, String string) {
        new AlertDialog.Builder(mContext)
                .setTitle("Failed")
                .setMessage("ไม่พบข้อมูล กรุณาลองใหม่ภายหลัง error code = " + string)
                .setNegativeButton("OK", null)
                .show();
    }

    private static void NoApart(final Context mContext) {
        new AlertDialog.Builder(mContext)
                .setTitle("Failed")
                .setMessage("ขออภัย ท่านยังไม่มีหอพัก หรือกรุณาลองใหม่อีกครั้ง")
                .setNegativeButton("OK", null)
                .show();
    }

    private static void ActiveError1(final Context mContext) {
        new AlertDialog.Builder(mContext)
                .setTitle("Failed")
                .setMessage("ขออภัย ท่านไม่สามารถส่งซ้ำได้")
                .setNegativeButton("OK", null)
                .show();
    }

    private static void ActiveError2(final Context mContext) {
        new AlertDialog.Builder(mContext)
                .setTitle("Failed")
                .setMessage("ขออภัย รหัสยืนยันไม่ถูกต้องกรุณาลองใหม่อีกครั้ง")
                .setNegativeButton("OK", null)
                .show();
    }

    private static void ActiveError3(final Context mContext) {
        new AlertDialog.Builder(mContext)
                .setTitle("Failed")
                .setMessage("ขออภัย รหัสยืนยันถูกใช้งานแล้ว")
                .setNegativeButton("OK", null)
                .show();
    }


    private static void dialogErrorNoIntent(final Context mContext, String string) {
        new AlertDialog.Builder(mContext)
                .setTitle("The system temporarily")
                .setMessage("ไม่สามารถเข้าใช้งานได้ กรุณาลองใหม่ภายหลัง error code = " + string)
                .setNegativeButton("OK", null)
                .show();
    }

    private static void dialogNotfound(final Context mContext) {
        new AlertDialog.Builder(mContext)
                .setTitle("Not Found")
                .setMessage("ไม่พบข้อมูล")
                .setNegativeButton("OK", null)
                .show();
    }

    private static void dialogNotfound2(final Context mContext) {
        new AlertDialog.Builder(mContext)
                .setTitle("Not Found")
                .setMessage("ไม่พบข้อมูล กรุณาลองใหม่ภายหลัง")
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                        ((MainActivity)mContext).finish();
                    }
                })
                .show();
    }

}
