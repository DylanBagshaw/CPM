
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


public class TimeSync implements Copyable, Serializable
{

    public String heartbeat_source = ""; /* maximum length = (64) */
    public String heartbeat_recipient = ""; /* maximum length = (64) */
    public ice.Time_t source_source_timestamp = (ice.Time_t) ice.Time_t.create();
    public ice.Time_t recipient_receipt_timestamp = (ice.Time_t) ice.Time_t.create();


    public TimeSync() {

    }


    public TimeSync(TimeSync other) {

        this();
        copy_from(other);
    }



    public static Object create() {
        TimeSync self;
        self = new TimeSync();
         
        self.clear();
        
        return self;
    }

    public void clear() {
        
        heartbeat_source = "";
            
        heartbeat_recipient = "";
            
        if (source_source_timestamp != null) {
            source_source_timestamp.clear();
        }
            
        if (recipient_receipt_timestamp != null) {
            recipient_receipt_timestamp.clear();
        }
            
    }

    public boolean equals(Object o) {
                
        if (o == null) {
            return false;
        }        
        
        

        if(getClass() != o.getClass()) {
            return false;
        }

        TimeSync otherObj = (TimeSync)o;



        if(!heartbeat_source.equals(otherObj.heartbeat_source)) {
            return false;
        }
            
        if(!heartbeat_recipient.equals(otherObj.heartbeat_recipient)) {
            return false;
        }
            
        if(!source_source_timestamp.equals(otherObj.source_source_timestamp)) {
            return false;
        }
            
        if(!recipient_receipt_timestamp.equals(otherObj.recipient_receipt_timestamp)) {
            return false;
        }
            
        return true;
    }

    public int hashCode() {
        int __result = 0;

        __result += heartbeat_source.hashCode();
                
        __result += heartbeat_recipient.hashCode();
                
        __result += source_source_timestamp.hashCode();
                
        __result += recipient_receipt_timestamp.hashCode();
                
        return __result;
    }
    

    /**
     * This is the implementation of the <code>Copyable</code> interface.
     * This method will perform a deep copy of <code>src</code>
     * This method could be placed into <code>TimeSyncTypeSupport</code>
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
        

        TimeSync typedSrc = (TimeSync) src;
        TimeSync typedDst = this;

        typedDst.heartbeat_source = typedSrc.heartbeat_source;
            
        typedDst.heartbeat_recipient = typedSrc.heartbeat_recipient;
            
        typedDst.source_source_timestamp = (ice.Time_t) typedDst.source_source_timestamp.copy_from(typedSrc.source_source_timestamp);
            
        typedDst.recipient_receipt_timestamp = (ice.Time_t) typedDst.recipient_receipt_timestamp.copy_from(typedSrc.recipient_receipt_timestamp);
            
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
        strBuffer.append("heartbeat_source: ").append(heartbeat_source).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("heartbeat_recipient: ").append(heartbeat_recipient).append("\n");
            
        strBuffer.append(source_source_timestamp.toString("source_source_timestamp ", indent+1));
            
        strBuffer.append(recipient_receipt_timestamp.toString("recipient_receipt_timestamp ", indent+1));
            
        return strBuffer.toString();
    }
    
}

