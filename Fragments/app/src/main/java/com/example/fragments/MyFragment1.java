package com.example.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Darja Kasnikova on 25.04.2015.
 */
public class MyFragment1 extends Fragment {

    private Listener listener;
    List<ListItem> listItems;
    public static final int ADD_TASK_REQUEST = 1;
    CustomAdapter adapter;
    private String title;
    private String description;
    private String url;
    private ListView listView;

    private ProgressDialog pDialog;
    private static String api = "http://demo2989980.mockable.io/api/test";

    /**
     * Static factory method that takes an int parameter,
     * initializes the fragment's arguments, and returns the
     * new fragment to the client.
     *
     * http://www.androiddesignpatterns.com/2012/05/using-newinstance-to-instantiate.html
     */
    public static Fragment newInstance(int id) {
        Fragment fragment = new MyFragment1();
        Bundle args = new Bundle();
        args.putInt("id", id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    // Have to return a view
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.my_fragment_1, container, false);
        Bundle myArgs = getArguments();
        int id = myArgs.getInt("id");
        listItems = new ArrayList<>();

        // Define and set the adapter
        // The fragment can access the Activity intance with getActivity()
        adapter = new CustomAdapter(getActivity(), R.layout.list_item, listItems);

        listView = (ListView) layout.findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get clicked item's URL
                // Send the event and URL to the host activity
                url = listItems.get(position).getUrl();
                listener.onReadyToDoSomething(url);
            }
        });
        return layout;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // A dialog for showing progress dialog with text message
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading data ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);

        // Load and show the dialog here
        pDialog.show();

        // Instantiate the RequestQueue
        RequestQueue queue = Volley.newRequestQueue(getActivity());

        // Request a JsonObject response from the provided URL
        // Parse JsonObject
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, api,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray jsonArray = null;
                        JSONObject jsonObject = null;

                        try {
                            jsonArray = response.getJSONArray("items");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (jsonArray != null) {
                            try {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    jsonObject = jsonArray.getJSONObject(i);
                                    if (jsonObject != null) {

                                        boolean isItemId = true;
                                        try {
                                            Integer.parseInt(jsonObject.getString("item_id"));
                                        } catch (JSONException e) {
                                            //e.printStackTrace();
                                            isItemId = false;
                                        }

                                        if (isItemId){
                                            try {
                                                title = jsonObject.getString("title");
                                                description = jsonObject.getString("description");
                                                url = jsonObject.getString("url");
                                                listItems.add(new ListItem(title, description, url));
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                }

                                adapter.notifyDataSetChanged();

                                // We don't know *exactly* when this will be called.
                                // All we know is that this will be called once the response has been
                                // parsed and added to the list.
                                // Needs to stop it
                                pDialog.dismiss();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        // What ever is here will be the next thing that's called
        // Add the request to the RequestQueue
        queue.add(jsonObjectRequest);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    // Activity calls method onAttach when adding the fragment to the activity.
    // MyFragment1's onAttach() callback method instantiates and instance of Listener
    // by casting the Activity that passed into Attach().
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //cast listener to activity
        if (activity instanceof Listener) {
            listener = (Listener) activity;
        } else {
            throw new ClassCastException("Did you implement Listener?");
        }
    }

    // Container Activity must implement this interface
    public interface Listener {
        void onReadyToDoSomething(String url);
    }
}