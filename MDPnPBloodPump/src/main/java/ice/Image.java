
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


public class Image implements Copyable, Serializable
{

    public String content_type = ""; /* maximum length = (64) */
    public ice.ImageData image = (ice.ImageData) ice.ImageData.create();


    public Image() {

    }


    public Image(Image other) {

        this();
        copy_from(other);
    }



    public static Object create() {
        Image self;
        self = new Image();
         
        self.clear();
        
        return self;
    }

    public void clear() {
        
        content_type = "";
            
        if (image != null) {
            image.clear();
        }
            
    }

    public boolean equals(Object o) {
                
        if (o == null) {
            return false;
        }        
        
        

        if(getClass() != o.getClass()) {
            return false;
        }

        Image otherObj = (Image)o;



        if(!content_type.equals(otherObj.content_type)) {
            return false;
        }
            
        if(!image.equals(otherObj.image)) {
            return false;
        }
            
        return true;
    }

    public int hashCode() {
        int __result = 0;

        __result += content_type.hashCode();
                
        __result += image.hashCode();
                
        return __result;
    }
    

    /**
     * This is the implementation of the <code>Copyable</code> interface.
     * This method will perform a deep copy of <code>src</code>
     * This method could be placed into <code>ImageTypeSupport</code>
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
        

        Image typedSrc = (Image) src;
        Image typedDst = this;

        typedDst.content_type = typedSrc.content_type;
            
        typedDst.image = (ice.ImageData) typedDst.image.copy_from(typedSrc.image);
            
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
        strBuffer.append("content_type: ").append(content_type).append("\n");
            
        strBuffer.append(image.toString("image ", indent+1));
            
        return strBuffer.toString();
    }
    
}

