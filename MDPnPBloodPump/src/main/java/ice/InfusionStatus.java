
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


public class InfusionStatus implements Copyable, Serializable
{

    public String unique_device_identifier = ""; /* maximum length = (64) */
    public boolean infusionActive = false;
    public String drug_name = ""; /* maximum length = (128) */
    public int drug_mass_mcg = 0;
    public int solution_volume_ml = 0;
    public int volume_to_be_infused_ml = 0;
    public int infusion_duration_seconds = 0;
    public float infusion_fraction_complete = 0;


    public InfusionStatus() {

    }


    public InfusionStatus(InfusionStatus other) {

        this();
        copy_from(other);
    }



    public static Object create() {
        InfusionStatus self;
        self = new InfusionStatus();
         
        self.clear();
        
        return self;
    }

    public void clear() {
        
        unique_device_identifier = "";
            
        infusionActive = false;
            
        drug_name = "";
            
        drug_mass_mcg = 0;
            
        solution_volume_ml = 0;
            
        volume_to_be_infused_ml = 0;
            
        infusion_duration_seconds = 0;
            
        infusion_fraction_complete = 0;
            
    }

    public boolean equals(Object o) {
                
        if (o == null) {
            return false;
        }        
        
        

        if(getClass() != o.getClass()) {
            return false;
        }

        InfusionStatus otherObj = (InfusionStatus)o;



        if(!unique_device_identifier.equals(otherObj.unique_device_identifier)) {
            return false;
        }
            
        if(infusionActive != otherObj.infusionActive) {
            return false;
        }
            
        if(!drug_name.equals(otherObj.drug_name)) {
            return false;
        }
            
        if(drug_mass_mcg != otherObj.drug_mass_mcg) {
            return false;
        }
            
        if(solution_volume_ml != otherObj.solution_volume_ml) {
            return false;
        }
            
        if(volume_to_be_infused_ml != otherObj.volume_to_be_infused_ml) {
            return false;
        }
            
        if(infusion_duration_seconds != otherObj.infusion_duration_seconds) {
            return false;
        }
            
        if(infusion_fraction_complete != otherObj.infusion_fraction_complete) {
            return false;
        }
            
        return true;
    }

    public int hashCode() {
        int __result = 0;

        __result += unique_device_identifier.hashCode();
                
        __result += (infusionActive == true)?1:0;
                
        __result += drug_name.hashCode();
                
        __result += (int)drug_mass_mcg;
                
        __result += (int)solution_volume_ml;
                
        __result += (int)volume_to_be_infused_ml;
                
        __result += (int)infusion_duration_seconds;
                
        __result += (int)infusion_fraction_complete;
                
        return __result;
    }
    

    /**
     * This is the implementation of the <code>Copyable</code> interface.
     * This method will perform a deep copy of <code>src</code>
     * This method could be placed into <code>InfusionStatusTypeSupport</code>
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
        

        InfusionStatus typedSrc = (InfusionStatus) src;
        InfusionStatus typedDst = this;

        typedDst.unique_device_identifier = typedSrc.unique_device_identifier;
            
        typedDst.infusionActive = typedSrc.infusionActive;
            
        typedDst.drug_name = typedSrc.drug_name;
            
        typedDst.drug_mass_mcg = typedSrc.drug_mass_mcg;
            
        typedDst.solution_volume_ml = typedSrc.solution_volume_ml;
            
        typedDst.volume_to_be_infused_ml = typedSrc.volume_to_be_infused_ml;
            
        typedDst.infusion_duration_seconds = typedSrc.infusion_duration_seconds;
            
        typedDst.infusion_fraction_complete = typedSrc.infusion_fraction_complete;
            
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
        strBuffer.append("infusionActive: ").append(infusionActive).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("drug_name: ").append(drug_name).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("drug_mass_mcg: ").append(drug_mass_mcg).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("solution_volume_ml: ").append(solution_volume_ml).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("volume_to_be_infused_ml: ").append(volume_to_be_infused_ml).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("infusion_duration_seconds: ").append(infusion_duration_seconds).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("infusion_fraction_complete: ").append(infusion_fraction_complete).append("\n");
            
        return strBuffer.toString();
    }
    
}

