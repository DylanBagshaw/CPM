
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


public class Patient implements Copyable, Serializable
{

    public String mrn = ""; /* maximum length = (16) */
    public String given_name = ""; /* maximum length = (256) */
    public String family_name = ""; /* maximum length = (256) */


    public Patient() {

    }


    public Patient(Patient other) {

        this();
        copy_from(other);
    }



    public static Object create() {
        Patient self;
        self = new Patient();
         
        self.clear();
        
        return self;
    }

    public void clear() {
        
        mrn = "";
            
        given_name = "";
            
        family_name = "";
            
    }

    public boolean equals(Object o) {
                
        if (o == null) {
            return false;
        }        
        
        

        if(getClass() != o.getClass()) {
            return false;
        }

        Patient otherObj = (Patient)o;



        if(!mrn.equals(otherObj.mrn)) {
            return false;
        }
            
        if(!given_name.equals(otherObj.given_name)) {
            return false;
        }
            
        if(!family_name.equals(otherObj.family_name)) {
            return false;
        }
            
        return true;
    }

    public int hashCode() {
        int __result = 0;

        __result += mrn.hashCode();
                
        __result += given_name.hashCode();
                
        __result += family_name.hashCode();
                
        return __result;
    }
    

    /**
     * This is the implementation of the <code>Copyable</code> interface.
     * This method will perform a deep copy of <code>src</code>
     * This method could be placed into <code>PatientTypeSupport</code>
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
        

        Patient typedSrc = (Patient) src;
        Patient typedDst = this;

        typedDst.mrn = typedSrc.mrn;
            
        typedDst.given_name = typedSrc.given_name;
            
        typedDst.family_name = typedSrc.family_name;
            
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
        strBuffer.append("mrn: ").append(mrn).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("given_name: ").append(given_name).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("family_name: ").append(family_name).append("\n");
            
        return strBuffer.toString();
    }
    
}

