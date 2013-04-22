package com.briandorey.projects.abelectronics;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.briandorey.projects.abelectronics.dummy.BoardComponents;
import com.briandorey.projects.abelectronics.dummy.BoardContent;

/**
 * A fragment representing a single Item detail screen. This fragment is either
 * contained in a {@link ItemListActivity} in two-pane mode (on tablets) or a
 * {@link ItemDetailActivity} on handsets.
 */


public class ItemDetailFragment extends Fragment  implements OnItemClickListener {
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	
	ArrayList<Integer> feederList  = new ArrayList<Integer>();
	int currentComponent = 0;
	
	
	public static final String ARG_ITEM_ID = "item_id";
	Fragment randomnameFragment;
	/**
	 * The dummy content this fragment is presenting.
	 */
	private BoardContent.BoardItem mItem;
	
	private BoardComponents.Components mComponent;
	
	ListView mylistview;
    ArrayAdapter<String> listAdapter;
    TextView tvTitle;
    TextView tvSubTitle;
    ImageView imageView;
    String [] items;
    String [] rotations;
    String imagenameprefix;
	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public ItemDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ARG_ITEM_ID)) {
			// Load the dummy content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			mItem = BoardContent.ITEM_MAP.get(getArguments().getString(
					ARG_ITEM_ID));
			mComponent = BoardComponents.ITEM_MAP.get(getArguments().getString(
					ARG_ITEM_ID));
		}
		
		feederList.add(R.drawable.signaldiode); 	// 1
		feederList.add(R.drawable.dualfet); 		// 2
		feederList.add(R.drawable.singlefet); 		// 3 
		feederList.add(R.drawable.cap10uf); 		// 4
		feederList.add(R.drawable.cap100nf); 		// 5
		feederList.add(R.drawable.res10k); 			// 6
		feederList.add(R.drawable.res6k8); 			// 7
		feederList.add(R.drawable.res1k);			// 8
		feederList.add(R.drawable.res100r);			// 9
		feederList.add(R.drawable.res10kx4);		// 10
		feederList.add(R.drawable.res2k2x4);		// 11

		//if(sharedData.getConnectionStatus() == true){
		
		//	sharedData.setConnectionStatus(false);
		//}
        
        
        
        
	}
	public void UpdateToast(String str) {
		if (str.contains("o")) {
			// rotation code to go here
			rotations = mComponent.rotation.split(",");
			if (rotations.length > currentComponent) { 
			Activity activity123 = getActivity();
			if(activity123 instanceof ItemListActivity) {
				
				    try {
				    	if (!rotations[currentComponent].equals("0")) {
				    		((ItemListActivity) activity123).sendData(String.valueOf(rotations[currentComponent]));
				    	}
					} catch (IOException e) {
						e.printStackTrace();
					}
				
			}
			// set value for next component
			currentComponent++;
			NextComponent();
			}
		}
		Toast.makeText(this.getActivity(), str, Toast.LENGTH_SHORT).show();
		//currentComponent
	}
	public void NextComponent() {
		items = mComponent.content.split(",");
		
		if (currentComponent < items.length){
			LoadComponent(currentComponent);
		}
		
	}
	public void LoadComponent(int activecomponent) {
		items = mComponent.content.split(",");
		tvSubTitle.setText(items[currentComponent]);
		imageView.setImageDrawable(null);
		
		//int currentcomponentimage = items[currentComponent]);
		String imagename = imagenameprefix + items[currentComponent] + ".png";
		
		try 
		{
		    // get input stream
		    InputStream ims = ((ContextWrapper) getActivity()).getAssets().open(imagename);
		    // load image as Drawable
		    Drawable d = Drawable.createFromStream(ims, null);
		    // set image to ImageView
		    imageView.setImageDrawable(d);
		}
		catch(IOException ex) 
		{
		    
		}
		
		
		
		//imageView.setImageResource(resID);
		
		
		//imageView.setBackgroundResource(feederList.get(currentcomponentimage-1));
		
		
		Activity activity123 = getActivity();
		if(activity123 instanceof ItemListActivity) {
		    try {
				((ItemListActivity) activity123).sendData(items[currentComponent]);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		
		 
		View rootView = inflater.inflate(R.layout.fragment_item_detail,
				container, false);
		// setup direction buttons and spinner
		Button actionButtonLeft = (Button)rootView.findViewById(R.id.ButtonLeft);
        
        Button actionButtonRight = (Button)rootView.findViewById(R.id.ButtonRight);
        
        Button actionButtonStart = (Button)rootView.findViewById(R.id.ButtonStart);
        
        actionButtonLeft.setOnClickListener(new View.OnClickListener()
        {
       	 public void onClick(View v)
            {
           	 Activity activity123 = getActivity();
				if(activity123 instanceof ItemListActivity) {
				    try {
						((ItemListActivity) activity123).sendData(",");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
            }
        });
        
        actionButtonRight.setOnClickListener(new View.OnClickListener()
        {
       	 public void onClick(View v)
            {
           	 Activity activity123 = getActivity();
				if(activity123 instanceof ItemListActivity) {
				    try {
						((ItemListActivity) activity123).sendData(".");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
            }
        });
        
        actionButtonStart.setOnClickListener(new View.OnClickListener()
        {
       	 public void onClick(View v)
            {
           	// start new board
       		currentComponent = 0;
       		LoadComponent(currentComponent);
            }
        });
        
        RotaryKnobView jogView = (RotaryKnobView)rootView.findViewById(R.id.jogView);
		jogView.setKnobListener(new RotaryKnobView.RotaryKnobListener() {
			@Override
			public void onKnobChanged(float delta, float angle) {
				//((TextView)MainActivity.this.findViewById(R.id.jogDelta)).setText("" + delta);
				//((TextView)MainActivity.this.findViewById(R.id.jogAngle)).setText("" + angle);
				if (delta > 0) {
					Activity activity123 = getActivity();
					if(activity123 instanceof ItemListActivity) {
					    try {
							((ItemListActivity) activity123).sendData("k");
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				} else {
					Activity activity123 = getActivity();
					if(activity123 instanceof ItemListActivity) {
					    try {
							((ItemListActivity) activity123).sendData("l");
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}	
		});
		// end setup direction buttons and spinner
        
		tvTitle = (TextView) rootView.findViewById(R.id.item_detail);
		tvSubTitle = (TextView) rootView.findViewById(R.id.sublabel);
		imageView = (ImageView)rootView.findViewById(R.id.imageView1);
		/*
		
		
		*/
		// Show the dummy content as text in a TextView.
		if (mItem != null) {
			//((TextView) rootView.findViewById(R.id.item_detail))
					tvTitle.setText(mItem.content);
					imagenameprefix = mItem.toimageprefix();
		}
		
		//if (mComponent != null) {
			//((TextView) rootView.findViewById(R.id.sublabel))
		//	tvSubTitle.setText(mComponent.content);
		//}
		
		
		String imagename = imagenameprefix + ".png";
		
		try 
		{
		    // get input stream
		    InputStream ims = ((ContextWrapper) getActivity()).getAssets().open(imagename);
		    // load image as Drawable
		    Drawable d = Drawable.createFromStream(ims, null);
		    // set image to ImageView
		    imageView.setImageDrawable(d);
		}
		catch(IOException ex) 
		{
		    
		}
		
		
		//items = mComponent.content.split(",");
		//mylistview = ((ListView) rootView.findViewById(R.id.listView1));
		//listAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, items);
       // mylistview.setAdapter(listAdapter);
       // mylistview.setOnItemClickListener(this);
        
		return rootView;
	}
	

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
		this.tvSubTitle.setText(String.valueOf(position));
		
	}
}
