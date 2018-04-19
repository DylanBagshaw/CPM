
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


public class GlobalSimulationObjective implements Copyable, Serializable
{

    public String metric_id = ""; /* maximum length = (64) */
    public float value = 0;
    public float jitterStep = 0;
    public float floor = 0;
    public float ceil = 0;


    public GlobalSimulationObjective() {

    }


    public GlobalSimulationObjective(GlobalSimulationObjective other) {

        this();
        copy_from(other);
    }



    public static Object create() {
        GlobalSimulationObjective self;
        self = new GlobalSimulationObjective();
         
        self.clear();
        
        return self;
    }

    public void clear() {
        
        metric_id = "";
            
        value = 0;
            
        jitterStep = 0;
            
        floor = 0;
            
        ceil = 0;
            
    }

    public boolean equals(Object o) {
                
        if (o == null) {
            return false;
        }        
        
        

        if(getClass() != o.getClass()) {
            return false;
        }

        GlobalSimulationObjective otherObj = (GlobalSimulationObjective)o;



        if(!metric_id.equals(otherObj.metric_id)) {
            return false;
        }
            
        if(value != otherObj.value) {
            return false;
        }
            
        if(jitterStep != otherObj.jitterStep) {
            return false;
        }
            
        if(floor != otherObj.floor) {
            return false;
        }
            
        if(ceil != otherObj.ceil) {
            return false;
        }
            
        return true;
    }

    public int hashCode() {
        int __result = 0;

        __result += metric_id.hashCode();
                
        __result += (int)value;
                
        __result += (int)jitterStep;
                
        __result += (int)floor;
                
        __result += (int)ceil;
                
        return __result;
    }
    

    /**
     * This is the implementation of the <code>Copyable</code> interface.
     * This method will perform a deep copy of <code>src</code>
     * This method could be placed into <code>GlobalSimulationObjectiveTypeSupport</code>
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
        

        GlobalSimulationObjective typedSrc = (GlobalSimulationObjective) src;
        GlobalSimulationObjective typedDst = this;

        typedDst.metric_id = typedSrc.metric_id;
            
        typedDst.value = typedSrc.value;
            
        typedDst.jitterStep = typedSrc.jitterStep;
            
        typedDst.floor = typedSrc.floor;
            
        typedDst.ceil = typedSrc.ceil;
            
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
        strBuffer.append("metric_id: ").append(metric_id).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("value: ").append(value).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("jitterStep: ").append(jitterStep).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("floor: ").append(floor).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("ceil: ").append(ceil).append("\n");
            
        return strBuffer.toString();
    }
    
}

