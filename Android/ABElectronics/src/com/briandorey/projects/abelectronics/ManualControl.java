package com.briandorey.projects.abelectronics;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ManualControl  extends Fragment {
	
	 TextView myLabel; 
     EditText myTextbox;
     BluetoothAdapter mBluetoothAdapter;
     BluetoothSocket mmSocket;
     BluetoothDevice mmDevice;
     OutputStream mmOutputStream;
     InputStream mmInputStream;
     Thread workerThread;
     byte[] readBuffer;
     int readBufferPosition;
     int counter;
     volatile boolean stopWorker;
     
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";

	/**
	 * The dummy content this fragment is presenting.
	 */


	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public ManualControl() {
		
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//if (getArguments().containsKey(ARG_ITEM_ID)) {
			// Load the dummy content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
		//	mItem = BoardContent.ITEM_MAP.get(getArguments().getString(
			//		ARG_ITEM_ID));
		//}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.manualcontrol,
				container, false);

		// Show the dummy content as text in a TextView.
		//if (mItem != null) {
		//	((TextView) rootView.findViewById(R.id.item_detail))
		//			.setText(mItem.content);
		//}
		
		 Button actionButton01 = (Button)rootView.findViewById(R.id.Button01);
         Button actionButton02 = (Button)rootView.findViewById(R.id.Button02);
         Button actionButton03 = (Button)rootView.findViewById(R.id.Button03);
         Button actionButton04 = (Button)rootView.findViewById(R.id.Button04);
         Button actionButton05 = (Button)rootView.findViewById(R.id.Button05);
         Button actionButton06 = (Button)rootView.findViewById(R.id.Button06);
         Button actionButton07 = (Button)rootView.findViewById(R.id.Button07);
         Button actionButton08 = (Button)rootView.findViewById(R.id.Button08);
         Button actionButton09 = (Button)rootView.findViewById(R.id.Button09);
         Button actionButton10 = (Button)rootView.findViewById(R.id.Button10);
         Button actionButton11 = (Button)rootView.findViewById(R.id.Button11);
         Button actionButton12 = (Button)rootView.findViewById(R.id.Button12);
         Button actionButton13 = (Button)rootView.findViewById(R.id.Button13);
         Button actionButton14 = (Button)rootView.findViewById(R.id.Button14);
         Button actionButton15 = (Button)rootView.findViewById(R.id.Button15);
         
         
         Button actionButtonLeft = (Button)rootView.findViewById(R.id.ButtonLeft);
         
         Button actionButtonRight = (Button)rootView.findViewById(R.id.ButtonRight);
         
         
         actionButton01.setOnClickListener(new View.OnClickListener()
         {
             public void onClick(View v)
             {
                Activity activity123 = getActivity();
				if(activity123 instanceof ItemListActivity) {
				    try {
						((ItemListActivity) activity123).sendData("1");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
             }
         });
         actionButton02.setOnClickListener(new View.OnClickListener()
         {
             public void onClick(View v)
             {
            	 Activity activity123 = getActivity();
 				if(activity123 instanceof ItemListActivity) {
 				    try {
 						((ItemListActivity) activity123).sendData("2");
 					} catch (IOException e) {
 						e.printStackTrace();
 					}
 				}
             }
         });
         actionButton03.setOnClickListener(new View.OnClickListener()
         {
             public void onClick(View v)
             {
            	 Activity activity123 = getActivity();
 				if(activity123 instanceof ItemListActivity) {
 				    try {
 						((ItemListActivity) activity123).sendData("3");
 					} catch (IOException e) {
 						e.printStackTrace();
 					}
 				}
             }
         });
         actionButton04.setOnClickListener(new View.OnClickListener()
         {
             public void onClick(View v)
             {
            	 Activity activity123 = getActivity();
 				if(activity123 instanceof ItemListActivity) {
 				    try {
 						((ItemListActivity) activity123).sendData("4");
 					} catch (IOException e) {
 						e.printStackTrace();
 					}
 				}
             }
         });
         actionButton05.setOnClickListener(new View.OnClickListener()
         {
             public void onClick(View v)
             {
            	 Activity activity123 = getActivity();
 				if(activity123 instanceof ItemListActivity) {
 				    try {
 						((ItemListActivity) activity123).sendData("5");
 					} catch (IOException e) {
 						e.printStackTrace();
 					}
 				}
             }
         });
         actionButton06.setOnClickListener(new View.OnClickListener()
         {
             public void onClick(View v)
             {
            	 Activity activity123 = getActivity();
 				if(activity123 instanceof ItemListActivity) {
 				    try {
 						((ItemListActivity) activity123).sendData("6");
 					} catch (IOException e) {
 						e.printStackTrace();
 					}
 				}
             }
         });
         actionButton07.setOnClickListener(new View.OnClickListener()
         {
             public void onClick(View v)
             {
            	 Activity activity123 = getActivity();
 				if(activity123 instanceof ItemListActivity) {
 				    try {
 						((ItemListActivity) activity123).sendData("7");
 					} catch (IOException e) {
 						e.printStackTrace();
 					}
 				}
             }
         });
         actionButton08.setOnClickListener(new View.OnClickListener()
         {
             public void onClick(View v)
             {
            	 Activity activity123 = getActivity();
 				if(activity123 instanceof ItemListActivity) {
 				    try {
 						((ItemListActivity) activity123).sendData("8");
 					} catch (IOException e) {
 						e.printStackTrace();
 					}
 				}
             }
         });
         actionButton09.setOnClickListener(new View.OnClickListener()
         {
             public void onClick(View v)
             {
            	 Activity activity123 = getActivity();
 				if(activity123 instanceof ItemListActivity) {
 				    try {
 						((ItemListActivity) activity123).sendData("9");
 					} catch (IOException e) {
 						e.printStackTrace();
 					}
 				}
             }
         });
         actionButton10.setOnClickListener(new View.OnClickListener()
         {
             public void onClick(View v)
             {
            	 Activity activity123 = getActivity();
 				if(activity123 instanceof ItemListActivity) {
 				    try {
 						((ItemListActivity) activity123).sendData("a");
 					} catch (IOException e) {
 						e.printStackTrace();
 					}
 				}
             }
         });
         actionButton11.setOnClickListener(new View.OnClickListener()
         {
             public void onClick(View v)
             {
            	 Activity activity123 = getActivity();
 				if(activity123 instanceof ItemListActivity) {
 				    try {
 						((ItemListActivity) activity123).sendData("b");
 					} catch (IOException e) {
 						e.printStackTrace();
 					}
 				}
             }
         });
         actionButton12.setOnClickListener(new View.OnClickListener()
         {
             public void onClick(View v)
             {
            	 Activity activity123 = getActivity();
 				if(activity123 instanceof ItemListActivity) {
 				    try {
 						((ItemListActivity) activity123).sendData("c");
 					} catch (IOException e) {
 						e.printStackTrace();
 					}
 				}
             }
         });
         actionButton13.setOnClickListener(new View.OnClickListener()
         {
             public void onClick(View v)
             {
            	 Activity activity123 = getActivity();
 				if(activity123 instanceof ItemListActivity) {
 				    try {
 						((ItemListActivity) activity123).sendData("d");
 					} catch (IOException e) {
 						e.printStackTrace();
 					}
 				}
             }
         });
         actionButton14.setOnClickListener(new View.OnClickListener()
         {
             public void onClick(View v)
             {
            	 Activity activity123 = getActivity();
 				if(activity123 instanceof ItemListActivity) {
 				    try {
 						((ItemListActivity) activity123).sendData("e");
 					} catch (IOException e) {
 						e.printStackTrace();
 					}
 				}
             }
         });
         actionButton15.setOnClickListener(new View.OnClickListener()
         {
             public void onClick(View v)
             {
            	 Activity activity123 = getActivity();
 				if(activity123 instanceof ItemListActivity) {
 				    try {
 						((ItemListActivity) activity123).sendData("f");
 					} catch (IOException e) {
 						e.printStackTrace();
 					}
 				}
             }
         });
         
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

		return rootView;
	}
}
