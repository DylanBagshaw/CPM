
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


public class LocalAlarmLimitObjective implements Copyable, Serializable
{

    public String unique_device_identifier = ""; /* maximum length = (64) */
    public String metric_id = ""; /* maximum length = (64) */
    public ice.LimitType limit_type = (ice.LimitType) ice.LimitType.create();
    public String unit_identifier = ""; /* maximum length = (64) */
    public float value = 0;


    public LocalAlarmLimitObjective() {

    }


    public LocalAlarmLimitObjective(LocalAlarmLimitObjective other) {

        this();
        copy_from(other);
    }



    public static Object create() {
        LocalAlarmLimitObjective self;
        self = new LocalAlarmLimitObjective();
         
        self.clear();
        
        return self;
    }

    public void clear() {
        
        unique_device_identifier = "";
            
        metric_id = "";
            
        limit_type = ice.LimitType.create();
            
        unit_identifier = "";
            
        value = 0;
            
    }

    public boolean equals(Object o) {
                
        if (o == null) {
            return false;
        }        
        
        

        if(getClass() != o.getClass()) {
            return false;
        }

        LocalAlarmLimitObjective otherObj = (LocalAlarmLimitObjective)o;



        if(!unique_device_identifier.equals(otherObj.unique_device_identifier)) {
            return false;
        }
            
        if(!metric_id.equals(otherObj.metric_id)) {
            return false;
        }
            
        if(!limit_type.equals(otherObj.limit_type)) {
            return false;
        }
            
        if(!unit_identifier.equals(otherObj.unit_identifier)) {
            return false;
        }
            
        if(value != otherObj.value) {
            return false;
        }
            
        return true;
    }

    public int hashCode() {
        int __result = 0;

        __result += unique_device_identifier.hashCode();
                
        __result += metric_id.hashCode();
                
        __result += limit_type.hashCode();
                
        __result += unit_identifier.hashCode();
                
        __result += (int)value;
                
        return __result;
    }
    

    /**
     * This is the implementation of the <code>Copyable</code> interface.
     * This method will perform a deep copy of <code>src</code>
     * This method could be placed into <code>LocalAlarmLimitObjectiveTypeSupport</code>
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
        

        LocalAlarmLimitObjective typedSrc = (LocalAlarmLimitObjective) src;
        LocalAlarmLimitObjective typedDst = this;

        typedDst.unique_device_identifier = typedSrc.unique_device_identifier;
            
        typedDst.metric_id = typedSrc.metric_id;
            
        typedDst.limit_type = (ice.LimitType) typedDst.limit_type.copy_from(typedSrc.limit_type);
            
        typedDst.unit_identifier = typedSrc.unit_identifier;
            
        typedDst.value = typedSrc.value;
            
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
            
        strBuffer.append(limit_type.toString("limit_type ", indent+1));
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("unit_identifier: ").append(unit_identifier).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("value: ").append(value).append("\n");
            
        return strBuffer.toString();
    }
    
}

