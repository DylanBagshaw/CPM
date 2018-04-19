
/*
  WARNING: THIS FILE IS AUTO-GENERATED. DO NOT MODIFY.

  This file was generated from .idl using "rtiddsgen".
  The rtiddsgen tool is part of the RTI Connext distribution.
  For more information, type 'rtiddsgen -help' at a command shell
  or consult the RTI Connext manual.
*/
    
package ice;
        

import com.rti.dds.util.Enum;
import com.rti.dds.cdr.CdrHelper;
import java.util.Arrays;
import java.io.ObjectStreamException;



public class AlarmPriority extends Enum
{

    public static final AlarmPriority low = new AlarmPriority("low", 0);
    public static final int _low = 0;
    
    public static final AlarmPriority medium = new AlarmPriority("medium", 1);
    public static final int _medium = 1;
    
    public static final AlarmPriority high = new AlarmPriority("high", 2);
    public static final int _high = 2;
    


    public static AlarmPriority valueOf(int ordinal) {
        switch(ordinal) {
            
              case 0: return AlarmPriority.low;
            
              case 1: return AlarmPriority.medium;
            
              case 2: return AlarmPriority.high;
            

        }
        return null;
    }

    public static AlarmPriority from_int(int __value) {
        return valueOf(__value);
    }

    public static int[] getOrdinals() {
        int i = 0;
        int[] values = new int[3];
        
        
        values[i] = low.ordinal();
        i++;
        
        values[i] = medium.ordinal();
        i++;
        
        values[i] = high.ordinal();
        i++;
        

        Arrays.sort(values);
        return values;
    }

    public int value() {
        return super.ordinal();
    }

    /**
     * Create a default instance
     */  
    public static AlarmPriority create() {
        

        return valueOf(0);
    }
    
    /**
     * Print Method
     */     
    public String toString(String desc, int indent) {
        StringBuffer strBuffer = new StringBuffer();

        CdrHelper.printIndent(strBuffer, indent);
            
        if (desc != null) {
            strBuffer.append(desc).append(": ");
        }
        
        strBuffer.append(this);
        strBuffer.append("\n");              
        return strBuffer.toString();
    }

    private Object readResolve() throws ObjectStreamException {
        return valueOf(ordinal());
    }

    private AlarmPriority(String name, int ordinal) {
        super(name, ordinal);
    }
}

