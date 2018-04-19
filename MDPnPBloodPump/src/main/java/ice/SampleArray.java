
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


public class SampleArray implements Copyable, Serializable
{

    public String unique_device_identifier = ""; /* maximum length = (64) */
    public String metric_id = ""; /* maximum length = (64) */
    public String vendor_metric_id = ""; /* maximum length = (64) */
    public int instance_id = 0;
    public String unit_id = ""; /* maximum length = (64) */
    public int frequency = 0;
    public ice.Values values = (ice.Values) ice.Values.create();
    public ice.Time_t device_time = (ice.Time_t) ice.Time_t.create();
    public ice.Time_t presentation_time = (ice.Time_t) ice.Time_t.create();


    public SampleArray() {

    }


    public SampleArray(SampleArray other) {

        this();
        copy_from(other);
    }



    public static Object create() {
        SampleArray self;
        self = new SampleArray();
         
        self.clear();
        
        return self;
    }

    public void clear() {
        
        unique_device_identifier = "";
            
        metric_id = "";
            
        vendor_metric_id = "";
            
        instance_id = 0;
            
        unit_id = "";
            
        frequency = 0;
            
        if (values != null) {
            values.clear();
        }
            
        if (device_time != null) {
            device_time.clear();
        }
            
        if (presentation_time != null) {
            presentation_time.clear();
        }
            
    }

    public boolean equals(Object o) {
                
        if (o == null) {
            return false;
        }        
        
        

        if(getClass() != o.getClass()) {
            return false;
        }

        SampleArray otherObj = (SampleArray)o;



        if(!unique_device_identifier.equals(otherObj.unique_device_identifier)) {
            return false;
        }
            
        if(!metric_id.equals(otherObj.metric_id)) {
            return false;
        }
            
        if(!vendor_metric_id.equals(otherObj.vendor_metric_id)) {
            return false;
        }
            
        if(instance_id != otherObj.instance_id) {
            return false;
        }
            
        if(!unit_id.equals(otherObj.unit_id)) {
            return false;
        }
            
        if(frequency != otherObj.frequency) {
            return false;
        }
            
        if(!values.equals(otherObj.values)) {
            return false;
        }
            
        if(!device_time.equals(otherObj.device_time)) {
            return false;
        }
            
        if(!presentation_time.equals(otherObj.presentation_time)) {
            return false;
        }
            
        return true;
    }

    public int hashCode() {
        int __result = 0;

        __result += unique_device_identifier.hashCode();
                
        __result += metric_id.hashCode();
                
        __result += vendor_metric_id.hashCode();
                
        __result += (int)instance_id;
                
        __result += unit_id.hashCode();
                
        __result += (int)frequency;
                
        __result += values.hashCode();
                
        __result += device_time.hashCode();
                
        __result += presentation_time.hashCode();
                
        return __result;
    }
    

    /**
     * This is the implementation of the <code>Copyable</code> interface.
     * This method will perform a deep copy of <code>src</code>
     * This method could be placed into <code>SampleArrayTypeSupport</code>
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
        

        SampleArray typedSrc = (SampleArray) src;
        SampleArray typedDst = this;

        typedDst.unique_device_identifier = typedSrc.unique_device_identifier;
            
        typedDst.metric_id = typedSrc.metric_id;
            
        typedDst.vendor_metric_id = typedSrc.vendor_metric_id;
            
        typedDst.instance_id = typedSrc.instance_id;
            
        typedDst.unit_id = typedSrc.unit_id;
            
        typedDst.frequency = typedSrc.frequency;
            
        typedDst.values = (ice.Values) typedDst.values.copy_from(typedSrc.values);
            
        typedDst.device_time = (ice.Time_t) typedDst.device_time.copy_from(typedSrc.device_time);
            
        typedDst.presentation_time = (ice.Time_t) typedDst.presentation_time.copy_from(typedSrc.presentation_time);
            
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
        strBuffer.append("metric_id: ").append(metric_id).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("vendor_metric_id: ").append(vendor_metric_id).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("instance_id: ").append(instance_id).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("unit_id: ").append(unit_id).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("frequency: ").append(frequency).append("\n");
            
        strBuffer.append(values.toString("values ", indent+1));
            
        strBuffer.append(device_time.toString("device_time ", indent+1));
            
        strBuffer.append(presentation_time.toString("presentation_time ", indent+1));
            
        return strBuffer.toString();
    }
    
}

