
#include <AccelStepper.h>
#include <Wire.h> 


// serial variables
char inData[20]; // Allocate some space for the string
//char inChar=-1; // Where to store the character read
byte index = 0; // Index into array; where to store the character

// feeder values
long feederArray[15]={2850,2649,2451,2253,2055,1857,1659,1461,1263,1065,867,669,471,273,75};

const int xpinDirection = 12;  
const int xpinStep = 13; 
const int xpinStop = 7;
const int xacceleration = 10000;
const int xmaxspeed = 2000;

const int ypinDirection = 9;  
const int ypinStep = 10; 
const int ypinStop = 6;
const int yacceleration = 20000;
const int ymaxspeed = 10000;

int incomingByte = 0; 
// Define a stepper and the pins it will use
AccelStepper stepperX(AccelStepper::DRIVER, xpinStep, xpinDirection);
AccelStepper stepperY(AccelStepper::DRIVER, ypinStep, ypinDirection);

// inturuupt variables
int moveInState = LOW;
int moveOutState = LOW;

void setup()
{  
  

  
 Wire.begin();  
  pinMode(ypinStop, INPUT); 
  pinMode(xpinStop, INPUT); 
  //pinMode(2, INPUT);
  //pinMode(4, INPUT);
  //digitalWrite(ypinStop, HIGH); // set pullup resistor to high
  //digitalWrite(xpinStop, HIGH); // set pullup resistor to high
  
  ///digitalWrite(2, HIGH); // set pullup resistor to high
  //digitalWrite(4, HIGH); // set pullup resistor to high
  
  Serial.begin(9600);   
  Serial1.begin(9600);  
   
  delayMicroseconds(500); 

  stepperX.setEnablePin(11);
  stepperY.setEnablePin(8);
 
  
  //
  // zero feeder
  stepperX.setAcceleration(10000);
  //
  stepperY.setMaxSpeed(2000);
  stepperY.setAcceleration(3000);
    
  while (!ZeroY());
  while (!ZeroX());

  // set default speeds
  stepperX.setMaxSpeed(xmaxspeed);
  stepperX.setAcceleration(xacceleration);
  stepperY.setMaxSpeed(ymaxspeed);
  stepperY.setAcceleration(yacceleration);
  
  pinMode(2, INPUT);
  pinMode(3, INPUT);
  attachInterrupt(0, movedOut, RISING);
  attachInterrupt(1, movedIn, RISING);
}

void loop()
{
 
  while (Serial1.available() > 0) {
        CheckInput(Serial1.read());
  }
  while (Serial.available() > 0) {
        CheckInput(Serial.read());
  }

  if (moveInState){
     WriteToSerial('i'); 
     moveInState = LOW;
  }
  if (moveOutState){
     WriteToSerial('o'); 
     moveOutState = LOW;
  }

}
void WriteToSerial(char s) {
  Serial.write(s);
  Serial1.write(s);
}

