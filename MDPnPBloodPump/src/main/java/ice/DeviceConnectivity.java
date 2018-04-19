
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


public class DeviceConnectivity implements Copyable, Serializable
{

    public String unique_device_identifier = ""; /* maximum length = (64) */
    public ice.ConnectionState state = (ice.ConnectionState) ice.ConnectionState.create();
    public ice.ConnectionType type = (ice.ConnectionType) ice.ConnectionType.create();
    public String info = ""; /* maximum length = (128) */
    public ice.ValidTargets valid_targets = (ice.ValidTargets) ice.ValidTargets.create();


    public DeviceConnectivity() {

    }


    public DeviceConnectivity(DeviceConnectivity other) {

        this();
        copy_from(other);
    }



    public static Object create() {
        DeviceConnectivity self;
        self = new DeviceConnectivity();
         
        self.clear();
        
        return self;
    }

    public void clear() {
        
        unique_device_identifier = "";
            
        state = ice.ConnectionState.create();
            
        type = ice.ConnectionType.create();
            
        info = "";
            
        if (valid_targets != null) {
            valid_targets.clear();
        }
            
    }

    public boolean equals(Object o) {
                
        if (o == null) {
            return false;
        }        
        
        

        if(getClass() != o.getClass()) {
            return false;
        }

        DeviceConnectivity otherObj = (DeviceConnectivity)o;



        if(!unique_device_identifier.equals(otherObj.unique_device_identifier)) {
            return false;
        }
            
        if(!state.equals(otherObj.state)) {
            return false;
        }
            
        if(!type.equals(otherObj.type)) {
            return false;
        }
            
        if(!info.equals(otherObj.info)) {
            return false;
        }
            
        if(!valid_targets.equals(otherObj.valid_targets)) {
            return false;
        }
            
        return true;
    }

    public int hashCode() {
        int __result = 0;

        __result += unique_device_identifier.hashCode();
                
        __result += state.hashCode();
                
        __result += type.hashCode();
                
        __result += info.hashCode();
                
        __result += valid_targets.hashCode();
                
        return __result;
    }
    

    /**
     * This is the implementation of the <code>Copyable</code> interface.
     * This method will perform a deep copy of <code>src</code>
     * This method could be placed into <code>DeviceConnectivityTypeSupport</code>
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
        

        DeviceConnectivity typedSrc = (DeviceConnectivity) src;
        DeviceConnectivity typedDst = this;

        typedDst.unique_device_identifier = typedSrc.unique_device_identifier;
            
        typedDst.state = (ice.ConnectionState) typedDst.state.copy_from(typedSrc.state);
            
        typedDst.type = (ice.ConnectionType) typedDst.type.copy_from(typedSrc.type);
            
        typedDst.info = typedSrc.info;
            
        typedDst.valid_targets = (ice.ValidTargets) typedDst.valid_targets.copy_from(typedSrc.valid_targets);
            
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
            
        strBuffer.append(state.toString("state ", indent+1));
            
        strBuffer.append(type.toString("type ", indent+1));
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("info: ").append(info).append("\n");
            
        strBuffer.append(valid_targets.toString("valid_targets ", indent+1));
            
        return strBuffer.toString();
    }
    
}

