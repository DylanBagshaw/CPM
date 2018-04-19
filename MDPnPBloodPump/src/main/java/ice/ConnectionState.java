
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



public class ConnectionState extends Enum
{

    public static final ConnectionState Initial = new ConnectionState("Initial", 0);
    public static final int _Initial = 0;
    
    public static final ConnectionState Connected = new ConnectionState("Connected", 1);
    public static final int _Connected = 1;
    
    public static final ConnectionState Connecting = new ConnectionState("Connecting", 2);
    public static final int _Connecting = 2;
    
    public static final ConnectionState Negotiating = new ConnectionState("Negotiating", 3);
    public static final int _Negotiating = 3;
    
    public static final ConnectionState Terminal = new ConnectionState("Terminal", 4);
    public static final int _Terminal = 4;
    


    public static ConnectionState valueOf(int ordinal) {
        switch(ordinal) {
            
              case 0: return ConnectionState.Initial;
            
              case 1: return ConnectionState.Connected;
            
              case 2: return ConnectionState.Connecting;
            
              case 3: return ConnectionState.Negotiating;
            
              case 4: return ConnectionState.Terminal;
            

        }
        return null;
    }

    public static ConnectionState from_int(int __value) {
        return valueOf(__value);
    }

    public static int[] getOrdinals() {
        int i = 0;
        int[] values = new int[5];
        
        
        values[i] = Initial.ordinal();
        i++;
        
        values[i] = Connected.ordinal();
        i++;
        
        values[i] = Connecting.ordinal();
        i++;
        
        values[i] = Negotiating.ordinal();
        i++;
        
        values[i] = Terminal.ordinal();
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
    public static ConnectionState create() {
        

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

    private ConnectionState(String name, int ordinal) {
        super(name, ordinal);
    }
}

