
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


public class DeviceIdentity implements Copyable, Serializable
{

    public String unique_device_identifier = ""; /* maximum length = (64) */
    public String manufacturer = ""; /* maximum length = (128) */
    public String model = ""; /* maximum length = (128) */
    public String serial_number = ""; /* maximum length = (128) */
    public ice.Image icon = (ice.Image) ice.Image.create();
    public String build = ""; /* maximum length = (128) */
    public String operating_system = ""; /* maximum length = (128) */


    public DeviceIdentity() {

    }


    public DeviceIdentity(DeviceIdentity other) {

        this();
        copy_from(other);
    }



    public static Object create() {
        DeviceIdentity self;
        self = new DeviceIdentity();
         
        self.clear();
        
        return self;
    }

    public void clear() {
        
        unique_device_identifier = "";
            
        manufacturer = "";
            
        model = "";
            
        serial_number = "";
            
        if (icon != null) {
            icon.clear();
        }
            
        build = "";
            
        operating_system = "";
            
    }

    public boolean equals(Object o) {
                
        if (o == null) {
            return false;
        }        
        
        

        if(getClass() != o.getClass()) {
            return false;
        }

        DeviceIdentity otherObj = (DeviceIdentity)o;



        if(!unique_device_identifier.equals(otherObj.unique_device_identifier)) {
            return false;
        }
            
        if(!manufacturer.equals(otherObj.manufacturer)) {
            return false;
        }
            
        if(!model.equals(otherObj.model)) {
            return false;
        }
            
        if(!serial_number.equals(otherObj.serial_number)) {
            return false;
        }
            
        if(!icon.equals(otherObj.icon)) {
            return false;
        }
            
        if(!build.equals(otherObj.build)) {
            return false;
        }
            
        if(!operating_system.equals(otherObj.operating_system)) {
            return false;
        }
            
        return true;
    }

    public int hashCode() {
        int __result = 0;

        __result += unique_device_identifier.hashCode();
                
        __result += manufacturer.hashCode();
                
        __result += model.hashCode();
                
        __result += serial_number.hashCode();
                
        __result += icon.hashCode();
                
        __result += build.hashCode();
                
        __result += operating_system.hashCode();
                
        return __result;
    }
    

    /**
     * This is the implementation of the <code>Copyable</code> interface.
     * This method will perform a deep copy of <code>src</code>
     * This method could be placed into <code>DeviceIdentityTypeSupport</code>
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
        

        DeviceIdentity typedSrc = (DeviceIdentity) src;
        DeviceIdentity typedDst = this;

        typedDst.unique_device_identifier = typedSrc.unique_device_identifier;
            
        typedDst.manufacturer = typedSrc.manufacturer;
            
        typedDst.model = typedSrc.model;
            
        typedDst.serial_number = typedSrc.serial_number;
            
        typedDst.icon = (ice.Image) typedDst.icon.copy_from(typedSrc.icon);
            
        typedDst.build = typedSrc.build;
            
        typedDst.operating_system = typedSrc.operating_system;
            
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
        strBuffer.append("manufacturer: ").append(manufacturer).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("model: ").append(model).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("serial_number: ").append(serial_number).append("\n");
            
        strBuffer.append(icon.toString("icon ", indent+1));
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("build: ").append(build).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("operating_system: ").append(operating_system).append("\n");
            
        return strBuffer.toString();
    }
    
}

