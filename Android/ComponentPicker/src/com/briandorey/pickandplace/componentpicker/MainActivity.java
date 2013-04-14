package com.briandorey.pickandplace.componentpicker;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;  
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;


    public class MainActivity extends Activity
    {
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
       // dev private static String address = "00:12:02:06:92:32";
        private static String address = "00:13:03:18:05:40";
       
        @Override
        public void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.newmain);
           
            // setup buttons
            Button actionButton01 = (Button)findViewById(R.id.Button01);
            Button actionButton02 = (Button)findViewById(R.id.Button02);
            Button actionButton03 = (Button)findViewById(R.id.Button03);
            Button actionButton04 = (Button)findViewById(R.id.Button04);
            Button actionButton05 = (Button)findViewById(R.id.Button05);
            Button actionButton06 = (Button)findViewById(R.id.Button06);
            Button actionButton07 = (Button)findViewById(R.id.Button07);
            Button actionButton08 = (Button)findViewById(R.id.Button08);
            Button actionButton09 = (Button)findViewById(R.id.Button09);
            Button actionButton10 = (Button)findViewById(R.id.Button10);
            Button actionButton11 = (Button)findViewById(R.id.Button11);
            Button actionButton12 = (Button)findViewById(R.id.Button12);
            Button actionButton13 = (Button)findViewById(R.id.Button13);
            Button actionButton14 = (Button)findViewById(R.id.Button14);
            Button actionButton15 = (Button)findViewById(R.id.Button15);
            
            Button actionButtonLeft = (Button)findViewById(R.id.ButtonLeft);
            
            Button actionButtonRight = (Button)findViewById(R.id.ButtonRight);
            
            actionButton01.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    try 
                    {
                        sendData("1");
                    }
                    catch (IOException ex) { }
                }
            });
            actionButton02.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    try 
                    {
                        sendData("2");
                    }
                    catch (IOException ex) { }
                }
            });
            actionButton03.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    try 
                    {
                        sendData("3");
                    }
                    catch (IOException ex) { }
                }
            });
            actionButton04.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    try 
                    {
                        sendData("4");
                    }
                    catch (IOException ex) { }
                }
            });
            actionButton05.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    try 
                    {
                        sendData("5");
                    }
                    catch (IOException ex) { }
                }
            });
            actionButton06.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    try 
                    {
                        sendData("6");
                    }
                    catch (IOException ex) { }
                }
            });
            actionButton07.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    try 
                    {
                        sendData("7");
                    }
                    catch (IOException ex) { }
                }
            });
            actionButton08.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    try 
                    {
                        sendData("8");
                    }
                    catch (IOException ex) { }
                }
            });
            actionButton09.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    try 
                    {
                        sendData("9");
                    }
                    catch (IOException ex) { }
                }
            });
            actionButton10.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    try 
                    {
                        sendData("a");
                    }
                    catch (IOException ex) { }
                }
            });
            actionButton11.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    try 
                    {
                        sendData("b");
                    }
                    catch (IOException ex) { }
                }
            });
            actionButton12.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    try 
                    {
                        sendData("c");
                    }
                    catch (IOException ex) { }
                }
            });
            actionButton13.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    try 
                    {
                        sendData("d");
                    }
                    catch (IOException ex) { }
                }
            });
            actionButton14.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    try 
                    {
                        sendData("e");
                    }
                    catch (IOException ex) { }
                }
            });
            actionButton15.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    try 
                    {
                        sendData("f");
                    }
                    catch (IOException ex) { }
                }
            });
            
            actionButtonLeft.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    try 
                    {
                        sendData(",");
                    }
                    catch (IOException ex) { }
                }
            });
            
            actionButtonRight.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    try 
                    {
                        sendData(".");
                    }
                    catch (IOException ex) { }
                }
            });
          
           
            RotaryKnobView jogView = (RotaryKnobView)findViewById(R.id.jogView);
    		jogView.setKnobListener(new RotaryKnobView.RotaryKnobListener() {
    			@Override
    			public void onKnobChanged(float delta, float angle) {
    				//((TextView)MainActivity.this.findViewById(R.id.jogDelta)).setText("" + delta);
    				//((TextView)MainActivity.this.findViewById(R.id.jogAngle)).setText("" + angle);
    				if (delta > 0)
    					try 
                    {
       				 sendData("k");
                    }
                    catch (IOException ex) { }
    					
    				else
    					try 
                    {
       				 sendData("l");
                    }
                    catch (IOException ex) { }
    			}	
    		});
           
        }
        
        void findBT()
        {
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if(mBluetoothAdapter == null)
            {
                
                Toast.makeText(getApplicationContext(), "No bluetooth adapter available", Toast.LENGTH_LONG).show();
            }
            
            if(!mBluetoothAdapter.isEnabled())
            {
                Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBluetooth, 0);
            }
            
            Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
            mmDevice = mBluetoothAdapter.getRemoteDevice(address);
            if (pairedDevices.contains(mmDevice))
            {
            //statusText.setText("Bluetooth Device Found, address: " + mmDevice.getAddress() );
            Log.d("ArduinoBT", "BT is paired");
            }
            Toast.makeText(getApplicationContext(), "Bluetooth Device Found", Toast.LENGTH_LONG).show();
         }
        
        void openBT() throws IOException
        {
            UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"); //Standard SerialPortService ID
            mmSocket = mmDevice.createRfcommSocketToServiceRecord(uuid);        
            mmSocket.connect();
            mmOutputStream = mmSocket.getOutputStream();
            mmInputStream = mmSocket.getInputStream();
            
            beginListenForData();
            Toast.makeText(getApplicationContext(), "Bluetooth Opened", Toast.LENGTH_LONG).show();
           
        }
        
        void beginListenForData()
        {
            final Handler handler = new Handler(); 
            final byte delimiter = 13; //This is the ASCII code for a newline character
            
            stopWorker = false;
            readBufferPosition = 0;
            readBuffer = new byte[1024];
            workerThread = new Thread(new Runnable()
            {
                public void run()
                {                
                   while(!Thread.currentThread().isInterrupted() && !stopWorker)
                   {
                        try 
                        {
                            int bytesAvailable = mmInputStream.available();                        
                            if(bytesAvailable > 0)
                            {
                                byte[] packetBytes = new byte[bytesAvailable];
                                mmInputStream.read(packetBytes);
                                for(int i=0;i<bytesAvailable;i++)
                                {
                                    byte b = packetBytes[i];
                                    if(b == delimiter)
                                    {
                                        byte[] encodedBytes = new byte[readBufferPosition];
                                        System.arraycopy(readBuffer, 0, encodedBytes, 0, encodedBytes.length);
                                        // return serial input data if needed
                                        //final String data = new String(encodedBytes, "US-ASCII");
                                        readBufferPosition = 0;
                                        
                                        handler.post(new Runnable()
                                        {
                                            public void run()
                                            {
                                            }
                                        });
                                    }
                                    else
                                    {
                                        readBuffer[readBufferPosition++] = b;
                                    }
                                }
                            }
                        } 
                        catch (IOException ex) 
                        {
                            stopWorker = true;
                        }
                   }
                }
            });

            workerThread.start();
        }
        
        void sendData(String msg) throws IOException
        {
            mmOutputStream.write(msg.getBytes());
           // myLabel.setText("Data Sent");
           // Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
        }
        
        void closeBT() throws IOException
        {
            stopWorker = true;
            mmOutputStream.close();
            mmInputStream.close();
            mmSocket.close();
            Toast.makeText(getApplicationContext(), "Bluetooth Closed", Toast.LENGTH_LONG).show();
        }
        
        // menu code
        
        @Override
    	public boolean onCreateOptionsMenu(Menu menu) {
    		MenuInflater inflater = getMenuInflater();
    	    inflater.inflate(R.menu.activity_main, menu);
    	    return true;
    	}

    	@Override
    	public boolean onOptionsItemSelected(MenuItem item) {
    		 if (item.getItemId() == R.id.menu_Open) {
    			 try 
                 {
                     findBT();
                     openBT();
                 }
                 catch (IOException ex) { }
    				
    		      return true;
    		    }
    		 if (item.getItemId() == R.id.menu_Close) {
    			 try 
                 {
                     closeBT();
                 }
                 catch (IOException ex) { }
    		      return true;
    		    }
    		 if (item.getItemId() == R.id.menu_Reset) {
    			 try 
                 {
    				 sendData("r");
                 }
                 catch (IOException ex) { }
    		      return true;
    		    }
    			return super.onOptionsItemSelected(item);
    		

    	}

    }
