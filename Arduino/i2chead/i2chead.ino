// i2chead.pde
//
// control stepper motor via i2c commands
//
// 

#include <AccelStepper.h>
#include <Wire.h> 

#define SLAVE_ADDRESS 0x29  //slave address, any number from 0x01 to 0x7F
#define REG_MAP_SIZE 5
#define MAX_SENT_BYTES 4

/********* Global  Variables  ***********/
byte registerMap[REG_MAP_SIZE];
byte registerMapTemp[REG_MAP_SIZE - 1];
byte receivedCommands[MAX_SENT_BYTES];

int stepperDirection = 0;
int stepperDegrees = 0;

float stepperMovement = 0.0;

boolean stepperMove =false;
boolean stepperPulseLeft =false;
boolean stepperPulseRight =false;

// Define a stepper and the pins it will use
AccelStepper stepper(AccelStepper::DRIVER, 8, 9);

void setup()
{  
  Wire.begin(SLAVE_ADDRESS);                // join i2c bus with address #2 
  Wire.onRequest(requestEvent);
  Wire.onReceive(receiveEvent);
  Serial.begin(9600);           // start serial for output 
  pinMode(7, OUTPUT); 
  digitalWrite(7, HIGH); 
  stepper.setMaxSpeed(40000);
  stepper.setAcceleration(40000);
}

void loop()
{
  if (stepperMove) {
    
    Serial.print(stepperDirection);
    Serial.print(stepperDegrees);
    digitalWrite(7, LOW);
    stepperMovement =  stepperDegrees * 8.8888888;
    if (stepperDirection == 0){
      stepper.runToNewPosition(stepperMovement);
    }
    else{
      stepper.runToNewPosition(-stepperMovement);
    }
    
    
    stepper.setCurrentPosition(0.0);
    digitalWrite(7, HIGH);
     stepperMove = false; 
  }
  
  if (stepperPulseLeft){
    digitalWrite(7, LOW);
     stepper.runToNewPosition(5);
     stepper.setCurrentPosition(0.0);
    digitalWrite(7, HIGH);
    stepperPulseLeft = false;
  }
  if (stepperPulseRight){
    digitalWrite(7, LOW);
     stepper.runToNewPosition(-5);
     stepper.setCurrentPosition(0.0);
    digitalWrite(7, HIGH);
    stepperPulseRight = false;
  }
}


 void requestEvent()
 {
     Wire.write(registerMap, REG_MAP_SIZE);  
}

 

void receiveEvent(int bytesReceived)
{
  Serial.print("received: ");
  for (int a = 0; a < bytesReceived; a++)
     {
      
        //int c = Wire.read();    
                     
          if ( a < MAX_SENT_BYTES)
          {
               receivedCommands[a] = Wire.read();
              
          }
          else
          {
               Wire.read();  // if we receive more data then allowed just throw it away
          }
     }
     
     
     switch(receivedCommands[0]){

          case 0x01:
               stepperDirection = receivedCommands[0]; //save the data to a separate variable
                stepperDegrees = receivedCommands[1];
               stepperMove = true;
               return; //we simply return here because the most bytes we can receive is three anyway
               break;
          case 0x00:
               stepperDirection = receivedCommands[0]; //save the data to a separate variable
                stepperDegrees = receivedCommands[1];
               stepperMove = true;
               return; //we simply return here because the most bytes we can receive is three anyway
               break;
          case 0x08:
               //stepperDirection = receivedCommands[0]; //save the data to a separate variable
               // stepperDegrees = receivedCommands[1];
               stepperPulseLeft = true;
               return; //we simply return here because the most bytes we can receive is three anyway
               break;
          case 0x09:
               //stepperDirection = receivedCommands[0]; //save the data to a separate variable
               // stepperDegrees = receivedCommands[1];
               stepperPulseRight = true;
               return; //we simply return here because the most bytes we can receive is three anyway
               break;
          default:

               return; // ignore the commands and return

     }
}
