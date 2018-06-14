
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


public class BypassStatus implements Copyable, Serializable
{

    public String unique_device_identifier = ""; /* maximum length = (64) */
    public boolean bypassActive = false;
    public double bypass_flow_lmin = 0;
    public double bypass_speed_rpm = 0;
    public double blood_temp_celsius = 0;
    public double blood_press_mmhg = 0;
    public double volume_bypassed_ml = 0;
    public int bypass_duration_seconds = 0;


    public BypassStatus() {

    }


    public BypassStatus(BypassStatus other) {

        this();
        copy_from(other);
    }



    public static Object create() {
        BypassStatus self;
        self = new BypassStatus();
         
        self.clear();
        
        return self;
    }

    public void clear() {
        
        unique_device_identifier = "";
            
        bypassActive = false;
            
        bypass_flow_lmin = 0;
            
        bypass_speed_rpm = 0;
            
        blood_temp_celsius = 0;
            
        blood_press_mmhg = 0;
            
        volume_bypassed_ml = 0;
            
        bypass_duration_seconds = 0;
            
    }

    public boolean equals(Object o) {
                
        if (o == null) {
            return false;
        }        
        
        

        if(getClass() != o.getClass()) {
            return false;
        }

        BypassStatus otherObj = (BypassStatus)o;



        if(!unique_device_identifier.equals(otherObj.unique_device_identifier)) {
            return false;
        }
            
        if(bypassActive != otherObj.bypassActive) {
            return false;
        }
            
        if(bypass_flow_lmin != otherObj.bypass_flow_lmin) {
            return false;
        }
            
        if(bypass_speed_rpm != otherObj.bypass_speed_rpm) {
            return false;
        }
            
        if(blood_temp_celsius != otherObj.blood_temp_celsius) {
            return false;
        }
            
        if(blood_press_mmhg != otherObj.blood_press_mmhg) {
            return false;
        }
            
        if(volume_bypassed_ml != otherObj.volume_bypassed_ml) {
            return false;
        }
            
        if(bypass_duration_seconds != otherObj.bypass_duration_seconds) {
            return false;
        }
            
        return true;
    }

    public int hashCode() {
        int __result = 0;

        __result += unique_device_identifier.hashCode();
                
        __result += (bypassActive == true)?1:0;
                
        __result += (int)bypass_flow_lmin;
                
        __result += (int)bypass_speed_rpm;
                
        __result += (int)blood_temp_celsius;
                
        __result += (int)blood_press_mmhg;
                
        __result += (int)volume_bypassed_ml;
                
        __result += (int)bypass_duration_seconds;
                
        return __result;
    }
    

    /**
     * This is the implementation of the <code>Copyable</code> interface.
     * This method will perform a deep copy of <code>src</code>
     * This method could be placed into <code>BypassStatusTypeSupport</code>
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
        

        BypassStatus typedSrc = (BypassStatus) src;
        BypassStatus typedDst = this;

        typedDst.unique_device_identifier = typedSrc.unique_device_identifier;
            
        typedDst.bypassActive = typedSrc.bypassActive;
            
        typedDst.bypass_flow_lmin = typedSrc.bypass_flow_lmin;
            
        typedDst.bypass_speed_rpm = typedSrc.bypass_speed_rpm;
            
        typedDst.blood_temp_celsius = typedSrc.blood_temp_celsius;
            
        typedDst.blood_press_mmhg = typedSrc.blood_press_mmhg;
            
        typedDst.volume_bypassed_ml = typedSrc.volume_bypassed_ml;
            
        typedDst.bypass_duration_seconds = typedSrc.bypass_duration_seconds;
            
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
        strBuffer.append("bypassActive: ").append(bypassActive).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("bypass_flow_lmin: ").append(bypass_flow_lmin).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("bypass_speed_rpm: ").append(bypass_speed_rpm).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("blood_temp_celsius: ").append(blood_temp_celsius).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("blood_press_mmhg: ").append(blood_press_mmhg).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("volume_bypassed_ml: ").append(volume_bypassed_ml).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("bypass_duration_seconds: ").append(bypass_duration_seconds).append("\n");
            
        return strBuffer.toString();
    }
    
}

