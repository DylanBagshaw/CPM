
/*
  WARNING: THIS FILE IS AUTO-GENERATED. DO NOT MODIFY.

  This file was generated from .idl using "rtiddsgen".
  The rtiddsgen tool is part of the RTI Connext distribution.
  For more information, type 'rtiddsgen -help' at a command shell
  or consult the RTI Connext manual.
*/
    
package ice;
        

import com.rti.dds.infrastructure.*;
import com.rti.dds.infrastructure.Copyable;

import java.io.Serializable;
import com.rti.dds.cdr.CdrHelper;


public class BypassObjective implements Copyable, Serializable
{

    public String unique_device_identifier = ""; /* maximum length = (64) */
    public String requestor = ""; /* maximum length = (128) */
    public boolean stopBypass = false;


    public BypassObjective() {

    }


    public BypassObjective(BypassObjective other) {

        this();
        copy_from(other);
    }



    public static Object create() {
        BypassObjective self;
        self = new BypassObjective();
         
        self.clear();
        
        return self;
    }

    public void clear() {
        
        unique_device_identifier = "";
            
        requestor = "";
            
        stopBypass = false;
            
    }

    public boolean equals(Object o) {
                
        if (o == null) {
            return false;
        }        
        
        

        if(getClass() != o.getClass()) {
            return false;
        }

        BypassObjective otherObj = (BypassObjective)o;



        if(!unique_device_identifier.equals(otherObj.unique_device_identifier)) {
            return false;
        }
            
        if(!requestor.equals(otherObj.requestor)) {
            return false;
        }
            
        if(stopBypass != otherObj.stopBypass) {
            return false;
        }
            
        return true;
    }

    public int hashCode() {
        int __result = 0;

        __result += unique_device_identifier.hashCode();
                
        __result += requestor.hashCode();
                
        __result += (stopBypass == true)?1:0;
                
        return __result;
    }
    

    /**
     * This is the implementation of the <code>Copyable</code> interface.
     * This method will perform a deep copy of <code>src</code>
     * This method could be placed into <code>BypassObjectiveTypeSupport</code>
     * rather than here by using the <code>-noCopyable</code> option
     * to rtiddsgen.
     * 
     * @param src The Object which contains the data to be copied.
     * @return Returns <code>this</code>.
     * @exception NullPointerException If <code>src</code> is null.
     * @exception ClassCastException If <code>src</code> is not the 
     * same type as <code>this</code>.
     * @see com.rti.dds.infrastructure.Copyable#copy_from(java.lang.Object)
     */
    public Object copy_from(Object src) {
        

        BypassObjective typedSrc = (BypassObjective) src;
        BypassObjective typedDst = this;

        typedDst.unique_device_identifier = typedSrc.unique_device_identifier;
            
        typedDst.requestor = typedSrc.requestor;
            
        typedDst.stopBypass = typedSrc.stopBypass;
            
        return this;
    }


    
    public String toString(){
        return toString("", 0);
    }
        
    
    public String toString(String desc, int indent) {
        StringBuffer strBuffer = new StringBuffer();        
                        
        
        if (desc != null) {
            CdrHelper.printIndent(strBuffer, indent);
            strBuffer.append(desc).append(":\n");
        }
        
        
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("unique_device_identifier: ").append(unique_device_identifier).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("requestor: ").append(requestor).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("stopBypass: ").append(stopBypass).append("\n");
            
        return strBuffer.toString();
    }
    
}