void CheckInput(char inChar){
  if (inChar == '\n') {
          
        }

        if (inChar == '1') {
          
          gotoFeeder(0); 
           WriteToSerial('s');
        }
        
        if (inChar == '2') {
           gotoFeeder(1); 
           WriteToSerial('s');
        }
        if (inChar == '3') {
           gotoFeeder(2); 
           WriteToSerial('s');
        }
        if (inChar == '4') {
           gotoFeeder(3); 
           WriteToSerial('s');
        }
        if (inChar == '5') {
           gotoFeeder(4);
          WriteToSerial('s'); 
        }
        if (inChar == '6') {
           gotoFeeder(5); 
           WriteToSerial('s');
        }
        if (inChar == '7') {
           gotoFeeder(6); 
           WriteToSerial('s');
        }
        if (inChar == '8') {
           gotoFeeder(7); 
           WriteToSerial('s');
        }
        if (inChar == '9') {
           gotoFeeder(8); 
           WriteToSerial('s');
        }
        if (inChar == 'a') {
           gotoFeeder(9); 
           WriteToSerial('s');
        }
        if (inChar == 'b') {
           gotoFeeder(10); 
           WriteToSerial('s');
        }
        if (inChar == 'c') {
           gotoFeeder(11); 
           WriteToSerial('s');
        }
        if (inChar == 'd') {
           gotoFeeder(12); 
           WriteToSerial('s');
        }
        if (inChar == 'e') {
           gotoFeeder(13); 
           WriteToSerial('s');
        }
        if (inChar == 'f') {
           gotoFeeder(14); 
           WriteToSerial('s');
        }

        if (inChar == 'r') {
           // zero feeder
          stepperX.setAcceleration(10000);
          //stepperX.disableOutputs();
          stepperY.setMaxSpeed(2000);
          stepperY.setAcceleration(3000);
          while (!ZeroY());
          while (!ZeroX());
        
          // set default speeds
          stepperX.setMaxSpeed(xmaxspeed);
          stepperX.setAcceleration(xacceleration);
          stepperY.setMaxSpeed(ymaxspeed);
          stepperY.setAcceleration(yacceleration);
        }
        
        // rotation commands
        
        if (inChar == ',') {
          WriteToSerial('p');
           rotateHead(0, 90);
            WriteToSerial('s');
        }
        if (inChar == '.') {
          WriteToSerial('p');
            rotateHead(1, 90);
            WriteToSerial('s');
        }
        
        if (inChar == 'l') {
          //WriteToSerial('k');
           rotateHeadStep(8);
            //WriteToSerial('s');
        }
        if (inChar == 'k') {
          //WriteToSerial('l');
            rotateHeadStep(9);
            //WriteToSerial('s');
        }
       
        
}

boolean ZeroX() {
   stepperX.enableOutputs();
   stepperX.setMaxSpeed(450);
    stepperX.moveTo(-20000);
   while (digitalRead(xpinStop)== LOW) {
      stepperX.run();
   }
   int x = 0;

  stepperX.stop();
  delayMicroseconds(500); 
  //stepperX.disableOutputs();
  stepperX.setCurrentPosition(0.0);
  
  return true;
}

boolean ZeroY() {
   stepperY.enableOutputs();
   stepperY.setMaxSpeed(800);
   stepperY.moveTo(50000);
   while (digitalRead(ypinStop) == LOW) {
      stepperY.run();
  }
  
  delayMicroseconds(100); 
  //stepperY.disableOutputs();
  stepperY.setCurrentPosition(0.0);
  return true;
}

void gotoFeeder(int feederid){
  
  retractComponent();
  stepperX.enableOutputs();
  stepperX.runToNewPosition(feederArray[feederid]);
  pickComponent();
}

void pickComponent(){
  stepperY.enableOutputs();
  stepperY.runToNewPosition(-1100);
  stepperY.enableOutputs();
}

void retractComponent(){
   stepperY.enableOutputs();
   stepperY.runToNewPosition(1);
   stepperY.enableOutputs();
}

void rotateHead(int dir, int degreesmotion) {
  Wire.beginTransmission(0x29); // transmit to device #44 (0x2c)
  Wire.write(dir);             // sends value byte  
  Wire.write(degreesmotion);             // sends value byte 
  Wire.endTransmission(); 
}

void rotateHeadStep(int dir) {
  Wire.beginTransmission(0x29); // transmit to device #44 (0x2c)
  Wire.write(dir);             // sends value byte  
  Wire.endTransmission(); 
}

// Install the interrupt routine.
void movedIn()
{
  moveInState = HIGH;
}

void movedOut()
{
  moveOutState = HIGH;
}

// serial methods
/*
char Comp(char* This) {
    while (Serial.available() > 0) // Don't read unless
                                   // there you know there is data
    {
        if(index < 19) // One less than the size of the array
        {
            inChar = Serial.read(); // Read a character
            inData[index] = inChar; // Store it
            index++; // Increment where to write next
            inData[index] = '\0'; // Null terminate the string
        }
    }

    if (strcmp(inData,This)  == 0) {
        for (int i=0;i<19;i++) {
            inData[i]=0;
        }
        index=0;
        return(0);
    }
    else {
        return(1);
    }
}*/
