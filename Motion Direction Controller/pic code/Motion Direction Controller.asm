
_main:

;Motion Direction Controller.c,1 :: 		void main() {
;Motion Direction Controller.c,3 :: 		IRCF0_bit = 1;
	BSF        IRCF0_bit+0, BitPos(IRCF0_bit+0)
;Motion Direction Controller.c,4 :: 		IRCF1_bit = 0;
	BCF        IRCF1_bit+0, BitPos(IRCF1_bit+0)
;Motion Direction Controller.c,5 :: 		IRCF2_bit = 0;
	BCF        IRCF2_bit+0, BitPos(IRCF2_bit+0)
;Motion Direction Controller.c,6 :: 		IRCF3_bit = 0;
	BCF        IRCF3_bit+0, BitPos(IRCF3_bit+0)
;Motion Direction Controller.c,9 :: 		ANSELA = 0x00;
	CLRF       ANSELA+0
;Motion Direction Controller.c,10 :: 		TRISA = 0;
	CLRF       TRISA+0
;Motion Direction Controller.c,11 :: 		TRISA.B2 = 1;
	BSF        TRISA+0, 2
;Motion Direction Controller.c,12 :: 		TRISA.B4 = 1;
	BSF        TRISA+0, 4
;Motion Direction Controller.c,16 :: 		PORTA.B1 = 0;
	BCF        PORTA+0, 1
;Motion Direction Controller.c,17 :: 		PORTA.B5 = 0;
	BCF        PORTA+0, 5
;Motion Direction Controller.c,19 :: 		INTCON = 0b10010000;
	MOVLW      144
	MOVWF      INTCON+0
;Motion Direction Controller.c,20 :: 		OPTION_REG.INTEDG = 0; // trigger INT on rising edge
	BCF        OPTION_REG+0, 6
;Motion Direction Controller.c,21 :: 		}
L_end_main:
	GOTO       $+0
; end of _main

_interrupt:
	CLRF       PCLATH+0
	CLRF       STATUS+0

;Motion Direction Controller.c,23 :: 		void interrupt() {
;Motion Direction Controller.c,24 :: 		if(INTCON.INTF == 1 ){
	BTFSS      INTCON+0, 1
	GOTO       L_interrupt0
;Motion Direction Controller.c,25 :: 		if (PORTA.B2 == 0){
	BTFSC      PORTA+0, 2
	GOTO       L_interrupt1
;Motion Direction Controller.c,26 :: 		if (PORTA.B4 == 0){
	BTFSC      PORTA+0, 4
	GOTO       L_interrupt2
;Motion Direction Controller.c,27 :: 		PORTA.B1 = 1;
	BSF        PORTA+0, 1
;Motion Direction Controller.c,28 :: 		Delay_ms(300);
	MOVLW      4
	MOVWF      R12
	MOVLW      28
	MOVWF      R13
L_interrupt3:
	DECFSZ     R13, 1
	GOTO       L_interrupt3
	DECFSZ     R12, 1
	GOTO       L_interrupt3
	NOP
;Motion Direction Controller.c,29 :: 		PORTA.B1 = 0;
	BCF        PORTA+0, 1
;Motion Direction Controller.c,30 :: 		}
	GOTO       L_interrupt4
L_interrupt2:
;Motion Direction Controller.c,32 :: 		PORTA.B5 = 1;
	BSF        PORTA+0, 5
;Motion Direction Controller.c,33 :: 		Delay_ms(300);
	MOVLW      4
	MOVWF      R12
	MOVLW      28
	MOVWF      R13
L_interrupt5:
	DECFSZ     R13, 1
	GOTO       L_interrupt5
	DECFSZ     R12, 1
	GOTO       L_interrupt5
	NOP
;Motion Direction Controller.c,34 :: 		PORTA.B5 = 0;
	BCF        PORTA+0, 5
;Motion Direction Controller.c,35 :: 		}
L_interrupt4:
;Motion Direction Controller.c,36 :: 		}
L_interrupt1:
;Motion Direction Controller.c,37 :: 		INTCON.INTF = 0;       // Clear Interrupt Flag
	BCF        INTCON+0, 1
;Motion Direction Controller.c,38 :: 		}
L_interrupt0:
;Motion Direction Controller.c,39 :: 		}
L_end_interrupt:
L__interrupt8:
	RETFIE     %s
; end of _interrupt
